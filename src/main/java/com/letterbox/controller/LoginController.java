package com.letterbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // Muestra el login
    @GetMapping("/login")
    public String showLoginPage() {
        return "redirect:/login.html";
    }

    // Después del login exitoso → te redirige al LetterBox
    @GetMapping("/home")
    public String showHomePage() {
        return "redirect:/index.html";
    }
}


