package com.example.restaurant.model;
import jakarta.persistence.*;

/*...*/
@Entity
@Table(name = "Restaurantes")
public class Restaurante {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique = true)
    private String nombre;

    private Double precioMedio;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean activo = true;

    private Integer numero_empleados;

    // Proximas tareas:
    // Enum
    // Fecha
    // Asociación con otra entidad/tabla
}
