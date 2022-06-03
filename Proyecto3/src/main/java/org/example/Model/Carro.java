package org.example.Model;

import java.math.BigDecimal;

public class Carro {
    private Integer ID;
    private String Placa;
    private Integer unidadesDisponibles;
    private Integer precio;
    private Integer puestos;

    public Carro(Integer ID, String placa, Integer unidadesDisponibles, Integer precio, Integer puestos) {
        this.ID = ID;
        Placa = placa;
        this.unidadesDisponibles = unidadesDisponibles;
        this.precio = precio;
        this.puestos = puestos;
    }

    public Carro(String placa, Integer unidadesDisponibles, Integer precio, Integer puestos) {
        Placa = placa;
        this.unidadesDisponibles = unidadesDisponibles;
        this.precio = precio;
        this.puestos = puestos;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        Placa = placa;
    }

    public Integer getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(Integer unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getPuestos() {
        return puestos;
    }

    public void setPuestos(Integer puestos) {
        this.puestos = puestos;
    }

    public void calcularValor (){

    }

    @Override
    public String toString() {
        return "Carro{" +
                "ID=" + ID +
                ", Placa='" + Placa + '\'' +
                ", unidadesDisponibles=" + unidadesDisponibles +
                ", precio=" + precio +
                ", puestos=" + puestos +
                '}';
    }
}
