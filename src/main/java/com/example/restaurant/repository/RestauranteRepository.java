package com.example.restaurant.repository;

import com.example.restaurant.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    List<Restaurante> findByActiveTrue();
    Optional<Restaurante> findByIdAndActiveTrue(Long id);
}