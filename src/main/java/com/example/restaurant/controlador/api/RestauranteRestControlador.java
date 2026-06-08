package com.example.restaurant.controlador.api;

import com.example.restaurant.model.Restaurante;
import com.example.restaurant.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

// http://localhost:8080/swagger-ui/index.html
@RestController
@RequestMapping("/api/v1/restaurantes")
@AllArgsConstructor
public class RestauranteRestControlador {
    private RestauranteRepository restauranteRepository;

    @GetMapping
    public List<Restaurante> findAll(){
        return restauranteRepository.findAll();
    }

    @GetMapping("{id}")
    public Restaurante findOne(@PathVariable Long id){
        return restauranteRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant" + id + " not found")
        );
    }

    @PostMapping
    public ResponseEntity<Restaurante> save(@RequestBody Restaurante restaurante){
        if (restaurante.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant ID must be null");
        }
        Restaurante saved = restauranteRepository.save(restaurante);
        //        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        return ResponseEntity.created(URI.create("/api/v1/restaurants/" + saved.getId())).body(saved);
    }

    @PutMapping
    public ResponseEntity<Restaurante> update(@RequestBody Restaurante restaurante) {
        if (restaurante.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant ID must not be null");
        }
        Restaurante existing = restauranteRepository.findById(restaurante.getId()).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant" + restaurante.getId() + " not found")
        );
        existing.setNombre(restaurante.getNombre());
        existing.setPrecioMedio(restaurante.getPrecioMedio());
        existing.setNumero_empleados(restaurante.getNumero_empleados());
        existing.setTipoComida(restaurante.getTipoComida());
        existing.setImageUrl(restaurante.getImageUrl());
        existing.setActivo(restaurante.getActivo());
// existing.setStartDate(restaurant.getStartDate()); // conservar fecha original

        return ResponseEntity.ok(restauranteRepository.save(existing));
    }

    @PatchMapping("{id}")
    public ResponseEntity<Restaurante> updatePartial(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        Restaurante existing = restauranteRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant " + id + " not found")
        );
        if(restaurante.getNombre() != null) existing.setNombre(restaurante.getNombre());
        if(restaurante.getPrecioMedio() != null) existing.setPrecioMedio(restaurante.getPrecioMedio());
        if(restaurante.getNumero_empleados() != null) existing.setNumero_empleados(restaurante.getNumero_empleados());
        if(restaurante.getTipoComida() != null) existing.setTipoComida(restaurante.getTipoComida());
        if(restaurante.getImageUrl() != null) existing.setImageUrl(restaurante.getImageUrl());

        return ResponseEntity.ok(restauranteRepository.save(existing));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!restauranteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant " + id + " not found");
        }
        try {
            restauranteRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Restaurant can't be deleted because it has relationships");
        }

    }
}
