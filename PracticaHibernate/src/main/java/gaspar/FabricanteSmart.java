package gaspar;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "fabricante")
public class FabricanteSmart implements Serializable {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "FUNDACION_YEAR")
    private String f_year;

    @Column(name = "MATRIZ")
    private Integer matriz;

    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
    private Set<Smartphone> smartphones;

    public FabricanteSmart(int id, String nombre, String f_year, Integer matriz) {
        this.id = id;
        this.nombre = nombre;
        this.f_year = f_year;
        this.matriz = matriz;
    }

    public FabricanteSmart(int id, String nombre, String f_year) {
        this.id = id;
        this.nombre = nombre;
        this.f_year = f_year;
    }

    public FabricanteSmart() {
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

    public Set<Smartphone> getSmartphones() {
        return smartphones;
    }

    public void setSmartphones(Set<Smartphone> smartphones) {
        this.smartphones = smartphones;
    }
}
