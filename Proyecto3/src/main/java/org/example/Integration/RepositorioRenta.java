package org.example.Integration;

import org.example.Controller.Constantes;
import org.example.Model.*;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

public class RepositorioRenta {

    public int insertarRenta(DTOResumen resumen, Calendar fecha) {
        int afectadas = 0;
        String SQL = "INSERT INTO Renta(fecha) VALUES (?)";
        try (
                Connection conex = DriverManager.getConnection(
                        Constantes.THINCONN,
                        Constantes.USERNAME,
                        Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);) {
            ps.setDate(1, new java.sql.Date(fecha.getTimeInMillis()));
            afectadas = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return afectadas;
    }

    public Renta ConsultarRenta(Integer numeroRenta) {
        Renta rentaConsultada = new Renta();
        Calendar fecha = Calendar.getInstance();
        StringBuilder SQL =
                new StringBuilder("select\n" +
                        "    *\n" +
                        "from renta r\n" +
                        "where r.ID = ?");
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL.toString());) {
            ps.setInt(1, numeroRenta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rentaConsultada.setNumero(rs.getInt("ID"));
                    fecha.setTime(rs.getDate("FECHA"));
                    rentaConsultada.setFechaHora(fecha);
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        rentaConsultada.setLineas(lineasPorRenta(numeroRenta));
        rentaConsultada.setPagoBilletes(billetesPorRenta(numeroRenta));

        return rentaConsultada;
    }
    public Linea buildLineaPorRenta (ResultSet rs) throws SQLException {
        Carro carro = new Carro(
                rs.getString("PLACA"),
                rs.getInt("UNIDADESDISPONIBLES"),
                rs.getInt("PRECIO"),
                rs.getInt("PUESTOS")
        );
        return new Linea(
                rs.getInt("NUMERO"),
                rs.getInt("CANTIDAD"),
                carro
        );
    }

    public ArrayList<Linea> lineasPorRenta (Integer numeroRenta){
        ArrayList <Linea> lineasRenta = new ArrayList<>();
        StringBuilder SQL =
                new StringBuilder("select\n" +
                        "    c.PLACA,\n" +
                        "    c.UNIDADESDISPONIBLES,\n" +
                        "    c.PRECIO,\n" +
                        "    c.PUESTOS,\n" +
                        "    l.NUMERO,\n" +
                        "    l.CANTIDAD\n" +
                        "from linea l, carro c\n" +
                        "where c.ID = l.CARROID and l.RENTAID = ?");
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL.toString());) {
            ps.setInt(1, numeroRenta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lineasRenta.add(buildLineaPorRenta(rs));
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return lineasRenta;
    }

    public ArrayList<Billete> billetesPorRenta (Integer numeroRenta){
        ArrayList<Billete> billetes = new ArrayList<>();
        StringBuilder SQL =
                new StringBuilder("select\n" +
                        "    b.DENOMINACION,\n" +
                        "    c.CANTIDAD\n" +
                        "from BILLETE b, CANTIDADPORBILLETE c\n" +
                        "where b.ID = c.BILLETEID and c.RENTAID = ?");
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL.toString());) {
            ps.setInt(1, numeroRenta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    billetes.add(buildBilletePorRenta(rs));
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return billetes;
    }

    public Billete buildBilletePorRenta (ResultSet rs) throws SQLException {
        return new Billete(
                rs.getInt("CANTIDAD"),
                rs.getInt("DENOMINACION")
        );
    }

    /*private void buildDTOReporte(ResultSet rs) throws SQLException {
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(rs.getDate("YEAR"));
        DTOReporte reporte = new DTOReporte();
        reporte.setFechaAnio(fecha);
        fecha.setTime(rs.getDate("MONTH"));
        reporte.setFechaMes(fecha);
        reporte.setCarrosRentados(rs.getInt("TOTAL"));
    }

    public ArrayList<DTOReporte> ConsultarAcumulados() {
        ArrayList<DTOReporte> DTOReporte = new ArrayList<>();
        StringBuilder SQL =
                new StringBuilder("select\n" +
                        " to_char(r.FECHA,'YYYY') as YEAR\n" +
                        " to_char(r.FECHA,'MM') as MONTH\n" +
                        "    sum(case when c.UNIDADESDISPONIBLES=0 then 0 else 1) as TOTAL,\n" +
                        "from CARRO c, LINEA l, RENTA r\n" +
                        "where c.ID = l.CARROID and r.ID = l.RENTAID" +
                        " group by to_char(r.FECHA,'YYYY')\n" +
                        " group by to_char(r.FECHA,'MM')\n");
       //ACA No
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL.toString());){
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    buildDTOReporte(rs);
                }
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return DTOReporte;
    }*/

    private void buildDTOReporte(ResultSet rs) throws SQLException {
        DTOReporte reporte = new DTOReporte();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(rs.getDate("FECHA"));

        reporte.setFechaAnioMes(calendar);
        reporte.setCarrosRentados(rs.getInt("CANTIDAD"));
    }
    public ArrayList<DTOReporte> ConsultarAcumulados() {
        ArrayList<DTOReporte> reportes = new ArrayList<>();
        StringBuilder SQL =
                new StringBuilder("select\n" +
                        " to_char(r.FECHA,'MM/YYYY')\n" +
                        "    sum(l.CANTIDAD),\n" +
                        "LINEA l, RENTA r\n" +
                        "where r.ID = l.RENTAID and l.CARROID=?" +
                        " group by to_char(r.FECHA,'MM/YYYY')\n") ;
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL.toString());) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                buildDTOReporte(rs);
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return reportes;
    }
}
