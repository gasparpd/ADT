package Libreria;

import java.util.ArrayList;

public class Libreria {
    private String nombre;
    private ArrayList<Libro> libros = new ArrayList();

    public Libreria() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }


}
