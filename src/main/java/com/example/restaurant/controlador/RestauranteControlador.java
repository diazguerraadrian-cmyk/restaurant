package com.example.restaurant.controlador;

import com.example.restaurant.repository.RestauranteRepository;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestauranteControlador {

    private final RestauranteRepository restauranteRepository;

    public RestauranteControlador(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @GetMapping("restaurantes")
    public String ListaRestaurantes(Model model) {
        model.addAttribute("restaurantes", restauranteRepository.findAll());
        return "restaurantes/Lista-restaurantes";
    }

}
