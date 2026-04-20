package com.example.restaurant.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HolaControlador {

    @GetMapping("Restaurante")
    public String Restaurante(){
        return "Restaurante";
    }

    @GetMapping("Pato")
    public String pato(Model model){
        model.addAttribute("mensaje", "Hasta luego reyes");
        return "Pato";
    }
}
