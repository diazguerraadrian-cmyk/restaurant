package com.example.restaurant.model;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
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

    // fecha de fundación
    @CreationTimestamp
    private LocalDate starDate = LocalDate.now();

    // tipo de comida


    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_comida")
    private TipoComida tipoComida = TipoComida.SPANISH;

    public TipoComida getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(TipoComida tipoComida) {
        this.tipoComida = tipoComida;
    }

    public Restaurante(String nombre, Double precioMedio, Integer numero_empleados) {
        this.nombre = nombre;
        this.precioMedio = precioMedio;
        this.numero_empleados = numero_empleados;
    }
    public Restaurante() {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecioMedio() {
        return precioMedio;
    }

    public void setPrecioMedio(Double precioMedio) {
        this.precioMedio = precioMedio;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getNumero_empleados() {
        return numero_empleados;
    }

    public void setNumero_empleados(Integer numero_empleados) {
        this.numero_empleados = numero_empleados;
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precioMedio=" + precioMedio +
                ", activo=" + activo +
                ", numero_empleados=" + numero_empleados +
                ", starDate=" + starDate +
                ", tipoComida=" + tipoComida +
                '}';
    }

    public LocalDate getStarDate() {
        return starDate;
    }

    public void setStarDate(LocalDate starDate) {
        this.starDate = starDate;
    }
    // Proximas tareas:
    // Enum
    // Fecha
    // Asociación con otra entidad/tabla
}
