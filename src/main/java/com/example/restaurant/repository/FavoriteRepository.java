package com.example.restaurant.repository;

import com.example.restaurant.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUsuario_IdAndRestauranteIsNotNull(Long usuarioId);
    List<Favorite> findByUsuario_IdAndPlatoIsNotNull(Long usuarioId);

    Optional<Favorite> findByUsuario_IdAndRestauranteId(Long userId, Long restauranteId);
    Optional<Favorite> findByUsuario_IdAndPlatoId(Long userId, Long platoId);

    @Query("""
    select f.restaurante.id from Favorite f 
    where f.usuario.id = :usuarioId 
    and f.restaurante IS NOT NULL
""")
    Set<Long> findRestauranteIdsByUsuario_Id(@Param("userId") Long usuarioId);
}