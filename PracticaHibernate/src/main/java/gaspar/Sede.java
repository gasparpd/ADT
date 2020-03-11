package gaspar;

import java.io.Serializable;

public class Sede implements Serializable {
    private int id;
    private String pais;
    private String localidad;
    private String cpostal;

    public Sede(int id, String pais, String localidad, String cpostal) {
        this.id = id;
        this.pais = pais;
        this.localidad = localidad;
        this.cpostal = cpostal;
    }

    public Sede() {
    }

    @Override
    public String toString() {
        return "Sede{" +
                "id=" + id +
                ", pais='" + pais + '\'' +
                ", localidad='" + localidad + '\'' +
                ", cpostal='" + cpostal + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCpostal() {
        return cpostal;
    }

    public void setCpostal(String cpostal) {
        this.cpostal = cpostal;
    }
}
