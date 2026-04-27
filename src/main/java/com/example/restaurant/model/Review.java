package com.example.restaurant.model;

import jakarta.persistence.*;
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
    private Long id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String Title;
    @Column(length = 1000)
    private String descripcion;
    private Integer calificacion;
    @Builder.Default
    private LocalDateTime fechacreada = LocalDateTime.now();
    @ManyToOne
    private Restaurante restaurante;
}
