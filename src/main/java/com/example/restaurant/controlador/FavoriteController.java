package com.example.restaurant.controlador;

import com.example.restaurant.service.FavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;
}
