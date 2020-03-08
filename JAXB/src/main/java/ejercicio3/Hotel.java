package ejercicio3;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "MiHotel")
@XmlType(propOrder = {"estrellas", "listaHabitaciones", "nombre", "provincia"})
public class Hotel {
    private ArrayList<Habitacion> listaHabitaciones;
    private String nombre;
    private String provincia;
    private int estrellas;

    public Hotel() {
    }

    @XmlElementWrapper(name = "listaHabitaciones")
    @XmlElement(name = "habitacion")
    public ArrayList<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public void setListaHabitaciones(ArrayList<Habitacion> listaHabitaciones) {
        this.listaHabitaciones = listaHabitaciones;
    }

    @XmlElement(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "provincia")
    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @XmlElement(name = "estrellas")
    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }
}
