package ejemplo01;

import java.io.Serializable;

public class CorreoElectronico5 implements Serializable {
    private int idCorreo;
    private String direccionCorreo;
    private Profesor profesor;

    public CorreoElectronico5(){
    }

    public CorreoElectronico5(int idCorreo, String direccionCorreo, Profesor profesor){
        this.idCorreo=idCorreo;
        this.direccionCorreo=direccionCorreo;
        this.profesor=profesor;
    }
}
