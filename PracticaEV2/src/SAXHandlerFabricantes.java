import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class SAXHandlerFabricantes extends DefaultHandler {

    private ArrayList<Fabricante> fabricantes = new ArrayList<>();
    private Fabricante fab;
    private StringBuilder buffer = new StringBuilder();

    public ArrayList<Fabricante> getFabricantes(){
        return fabricantes;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        buffer.append(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName){
            case "fabricante":
                fab = new Fabricante();
                fabricantes.add(fab);
                fab.setId(Integer.parseInt(attributes.getValue(0)));
                break;
            case "nombre":
            case "foundation_year":
            case "matriz":
                buffer.delete(0, buffer.length());
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName){
            case "nombre":
                fab.setNombre(buffer.toString());
                break;
            case "foundation_year":
                fab.setF_year(buffer.toString());
                break;
            case "matriz":
                fab.setMatriz(Integer.parseInt(buffer.toString()));
                break;
        }
    }
}
