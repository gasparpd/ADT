import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LecturaSAX {

    public static void main(String [] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        File file = new File("./Ficheros/fabricantes.xml");
        SAXHandlerFabricantes handler = new SAXHandlerFabricantes();
        saxParser.parse(file, handler);

        ArrayList<Fabricante> fabricantes = handler.getFabricantes();

        for (Fabricante s: fabricantes){
            System.out.println(s);
        }
    }
}
