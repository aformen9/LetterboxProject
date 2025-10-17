package com.letterbox.controller;

import com.letterbox.dto.UserDTO;
import com.letterbox.entity.User;
import com.letterbox.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseBody
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password
    ) {
        UserDTO dto = new UserDTO();
        dto.setUsername(username);
        dto.setPassword(password);
        dto.setDisplayName(username);
        dto.setEmail(username + "@example.com"); // o lo que uses

        userService.register(dto);
        return "Usuario registrado correctamente";
    }
}
