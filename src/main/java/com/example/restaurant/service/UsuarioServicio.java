package com.example.restaurant.service;
import com.example.restaurant.dto.RegisterForm;
import com.example.restaurant.model.Usuario;
import com.example.restaurant.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
        } else{
            throw new UsernameNotFoundException("Usuario no encontrado con username: " + username);
        }
    }
    public void register(RegisterForm form) {
        // TODO
    }
}
