package ejercicio1;

import java.io.*;

public class Ejercicio1 {
    public static void main(String[] args) throws IOException {
        File fichero = new File("./src/ejercicio1/ejer1.dat");
        DataInputStream dataIS = new DataInputStream(new FileInputStream(fichero));

        String n;
        float p;
        boolean per;
        char alm;

        try {
            while (true) {
                n = dataIS.readUTF();
                p = dataIS.readFloat();
                per = dataIS.readBoolean();
                alm = dataIS.readChar();
                System.out.printf("Nombre: %s, Precio: %f€, Personalizable: %b, Almacén: %c\n", n, p, per, alm);
            }
        } catch (EOFException eo) {
            System.out.println("Fin del fichero");
        }

        dataIS.close();  //cerrar stream
    }
}
