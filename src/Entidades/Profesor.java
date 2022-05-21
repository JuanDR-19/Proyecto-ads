package Entidades;

public class Profesor extends Empleado {

	private int escalafon; //valores de 1-4
	
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
}
