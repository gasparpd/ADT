import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;

public class EscrituraJAXB {

    public static void main (String[]args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Libreria.class);

        Marshaller marshaller = context.createMarshaller();

        Libreria libreria = new Libreria();
        libreria.setNombre("Librería 1");

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

        libreria.setLibros(libros);

        marshaller.marshal(libreria, new File("./src/main/resources/Ficheros/EscrituraLibreria.xml"));
        System.out.println("Fichero escrito.");
    }
}
