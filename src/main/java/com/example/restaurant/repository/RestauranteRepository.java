package com.example.restaurant.repository;

import com.example.restaurant.model.Restaurante;
import com.example.restaurant.model.TipoComida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    List<Restaurante> findByActivoTrue();
    Optional<Restaurante> findByIdAndActivoTrue(Long id);
    @Query("""
    SELECT r from Restaurante r
    WHERE r.activo = true
    AND (:tipoComida IS NULL OR r.tipoComida = :tipoComida)
    AND (:precio IS NULL OR r.precioMedio <= :precio)
    AND (:titulo IS NULL OR LOWER(r.nombre) LIKE LOWER(CONCAT('%', :titulo, '%')))
    """)
    List<Restaurante> findActiveFiltering(@Param("tipoComida")TipoComida tipoComida, @Param("precio") Double precio, @Param("titulo") String titulo);
}