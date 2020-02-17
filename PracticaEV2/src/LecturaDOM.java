import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class LecturaDOM {
    private static ArrayList<Smartphone> smartphones;
    private static ArrayList<Fabricante> fabricantes;

    public static void main(String tabla) {

        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("./Ficheros/" + tabla + ".xml"));
            document.getDocumentElement().normalize();

            /*System.out.printf("Elemento raiz: %s %n",
                    document.getDocumentElement().getNodeName());*/

            //crea una lista con todos los nodos FABRICANTE o SMARTPHONE
            NodeList fabsNodeList = null, smartNodeList = null;
            if (tabla.equalsIgnoreCase("fabricantes")) {
                fabsNodeList = document.getElementsByTagName("fabricante");
            } else {
                smartNodeList = document.getElementsByTagName("smartphone");
            }

            /*Recorremos los NodeList obtenidos y cargamos los arrays*/
            if (tabla.equalsIgnoreCase("fabricantes")) {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}