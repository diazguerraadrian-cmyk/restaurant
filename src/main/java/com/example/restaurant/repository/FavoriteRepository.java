package com.example.restaurant.repository;

import com.example.restaurant.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUsuario_IdAndRestauranteIsNotNull(Long usuarioId);
    List<Favorite> findByUsuario_IdAndPlatoIsNotNull(Long usuarioId);
}