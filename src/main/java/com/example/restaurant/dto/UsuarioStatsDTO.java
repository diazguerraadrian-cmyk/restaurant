package com.example.restaurant.dto;

import com.example.restaurant.model.Pedido;
import com.example.restaurant.model.Review;

import java.util.List;


public record UsuarioStatsDTO(
    long countReviews,
    List<Review> reviews,
    long countOrders,
    List<Pedido> pedidos,
    double totalMoneySpent
){
}