package gaspar;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fabricante")
public class FabricanteSede implements Serializable {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "FUNDACION_YEAR")
    private String f_year;

    @Column(name = "MATRIZ")
    private Integer matriz;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Sede sede;

    public FabricanteSede(int id, String nombre, String f_year, Integer matriz) {
        this.id = id;
        this.nombre = nombre;
        this.f_year = f_year;
        this.matriz = matriz;
    }

    public FabricanteSede(int id, String nombre, String f_year) {
        this.id = id;
        this.nombre = nombre;
        this.f_year = f_year;
    }

    public FabricanteSede() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getF_year() {
        return f_year;
    }

    public void setF_year(String f_year) {
        this.f_year = f_year;
    }

    public Integer getMatriz() {
        return matriz;
    }

    public void setMatriz(Integer matriz) {
        this.matriz = matriz;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }
}
