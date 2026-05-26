package com.example.restaurant.service;
import com.example.restaurant.dto.RegisterForm;
import com.example.restaurant.model.Role;
import com.example.restaurant.model.Usuario;
import com.example.restaurant.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioServicio implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        if (usuario.isPresent()) {
            throw new RuntimeException("Usuario no encontrado");
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado con username: " + username);
        }
    }

    public Usuario register(RegisterForm form) {
        if (usuarioRepository.existsByUsername(form.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        if (usuarioRepository.existsByEmail(form.getEmail())) {
            throw new RuntimeException("El correo electrónico ya existe");
        }
        if (!form.getPassword().equals(form.getPasswordConfirm())) {
            throw new RuntimeException("Las contraseñas no coinciden");
        }
      //  if (! form.getAccepTermsandConditions())

        Usuario usuario = new Usuario();
        usuario.setUsername(form.getUsername());
        usuario.setEmail(form.getEmail());
        usuario.setPassword(passwordEncoder.encode(form.getPassword()));
        usuario.setRole(Role.ROLE_USER);
        return usuarioRepository.save(usuario);

    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }
}
