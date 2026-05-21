package com.example.restaurant.config;

import com.example.restaurant.dto.RegisterForm;
import com.example.restaurant.model.Role;
import com.example.restaurant.model.Usuario;
import com.example.restaurant.repository.UsuarioRepository;
import com.example.restaurant.service.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Datainitializer implements ApplicationRunner {

    private UsuarioRepository usuarioRepository;

    private UsuarioServicio usuarioServicio;

    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Usuario user = usuarioServicio.register(RegisterForm.builder()
                .username("user")
                .email("user@gmail.com")
                .password("user")
                .passwordConfirm("user")
                .build());

        Usuario admin = usuarioRepository.save(Usuario.builder()
                .username("admin")
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("admin"))
                .role(Role.ROLE_ADMIN)
                .build());
    }

}
