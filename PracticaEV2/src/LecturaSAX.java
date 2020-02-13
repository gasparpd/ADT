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
        File file = new File("./Ficheros/smartphones.xml");
        SAXHandlerSmartphones handler = new SAXHandlerSmartphones();
        saxParser.parse(file, handler);

        ArrayList<Smartphone> smartphones = handler.getSmartphones();

        for (Smartphone s: smartphones){
            System.out.println(s);
        }
    }
}
