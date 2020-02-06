package Alumno;

/*@XmlType(propOrder = { "nota", "nombre", "apellidos"})*/

public class Alumno {
	public Alumno() {
		super();
	}
	private int nota;
	private String nombre;
	private String apellidos;
	
	public Alumno(int nota, String nombre, String apellidos) {
		super();
		this.nota = nota;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
	/*@XmlElement(name = "MiNota")*/
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	
	/*@XmlElement(name = "MiNombre")*/
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/*@XmlElement(name = "MisApellidos")*/
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	

	
}
