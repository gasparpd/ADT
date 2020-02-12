import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class EscrituraDOM {
    public static void main(String [] args) throws ParserConfigurationException, TransformerException, IOException {
        String claseFab = "fabricantes";
        String claseSmart = "smarphones";
        GeneradorDOM generadorDOM = new GeneradorDOM();
        generadorDOM.generarDocument(claseFab);
        generadorDOM.generarXML(claseFab);
    }
}
