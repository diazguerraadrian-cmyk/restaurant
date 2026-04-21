package com.example.restaurant.model;

public enum TipoPlato {
    PRIMERO("Primer plato"), SEGUNDO("Segundo Plato"), POSTRE("Tercer Plato");

    private final String label;

    TipoPlato(String label){
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
