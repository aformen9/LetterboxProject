package com.letterbox.controller;

import com.letterbox.entity.User;
import com.letterbox.dto.UserDTO;
import com.letterbox.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/users","/users"})
public class UserController {

    private final UserService users;

    public UserController(UserService users) { this.users = users; }

    @PostMapping
    public User register(@Valid @RequestBody UserDTO dto) {
        return users.register(dto);
    }

    @GetMapping
    public List<User> list() { return users.list(); }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) { return users.get(id); }
}
