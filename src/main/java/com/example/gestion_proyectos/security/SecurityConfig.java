package com.example.gestion_proyectos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Permitir todo
            .csrf(csrf -> csrf.disable()); // Desactivar CSRF para permitir POST/PUT sin problemas

        return http.build();
    }
}

// Hola Javier, este era mi SecurityConfig (o una de las ultimas versiones al menos),
// pero me bloqueaba todas las peticiones y no ha habido manera de solucionarlo,
// me encantaría dedicarle mas tiempo a solucionarlo pero sencillamente no dispongo de ello
// asi que lo configure para que no bloqueara el resto de paths y poder desarrollarlos de forma funcional

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable()) // desactivado para que pueda hacer POST/PUT sin problemas
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/", "/login", "/register").permitAll() // Rutas públicas
//                 .requestMatchers("/admin/**").hasRole("ADMIN") // Solo para administradores
//                 .requestMatchers("/proyectos/**", "/tareas/**").authenticated() // Solo usuarios autenticados
//                 .anyRequest().authenticated()
//             )
//             .formLogin(login -> login
//                 .loginPage("/login")  // Página de login personalizada
//                 .defaultSuccessUrl("/proyectos", true) // Redirigir tras login
//                 .permitAll()
//             )
//             .logout(logout -> logout
//                 .logoutUrl("/logout")
//                 .logoutSuccessUrl("/login?logout")
//                 .permitAll()
//             );

//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }
//
// tambien puse por aqui otro bean con UserDetailsService que tambien quite al reconfigurar el SecurityFilterChain
