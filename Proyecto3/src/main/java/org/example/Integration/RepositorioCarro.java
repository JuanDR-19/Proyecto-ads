package org.example.Integration;

import org.example.Controller.Constantes;
import org.example.Model.Carro;
import org.example.Model.Linea;

import java.sql.*;
import java.util.ArrayList;

public class RepositorioCarro {

    public ArrayList <Carro> consultarCarros (){
        ArrayList <Carro> carros = new ArrayList<>();
        String SQL = "select *\n" +
                "from carro";
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                carros.add(crearCarro(rs));
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return carros;
    }

    public Carro existeCarro (String placa){
        StringBuilder SQL =
                new StringBuilder("select c.ID, c.PLACA, c.PRECIO, c.UNIDADESDISPONIBLES, c.PUESTOS\n" +
                        "from CARRO c\n" +
                        "where c.PLACA = ?");
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL.toString());) {
            //se asignan los valores a los parametros
            ps.setString(1, placa);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return crearCarro(rs);
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return null;
    }

    public Carro verificarExistencias (String placa, Integer unidadesSolicitadas){
        StringBuilder SQL =
                new StringBuilder("select c.ID, c.PLACA, c.PRECIO, c.UNIDADESDISPONIBLES, c.PUESTOS\n" +
                        "from CARRO c\n" +
                        "where c.PLACA = ? and c.UNIDADESDISPONIBLES > ?");
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL.toString());) {
            //se asignan los valores a los parametros
            ps.setString(1, placa);
            ps.setInt(2, unidadesSolicitadas);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return crearCarro(rs);
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return null;
    }

    public Linea existeCarroEnLinea (String placa){
        StringBuilder SQL =
                new StringBuilder("select l.id ,l.numero, l.cantidad\n" +
                        "from carro c, linea l\n" +
                        "where c.id = l.carroid and c.placa = ?");
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL.toString());) {
            //se asignan los valores a los parametros
            ps.setString(1, placa);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return new Linea(
                            rs.getInt("ID"),
                            rs.getInt("NUMERO"),
                            rs.getInt("CANTIDAD")
                    );
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return null;
    }

    public Integer updateLinea (Integer cantidad, Integer IDLinea){
        int afectadas = 0;
        String SQL = "update Linea set cantidad = ? where ID = ?";
        try (
                Connection conex = DriverManager.getConnection(
                        Constantes.THINCONN,
                        Constantes.USERNAME,
                        Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);) {

            ps.setInt(1, cantidad);
            ps.setInt(2, IDLinea);
            afectadas = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return afectadas;
    }

    public Carro crearCarro (ResultSet rs) throws SQLException {
        return new Carro(
                rs.getInt("ID"),
                rs.getString("PLACA"),
                rs.getInt("UNIDADESDISPONIBLES"),
                rs.getInt("PRECIO"),
                rs.getInt("PUESTOS")
        );
    }

    public int insertarLinea(Linea linea, Integer IDRenta) {
        int afectadas = 0;
        String SQL = "INSERT INTO Linea(Rentaid, Carroid, numero, cantidad) VALUES (?, ?, ?, ?)";
        try (
                Connection conex = DriverManager.getConnection(
                        Constantes.THINCONN,
                        Constantes.USERNAME,
                        Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);) {

            ps.setInt(1, IDRenta);
            ps.setInt(2, linea.getCarroRentado().getID());
            ps.setInt(3, linea.getNumero());
            ps.setInt(4, linea.getCantidad());
            afectadas = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return afectadas;
    }

    public int eliminar(Linea linea, Integer IDRenta) {
        int afectadas = 0;
        String SQL = "DELETE FROM Linea WHERE Numero = ?";

        try (
                Connection conex = DriverManager.getConnection(
                        Constantes.THINCONN,
                        Constantes.USERNAME,
                        Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL);) {
            ps.setInt(1, linea.getNumero());
            afectadas = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return afectadas;
    }

    public Integer cantidadCarrosRenta(Integer rentaActual){
        Integer filas = 0;
        StringBuilder SQL =
                new StringBuilder("select *\n" +
                        "from LINEA l, RENTA r\n" +
                        "where r.ID = l.RENTAID");
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL.toString());) {
            //se asignan los valores a los parametros
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    filas ++;
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return filas;
    }

    public Double calcularDescuento ( Integer cantidad){
        StringBuilder SQL =
                new StringBuilder("select r.descuento\n" +
                        "from rangodescuento  r\n" +
                        "where r.cantidad1 <= ? and r.cantidad2 >= ?");
        try (
                Connection conex = DriverManager.getConnection(Constantes.THINCONN, Constantes.USERNAME, Constantes.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL.toString());) {
            //se asignan los valores a los parametros
            ps.setInt(1, cantidad);
            ps.setInt(2, cantidad);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return (rs.getDouble("DESCUENTO")/100);
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return null;
    }
}
