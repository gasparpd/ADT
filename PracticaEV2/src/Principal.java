import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Principal {
    public static void main(String[]args){
        Scanner teclado = new Scanner(System.in);
        System.out.println("--------------------MENU-------------------\n" +
                "1 - Crea tablas\n" +
                "2 - Insertar datos de prueba\n" +
                "-------------------------------------------");
        int res = teclado.nextInt();
        if(res == 1){
            crearTablas();
        } else if (res == 2){
            datosPrueba();
        }
    }

    public static void crearTablas() {
        File scriptFile = new File("./script/smartphones(estructura).sql");
        System.out.println("--------------------------------------------");
        System.out.println("\n\nFichero de consulta : " + scriptFile.getName());
        System.out.println("Convirtiendo el fichero a cadena...");

        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader(scriptFile));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR NO ENCUENTRA el fichero: " + e.getMessage());
            e.printStackTrace();
        }
        String linea = null;
        StringBuilder stringBuilder = new StringBuilder();
        String salto = System.getProperty("line.separator");
        try {
            while ((linea = entrada.readLine()) != null) {
                stringBuilder.append(linea);
                stringBuilder.append(salto);
            }
        } catch (IOException e) {
            System.out.println("ERROR de E/S, al operar con el fichero: " + e.getMessage());
            e.printStackTrace();
        }
        String consulta = stringBuilder.toString();

        System.out.println(consulta);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR En el Driver: " + e.getMessage());
            e.printStackTrace();
        }
        try {
            Connection connmysql = DriverManager
                    .getConnection("jdbc:mysql://localhost/smartphones?allowMultiQueries=true", "ejemplo", "ejemplo");
            Statement sents = connmysql.createStatement();
            int res = sents.executeUpdate(consulta);
            System.out.println("Script creado con éxito, res = " + res);
            connmysql.close();
            sents.close();
        } catch (SQLException e) {
            System.out.println("ERROR AL EJECUTAR EL SCRIPT: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void datosPrueba() {
        File script = new File("./script/smartphones(datos-prueba).sql");
        System.out.println("--------------------------------------------");
        System.out.println("\n\nFichero de consulta : " + script.getName());
        System.out.println("Convirtiendo el fichero a cadena...");

        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader(script));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR NO ENCUENTRA el fichero: " + e.getMessage());
            e.printStackTrace();
        }
        String linea = null;
        StringBuilder stringBuilder = new StringBuilder();
        String salto = System.getProperty("line.separator");
        try {
            while ((linea = entrada.readLine()) != null) {
                stringBuilder.append(linea);
                stringBuilder.append(salto);
            }
        } catch (IOException e) {
            System.out.println("ERROR de E/S, al operar con el fichero: " + e.getMessage());
            e.printStackTrace();
        }
        String consulta = stringBuilder.toString();

        System.out.println(consulta);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR En el Driver: " + e.getMessage());
            e.printStackTrace();
        }
        try {
            Connection connmysql = DriverManager
                    .getConnection("jdbc:mysql://localhost/smartphones?allowMultiQueries=true", "ejemplo", "ejemplo");
            Statement sents = connmysql.createStatement();
            int res = sents.executeUpdate(consulta);
            System.out.println("Script creado con éxito, res = " + res);
            connmysql.close();
            sents.close();
        } catch (SQLException e) {
            System.out.println("ERROR AL EJECUTAR EL SCRIPT: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
