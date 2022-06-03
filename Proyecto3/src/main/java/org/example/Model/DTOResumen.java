package org.example.Model;

import org.example.Utils.AlertUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class DTOResumen {
    private String mensajeError;
    private ArrayList <Linea> lineas;
    private Integer totalRenta = 0;
    private Integer saldoBilletes = 0;
    private Integer vueltas = 0;
    private Calendar fecha;


    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public ArrayList<Linea> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<Linea> lineas) {
        this.lineas = lineas;
    }

    public Integer getTotalRenta() {
        return totalRenta;
    }

    public void setTotalRenta(Integer totalRenta) {
        this.totalRenta = totalRenta;
    }

    public Integer getSaldoBilletes() {
        return saldoBilletes;
    }

    public void setSaldoBilletes(Integer saldoBilletes) {
        this.saldoBilletes = saldoBilletes;
    }

    public Integer getVueltas() {
        return vueltas;
    }

    public void setVueltas(Integer vueltas) {
        this.vueltas = vueltas;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }
}
