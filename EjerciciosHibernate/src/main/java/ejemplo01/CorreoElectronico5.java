package ejemplo01;

import java.io.Serializable;

public class CorreoElectronico5 implements Serializable {
    private int idCorreo;
    private String direccionCorreo;
    private Profesor5 profesor;

    public CorreoElectronico5(){
    }

    public CorreoElectronico5(int idCorreo, String direccionCorreo, Profesor5 profesor){
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
