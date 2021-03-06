package gaspar;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "fabricante")
public class FabricanteSmartD implements Serializable {

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
    private Set<SmartphoneD> smartphones;

    public FabricanteSmartD(int id, String nombre, String f_year, Integer matriz) {
        this.id = id;
        this.nombre = nombre;
        this.f_year = f_year;
        this.matriz = matriz;
    }

    public FabricanteSmartD(int id, String nombre, String f_year) {
        this.id = id;
        this.nombre = nombre;
        this.f_year = f_year;
    }

    public FabricanteSmartD() {
    }

    @Override
    public String toString() {
        return "FabricanteSmart{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", f_year='" + f_year + '\'' +
                ", matriz=" + matriz +
                '}';
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

    public Set<SmartphoneD> getSmartphones() {
        return smartphones;
    }

    public void setSmartphones(Set<SmartphoneD> smartphones) {
        this.smartphones = smartphones;
    }
}
