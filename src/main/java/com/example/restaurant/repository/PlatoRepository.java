package com.example.restaurant.repository;

import com.example.restaurant.model.Plato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatoRepository extends JpaRepository<Plato, Long> {
    List<Plato> findByPrecioLessThanEqual(Double precio);

    // Consulta para traer todos los platos de un restaurante por id,
    // ordenados por precio ascendente
    List<Plato> findByRestauranteIdOrderByPrecio(Long id);

}