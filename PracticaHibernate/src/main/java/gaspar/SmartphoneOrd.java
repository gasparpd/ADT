package gaspar;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "smartphone_order")
public class SmartphoneOrd implements Serializable {

    @Id
    @Column(name = "ID_SMARTPHONE")
    private int id_smartphone;

    @ManyToOne
    @JoinColumn(name = "ID_MARCA")
    private FabricanteSmart marca;

    @Column(name = "MODELO")
    private String modelo;

    @Column(name = "PULGADAS_PANTALLA")
    private String pulgadas;

    @Column(name = "PRECIO")
    private int precio;

    public SmartphoneOrd() {
    }

    public SmartphoneOrd(int id_smartphone, FabricanteSmart marca, String modelo, String pulgadas, int precio) {
        this.id_smartphone = id_smartphone;
        this.marca = marca;
        this.modelo = modelo;
        this.pulgadas = pulgadas;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Smartphone{" +
                "id_smartphone=" + id_smartphone +
                ", modelo='" + modelo + '\'' +
                ", pulgadas='" + pulgadas + '\'' +
                ", precio=" + precio +
                '}';
    }

    public int getId_smartphone() {
        return id_smartphone;
    }

    public void setId_smartphone(int id_smartphone) {
        this.id_smartphone = id_smartphone;
    }

    public FabricanteSmart getMarca() {
        return marca;
    }

    public void setMarca(FabricanteSmart marca) {
        this.marca = marca;
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
