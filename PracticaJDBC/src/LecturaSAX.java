import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class LecturaSAX {

    private static ArrayList<Fabricante> fabricantes;
    private static ArrayList<Smartphone> smartphones;

    public static void leerXML(String tabla) {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            File file = new File("./Ficheros/" + tabla + ".xml");
            if (tabla.equalsIgnoreCase("fabricantes")) {
                SAXHandlerFabricantes handler = new SAXHandlerFabricantes();
                saxParser.parse(file, handler);
                fabricantes = handler.getFabricantes();
            } else {
                SAXHandlerSmartphones handler = new SAXHandlerSmartphones();
                saxParser.parse(file, handler);
                smartphones = handler.getSmartphones();
            }
        /*Imprimir objetos
        for (Fabricante s: fabricantes){
            System.out.println(s);
        }*/

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        insertarDatosEnBD();
    }

    public static void insertarDatosEnBD() {
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
