import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class EscrituraDOM {
    public static void main(String [] args) throws ParserConfigurationException, TransformerException, IOException {
        GeneradorMarcaDOM generadorDOM = new GeneradorMarcaDOM();
        generadorDOM.generarDocument();
        generadorDOM.generarXML();
    }
}
