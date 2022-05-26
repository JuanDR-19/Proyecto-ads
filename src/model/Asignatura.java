package model;

public class Asignatura {
	private int horas;
	private String nombre;

	public Asignatura() {
		// TODO Auto-generated constructor stub
	}
	
	public Asignatura(String nombre) {
		this.nombre=nombre;
	}
	
	public Asignatura(int horas) {
		this.horas=horas;
	}
	
	public Asignatura(int horas, String nombre) {
		this.horas=horas;
		this.nombre=nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void SetNombre(String nombre) {
		this.nombre=nombre;
	}
	
	public int getHoras() {
		return this.horas;
	}
	
	public void SetHoras(int horas) {
		this.horas=horas;
	}


}
