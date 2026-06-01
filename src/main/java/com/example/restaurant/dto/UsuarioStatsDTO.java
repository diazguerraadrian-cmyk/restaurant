package com.example.restaurant.dto;

import com.example.restaurant.model.Favorite;
import com.example.restaurant.model.Pedido;
import com.example.restaurant.model.Review;

import java.util.List;


public record UsuarioStatsDTO(
    long countReviews,
    List<Review> reviews,
    long countPedidos,
    List<Pedido> pedidos,
    double totalMoneySpent,
    List<Favorite> favoriteRestaurantes,
    List<Favorite> favoritePlatos
){
}