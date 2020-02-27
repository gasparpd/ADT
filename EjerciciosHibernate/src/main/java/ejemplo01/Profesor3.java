package ejemplo01;

import java.io.Serializable;

public class Profesor3 implements Serializable{
    private int id;
    private String nombre;
    private String ape1;
    private String ape2;
    private Direccion3 direccion;

    public Profesor3() {
    }

    public Profesor3(int id, String nombre, String ape1, String ape2) {
        this.id = id;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
    }
}