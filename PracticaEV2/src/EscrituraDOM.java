import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class EscrituraDOM {
    public static void main(String tabla) {
        try {
            /*Recordar que la tabla que nos pasan debe acabar en "S"
              (fabricantes, smartphones)*/
            GeneradorDOM generadorDOM = new GeneradorDOM();
            generadorDOM.generarDocument(tabla);
            generadorDOM.generarXML(tabla);
        } catch (TransformerException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
