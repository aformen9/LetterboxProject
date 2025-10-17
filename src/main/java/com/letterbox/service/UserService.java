package com.letterbox.service;

import com.letterbox.dto.UserDTO;
import com.letterbox.entity.User;
import com.letterbox.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(UserDTO dto) {
        users.findByUsernameIgnoreCase(dto.getUsername()).ifPresent(u -> {
            throw new IllegalArgumentException("El usuario ya existe");
        });

        User u = new User();
        u.setUsername(dto.getUsername());
        u.setDisplayName(dto.getDisplayName());
        u.setEmail(dto.getEmail());
        u.setPassword(passwordEncoder.encode(dto.getPassword())); // 🔐 Encripta la clave

        return users.save(u);
    }

    public List<User> list() {
        return users.findAll();
    }

    public User get(Long id) {
        return users.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }
}
