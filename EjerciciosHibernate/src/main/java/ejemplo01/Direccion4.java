package ejemplo01;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Direccion")
public class Direccion4 implements Serializable {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "calle")
    private String calle;

    @Column(name = "numero")
    private int numero;

    @Column(name = "poblacion")
    private String poblacion;

    @Column(name = "provincia")
    private String provincia;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Profesor4 profesor;

    public Direccion4() {
    }

    public Direccion4(int id, String calle, int numero, String poblacion, String provincia) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.poblacion = poblacion;
        this.provincia = provincia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Profesor4 getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor4 profesor) {
        this.profesor = profesor;
    }
}