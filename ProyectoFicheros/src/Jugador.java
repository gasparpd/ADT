
public class Jugador {
	private String nombre;
	private String apellidos;
	private int dorsal;
	private String posicion;
	
	public Jugador() {
		nombre="";
		apellidos="";
		dorsal=-1;
		posicion="";
	}
	
	public void mostraDatos() {
		System.out.println(this.nombre);
		System.out.println(this.apellidos);
		System.out.println(this.dorsal);
		System.out.println(this.posicion);
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
	public int getDorsal() {
		return dorsal;
	}
	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	
}
