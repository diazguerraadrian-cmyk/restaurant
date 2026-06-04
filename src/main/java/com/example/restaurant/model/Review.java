package com.example.restaurant.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El título no puede estar vacío")
    private String title;
    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres")
    @Column(length = 1000)
    private String descripcion;
    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 1, message = "La calificación mínima es 1")
    @Max(value = 5, message = "La calificación máxima es 5")
    private Integer calificacion;
    @Builder.Default
    private LocalDateTime fechacreada = LocalDateTime.now();
    @ToString.Exclude
    @ManyToOne
    private Restaurante restaurante;
    @ToString.Exclude
    @ManyToOne
    private Plato plato;
    @ToString.Exclude
    @ManyToOne
    private Usuario user;
}
