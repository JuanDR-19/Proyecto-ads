package Entidades;

public class Empleado {

	private String nombre; 
	private int Documento;
	private String Dependencia;
	private String cargo;
	private String SalariosMinimos;
	private double valorHora;
	
	
	public Empleado() {
		// TODO Auto-generated constructor stub
	}
	
	public Empleado(String nombre, int documento, String dependencia, String cargo, String salarios, double valor) {
		this.nombre=nombre;
		this.Documento=documento;
		this.Dependencia=dependencia;
		this.cargo=cargo;
		this.SalariosMinimos=salarios;
		this.valorHora=valor;
	}
	
	public Empleado(String nombre, int documento, String dependencia, String cargo, String salarios) {
		this.nombre=nombre;
		this.Documento=documento;
		this.Dependencia=dependencia;
		this.cargo=cargo;
		this.SalariosMinimos=salarios;
		
	}
	
	public Empleado(String nombre, int documento, String dependencia, String cargo) {
		this.nombre=nombre;
		this.Documento=documento;
		this.Dependencia=dependencia;
		this.cargo=cargo;
		
	}

	public Empleado(String nombre, int documento, String dependencia) {
		this.nombre=nombre;
		this.Documento=documento;
		this.Dependencia=dependencia;
		
	}
	
	public Empleado(String nombre, int documento) {
		this.nombre=nombre;
		this.Documento=documento;
		
	
	}
	
	public Empleado(String nombre) {
		this.nombre=nombre;
		
	}
}
