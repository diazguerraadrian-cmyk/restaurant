package com.example.restaurant.repository;

import com.example.restaurant.model.Pedido;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUser_IdOrderByDateDesc(Long id);

    long countByUserId(Long id);

    @Query("""
    SELECT SUM(p.totalPrice) from Pedido p where p.user.id = :userId
    """)
    double calculateTotalMoneySpentByUserId(Long Id);

    // findByRestauranteId
}