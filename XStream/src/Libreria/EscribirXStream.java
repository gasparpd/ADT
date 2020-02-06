package Libreria;

import com.thoughtworks.xstream.XStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class EscribirXStream {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Creamos la librería
        Libreria libreria = new Libreria();
        libreria.setNombre("Librería 1");

        /*
        Creamos el array de libros y los propios libros
        y los metemos en el array "libros"
         */
        ArrayList<Libro> libros = new ArrayList<Libro>();
        Libro libro = new Libro();
        libro.setIsbn("12304506");
        libro.setTitulo("El capital");
        libro.setAutor("Karl Marx");
        libros.add(libro);

        libro = new Libro();
        libro.setIsbn("83489723");
        libro.setTitulo("Titulo2");
        libro.setAutor("Autor2");
        libros.add(libro);

        /*Añadimos el array de libreos a la librería*/
        libreria.setLibros(libros);

        try {
            XStream xstream = new XStream();

            //Alias de las clases
            xstream.alias("Libreria", Libreria.class);
            xstream.alias("Libreria.Libro", Libro.class);

            //Campos de la clase librería
            xstream.aliasField("NombreLibreria", Libreria.class, "nombre");

            //Campos de la clase libro
            //Atributo de la clase libro (ISBN)
            xstream.useAttributeFor(Libro.class, "isbn");
            xstream.aliasField("ISBN", Libro.class, "isbn");
            xstream.aliasField("Titulo", Libro.class, "titulo");
            xstream.aliasField("Autor", Libro.class, "Autor");

            //Array de libros de la clase Librería
            xstream.addImplicitCollection(Libreria.class, "libros");

            //xstream.toXML(libros, new FileOutputStream("Alumnos.xml"));
            xstream.toXML(libreria, new FileOutputStream("Alumnos.xml"));

            System.out.println("Creado fichero XML....");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
