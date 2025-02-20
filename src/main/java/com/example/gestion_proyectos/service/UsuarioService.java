package com.example.gestion_proyectos.service;

import com.example.gestion_proyectos.model.Usuario;
import com.example.gestion_proyectos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder; no hace falta con security desactivado
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    public void registrarUsuario(String username, String password, String role) {
        String encodedPassword = password; // Guardar en texto plano (solo para pruebas)

        System.out.println("Registrando usuario...");
        System.out.println("Username: " + username);
        System.out.println("Contraseña encriptada: " + encodedPassword);
        System.out.println("Role: " + role);

        Usuario usuario = new Usuario(username, encodedPassword, role);
        usuarioRepository.save(usuario);

        System.out.println("Usuario guardado con éxito en la base de datos.");
    }

    public Optional<Usuario> encontrarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
