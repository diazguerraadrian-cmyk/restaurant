package com.example.restaurant.controlador.api;

import com.example.restaurant.model.Plato;
import com.example.restaurant.model.Restaurante;
import com.example.restaurant.repository.PlatoRepository;
import com.example.restaurant.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class PlatoRestControlador {

    private final PlatoRepository platoRepository;
    private final RestauranteRepository restauranteRepository;

    @GetMapping("platos")
    public List<Plato> traeAllPlatos(){
        return platoRepository.findAll();
    }

    @GetMapping("platos/{id}")
    public Plato findById(@PathVariable Long id){
         return platoRepository.findById(id).orElseThrow(() ->
                         new ResponseStatusException(HttpStatus.NOT_FOUND,  String.format("Dish with id %d not found", id)
         )
         );
    }

    @GetMapping("restaurantes/{id}/platos")
    public List<Plato> findAllByRestaurant(@PathVariable Long id) {
        return platoRepository.findByRestauranteIdOrderByPrecio(id);
    }

    @PostMapping("platos")
    public ResponseEntity<Plato> crear(@RequestBody Plato plato){
        if (plato.getId() != null || plato.getId() == null || plato.getRestaurante().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Plato ID must be null");
        }

        plato.setRestaurante(resolveRestaurante(plato));
        Plato saved = platoRepository.save(plato);
        return ResponseEntity.created(URI.create("/api/v1/dishes/" + saved.getId())).body(saved);
    }
    private Restaurante resolveRestaurante(Plato plato) {
        if (plato.getRestaurante() == null || plato.getRestaurante().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant ID can not be null");
        }
        return restauranteRepository.findById(plato.getRestaurante().getId()).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,  String.format("Restaurant with id %d not found",
                        plato.getRestaurante().getId())
                )
        );
    }

    @PutMapping("dishes/{id}")
    public ResponseEntity<Plato> update(
            @PathVariable Long id,
            @RequestBody Plato plato
    ) {
        Plato existing = platoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Dish " + id + " not found")
        );
        existing.setNombre(plato.getNombre());
        existing.setPrecio(plato.getPrecio());
        existing.setImageUrl(plato.getImageUrl());
        existing.setDescripcion(plato.getDescripcion());
        existing.setTipoPlato(plato.getTipoPlato());

        existing.setRestaurante(resolveRestaurante(plato)); // asociación

        return ResponseEntity.ok(platoRepository.save(existing));
    }

    @PutMapping("platos/{id}")
    public ResponseEntity<Plato> updatePartial(@PathVariable Long id, @RequestBody Plato plato) {
        if (plato.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Plato ID must not be null");
        }
        Plato existing = platoRepository.findById(plato.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plato" + plato.getId() + " not found")
        );
        if (plato.getNombre() != null) existing.setNombre(plato.getNombre());
        if (plato.getPrecio() != null) existing.setPrecio(plato.getPrecio());
        if (plato.getImageUrl() != null) existing.setImageUrl(plato.getImageUrl());
        if (plato.getDescripcion() != null) existing.setDescripcion(plato.getDescripcion());
        if (plato.getTipoPlato() != null) existing.setTipoPlato(plato.getTipoPlato());
        if (plato.getRestaurante() != null && plato.getRestaurante().getId() != null) existing.setRestaurante(resolveRestaurante(plato));  // asociación

        return ResponseEntity.ok(platoRepository.save(existing));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void delete(@PathVariable Long id) {
        if(!platoRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dish " + id + " not found");

        platoRepository.deleteById(id);
    }
}
