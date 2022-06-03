package org.example.Integration;

import org.example.Controller.Constantes;
import org.example.Model.*;

import java.sql.*;
import java.util.ArrayList;

public class RepositorioBillete{
    public ArrayList<Billete> consultarTiposBillete(){
        ArrayList <Billete> billetes = new ArrayList<>();
        String SQL = "select *\n" +
                "from billete";
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                billetes.add(crearBillete(rs));
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return billetes;
    }

    public Billete crearBillete(ResultSet rs) throws SQLException {
        return new Billete(
                rs.getInt("ID"),
                rs.getInt("DENOMINACION")
        );
    }


    public int existeIdBillete(Billete billete){
        int id;
        StringBuilder SQL =
                new StringBuilder("select b.ID\n" +
                        "from BILLETE b\n" +
                        "where b.denominacion = ?");
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL.toString());) {
            //se asignan los valores a los parametros
            ps.setInt(1, billete.getDenominacion());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("ID");
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return 0;

    }

    public Billete existeBillete (Integer denominacion){
        StringBuilder SQL =
                new StringBuilder("select b.ID, b.denominacion\n" +
                        "from BILLETE b\n" +
                        "where b.denominacion = ?");
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL.toString());) {
            //se asignan los valores a los parametros
            ps.setInt(1, denominacion);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return crearBillete(rs);
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return null;

    }
    public int insertarBillete(Billete billete,Integer IDRenta ) {
        int afectadas = 0;
        String SQL = "INSERT INTO CantidadPorbillete(Billeteid, Rentaid, cantidad) VALUES (?, ?, ?)";
        try (
                Connection conex = DriverManager.getConnection(
                        Constantes.THINCONN,
                        Constantes.USERNAME,
                        Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);) {

            ps.setInt(1, billete.getId());
            ps.setInt(2, IDRenta);
            ps.setInt(3, billete.getCantidad());
            afectadas = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return afectadas;
    }
}


