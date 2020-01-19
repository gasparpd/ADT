import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Principal {
    static Scanner teclado;

    public static void main(String[]args){
        teclado = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.println("--------------------MENU-------------------\n" +
                    "1 - Crea tablas.\n" +
                    "2 - Insertar datos de prueba.\n" +
                    "3 - Eliminar base de datos.\n" +
                    "4 - Visualizar datos de una tabla.\n" +
                    "0 - Salir.\n" +
                    "-------------------------------------------");
            int res = teclado.nextInt();
            switch (res){
                case 1:
                    crearTablas();
                    break;
                case 2:
                    datosPrueba();
                    break;
                case 3:
                    borrarDB();
                    break;
                case 4:
                    consultaPreparada();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Operación no válida.");
            }
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
    public static void borrarDB() {
        File script = new File("./script/borrarbasedatos.sql");
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
    public static void consultaPreparada() {
        try {
            Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection
                    ("jdbc:mysql://localhost/smartphones", "ejemplo", "ejemplo");

            // Pedir la tabla para mostrar sus datos
            teclado.nextLine();
            String tabla = "";
            do {
                System.out.println("Introduce la el nombre de la tabla de la cual quieres sacar los datos.\n(smartphone / fabricante)");
                tabla = teclado.nextLine();
            }while (tabla.equalsIgnoreCase("smartphone") || tabla.equalsIgnoreCase("fabricante"));


            // construir orden SELECT
            String sql = "SELECT * FROM ?";

            System.out.println(sql);
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, tabla);

            int filas;
            try {
                filas = sentencia.executeUpdate();
                System.out.println("Filas afectadas: " + filas);
            } catch (SQLException e) {
                System.out.println("HA OCURRIDO UNA EXCEPCIÓN:");
                System.out.println("Mensaje:    "+ e.getMessage());
                System.out.println("SQL estado: "+ e.getSQLState());
                System.out.println("Cód error:  "+ e.getErrorCode());
            }

            sentencia.close(); // Cerrar Statement
            conexion.close(); // Cerrar conexión

        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
