package com.letterbox.service;

import com.letterbox.entity.User;
import com.letterbox.dto.UserDTO;
import com.letterbox.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final UserRepository users;

    public UserService(UserRepository users) { this.users = users; }

    // ==================== VALIDACIÓN CON PATTERN/MATCHER ====================

    /**
     * Valida formato de email usando expresiones regulares
     * Patrón: usuario@dominio.extension
     */
    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        // Patrón regex para email
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public User register(UserDTO dto) {
        // Validar username duplicado
        users.findByUsernameIgnoreCase(dto.getUsername()).ifPresent(u -> {
            throw new IllegalArgumentException("username ya existe");
        });

        // NUEVO: Validar formato de email con regex
        if (dto.getEmail() != null && !dto.getEmail().trim().isEmpty()) {
            if (!isValidEmail(dto.getEmail())) {
                throw new IllegalArgumentException("formato de email inválido");
            }
        }

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
