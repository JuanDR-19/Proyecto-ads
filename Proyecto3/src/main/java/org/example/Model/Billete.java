package org.example.Model;

public class Billete {


    private Integer id;
    private Integer cantidad;
    private Integer denominacion;

    private Integer total;

    public Billete(Integer cantidad, Integer denominacion) {
        this.cantidad = cantidad;
        this.denominacion = denominacion;
        this.total = cantidad*denominacion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }



    public Integer getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(Integer denominacion) {
        this.denominacion = denominacion;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Billete{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", denominacion=" + denominacion +
                ", total=" + total +
                '}';
    }
}
