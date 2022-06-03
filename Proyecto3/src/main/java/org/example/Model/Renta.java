package org.example.Model;

import java.util.ArrayList;
import java.util.Calendar;

public class Renta {
    private Integer ID;
    private Integer numero;
    private Calendar fechaHora;
    private ArrayList<Linea> lineas = new ArrayList<>();
    private ArrayList<Billete> pagoBilletes = new ArrayList<>();

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

    public Calendar getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Calendar fechaHora) {
        this.fechaHora = fechaHora;
    }

    public ArrayList<Linea> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<Linea> lineas) {
        this.lineas = lineas;
    }

    public ArrayList<Billete> getPagoBilletes() {
        return pagoBilletes;
    }

    public void setPagoBilletes(ArrayList<Billete> pagoBilletes) {
        this.pagoBilletes = pagoBilletes;
    }

    @Override
    public String toString() {
        return "Renta{" +
                "ID=" + ID +
                ", numero=" + numero +
                ", fechaHora=" + fechaHora +
                ", lineas=" + lineas +
                ", pagoBilletes=" + pagoBilletes +
                '}';
    }
}
