package com.example.restaurant.controlador;

import com.example.restaurant.model.Plato;
import com.example.restaurant.model.Review;
import com.example.restaurant.model.Usuario;
import com.example.restaurant.repository.PlatoRepository;
import com.example.restaurant.repository.RestauranteRepository;
import com.example.restaurant.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class ReviewControlador {

    //Inyectar el repositorio de reviews
    private final ReviewRepository reviewRepository;
    private final RestauranteRepository restauranteRepository;
    private final PlatoRepository platoRepository;

    // getmapping reviews
    @GetMapping("reviews")
    public String reviews(Model model) {
        model.addAttribute("reviews", reviewRepository.findAll());
        return "reviews/Lista-reviews";
    }


    @GetMapping("reviews/{id}")
    public String review(Model model, @PathVariable Long id){
        model.addAttribute("review", reviewRepository.findById(id).orElseThrow());
        return "reviews/detalles-review";
    }

    @GetMapping("reviews/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        reviewRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensaje", "Review eliminada correctamente");
        return "redirect:/reviews";
    }
    @GetMapping("reviews/new")
    public String nuevaReview(Model model, @RequestParam(required = false) Long restauranteId, @RequestParam(required = false) Long platoId) {
        Review review = new Review();
        if (restauranteId != null)
            review.setRestaurante(restauranteRepository.findById(restauranteId).orElseThrow());
        if (platoId != null)
            review.setPlato(platoRepository.findById(platoId).orElseThrow());
        model.addAttribute("review", review);
        return "reviews/form-review";
    }
    @GetMapping("reviews/edit/{id}")
    public String editarReview(Model model, @PathVariable Long id) {
        model.addAttribute("review", reviewRepository.findById(id).orElseThrow());
        return "reviews/form-review";
    }
    @PostMapping("reviews")
    public String guardarReview(@ModelAttribute Review review, @AuthenticationPrincipal Usuario user) {
        review.setUser(user);
        reviewRepository.save(review);
        if (review.getRestaurante() != null)
            return "redirect:/Restaurantes/" + review.getRestaurante().getId();
        if (review.getPlato() != null)
            return "redirect:/platos/" + review.getPlato().getId();
        return "redirect:/reviews";
    }
}
