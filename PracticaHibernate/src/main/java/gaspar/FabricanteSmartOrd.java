package gaspar;

import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "fabricante")
public class FabricanteSmartOrd implements Serializable {

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
    @IndexColumn(name = "ID_X")
    private List<SmartphoneOrd> smartphones;

    public FabricanteSmartOrd(int id, String nombre, String f_year, Integer matriz) {
        this.id = id;
        this.nombre = nombre;
        this.f_year = f_year;
        this.matriz = matriz;
    }

    public FabricanteSmartOrd(int id, String nombre, String f_year) {
        this.id = id;
        this.nombre = nombre;
        this.f_year = f_year;
    }

    public FabricanteSmartOrd() {
    }

    @Override
    public String toString() {
        return "Fabricante{" +
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

    public List<SmartphoneOrd> getSmartphones() {
        return smartphones;
    }

    public void setSmartphones(List<SmartphoneOrd> smartphones) {
        this.smartphones = smartphones;
    }
}
