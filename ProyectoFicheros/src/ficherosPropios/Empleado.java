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

	public Empleado(String nombre, String apellidos, int edad, String cargo, int duracionContrato, char sexo) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.cargo = cargo;
		this.duracionContrato = duracionContrato;
		this.sexo = sexo;
	}

	public void mostrarDatos() {
		System.out.println("Nombre: " +this.nombre);
		System.out.println("Apellidos: " +this.apellidos);
		System.out.println("Edad: " +this.edad);
		System.out.println("Cargo: " +this.cargo);
		System.out.println("Duración del contrato: " +this.duracionContrato);
		System.out.println("Sexo: " +this.sexo);
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
