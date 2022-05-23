package Entidades;
import java.util.ArrayList;
import otros.asignatura;

public class Empleado {

	private String nombre; 
	private int Documento;
	private String Dependencia;
	private String cargo;
	private int SalariosMinimos;
	private double valorHora;
	protected ArrayList<asignatura> asignaturas= new ArrayList<asignatura>();
	private static float SMLV=1000000;
	
	
	public Empleado() {
		// TODO Auto-generated constructor stub
	}
	
	public Empleado(String nombre, int documento, String dependencia, String cargo, int salarios, double valor) {
		this.nombre=nombre;
		this.Documento=documento;
		this.Dependencia=dependencia;
		this.cargo=cargo;
		this.SalariosMinimos=salarios;
		this.valorHora=valor;
	}
	
	public Empleado(String nombre, int documento, String dependencia, String cargo, int salarios) {
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
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	public int getDocumento() {
		return this.Documento;
	}
	
	public void setDocumento(int Documento) {
		this.Documento=Documento;
	}
	
	public String getDependencia() {
		return this.Dependencia;
	}
	
	public void setDependencia(String Dependencia) {
		this.Dependencia=Dependencia;
	}
	
	public String getCargo() {
		return this.cargo;
	}
	
	public void setCargo(String cargo) {
		this.cargo=cargo;
	}
	
	public int getSalariosMinimos() {
		return this.SalariosMinimos;
	}
	
	public void setSalariosMinimo(int Salarios) {
		this.SalariosMinimos=Salarios;
	}
	
	public double getValorhora() {
		return this.valorHora;
	}
	
	public void setValorHora(double valorhora) {
		this.valorHora=valorhora;
	}
	
	public ArrayList<asignatura> getAsignaturas() {
		return this.asignaturas;
	}
	
	public void AñadirAsignatura(String nombre, int horas) {
		asignatura asignatura= new asignatura(horas,nombre);
		asignaturas.add(asignatura);
	}
	
	public float getSMLV() {
		return SMLV;
	}
	
	public String CalcularSalario() {
		int q=this.getSalariosMinimos();
		float salario=(float) (getSMLV()*0.88*q);
		return  "El empleado "+getNombre()+" devenga un salario total de: "+ salario;
	}
	
	
	
	
	
	
}
