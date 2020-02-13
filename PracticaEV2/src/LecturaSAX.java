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

    public static void LeerXML(String tabla) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        File file = new File("./Ficheros/" +tabla +".xml");
        if (tabla.equalsIgnoreCase("fabricantes")) {
            SAXHandlerFabricantes handler = new SAXHandlerFabricantes();
            saxParser.parse(file, handler);
            fabricantes = handler.getFabricantes();
        } else {
            SAXHandlerSmartphones handler = new SAXHandlerSmartphones();
            saxParser.parse(file, handler);
            smartphones = handler.getSmartphones();
        }

        /*for (Fabricante s: fabricantes){
            System.out.println(s);
        }*/
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
            PreparedStatement sentencia;
            /*if (!smartphones.isEmpty()) {
                System.out.println(sql_smart);
                sentencia = conexion.prepareStatement(sql_smart);
                sentencia.setInt(1, id_marca);
                sentencia.setString(2, modelo);
                sentencia.setString(3, pulgadas);
                sentencia.setInt(4, precio);
            } else {
                System.out.println(sql_fab);
                sentencia = conexion.prepareStatement(sql_smart);
                sentencia.setInt(1, id_fab);
                sentencia.setString(2, nombre_marca);
                sentencia.setString(3, year_foundation);
                sentencia.setInt(4, matriz);
            }*/

            //Ejecutamos la setencia INSERT y recogemos las filas afectadas
            //int filas_afectadas = sentencia.executeUpdate();
            //System.out.println("Filas afectadas: " + filas_afectadas);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
