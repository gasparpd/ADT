import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class LecturaDOM {
    private static ArrayList<Smartphone> smartphones;
    private static ArrayList<Fabricante> fabricantes;

    public static void leerXML(String nombreFichero) {

        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("./Ficheros/" + nombreFichero + ".xml"));
            document.getDocumentElement().normalize();

            //crea una lista con todos los nodos FABRICANTE o SMARTPHONE
            NodeList fabsNodeList = null, smartNodeList = null;
            if (nombreFichero.equalsIgnoreCase("fabricantes")) {
                fabsNodeList = document.getElementsByTagName("fabricante");
            } else {
                smartNodeList = document.getElementsByTagName("smartphone");
            }

            /*Recorremos los NodeList obtenidos y cargamos los arrays*/
            if (nombreFichero.equalsIgnoreCase("fabricantes")) {
                fabricantes = new ArrayList<>();

                for (int c = 0; c < fabsNodeList.getLength(); c++) {
                    Node fab = fabsNodeList.item(c);

                    if (fab.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) fab;
                        int id = Integer.parseInt(element.getAttribute("id"));
                        String nombre = element.getElementsByTagName("nombre").
                                item(0).getTextContent();
                        String f_year = element.getElementsByTagName("foundation_year").
                                item(0).getTextContent();
                        int matriz = Integer.parseInt(element.getElementsByTagName("matriz").
                                item(0).getTextContent());
                        Fabricante fabricante = new Fabricante(id, nombre, f_year, matriz);
                        fabricantes.add(fabricante);
                    }
                }
            } else {
                smartphones = new ArrayList<>();

                for (int c = 0; c < smartNodeList.getLength(); c++) {
                    Node smart = smartNodeList.item(c);

                    if (smart.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) smart;
                        int id = Integer.parseInt(element.getAttribute("id"));
                        int id_marca = Integer.parseInt(element.getElementsByTagName("id_marca").
                                item(0).getTextContent());
                        String modelo = element.getElementsByTagName("modelo").
                                item(0).getTextContent();
                        String pulgadas = element.getElementsByTagName("pulgadas_pantalla").
                                item(0).getTextContent();
                        int precio = Integer.parseInt(element.getElementsByTagName("precio").
                                item(0).getTextContent());
                        Smartphone smartphone = new Smartphone(id, id_marca, modelo, pulgadas, precio);
                        smartphones.add(smartphone);
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        insertarEnDB();
    }

    private static void insertarEnDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection
                    ("jdbc:mysql://localhost/smartphones", "ejemplo", "ejemplo");

            // construir orden INSERT
            String sql_smart = "INSERT INTO `smartphone`(`ID_MARCA`, `MODELO`, `PULGADAS_PANTALLA`, `PRECIO`) VALUES (?, ?, ?, ?)";
            String sql_fab = "INSERT INTO `fabricante`(`ID`, `NOMBRE`, `FUNDACION_YEAR`, `MATRIZ`) VALUES (?, ?, ?, ?)";

            //Hacemos el PreparedStatement para cada opción (fabricante o smartphone)
            //Bucle para insertar las filas que obtenemos del XML (almacenadas ahora en un array)
            PreparedStatement sentencia;
            if (smartphones != null) {
                for (Smartphone smart: smartphones) {
                    sentencia = conexion.prepareStatement(sql_smart);
                    sentencia.setInt(1, smart.getId_marca());
                    sentencia.setString(2, smart.getModelo());
                    sentencia.setString(3, smart.getP_pantalla());
                    sentencia.setInt(4, smart.getPrecio());
                    sentencia.executeUpdate();
                }
            } else {
                for (Fabricante fab: fabricantes) {
                    sentencia = conexion.prepareStatement(sql_fab);
                    sentencia.setInt(1, fab.getId());
                    sentencia.setString(2, fab.getNombre());
                    sentencia.setString(3, fab.getF_year());
                    sentencia.setInt(4, fab.getMatriz());
                    sentencia.executeUpdate();
                }
            }

            System.out.println("Datos insertados.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}