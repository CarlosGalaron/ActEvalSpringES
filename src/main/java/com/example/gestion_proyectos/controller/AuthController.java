package com.example.gestion_proyectos.controller;

import com.example.gestion_proyectos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        System.out.println(">>> Se recibió una solicitud de registro");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        usuarioService.registrarUsuario(username, password, "USER");
        System.out.println("Usuario registrado con éxito.");
        return "redirect:/login";
    }
}
