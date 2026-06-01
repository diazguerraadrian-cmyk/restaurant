package com.example.restaurant.service;

import com.example.restaurant.model.Favorite;
import com.example.restaurant.model.Plato;
import com.example.restaurant.model.Restaurante;
import com.example.restaurant.model.Usuario;
import com.example.restaurant.repository.FavoriteRepository;
import com.example.restaurant.repository.PlatoRepository;
import com.example.restaurant.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final RestauranteRepository restauranteRepository;
    private final PlatoRepository platoRepository;

    public List<Favorite> findFavoriteRestaurantes(Long usuarioId){
        return favoriteRepository.findByUsuario_IdAndRestauranteIsNotNull(usuarioId);
    }

    public List<Favorite> findFavoritePlatos(Long usuarioId){
        return favoriteRepository.findByUsuario_IdAndPlatoIsNotNull(usuarioId);
    }

    public Set<Long> findRestaurantesIdsByUsuario_Id(Long usuarioId){
        return favoriteRepository.findRestauranteIdsByUsuario_Id(usuarioId);
    }

    public boolean toggleRestaurante(Usuario user, Long restauranteId){
        Optional<Favorite> existing = favoriteRepository.findByUsuario_IdAndRestauranteId(user.getId(), restauranteId);
        if (existing.isPresent()) {
            favoriteRepository.delete(existing.get());
            return false;
        }

        Restaurante restaurante = restauranteRepository.findById(restauranteId).orElseThrow();
        favoriteRepository.save(Favorite.builder().restaurante(restaurante).user(user).build());
        return true;
    }
    public boolean toggleDish(Usuario user, Long platoId) {
        Optional<Favorite> existing = favoriteRepository.findByUsuario_IdAndPlatoId(user.getId(), platoId);
        if (existing.isPresent()) {
            favoriteRepository.delete(existing.get());
            return false;
        }

        Plato plato = platoRepository.findById(platoId).orElseThrow();
        favoriteRepository.save(Favorite.builder().plato(plato).user(user).build());
        return true;
    }
}
