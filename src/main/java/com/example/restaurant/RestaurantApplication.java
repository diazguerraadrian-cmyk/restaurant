package com.example.restaurant;

import com.example.restaurant.Repository.EmpleadoRepository;
import com.example.restaurant.Repository.RestauranteRepository;
import com.example.restaurant.model.Empleado;
import com.example.restaurant.model.Restaurante;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RestaurantApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(RestaurantApplication.class, args);

        // obtener los repositorios para hacer operaciones de base de datos
        RestauranteRepository restauranteRepository = context.getBean(RestauranteRepository.class);
        EmpleadoRepository empleadoRepository = context.getBean(EmpleadoRepository.class);

        // crear un objeto restaurante: new
        Restaurante pacoBar = new Restaurante();
        pacoBar.setNombre("pacoBar");
        pacoBar.setPrecioMedio(20.0);
        pacoBar.setNumero_empleados(5);

        // guardar el restaurante en base de datos usando el repositorio: .save()
        restauranteRepository.save(pacoBar);

        // Opción 1: crear objeto vacío
        Restaurante rest = new Restaurante();

        // Opción 2: crear un objeto con datos
        Restaurante rest2 = new Restaurante("100 montaditos", 33.0, 7);
        restauranteRepository.save(rest2);

        // Opción 3: crearlo vacío y actualizarlo con métodos set
        Restaurante rest3 = new Restaurante();
        rest3.setNombre("Rest3");
        rest3.setPrecioMedio(50.22);
        restauranteRepository.save(rest3);


        Empleado emp1 = new Empleado();
        emp1.setNombre("Pablo");
        emp1.setApellidos("López");
        emp1.setEdad(45);
        empleadoRepository.save(emp1);

        // Obtener todos los restaurantes de la base de datos
        // SELECT * from restaurantes;
        List<Restaurante> restaurantes = restauranteRepository.findAll();

        // imprimir los restaurantes obtenidos con un bucle for
        System.out.println(restauranteRepository.findAll());
        System.out.println(restaurantes);
        //for (int i = 0; i < restaurantes.size(); i++) {
           // System.out.println(restaurantes.get(i));
       // }
        for (Restaurante restaurante : restaurantes) { // bucle for-each: itera uno a uno los restaurantes sin crear un index
            System.out.println(restaurante);
        }

        List<Empleado> empleados = empleadoRepository.findAll();
        System.out.println(empleadoRepository.findAll());

        for (Empleado empleado : empleados) {
            System.out.println(empleado);
        }
    }

}
