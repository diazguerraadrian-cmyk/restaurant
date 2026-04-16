package com.example.restaurant.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaPedido = LocalDateTime.now();

    private Double precioTotal;

    private Integer numeroTabla;

    private Integer numeroPersonas;

    private Double propina;

    @Enumerated(EnumType.STRING)
    private TipoPedido pedido = TipoPedido.PENDING;

    @ManyToOne
    @JoinColumn
    private Restaurante restaurante;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Integer getNumeroTabla() {
        return numeroTabla;
    }

    public void setNumeroTabla(Integer numeroTabla) {
        this.numeroTabla = numeroTabla;
    }

    public Integer getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(Integer numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public Double getPropina() {
        return propina;
    }

    public void setPropina(Double propina) {
        this.propina = propina;
    }

    public TipoPedido getTipoPedido() {
        return pedido;
    }

    public void setTipoPedido(TipoPedido pedido) {
        this.pedido = pedido;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", fechaPedido=" + fechaPedido +
                ", precioTotal=" + precioTotal +
                ", numeroTabla=" + numeroTabla +
                ", numeroPersonas=" + numeroPersonas +
                ", propina=" + propina +
                ", pedido=" + pedido +
                ", restaurante=" + restaurante +
                '}';
    }

    public Pedido(){}
    public Pedido(Integer numeroTabla, Integer numeroPersonas, Double propina, Restaurante restaurante) {
        this.numeroTabla = numeroTabla;
        this.numeroPersonas = numeroPersonas;
        this.propina = propina;
        this.restaurante = restaurante;
    }
}
