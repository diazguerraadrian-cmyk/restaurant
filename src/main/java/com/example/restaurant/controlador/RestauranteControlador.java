package com.example.restaurant.controlador;

import com.example.restaurant.model.Restaurante;
import com.example.restaurant.repository.RestauranteRepository;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RestauranteControlador {

    private final RestauranteRepository restauranteRepository;

    public RestauranteControlador(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @GetMapping("restaurantes")
    public String ListaRestaurantes(Model model) {

        List<Restaurante> restaurantes = restauranteRepository.findAll();
        model.addAttribute("restaurantes", restauranteRepository.findAll());
        model.addAttribute("numRestaurantes", 5);
        model.addAttribute("titulo", "Lista de restaurantes");
        return "restaurantes/Lista-restaurantes";
    }

}
