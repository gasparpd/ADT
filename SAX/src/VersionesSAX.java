import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VersionesSAX {

    public static void main(String [] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        File file = new File("./Ficheros/Smartphones.xml");
        SAXHandler handler = new SAXHandler();
        saxParser.parse(file, handler);

        ArrayList<Smartphone> smartphones = handler.getSmartphones();

        for (Smartphone s: smartphones){
            System.out.println(s);
        }
    }
}
