package com.example.restaurant.controlador;

import com.example.restaurant.model.Plato;
import com.example.restaurant.model.Restaurante;
import com.example.restaurant.repository.PlatoRepository;
import com.example.restaurant.repository.RestauranteRepository;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class RestauranteControlador {

    private final RestauranteRepository restauranteRepository;
    private final PlatoRepository platoRepository;

    public RestauranteControlador(RestauranteRepository restauranteRepository, PlatoRepository platoRepository) {
        this.restauranteRepository = restauranteRepository;
        this.platoRepository = platoRepository;
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

    @GetMapping("Restaurantes/{id}")
    public String detallesRestaurante(@PathVariable Long id, Model model){

        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);
        if (restauranteOptional.isPresent()){

            Restaurante restaurante = restauranteOptional.get();
            model.addAttribute("restaurante", restaurante);
            List<Plato> platos = platoRepository.findByRestauranteIdOrderByPrecio(restaurante.getId());
            model.addAttribute("platos", platos);
            return "restaurantes/Detalles-restaurantes";

        } else{
            return "redirect:/restaurantes";
        }
    }

}
