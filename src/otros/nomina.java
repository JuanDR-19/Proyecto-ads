package otros;

import Entidades.Empleado;
import Entidades.Monitor;
import Entidades.Profesor;
import java.util.ArrayList;

public class nomina {

	//Esto no deberia estar aca, no? No debemos crear nada, simplemente establecer el parámetro
	ArrayList<Empleado> empleados= new ArrayList<Empleado>();
	
	public nomina() {
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
	public void calcularSalarioEmpleado(int documento){
		for (int i=0; i<empleados.size(); i++) {
			//realmente existe el empleado
			if(empleados.get(i).getDocumento()==documento) {
				empleados.get(i).CalcularSalario();
			}
		}
	}
	
	

	


}
