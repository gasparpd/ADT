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
                    "5 - Insertar datos en una tabla.\n" +
                    "6 - Modificar datos de una tabla.\n" +
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
                    visualizarTabla();
                    break;
                case 5:
                    insertConsultaPreparada();
                    break;
                case 6:
                    modificarDatos();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Operación no válida.");
            }
        }
    }

    public static void modificarDatos() {
        try {
            Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection
                    ("jdbc:mysql://localhost/smartphones", "ejemplo", "ejemplo");

            // Pedir la tabla para mostrar sus datos
            teclado.nextLine();
            String precio= "", nombre_marca = "";
            int id_smart = 0, id_fab = 0, tabla = 0;
            do {
                System.out.println("¿Qué tabla quieres modificar?");
                System.out.println("--------------------MENU-------------------\n" +
                        "1 - Tabla Smartphone.\n" +
                        "2 - Tabla Fabricante.\n" +
                        "-------------------------------------------");
                tabla = teclado.nextInt();
                if (tabla == 1) {
                    System.out.println("Introduce ID_SMARTPHONE.");
                    id_smart = teclado.nextInt();
                    teclado.nextLine();
                    System.out.println("Introduce el PRECIO.");
                    precio = teclado.nextLine();
                }
                else if (tabla == 2){
                    System.out.println("Introduce ID.");
                    id_fab = teclado.nextInt();
                    teclado.nextLine();
                    System.out.println("Introduce NOMBRE.");
                    nombre_marca = teclado.nextLine();
                }
                else {
                    System.out.println("El número de la tabla introducido (" +tabla +") no es válido.");
                }
            }while (tabla != 1 && tabla != 2);


            // construir orden INSERT
            String sql_smart = "UPDATE `smartphone` SET `PRECIO`= ? WHERE `ID_SMARTPHONE` = ?";
            String sql_fab = "UPDATE `fabricante` SET `NOMBRE`= ? WHERE `ID` = ?";

            //Hacemos el PreparedStatement para cada opción (fabricante o smartphone)
            PreparedStatement sentencia;
            if (tabla == 1) {
                System.out.println(sql_smart);
                sentencia = conexion.prepareStatement(sql_smart);
                sentencia.setString(1, precio);
                sentencia.setInt(2, id_smart);
            }
            else {
                System.out.println(sql_fab);
                sentencia = conexion.prepareStatement(sql_smart);
                sentencia.setString(1, nombre_marca);
                sentencia.setInt(2, id_fab);

            }

            try {
                //Ejecutamos la setencia UPDATE y recogemos las filas afectadas
                boolean ex_bool = sentencia.execute();
                if(ex_bool){
                    ResultSet rs = sentencia.getResultSet();
                    while (rs.next())
                        System.out.printf("%d, %s %n", rs.getInt(1), rs.getInt(2));
                    rs.close();
                } else {
                    int f = sentencia.getUpdateCount();
                    System.out.printf("Filas afectadas:%d %n", f);
                }
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

    public static void insertConsultaPreparada() {
        try {
            Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection
                    ("jdbc:mysql://localhost/smartphones", "ejemplo", "ejemplo");

            // Pedir la tabla para mostrar sus datos
            teclado.nextLine();
            String tabla, modelo="", pulgadas="", precio="", nombre_marca="", year_foundation="";
            int id_marca = 0, id_fab = 0, matriz = 0;
            do {
                System.out.println("Dame el nombre de la tabla en la que quieres introducir los datos.\n(smartphone / fabricante).");
                tabla = teclado.nextLine();
                if (tabla.equalsIgnoreCase("smartphone")) {
                    System.out.println("Introduce ID_MARCA.");
                    id_marca = teclado.nextInt();
                    teclado.nextLine();
                    System.out.println("Introduce MODELO.");
                    modelo = teclado.nextLine();
                    System.out.println("Introduce PULGADAS de PANTALLA.");
                    pulgadas = teclado.nextLine();
                    System.out.println("Introduce el PRECIO.");
                    precio = teclado.nextLine();
                }
                else if (tabla.equalsIgnoreCase("fabricante")){
                    System.out.println("Introduce ID.");
                    id_fab = teclado.nextInt();
                    teclado.nextLine();
                    System.out.println("Introduce NOMBRE.");
                    nombre_marca = teclado.nextLine();
                    System.out.println("Introduce AÑO de FUNDACIÓN.");
                    year_foundation = teclado.nextLine();
                    System.out.println("Introduce MATRIZ.");
                    matriz = teclado.nextInt();
                }
                else {
                    System.out.println("El nombre de la tabla introducido (" +tabla +") no es válido.");
                }
            }while (!tabla.equalsIgnoreCase("smartphone") && !tabla.equalsIgnoreCase("fabricante"));


            // construir orden INSERT
            String sql_smart = "INSERT INTO `smartphone`(`ID_MARCA`, `MODELO`, `PULGADAS_PANTALLA`, `PRECIO`) VALUES (?, ?, ?, ?)";
            String sql_fab = "INSERT INTO `fabricante`(`ID`, `NOMBRE`, `FUNDACION_YEAR`, `MATRIZ`) VALUES (?, ?, ?, ?)";

            //Hacemos el PreparedStatement para cada opción (fabricante o smartphone)
            PreparedStatement sentencia;
            if (tabla.equalsIgnoreCase("smartphone")) {
                System.out.println(sql_smart);
                sentencia = conexion.prepareStatement(sql_smart);
                sentencia.setInt(1, id_marca);
                sentencia.setString(2, modelo);
                sentencia.setString(3, pulgadas);
                sentencia.setString(4, precio);
            }
            else {
                System.out.println(sql_fab);
                sentencia = conexion.prepareStatement(sql_smart);
                sentencia.setInt(1, id_fab);
                sentencia.setString(2, nombre_marca);
                sentencia.setString(3, year_foundation);
                sentencia.setInt(4, matriz);
            }

            try {
                //Ejecutamos la setencia INSERT y recogemos las filas afectadas
                int filas_afectadas = sentencia.executeUpdate();
                System.out.println("Filas afectadas: " + filas_afectadas);
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

    public static void visualizarTabla() {
        try {
            Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection
                    ("jdbc:mysql://localhost/smartphones", "ejemplo", "ejemplo");

            // Pedir la tabla para mostrar sus datos
            int tabla = 0;
            do {
                System.out.println("¿Qué tabla quieres visualizar?");
                System.out.println("--------------------MENU-------------------\n" +
                        "1 - Tabla Smartphone.\n" +
                        "2 - Tabla Fabricante.\n" +
                        "-------------------------------------------");
                tabla = teclado.nextInt();
            } while (tabla != 1 && tabla != 2);

            //Preparamos la sentencia SQL para sacar todos los datos de la tabla pedida
            String sql;
            if (tabla == 1) {
                sql = "SELECT * FROM smartphone";
            } else {
                sql = "SELECT * FROM fabricante";
            }
            System.out.println(sql);

            //Inicializamos la sentencia
            Statement sentencia = conexion.createStatement();
            try {
                //Ejecutamos la sentencia con executeQuery y recoremos el ResulSet que
                //nos devuelve

                ResultSet rs = sentencia.executeQuery(sql);
                while (rs.next()) {
                    if (tabla == 1){
                        System.out.printf("%d, %d, %s, %s, %s\n", rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
                    } else {
                        System.out.printf("%d, %s, %s, %d\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                    }
                }

            }catch (SQLException e) {
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