package org.example.Model;

import java.util.Calendar;
import java.time.LocalDate;

public class DTOReporte {
    private Calendar fechaAnioMes;
    private Integer carrosRentados;

    public DTOReporte(Calendar fechaAnioMes, Integer carrosRentados) {
        this.fechaAnioMes = fechaAnioMes;
        this.carrosRentados = carrosRentados;
    }

    public DTOReporte() {
    }

    public Calendar getFechaAnioMes() {
        return fechaAnioMes;
    }

    public void setFechaAnioMes(Calendar fechaAnioMes) {
        this.fechaAnioMes = fechaAnioMes;
    }

    public Integer getCarrosRentados() {
        return carrosRentados;
    }

    public void setCarrosRentados(Integer carrosRentados) {
        this.carrosRentados = carrosRentados;
    }
}
