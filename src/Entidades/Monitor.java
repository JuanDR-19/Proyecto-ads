package Entidades;

public class Monitor extends Empleado {
	

	public Monitor() {
		// TODO Auto-generated constructor stub
	}
	
	public String CalcularSalario() {
		int total=this.asignaturas.size();
		int valor=0;
		for(int i=0;i<total;i++) {
			valor=this.asignaturas.get(i).getHoras()+valor;
		}
		float salario=(float)(valor*getValorhora());
		return  "El empleado "+getNombre()+" devenga un salario total de: "+ salario;
	}

}
