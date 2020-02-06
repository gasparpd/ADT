package Alumno;

import java.util.ArrayList;

/*@XmlRootElement(name = "Grupo")
@XmlType(propOrder = { "grupo", "turno","listaAlumnos" })*/
public class ListaAlumnos {

	private String grupo;
	private String turno;
	private ArrayList<Alumno> listaAlumnos;

	public ListaAlumnos(ArrayList<Alumno> listaAlumnos, String grupo, String turno) {
		super();
		this.listaAlumnos = listaAlumnos;
		this.grupo = grupo;
		this.turno = turno;
	}

	public ListaAlumnos() {
	}
	/*@XmlElement(name = "MiGrupo")*/
	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/*@XmlElement(name = "MiTurno")*/
	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	/*@XmlElementWrapper(name = "ListaAlumnos") //
	@XmlElement(name = "alumno")*/
	public ArrayList<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}

	public void setListaAlumnos(ArrayList<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}
}