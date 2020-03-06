package ejemplo01;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "correoelectronico5")
public class CorreoElectronico6 implements Serializable {
    @Id
    @Column(name = "idCorreo")
    private int idCorreo;
    @Column(name = "direccionCorreo")
    private String direccionCorreo;
    @ManyToOne
    @JoinColumn(name="idProfesor")
    private Profesor5 profesor;

    public CorreoElectronico6(){
    }

    public CorreoElectronico6(int idCorreo, String direccionCorreo, Profesor5 profesor){
        this.idCorreo=idCorreo;
        this.direccionCorreo=direccionCorreo;
        this.profesor=profesor;
    }

    public int getIdCorreo() {
        return idCorreo;
    }

    public void setIdCorreo(int idCorreo) {
        this.idCorreo = idCorreo;
    }

    public String getDireccionCorreo() {
        return direccionCorreo;
    }

    public void setDireccionCorreo(String direccionCorreo) {
        this.direccionCorreo = direccionCorreo;
    }

    public Profesor5 getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor5 profesor) {
        this.profesor = profesor;
    }
}
