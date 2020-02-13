import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class SAXHandler extends DefaultHandler {

    private ArrayList<Smartphone> smartphones = new ArrayList<>();
    private Smartphone smart;
    private StringBuilder buffer = new StringBuilder();

    public ArrayList<Smartphone> getSmartphones(){
        return smartphones;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        buffer.append(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName){
            case "smartphone":
                smart = new Smartphone();
                smartphones.add(smart);
                break;
            case "marca":
            case "pantalla":
            case "precio":
                buffer.delete(0, buffer.length());
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName){
            case "smartphone":
                break;
            case "marca":
                smart.setMarca(buffer.toString());
                break;
            case "pantalla":
                smart.setPantalla(buffer.toString());
                break;
            case "precio":
                smart.setPrecio(Float.parseFloat(buffer.toString()));
                break;
        }
    }
}
