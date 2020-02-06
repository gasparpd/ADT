package ejercicio2;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.Scanner;

public class CrearEmpleadoXml {
    private static Scanner input;
    private static Document docum;

    public static void main(String args[]) throws IOException {
        input = new Scanner(System.in);

        try {
            DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factoria.newDocumentBuilder();
            docum = builder.newDocument();


            boolean continuar = true;
            while (continuar) {
                System.out.println("Dame el nombre del hotel.");
                String nombre = input.nextLine();
                System.out.println("Dime donde se encuentra el hotel.");
                String prov = input.nextLine();
                System.out.println("Dame el número de estrellas.");
                int estrellas = input.nextInt();
                System.out.println("Dame el número de habitaciones.");
                int numHabit = input.nextInt();
                Element raiz = docum.createElement("hotel"); //nodo empleado
                docum.getDocumentElement().appendChild(raiz);

                CrearElemento("nombre", nombre, raiz, docum);
                CrearElemento("estrellas", Integer.toString(estrellas), raiz, docum);
                CrearElemento("habitaciones", Integer.toString(numHabit), raiz, docum);
                CrearElemento("provincia", prov, raiz, docum);


                System.out.println("Introduce '1' si quiere continuar agregando hoteles, '0' para salir");
                int resp = input.nextInt();
                if (resp == 0)
                    continuar = false;
            }

            generarXML();
        }//fin del for que recorre el fichero
        catch (ParserConfigurationException | TransformerException ex) {
            ex.printStackTrace();
        }


    }

    //Inserci�n de los datos del empleado
    static void CrearElemento(String datoHotel, String valor,
                              Element raiz, Document document) {
        Element elem = document.createElement(datoHotel);
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor
    }

    public static void generarXML() throws TransformerException, IOException {
        TransformerFactory factoria = TransformerFactory.newInstance();
        Transformer transformer = factoria.newTransformer();

        Source source = new DOMSource(docum);
        File file = new File("hoteles.xml");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        Result result = new StreamResult(pw);

        transformer.transform(source, result);

    }
}//fin de la clase

