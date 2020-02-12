import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

public class GeneradorDOM {
    private Document docum;
    private ArrayList<Fabricante> fabricantes;
    private ArrayList<Smartphone> smartphones;

    public GeneradorDOM() throws ParserConfigurationException {
        DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factoria.newDocumentBuilder();
        docum = builder.newDocument();
    }

    public void generarDocument(String clase) {
        if (clase.equalsIgnoreCase("fabricantes")) {
            /* Creamos el nodo raíz (Fabricantes)*/
            Element raiz = docum.createElement(clase);
            docum.appendChild(raiz);

            /*Cargamos el array fabricantes*/
            //cargarArrayFabricantes();
            obtenerDatosTabla("fabricante");

            /* Leemos el array de objetos Fabricante*/
            for (Fabricante fab : fabricantes) {
                Element fabricante = docum.createElement("fabricante");
                raiz.appendChild(fabricante);

                crearElemento(fabricante, "id", Integer.toString(fab.getId()));
                crearElemento(fabricante, "nombre", fab.getNombre());
                crearElemento(fabricante, "foundation_year", fab.getF_year());
                crearElemento(fabricante, "matriz", Integer.toString(fab.getMatriz()));
            }
        }

        if (clase.equalsIgnoreCase("smartphones")) {
            /* Creamos el nodo raíz (smartphones)*/
            Element raiz = docum.createElement(clase);
            docum.appendChild(raiz);

            /*Cargamos el array fabricantes*/
            //cargarArraySmartphones();
            obtenerDatosTabla("smartphone");

            /* Leemos el array de objetos smartphone*/
            for (Smartphone smart : smartphones) {
                Element smartphone = docum.createElement("smartphone");
                raiz.appendChild(smartphone);

                crearElemento(smartphone, "id", Integer.toString(smart.getId_smartphone()));
                crearElemento(smartphone, "id_marca", Integer.toString(smart.getId_marca()));
                crearElemento(smartphone, "modelo", smart.getModelo());
                crearElemento(smartphone, "pulgadas_pantalla", smart.getP_pantalla());
                crearElemento(smartphone, "precio", Integer.toString(smart.getPrecio()));
            }
        }
    }

    public void generarXML(String clase) throws TransformerException, IOException {
        TransformerFactory factoria = TransformerFactory.newInstance();
        Transformer transformer = factoria.newTransformer();

        Source source = new DOMSource(docum);
        File file = new File("./Ficheros/" + clase + ".xml");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        Result result = new StreamResult(pw);

        System.out.println("Fichero: " +clase +".xml escrito.\n" +
                "Ruta: ./Ficheros/" +clase +".xml");

        transformer.transform(source, result);
    }

    public void crearElemento(Element raiz, String campo, String valor) {
        // Si el campo es "ID", lo metemos como atributo, no como textNode
        if (campo.equalsIgnoreCase("id"))
            raiz.setAttribute(campo, valor);
        else {
            Element elem = docum.createElement(campo);
            elem.appendChild(docum.createTextNode(valor));
            raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        }
    }

    public void obtenerDatosTabla(String tabla) {
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
                // Establecemos la conexion con la BD
                Connection conexion = DriverManager.getConnection
                        ("jdbc:mysql://localhost/smartphones", "ejemplo", "ejemplo");

                //Preparamos la sentencia SQL para sacar todos los datos de la tabla pedida
                String sql;
                if (tabla.equalsIgnoreCase("smartphone")) {
                    sql = "SELECT * FROM smartphone";
                    smartphones = new ArrayList<>();
                } else {
                    sql = "SELECT * FROM fabricante";
                    fabricantes = new ArrayList<>();
                }

                //Inicializamos la sentencia
                Statement sentencia = conexion.createStatement();
                try {
                    /*Ejecutamos la sentencia con executeQuery y recorremos el ResulSet que
                    nos devuelve creando objetos y metiéndolos en su array*/

                    ResultSet rs = sentencia.executeQuery(sql);
                    while (rs.next()) {
                        if (tabla.equalsIgnoreCase("smartphone")) {
                            Smartphone smart= new Smartphone();
                            smart.setId_smartphone(rs.getInt(1));
                            smart.setId_marca(rs.getInt(2));
                            smart.setModelo(rs.getString(3));
                            smart.setP_pantalla(rs.getString(4));
                            smart.setPrecio(rs.getInt(5));

                            smartphones.add(smart);
                        } else {
                            Fabricante fab= new Fabricante();
                            fab.setId(rs.getInt(1));
                            fab.setNombre(rs.getString(2));
                            fab.setF_year(rs.getString(3));
                            fab.setMatriz(rs.getInt(4));

                            fabricantes.add(fab);
                        }
                    }

                } catch (SQLException e) {
                    System.out.println("HA OCURRIDO UNA EXCEPCIÓN:");
                    System.out.println("Mensaje:    " + e.getMessage());
                    System.out.println("SQL estado: " + e.getSQLState());
                    System.out.println("Cód error:  " + e.getErrorCode());
                }

                sentencia.close(); // Cerrar Statement
                conexion.close(); // Cerrar conexión

            } catch (ClassNotFoundException | SQLException cn) {
                cn.printStackTrace();
            }
        }
    }

    /*public void cargarArrayFabricantes() {
        fabricantes = new ArrayList<>();

        Fabricante fab = new Fabricante(1, "APPLE", "1976");
        fabricantes.add(fab);
        fab = new Fabricante(2, "SAMSUNG", "1938");
        fabricantes.add(fab);
        fab = new Fabricante(3, "OPPO", "2004");
        fabricantes.add(fab);
        fab = new Fabricante(4, "HUAWEI", "1987");
        fabricantes.add(fab);
        fab = new Fabricante(5, "REALME", "2018", 3);
        fabricantes.add(fab);
        fab = new Fabricante(6, "HONOR", "2013", 4);
        fabricantes.add(fab);
        fab = new Fabricante(7, "XIAOMI", "2010");
        fabricantes.add(fab);
    }

    public void cargarArraySmartphones() {
        smartphones = new ArrayList<>();

        Smartphone smart = new Smartphone(1, 1, "iPhone X", "5.8", 1159);
        smartphones.add(smart);
        smart = new Smartphone(2, 2, "GALAXY S10", "6.1", 909);
        smartphones.add(smart);
        smart = new Smartphone(3, 3, "RENO 2", "6.5", 499);
        smartphones.add(smart);
        smart = new Smartphone(4, 4, "MATE 30 PRO", "6.53", 1099);
        smartphones.add(smart);
        smart = new Smartphone(5, 5, "X2 PRO", "6.5", 399);
        smartphones.add(smart);
        smart = new Smartphone(6, 6, "20 PRO", "6.26", 599);
        smartphones.add(smart);
        smart = new Smartphone(7, 7, "MI NOTE 10", "6.47", 549);
        smartphones.add(smart);
        smart = new Smartphone(8, 7, "MI 9", "6.39", 449);
        smartphones.add(smart);
    }*/
}
