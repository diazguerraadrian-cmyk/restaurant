package com.example.restaurant.service;
import com.example.restaurant.dto.RegisterForm;
import com.example.restaurant.dto.UsuarioStatsDTO;
import com.example.restaurant.model.Role;
import com.example.restaurant.model.Usuario;
import com.example.restaurant.repository.PedidoRepository;
import com.example.restaurant.repository.ReviewRepository;
import com.example.restaurant.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioServicio implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReviewRepository reviewRepository;
    private final PedidoRepository pedidoRepository;
    private final FavoriteService favoriteService;

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
        usuario.setActive(true);
        return usuarioRepository.save(usuario);

    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }
    public UsuarioStatsDTO findStatsById(Long id){
        return new UsuarioStatsDTO(
        reviewRepository.countByUser_Id(id),
        reviewRepository.findByUser_Id(id),
        pedidoRepository.countByUser_Id(id),
        pedidoRepository.findByUser_IdOrderByDateDesc(id),
        pedidoRepository.calculateTotalMoneySpentByUserId(id),
        favoriteService.findFavoriteRestaurantes(id),
        favoriteService.findFavoritePlatos(id)
        );
    }

    public Usuario create(Usuario user) {
        if (usuarioRepository.existsByUsername(user.getUsername()))
            throw new IllegalArgumentException("El nombre de usuario ya existe");

        if (usuarioRepository.existsByEmail(user.getEmail()))
            throw new IllegalArgumentException("El correo electrónico ya existe");

//        if (user.getPassword() == null || user.getPassword().isBlank())
//            throw new IllegalArgumentException("La contraseña es obligatoria");

        if (!StringUtils.hasText(user.getPassword()))
            throw new IllegalArgumentException("Password no puede estar vacía");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usuarioRepository.save(user);
    }
    public Usuario update(Usuario userForm){
        Usuario userDB = findById(userForm.getId());
        Optional<Usuario> userOPT = usuarioRepository.findByUsername(userForm.getUsername());
        if (userOPT.isPresent() && !userOPT.get().getId().equals(userForm.getId()))
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        // lo mismo para el email pero en estilo programación funcional
        usuarioRepository.findByEmail(userForm.getEmail())
                .filter(user -> !user.getId().equals(userForm.getId()))
                .ifPresent(user -> {
                    throw new IllegalArgumentException("El email de usuario ya existe");
                });
        userDB.setUsername(userForm.getUsername());
        userDB.setEmail(userForm.getEmail());
        userDB.setRole(userForm.getRole());

        if (!StringUtils.hasText(userForm.getPassword()))
            userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
        return usuarioRepository.save(userDB);
    }
}
