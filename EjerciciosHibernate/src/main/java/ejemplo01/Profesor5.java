package ejemplo01;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="Profesor5")
public class Profesor5 implements Serializable{
    @Id
    @Column(name="ape2")
    private int id;
    @Column(name="ape2")
    private String nombre;
    @Column(name="ape2")
    private String ape1;
    @Column(name="ape2")
    private String ape2;
    @OneToMany(mappedBy="profesor",cascade= CascadeType.ALL)
    private Set<CorreoElectronico5> correosElectronicos;

    public Profesor5() {
    }

    public Profesor5(int id, String nombre, String ape1, String ape2) {
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

    public Set<CorreoElectronico5> getCorreosElectronicos() {
        return correosElectronicos;
    }

    public void setCorreosElectronicos(Set<CorreoElectronico5> correosElectronicos) {
        this.correosElectronicos = correosElectronicos;
    }
}