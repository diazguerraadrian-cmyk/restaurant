package com.example.restaurant.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "usuarios")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @ToString.Exclude
    private Usuario user;

    @ManyToOne
    @ToString.Exclude
    private Restaurante restaurante;

    @ManyToOne
    @ToString.Exclude
    private Plato plato;
}
