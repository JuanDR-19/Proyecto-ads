package Entidades;

public class Profesor extends Empleado {

	private int escalafon; //valores de 1-5
	
	public Profesor() {
		// TODO Auto-generated constructor stub
	}

	public Profesor(int escalafon) {
		this.escalafon=escalafon;
	}
	
	public void SetEscalafon(int escalafon) {
		this.escalafon=escalafon;
	}
	
	public int GetEscalafon() {
		return this.escalafon;
	}
	
	
	//salarios por escalafon uno sacados de: https://www.universidad.edu.co/en-2-022-hora-catedra-en-ies-privadas-debe-pagarse-como-minimo-en-41-667-mas-prestaciones/
	//salario por escalafon 2 sacado de=https://co.talent.com/salary?job=instructor
	//salario por escalafon 3 sacado de=  https://tusalario.org/colombia/carrera/funcion-y-salario/profesores-adjuntos
	// salario por escalafon 4 sacado de= https://gomezpinzon.com/salario-minimo-y-calendario-de-pago-de-prestaciones-sociales-en-202/#:~:text=El%2015%20de%20diciembre%20de,%2C%20es%20decir%2C%20COP%20%2491.474.
	//salario por escalafon 5 sacado de= https://www.elempleo.com/co/noticias/noticias-laborales/definido-el-salario-minimo-en-colombia-para-2022-en-cuanto-quedo-la-cifra-6584
	
	
	public String CalcularSalario() {
		int q=this.getSalariosMinimos();
		int total=this.asignaturas.size();
		int valor=0;
		float salario=0;
		
		for(int i=0;i<total;i++) {
			valor=this.asignaturas.get(i).getHoras()+valor;
		}
		
		float uno=(8*this.getSMLV()/valor);
		float dos=(8094*valor);
		float tres= 785434;
		float cuatro=91474*valor;
		float cinco=1000000; //sin subsidio de transporte
		
		int escalaf=this.GetEscalafon();
		
		if(escalaf==1) {
			salario=(float)(uno*0.88*q);
		}else if(escalaf==2) {
			salario=(float)(dos*0.88*q);
		}else if(escalaf==3) {
			salario=(float)(tres*0.88*q);
		}else if(escalaf==4) {
			salario=(float)(cuatro*0.88*q);
		}else if(escalaf==5) {
			salario=(float)(cinco*0.88*q);
		}
	
		return  "El empleado "+getNombre()+" devenga un salario total de: "+ salario;
	}
	
}
