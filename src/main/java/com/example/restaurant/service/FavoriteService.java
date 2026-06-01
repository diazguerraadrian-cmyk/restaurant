package com.example.restaurant.service;

import com.example.restaurant.repository.FavoriteRepository;
import com.example.restaurant.repository.PlatoRepository;
import com.example.restaurant.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final RestauranteRepository restauranteRepository;
    private final PlatoRepository platoRepository;
}
