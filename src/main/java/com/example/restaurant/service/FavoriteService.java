package com.example.restaurant.service;

import com.example.restaurant.model.Favorite;
import com.example.restaurant.repository.FavoriteRepository;
import com.example.restaurant.repository.PlatoRepository;
import com.example.restaurant.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
