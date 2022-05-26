package model;

import java.util.ArrayList;

public class Nomina {

	//Esto no deberia estar aca, no? No debemos crear nada, simplemente establecer el parámetro
	ArrayList<Empleado> empleados= new ArrayList<Empleado>();
	
	public Nomina() {
		// TODO Auto-generated constructor stub
	}
	
	public void adicionarAsignaturaEmpleado(String nombreAsignatura, int horas, int documento) {
		for (int i=0; i<empleados.size(); i++) {
			//realmente existe el empleado
			if(empleados.get(i).getDocumento()==documento) {
				empleados.get(i).AñadirAsignatura(nombreAsignatura, horas);
			}
		}
	}
	
	public String calcularSalarioEmpleado(int documento){
		String retorno="";
		for (int i=0; i<empleados.size(); i++) {
			//realmente existe el empleado
			if(empleados.get(i).getDocumento()==documento) {
				
				retorno=empleados.get(i).CalcularSalario();
			}
		}
		return retorno;
	}
}
