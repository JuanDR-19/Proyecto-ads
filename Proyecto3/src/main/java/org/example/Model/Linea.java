package org.example.Model;

public class Linea {
    private Integer ID;
    private Integer numero;
    private Integer cantidad;
    private Carro carroRentado;
    private Integer subTotal;

    public Linea (){

    }

    public Linea(Integer cantidad, Carro carroRentado) {
        this.cantidad = cantidad;
        this.carroRentado = carroRentado;
        this.subTotal = cantidad*carroRentado.getPrecio();
    }

    public Linea(Integer ID, Integer numero, Integer cantidad) {
        this.ID = ID;
        this.numero = numero;
        this.cantidad = cantidad;
    }

    public Linea(Integer numero, Integer cantidad, Carro carroRentado) {
        this.numero = numero;
        this.cantidad = cantidad;
        this.carroRentado = carroRentado;
        this.subTotal = cantidad*carroRentado.getPrecio();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Carro getCarroRentado() {
        return carroRentado;
    }

    public String getPlacaCarroRentado (){
        return carroRentado.getPlaca();
    }
    public Integer getPrecioCarro (){
        return carroRentado.getPrecio();
    }

    public void setCarroRentado(Carro carroRentado) {
        this.carroRentado = carroRentado;
    }

    public Integer getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "Linea{" +
                "ID=" + ID +
                ", numero=" + numero +
                ", cantidad=" + cantidad +
                ", carroRentado=" + carroRentado +
                ", subTotal=" + subTotal +
                '}';
    }
}
