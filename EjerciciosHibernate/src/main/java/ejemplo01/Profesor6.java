package ejemplo01;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="profesor6")
public class Profesor6 implements Serializable{
    @Id
    @Column(name="id")
    private int id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="ape1")
    private String ape1;
    @Column(name="ape2")
    private String ape2;
    @OneToMany(mappedBy="profesor",cascade= CascadeType.ALL)
    private List<CorreoElectronico6> correosElectronicos;

    public Profesor6() {
    }

    public Profesor6(int id, String nombre, String ape1, String ape2) {
        this.id = id;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
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

    public String getApe1() {
        return ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public String getApe2() {
        return ape2;
    }

    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    public List<CorreoElectronico6> getCorreosElectronicos() {
        return correosElectronicos;
    }

    public void setCorreosElectronicos(List<CorreoElectronico6> correosElectronicos) {
        this.correosElectronicos = correosElectronicos;
    }
}