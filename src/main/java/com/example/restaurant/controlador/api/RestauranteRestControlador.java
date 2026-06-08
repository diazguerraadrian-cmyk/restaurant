package com.example.restaurant.controlador.api;

import com.example.restaurant.model.Restaurante;
import com.example.restaurant.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurantes")
@AllArgsConstructor
public class RestauranteRestControlador {
    private RestauranteRepository restauranteRepository;

    @GetMapping
    public List<Restaurante> findAll(){
        return restauranteRepository.findAll();
    }


}
