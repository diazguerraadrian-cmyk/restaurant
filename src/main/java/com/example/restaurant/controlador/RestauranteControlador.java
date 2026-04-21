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
    /*
    Resumen de métodos típicos en una clase controller:
    @GetMapping("restaurantes") findAll
    @GetMapping("restaurantes/{id}") findById

    @GetMapping("restaurantes/create") createForm
    @PostMapping("restaurantes") create

    @GetMapping("restaurantes/{id}/edit") editForm
    @PostMapping("restaurantes/{id}/edit") edit

    @GetMapping("restaurantes/delete/{id}") delete

     */

    @GetMapping("restaurantes")
    public String ListaRestaurantes(Model model) {

        List<Restaurante> restaurantes = restauranteRepository.findAll();
        model.addAttribute("restaurantes", restauranteRepository.findAll());
        model.addAttribute("numRestaurantes", 5);
        model.addAttribute("titulo", "Lista de restaurantes");
        return "restaurantes/Lista-restaurantes";
    }

}
