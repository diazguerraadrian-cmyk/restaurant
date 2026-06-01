package com.example.restaurant.controlador;

import com.example.restaurant.model.Usuario;
import com.example.restaurant.service.FavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/toggle")
    public String toggle(
            @RequestParam String type,
            @RequestParam Long targetId,
            @RequestParam(defaultValue = "/restaurantes") String redirectUrl,
            @AuthenticationPrincipal Usuario user,
            RedirectAttributes ra

    ){
        boolean favorited;
        if(type.equalsIgnoreCase("restsaurante")) {
           favorited = favoriteService.toggleRestaurante(user, targetId);
        } else if (type.equalsIgnoreCase("plato")) {
            favorited = favoriteService.togglePlato(user, targetId);
        }else {
            return "redirect:" + redirectUrl;
        }

        if (favorited){
            ra.addFlashAttribute("message", "añadido como favoritos");
        } else{
            ra.addFlashAttribute("message", "quitado como favoritos");
        }
        return "redirect:" + redirectUrl;
    }
}
