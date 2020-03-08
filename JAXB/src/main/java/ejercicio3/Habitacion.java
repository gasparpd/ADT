package ejercicio3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Habitacion")
@XmlType(propOrder = {"numero", "camas", "tipo", "exterior"})
public class Habitacion {
    private int numero;
    private boolean exterior;
    private int camas;
    private String tipo;

    public Habitacion() {
    }

    @XmlElement(name = "numero")
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @XmlElement(name = "exterior")
    public boolean isExterior() {
        return exterior;
    }

    public void setExterior(boolean exterior) {
        this.exterior = exterior;
    }

    @XmlElement(name = "camas")
    public int getCamas() {
        return camas;
    }

    public void setCamas(int camas) {
        this.camas = camas;
    }

    @XmlElement(name = "tipo")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
