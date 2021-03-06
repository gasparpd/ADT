import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class SAXHandlerSmartphones extends DefaultHandler {

    private ArrayList<Smartphone> smartphones = new ArrayList<>();
    private Smartphone smart;
    private StringBuilder buffer = new StringBuilder();

    public ArrayList<Smartphone> getSmartphones() {
        return smartphones;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        buffer.append(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case "smartphone":
                smart = new Smartphone();
                smartphones.add(smart);
                smart.setId_smartphone(Integer.parseInt(attributes.getValue(0)));
                break;
            case "id_marca":
            case "modelo":
            case "pulgadas_pantalla":
            case "precio":
                buffer.delete(0, buffer.length());
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "id_marca":
                smart.setId_marca(Integer.parseInt(buffer.toString()));
                break;
            case "modelo":
                smart.setModelo(buffer.toString());
                break;
            case "pulgadas_pantalla":
                smart.setP_pantalla(buffer.toString());
                break;
            case "precio":
                smart.setPrecio(Integer.parseInt(buffer.toString()));
                break;
        }
    }
}
