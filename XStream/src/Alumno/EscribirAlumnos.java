package Alumno;

import java.io.*;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;

public class EscribirAlumnos {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		ArrayList<Alumno> lista = new ArrayList<Alumno>();

		Alumno alumno1 = new Alumno(7, "Juan", "Roca");
		lista.add(alumno1);
		Alumno alumno2 = new Alumno(8, "Sandra", "Ruiz");
		lista.add(alumno2);
		
		ArrayList<ListaAlumnos> listaGrupo = new ArrayList<ListaAlumnos>();
		
		ListaAlumnos listaGrupo1 = new ListaAlumnos(lista, "2DAM", "Tarde");
		listaGrupo.add(listaGrupo1);

		try {
			XStream xstream = new XStream();
			
			xstream.alias("MisAlumnos", ListaAlumnos.class);
			xstream.alias("Alumno", Alumno.class);
			
			xstream.aliasField("MiGrupo", ListaAlumnos.class, "grupo");
			xstream.aliasField("MiTurno", ListaAlumnos.class, "turno");
			
			xstream.aliasField("MiNota", Alumno.class, "nota");
			xstream.aliasField("MiNombre", Alumno.class, "nombre");
			xstream.aliasField("MisApellidos", Alumno.class, "apellidos");

			xstream.addImplicitCollection(ListaAlumnos.class, "listaAlumnos");
			
			xstream.toXML(lista, new FileOutputStream("Alumnos.xml"));
			xstream.toXML(listaGrupo, new FileOutputStream("Alumnos.xml"));
			
			System.out.println("Creado fichero XML....");

		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
} 
