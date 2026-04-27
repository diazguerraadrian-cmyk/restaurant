package com.example.restaurant.controlador;

import com.example.restaurant.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
