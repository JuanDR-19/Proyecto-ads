package org.example.View.Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import oracle.sql.DATE;
import org.example.Controller.FacadeOCR;
import org.example.Model.Billete;
import org.example.Model.Carro;
import org.example.Model.DTOReporte;
import org.example.Integration.RepositorioRenta;
import org.example.Model.DTOResumen;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ControllerReporte {

    private final FacadeOCR facadeOCR = new FacadeOCR();
    ObservableList<DTOReporte> listaReporte;
    @FXML
    private Button VolverID;

    @FXML
    private TableColumn<DTOReporte, Calendar> mesAnioTabla;

    @FXML
    private TableView<DTOReporte> reporteTabla;

    @FXML
    private TableColumn<DTOReporte, Integer> totalTabla;

    /* boton para prubeas
    @FXML
    void reportA(ActionEvent event) {
     renderTable();
    }*/
    @FXML
    void VolverAction(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(ControllerRentaCarros.class.getResource("../RentaCarros.fxml"));
        Parent root = loader.load();
        ControllerRentaCarros ViewControllerReporte = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
        Stage myStage = (Stage) this.VolverID.getScene().getWindow();
        myStage.close();
    }

    public void renderTable (){

        listaReporte = FXCollections.observableArrayList();
        reporteTabla.setItems(listaReporte);
        listaReporte = FXCollections.observableArrayList();

        mesAnioTabla.setCellValueFactory(new PropertyValueFactory<DTOReporte,Calendar> ("fechaAnioMes"));
        totalTabla.setCellValueFactory(new PropertyValueFactory<DTOReporte,Integer> ("carrosRentados"));
        /*for (DTOReporte e : reporte) {
            listaParaReporte.add(new DTOReporte(Integer.toString(e.getFechaAnio()),Integer.toString(e.getFechaMes()),Integer.toString(e.getCarrosRentados()));
        }
        listaReporte.setAll(listaParaReporte);
        reporteTabla.setItems(listaReporte);*/
        //clearTable();
        /*anioTabla.setText(String.valueOf(reporte.getFechaAnio()));
        mesTabla.setText(String.valueOf(reporte.getFechaMes()));
        //anioTabla.setText(Fecha.format(reporte.getFechaAnio().getTime()));
        //mesTabla.setText(Fecha.format(reporte.getFechaMes().getTime()));
        totalTabla.setText(String.valueOf(reporte.getCarrosRentados()));*/

    }

}
