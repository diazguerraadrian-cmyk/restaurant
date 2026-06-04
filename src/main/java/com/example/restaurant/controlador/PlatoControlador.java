package com.example.restaurant.controlador;

import com.example.restaurant.model.Plato;
import com.example.restaurant.model.Review;
import com.example.restaurant.model.TipoComida;
import com.example.restaurant.model.TipoPlato;
import com.example.restaurant.repository.PlatoRepository;
import com.example.restaurant.repository.RestauranteRepository;
import com.example.restaurant.repository.ReviewRepository;
import com.example.restaurant.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class PlatoControlador {

    private final PlatoRepository platoRepository;
    private final ReviewRepository reviewRepository;
    private final RestauranteRepository restauranteRepository;
    private final FileService fileService;


    @GetMapping("platos")
    public String listaPlatos(Model model) {
        List<Plato> platos = platoRepository.findAll();
        model.addAttribute("platos", platos);
        return "Platos/lista-platos";
    }


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
    @GetMapping("platos/new")
    public String nuevoPlato(Model model) {
        model.addAttribute("plato", new Plato());
        model.addAttribute("tipoPlatos", TipoPlato.values());
        model.addAttribute("restaurantes", restauranteRepository.findAll());
        return "platos/form-platos";
    }

    @GetMapping("platos/edit/{id}")
    public String editarPlato(@PathVariable Long id, Model model) {
        model.addAttribute("plato", platoRepository.findById(id).orElseThrow());
        model.addAttribute("tipoPlatos", TipoPlato.values());
        model.addAttribute("restaurantes", restauranteRepository.findAll());
        return "platos/form-platos";
    }


    @PostMapping("platos")
    public String guardarPlato(@ModelAttribute Plato plato,
                               @RequestParam("imageFile") MultipartFile imageFile) {

        String imageUrl = fileService.store(imageFile);
        if (imageUrl != null)
            plato.setImageUrl(imageUrl);
        platoRepository.save(plato);
        return "redirect:/platos";
    }
}
