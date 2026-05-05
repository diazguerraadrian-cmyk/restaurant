package com.example.restaurant.controlador;

import com.example.restaurant.model.Plato;
import com.example.restaurant.model.Restaurante;
import com.example.restaurant.model.Review;
import com.example.restaurant.model.TipoComida;
import com.example.restaurant.repository.PlatoRepository;
import com.example.restaurant.repository.RestauranteRepository;
import com.example.restaurant.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class RestauranteControlador {

    private final RestauranteRepository restauranteRepository;
    private final PlatoRepository platoRepository;
    private final ReviewRepository reviewRepository;


    @GetMapping("restaurantes")
    public String ListaRestaurantes(
            Model model,
            @RequestParam(required = false) TipoComida tipoComida,
            @RequestParam(required = false) Double precio
            ){

        // List<Restaurante> restaurantes = restauranteRepository.findAll();
        List<Restaurante> restaurantes = restauranteRepository.findActiveFiltering(tipoComida, precio);
        model.addAttribute("restaurantes", restauranteRepository.findAll());
        model.addAttribute("numRestaurantes", 5);
        model.addAttribute("titulo", "Lista de restaurantes");
        model.addAttribute("precioMedio","precioMedio");
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

            // reviews
            List<Review> reviews = reviewRepository.findByRestaurante_IdOrderByFechacreadaDesc(restaurante.getId());
            model.addAttribute("reviews", reviews);

            return "restaurantes/Detalles-restaurantes";

        } else{
            return "redirect:/restaurantes";
        }
    }

    @GetMapping("Restaurantes/desactivar/{id}")
    public String restauranteDesactivar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        Optional<Restaurante> restauranteOptional = restauranteRepository.findByIdAndActiveTrue(id);
        if (restauranteOptional.isPresent()){
            Restaurante restaurante = restauranteOptional.get();
            restaurante.setActivo(false);
            restauranteRepository.save(restaurante);
            redirectAttributes.addFlashAttribute("mensaje", "Restaurante desactivado correctamente");
            return "redirect:/restaurantes";
        } else {
            return "redirect:/restaurantes";
        }
    }
}
