package controller;

import java.net.URL;
import java.util.ResourceBundle;
import model.Nomina;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class viewControllerExample implements Initializable{
	
	Nomina nominaUsar = new Nomina(); 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void cargarEmpleados() {
		//aca debemos leer archivo
	}
	
	public void generarArchivo() {
		//aca debemos escribir archivo
	}
	
	public void adicionarAsignaturaEmpleado(String nombreAsignatura, int horas, int documento) {
		nominaUsar.adicionarAsignaturaEmpleado(nombreAsignatura, horas, documento);
	}
	
	public void  calcularSalarioEmpleado (int documento){
		nominaUsar.calcularSalarioEmpleado(documento);
	}
	
	@FXML
	private TextField asignatura;

	@FXML
	private TextField documentoA;

	@FXML
	private TextField documentoS;

	@FXML
	private TextField hora;

	@FXML
	void agregarAsignatura(ActionEvent event) {
		String Asignatura=asignatura.getText();
		int Hora=Integer.parseInt(hora.getText());
		int Documento=Integer.parseInt(documentoA.getText());
		nominaUsar.adicionarAsignaturaEmpleado(Asignatura, Hora, Documento);
	}

	@FXML
	void calcularSalario(ActionEvent event) {
		int Documento=Integer.parseInt(documentoS.getText());
		nominaUsar.calcularSalarioEmpleado(Documento);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}





