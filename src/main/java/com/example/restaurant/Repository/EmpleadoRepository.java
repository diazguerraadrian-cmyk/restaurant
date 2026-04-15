package com.example.restaurant.Repository;

import com.example.restaurant.model.Empleado;
import com.example.restaurant.model.TipoComida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    // Métodos de consulta derivados (derived query) basados en el nombre del metodo

    List<Empleado> findByNombre(String nombre);

    List<Empleado> findByEdad(Integer edad);

    List<Empleado> findByRestauranteNombre(String restauranteAsociado);

    List<Empleado> findByApellidos(String apellidos);

    List<Empleado> findByRestaurante_TipoComida(TipoComida tipoComida);

    List<Empleado> findByRestaurante_PrecioMedio(double Precio);


    List<Empleado> findByEdadGreaterThanEqual(Integer edadIsGreaterThan);

    @Query("select e from Empleado e order by e.nombre")
    List<Empleado> findByOrderByNombreAsc();
    // OTRAS FORMAS DE ORDENAR TIPICAS SERÍAN ORDENAR POR PRECIO ASC EN PRODUCTOS


}
