package com.example.restaurant.controlador;

import com.example.restaurant.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class ReviewControlador {

    //Inyectar el repositorio de reviews
    private final ReviewRepository reviewRepository;

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
}
