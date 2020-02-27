package ejemplo01;

import java.io.Serializable;

public class Direccion3 implements Serializable {
    private int id;
    private String calle;
    private int numero;
    private String poblacion;
    private String provincia;

    public Direccion3() {
    }

    public Direccion3(int id, String calle, int numero, String poblacion, String provincia) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.poblacion = poblacion;
        this.provincia = provincia;
    }
}