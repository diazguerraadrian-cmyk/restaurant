package com.example.restaurant.controlador;

import com.example.restaurant.model.Plato;
import com.example.restaurant.repository.PlatoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
public class PlatoControlador {

    private final PlatoRepository platoRepository;

    public PlatoControlador(PlatoRepository platoRepository) {
        this.platoRepository = platoRepository;
    }

    @GetMapping("plato/{id}")
    public String platoDetalles(@PathVariable Long id, Model model) {

        Optional<Plato> platoOptional = platoRepository.findById(id);

        if (platoOptional.isPresent()){
            Plato plato = platoOptional.get();
            model.addAttribute("plato", plato);
            return "Platos/Detalles-plato";
        }

        return "redirect:/restaurantes";
    }
}
