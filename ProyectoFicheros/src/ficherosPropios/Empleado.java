package ficherosPropios;

public class Empleado {
	private String nombre;
	private String apellidos;
	private int edad;
	private String cargo;
	private int duracionContrato;
	private char sexo;
	
	public Empleado() {
		nombre="";
		apellidos="";
		edad= -1;
		cargo="";
		duracionContrato= -1;
		sexo= ' ';
	}
	
	public void mostraDatos() {
		System.out.println(this.nombre);
		System.out.println(this.apellidos);
		System.out.println(this.edad);
		System.out.println(this.cargo);
		System.out.println(this.duracionContrato);
		System.out.println(this.sexo);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public int getDuracionContrato() {
		return duracionContrato;
	}
	public void setDuracionContrato(int duracionContrato) {
		this.duracionContrato = duracionContrato;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
}
