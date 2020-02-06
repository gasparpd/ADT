package ejercicio3;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;

public class EscrituraHotel {
    public static void main (String[]args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Hotel.class);

        Marshaller marshaller = context.createMarshaller();

        //Array de habitaciones
        ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
        Habitacion habitacion = new Habitacion();
        habitacion.setNumero(1);
        habitacion.setCamas(2);
        habitacion.setTipo("Normal");
        habitacion.setExterior(false);
        habitaciones.add(habitacion);

        habitacion = new Habitacion();
        habitacion.setNumero(2);
        habitacion.setCamas(1);
        habitacion.setTipo("Lujo");
        habitacion.setExterior(true);
        habitaciones.add(habitacion);

        Hotel hotel1 = new Hotel();
        hotel1.setEstrellas(4);
        hotel1.setListaHabitaciones(habitaciones);
        hotel1.setNombre("Monumental");
        hotel1.setProvincia("Asturias");

        marshaller.marshal(hotel1, new File("./src/main/java/ejercicio3/EscrituraHotel.xml"));
        System.out.println("Fichero escrito.");
    }
}
