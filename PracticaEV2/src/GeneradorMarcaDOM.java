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

public class GeneradorMarcaDOM {
    private Document docum;

    public GeneradorMarcaDOM() throws ParserConfigurationException {
        DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factoria.newDocumentBuilder();
        docum = builder.newDocument();

    }

    public void generarDocument(){
        Element element = docum.createElement("marcas");
        docum.appendChild(element);

        Element marca = docum.createElement("marca");
        element.appendChild(marca);
        marca.setAttribute("codigo", "1");

        Element nombre = docum.createElement("nombre");
        nombre.appendChild(docum.createTextNode("Teclado"));
        marca.appendChild(nombre);

    }

    public void generarXML() throws TransformerException, IOException {
        TransformerFactory factoria = TransformerFactory.newInstance();
        Transformer transformer = factoria.newTransformer();

        Source source = new DOMSource(docum);
        File file = new File("productos.xml");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        Result result = new StreamResult(pw);

        transformer.transform(source, result);

    }
}
