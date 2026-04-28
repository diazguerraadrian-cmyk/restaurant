package com.example.restaurant.controlador;

import com.example.restaurant.model.Plato;
import com.example.restaurant.model.Review;
import com.example.restaurant.repository.PlatoRepository;
import com.example.restaurant.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class PlatoControlador {

    private final PlatoRepository platoRepository;
    private final ReviewRepository reviewRepository;


    @GetMapping("plato/{id}")
    public String platoDetalles(@PathVariable Long id, Model model) {

        Optional<Plato> platoOptional = platoRepository.findById(id);

        if (platoOptional.isPresent()){
            Plato plato = platoOptional.get();
            model.addAttribute("plato", plato);
            List<Review> reviews = reviewRepository.findByPlato_IdOrderByFechacreadaDesc(plato.getId());
            model.addAttribute("reviews", reviews);
            return "Platos/Detalles-plato";
        }

        return "redirect:/restaurantes";
    }
}
