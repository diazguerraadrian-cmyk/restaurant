package com.example.restaurant.config;

import com.example.restaurant.dto.RegisterForm;
import com.example.restaurant.model.*;
import com.example.restaurant.repository.FavoriteRepository;
import com.example.restaurant.repository.PlatoRepository;
import com.example.restaurant.repository.RestauranteRepository;
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

    private final RestauranteRepository restauranteRepository;

    private final PlatoRepository platoRepository;

    private final FavoriteRepository favoriteRepository;

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
                .active(true)
                .build());

        Restaurante restaurante5 = new Restaurante();
        restaurante5.setNombre("Restaurante para fav");
        restaurante5.setPrecioMedio(20d);
        restaurante5.setNumero_empleados(2);
        restaurante5.setTipoComida(TipoComida.SPANISH);
        restauranteRepository.save(restaurante5);

        Plato plato5 = new Plato();
        plato5.setNombre("Plato para fav");
        plato5.setPrecio(10.5d);
        plato5.setDescripcion("Plato para darle a favoritos");
        platoRepository.save(plato5);

        favoriteRepository.save(Favorite.builder().plato(plato5).user(user).build());
        favoriteRepository.save(Favorite.builder().restaurante(restaurante5).user(user).build());
    }

}
