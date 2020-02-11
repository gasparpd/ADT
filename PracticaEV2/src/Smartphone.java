public class Smartphone {

    private int id_smartphone;
    private int id_marca;
    private String modelo;
    private String p_pantalla;
    private int precio;

    public Smartphone() {
    }

    public Smartphone(int id_smartphone, int id_marca, String modelo, String p_pantalla, int precio) {
        this.id_smartphone = id_smartphone;
        this.id_marca = id_marca;
        this.modelo = modelo;
        this.p_pantalla = p_pantalla;
        this.precio = precio;
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

    public String getP_pantalla() {
        return p_pantalla;
    }

    public void setP_pantalla(String p_pantalla) {
        this.p_pantalla = p_pantalla;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
