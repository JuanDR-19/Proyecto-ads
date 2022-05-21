package otros;

public class asignatura {
	private int horas;
	private String nombre;

	public asignatura() {
		// TODO Auto-generated constructor stub
	}
	
	public asignatura(String nombre) {
		this.nombre=nombre;
	}
	
	public asignatura(int horas) {
		this.horas=horas;
	}
	
	public asignatura(int horas, String nombre) {
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
