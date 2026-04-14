package com.example.restaurant.Repository;

import com.example.restaurant.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    // Métodos de consulta derivados (derived query) basados en el nombre del metodo

    List<Empleado> findByNombre(String nombre);

    List<Empleado> findByEdad(Integer edad);

    List<Empleado> findByRestauranteNombre(String restauranteAsociado);

    List<Empleado> findByApellidos(String apellidos);



}
