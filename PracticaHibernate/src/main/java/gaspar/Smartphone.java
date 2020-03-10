package gaspar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "smartphone")
public class Smartphone implements Serializable {

    @Id
    @Column(name = "ID_SMARTPHONE")
    private int id_smartphone;

    @Column (name = "ID_MARCA")
    private int id_marca;

    @Column (name = "MODELO")
    private String modelo;

    @Column (name = "PULGADAS_PANTALLA")
    private String pulgadas;

    @Column (name = "PRECIO")
    private int precio;

    public Smartphone(int id, int id_marca, String modelo, String pulgadas, int precio) {
        this.id_smartphone = id;
        this.id_marca = id_marca;
        this.modelo = modelo;
        this.pulgadas = pulgadas;
        this.precio = precio;
    }

    public Smartphone() {
    }

    public int getId_smartphone() {
        return id_smartphone;
    }

    public void setId_smartphone(int id_smartphone) {
        this.id_smartphone = id_smartphone;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(String pulgadas) {
        this.pulgadas = pulgadas;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
