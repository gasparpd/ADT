public class Fabricante {

    private int id;
    private String nombre;
    private String f_year;
    private int matriz;

    public Fabricante() {
    }

    public Fabricante(int id, String nombre, String f_year, int matriz) {
        this.id = id;
        this.nombre = nombre;
        this.f_year = f_year;
        this.matriz = matriz;
    }

    public Fabricante(int id, String nombre, String f_year) {
        this.id = id;
        this.nombre = nombre;
        this.f_year = f_year;
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

    public String getF_year() {
        return f_year;
    }

    public void setF_year(String f_year) {
        this.f_year = f_year;
    }

    public int getMatriz() {
        return matriz;
    }

    public void setMatriz(int matriz) {
        this.matriz = matriz;
    }
}
