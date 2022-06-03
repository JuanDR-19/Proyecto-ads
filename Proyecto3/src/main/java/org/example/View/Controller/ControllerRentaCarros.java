package org.example.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.Controller.FacadeOCR;
import org.example.Model.*;
import org.example.Utils.AlertUtils;
import org.example.Utils.Exeptions.*;
import org.example.View.Controller.ControllerReporte;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ControllerRentaCarros implements Initializable {

    private final FacadeOCR facadeOCR = new FacadeOCR();
    Integer numeroRenta = 0;

    @FXML
    private Button Button_AgregarBillete;

    @FXML
    private Button Button_AgregarLinea;

    @FXML
    private Button Button_EliminarLinea;

    @FXML
    private Button Button_GenerarReporte;

    @FXML
    private Button Button_NuevaRenta;

    @FXML
    private Button Button_TerminarRenta;

    @FXML
    private TextField cantidadBilletes;

    @FXML
    private TextField cantidadCarro;

    @FXML
    private Button btnConsultar;

    @FXML
    private TextField numRenta;

    @FXML
    private ComboBox<String> carroXPuestos;

    @FXML
    private ChoiceBox<Integer> denominaciones;

    @FXML
    private Label fecha;

    @FXML
    private Label puestosLabel;

    @FXML
    private Label saldoBilletes;

    @FXML
    private Label totalRenta;

    @FXML
    private Label vueltas;

    @FXML
    private TableView<Linea> tablaLinea;

    @FXML
    private TableColumn<Linea, Integer> columCantidad;

    @FXML
    private TableColumn<Linea, String> columPlaca;

    @FXML
    private TableColumn<Linea, Integer> columPrecio;

    @FXML
    private TableColumn<Linea, Integer> columSubTotal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Carro c : facadeOCR.consultarCarros()){
            carroXPuestos.getItems().add(c.getPlaca());
        }
        for (Billete b : facadeOCR.consultarBilletes()){
            denominaciones.getItems().add(b.getDenominacion());
        }

    }

    @FXML
    void agregarBillete(ActionEvent event) {
        DTOResumen resumen;
        Integer denominacion= denominaciones.getSelectionModel().getSelectedItem();
        Billete billete=new Billete(
                Integer.parseInt(cantidadBilletes.getText()),denominacion);


        try{
            resumen = facadeOCR.agregarBillete(billete);
            if (resumen.getMensajeError() !=  null)
                throw new ErrorAgregarBillete(resumen.getMensajeError());
            System.out.println();
            renderTable(resumen);


        }catch (ErrorAgregarBillete ex){
            AlertUtils.alertError("Error", ex.getMessage(), "");
        }
        catch (ErrorPago ex){
            AlertUtils.alertError("Error", ex.getMessage(), "");
        }

    }

    @FXML
    void agregarLinea(ActionEvent event) {
        DTOResumen resumen;
        try{
            Linea linea = new Linea(
                    Integer.parseInt(cantidadCarro.getText()),
                    facadeOCR.getCarroContro().existeCarro(carroXPuestos.getSelectionModel().getSelectedItem())
            );
            resumen = facadeOCR.agregarLinea(linea);
            if (resumen.getMensajeError() !=  null)
                throw new ErrorAgregarLinea(resumen.getMensajeError());
            System.out.println();
            renderTable(resumen);

        }catch(RuntimeException ex){
            AlertUtils.alertError("Error", "Primero debe crear una nueva renta", "");
        }
        catch (ErrorAgregarLinea ex){
            AlertUtils.alertError("Error", ex.getMessage(), "");
        }
        catch (ErrorPago ex){
            AlertUtils.alertError("Error", ex.getMessage(), "");
        }
    }

    @FXML
    void eliminarLinea(ActionEvent event) {
        DTOResumen resumen;
        try{
            resumen = facadeOCR.eliminarLinea(this.tablaLinea.getSelectionModel().getSelectedItem());
            if (resumen.getMensajeError() !=  null)
                throw new ErrorAgregarLinea(resumen.getMensajeError());
            System.out.println();
            renderTable(resumen);


        }catch (ErrorAgregarLinea ex){
            AlertUtils.alertError("Error", ex.getMessage(), "");
        }
        catch (ErrorPago ex){
            AlertUtils.alertError("Error", ex.getMessage(), "");
        }

    }

    @FXML
    void generarReporte(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader(ControllerReporte.class.getResource("../Reporte.fxml"));
        Parent root = loader.load();
        ControllerReporte ViewControllerReporte = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Reporte");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
        stage.show();
        Stage myStage = (Stage) this.Button_GenerarReporte.getScene().getWindow();
        myStage.close();


    }

    @FXML
    void mostrarPuestosXPlaca(ActionEvent event) {
        /*
        * cada vez que se seleccione una placa del combobox se debe actualizar los puestos en la variable puestosLabel
        * para buscar los puestos se puede usar la consulta existe carro, devuelve todos los atributos con tan solo dar la placa
        * */
        Integer puestos = facadeOCR.getCarroContro().existeCarro(carroXPuestos.getSelectionModel().getSelectedItem()).getPuestos();
        puestosLabel.setText(String.valueOf(puestos));
    }

    @FXML
    void nuevaRenta(ActionEvent event) throws ErrorPago {

        /*
         * es necesario que cuando se inicie el programa darle al boton nueva renta, este crea una instancia de renta
         * para poder agregar las lineas
         * en este parte del codigo se puso siempre la renta 1 hay que llevar un consecutivo de rentas
         *
         * se deben crear metodos para llenar los combox de placa, y billetes y esto solo cada vez que se haga una nueva renta
         * */
        DTOResumen resumen;
        this.numeroRenta ++;
        this.clearScreen();
        try{
            Calendar fecha = setFecha();
            Renta renta = this.facadeOCR.buildNuevaRenta(numeroRenta, new Renta(),fecha);
            resumen = this.facadeOCR.crearRenta(renta);
            if (resumen.getMensajeError() !=  null)
                throw new ErrorAgregarRenta(resumen.getMensajeError());


        }catch (ErrorAgregarRenta ex){
            AlertUtils.alertError("Error", ex.getMessage(), "");
        }
        catch (ErrorPago ex){
            AlertUtils.alertError("Error", ex.getMessage(), "");
        }

    }

    @FXML
    void terminarRenta(ActionEvent event) {

    }

  @FXML
    void consultarRenta(ActionEvent event) {
        DTOResumen resumen;
        Renta renta = new Renta();
        renta.setNumero(Integer.parseInt(numRenta.getText()));
        try{
            resumen = facadeOCR.consultarRenta(renta);
            if (resumen.getMensajeError() != null){
                throw new ErrorRentaNoExiste(resumen.getMensajeError());
            }
            renderTable(resumen);
        }catch (ErrorRentaNoExiste ex){
            AlertUtils.alertError("Error", ex.getMessage(), "");
        }

    }

    public Calendar setFecha (){
        SimpleDateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
        Calendar fechaActual = Calendar.getInstance();
        this.fecha.setText(Fecha.format(fechaActual.getTime()));
        return fechaActual;
    }

    public void clearTable (){
        tablaLinea.getItems().clear();
        totalRenta.setText("0");
    }

    public void clearScreen(){
        tablaLinea.getItems().clear();
        totalRenta.setText("0");
        cantidadCarro.setText("1");
        cantidadBilletes.setText("1");
        saldoBilletes.setText("0");
    }

    public void renderTable (DTOResumen resumen){
        clearTable();
        SimpleDateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
        tablaLinea.getItems().addAll(resumen.getLineas());
        totalRenta.setText(String.valueOf(resumen.getTotalRenta()));
        saldoBilletes.setText(String.valueOf(resumen.getSaldoBilletes()));
        vueltas.setText(String.valueOf(resumen.getVueltas()));
        fecha.setText(Fecha.format(resumen.getFecha().getTime()));
    }
}
