package com.letterbox.service;

import com.letterbox.entity.User;
import com.letterbox.dto.UserDTO;
import com.letterbox.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository users;

    public UserService(UserRepository users) { this.users = users; }

    public User register(UserDTO dto) {
        users.findByUsernameIgnoreCase(dto.getUsername()).ifPresent(u -> {
            throw new IllegalArgumentException("username ya existe");
        });
        User u = new User();
        u.setUsername(dto.getUsername());
        u.setDisplayName(dto.getDisplayName());
        u.setEmail(dto.getEmail());
        return users.save(u);
    }

    public List<User> list() { return users.findAll(); }

    public User get(Long id) {
        return users.findById(id).orElseThrow(() -> new IllegalArgumentException("user no encontrado"));
    }
}
