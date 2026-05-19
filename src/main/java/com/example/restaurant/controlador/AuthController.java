package com.example.restaurant.controlador;

import com.example.restaurant.dto.RegisterForm;
import com.example.restaurant.service.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AuthController {
    private final UsuarioServicio usuarioServicio;


    @GetMapping("register")
    public String register(Model model){
        model.addAttribute("user", new RegisterForm());
        return "auth/register";
    }

    @PostMapping("register")
    public String register(Model model, RegisterForm form){
        System.out.println(form);
        return "redirect:/login";
    }

    @GetMapping("login")
    public String login(){
        return "auth/login";
    }
}
