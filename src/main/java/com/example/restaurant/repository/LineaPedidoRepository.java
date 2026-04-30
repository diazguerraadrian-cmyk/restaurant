package com.example.restaurant.repository;

import com.example.restaurant.model.LineaPedido;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LineaPedidoRepository extends JpaRepository<LineaPedido, Long> {
    /*@Query("""
        SELECT SUM(lp.cantidad * p.popina)
        FROM LineaPedido lp where lp.pedido.id = ?1

        """)
     */
    @Query("""
    SELECT SUM(lp.cantidad * lp.plato.precio)
    FROM LineaPedido lp where lp.pedido.id = ?1
    """)
    Double calculatepreciototal(Long id);

    @Nullable Object findByOrder_Id(Long id);
}