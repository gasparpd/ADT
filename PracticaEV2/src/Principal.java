import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Principal {
    private static Scanner teclado;

    public static void main(String[] args) {
        teclado = new Scanner(System.in);
        boolean salir;

        do {
            salir = menu();
        } while (!salir);

        System.out.println("-------------- FIN DEL PROGRAMA --------------");
    }

    private static boolean menu() {
        boolean salir = false;

        System.out.println("-------------------- MENÚ -------------------\n" +
                "1 - Crear base de datos.\n" +
                "2 - Crear estructura.\n" +
                "3 - Insertar datos de prueba (script).\n" +
                "4 - Insertar datos en una tabla.\n" +
                "5 - Eliminar base de datos.\n" +
                "6 - Visualizar datos de una tabla.\n" +
                "7 - Modificar datos de una tabla.\n" +
                "8 - Eliminar datos de una tabla.\n" +
                "9 - Procedimientos almacenados.\n" +
                "10 - Generar XML de datos (DOM)\n" +
                "0 - Salir.\n" +
                "---------------------------------------------");
        int res = teclado.nextInt();
        switch (res) {
            case 1:
                crearBaseDatos();
                break;
            case 2:
                crearTablas();
                break;
            case 3:
                datosPrueba();
                break;
            case 4:
                menuInsert();
                break;
            case 5:
                borrarDB();
                break;
            case 6:
                visualizarTabla();
                break;
            case 7:
                modificarDatos();
                break;
            case 8:
                eliminarDatos();
                break;
            case 9:
                procedimientoAlmacenado();
                break;
            case 10:
                generarXMLDOM();
                break;
            case 0:
                salir = true;
                break;
            default:
                System.out.println("Operación no válida.");
        }

        return salir;
    }

    private static void menuInsert() {
        System.out.println("-------------------- MENÚ -------------------\n" +
                "1 - Consultas preparadas.\n" +
                "2 - Insertar datos con DOM (xml).\n" +
                "3 - Insertar datos con SAX (xml).\n" +
                "0 - Salir.\n" +
                "---------------------------------------------");
        int res = teclado.nextInt();

        switch (res) {
            case 1:
                insertConsultaPreparada();
                break;
            case 2:
                //Insertar datos con DOM
                insertXMLDOM();
                break;
            case 3:
                //Insertar datos con SAX
                insertXMLSAX();
                break;
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
        String linea;
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

    private static void crearBaseDatos() {
        File scriptFile = new File("./script/crearbasedatos.sql");
        System.out.println("--------------------------------------------");
        System.out.println("\n\nFichero de consulta : " + scriptFile.getName());
        System.out.println("Convirtiendo el fichero a cadena...");

        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader(scriptFile));


            String linea;
            StringBuilder stringBuilder = new StringBuilder();
            String salto = System.getProperty("line.separator");

            while ((linea = entrada.readLine()) != null) {
                stringBuilder.append(linea);
                stringBuilder.append(salto);
            }

            String consulta = stringBuilder.toString();

            System.out.println(consulta);


            Connection connmysql = DriverManager
                    .getConnection("jdbc:mysql://localhost/?allowMultiQueries=true", "root", "root");
            Statement sents = connmysql.createStatement();
            int res = sents.executeUpdate(consulta);
            System.out.println("Script creado con éxito, res = " + res);
            connmysql.close();
            sents.close();
        } catch (SQLException | IOException e) {
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
        String linea;
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
            String modelo = "", pulgadas = "", nombre_marca = "", year_foundation = "";
            int id_marca = 0, id_fab = 0, matriz = 0, tabla, precio = 0;
            do {
                System.out.println("¿En qué tabla quieres insertar datos?");
                System.out.println("-------------------- MENÚ -------------------\n" +
                        "1 - Tabla Smartphone.\n" +
                        "2 - Tabla Fabricante.\n" +
                        "---------------------------------------------");
                tabla = teclado.nextInt();
                if (tabla == 1) {
                    System.out.println("Introduce ID_MARCA.");
                    id_marca = teclado.nextInt();
                    teclado.nextLine();
                    System.out.println("Introduce MODELO.");
                    modelo = teclado.nextLine();
                    System.out.println("Introduce PULGADAS de PANTALLA.");
                    pulgadas = teclado.nextLine();
                    System.out.println("Introduce el PRECIO.");
                    precio = teclado.nextInt();
                } else if (tabla == 2) {
                    System.out.println("Introduce ID.");
                    id_fab = teclado.nextInt();
                    teclado.nextLine();
                    System.out.println("Introduce NOMBRE.");
                    nombre_marca = teclado.nextLine();
                    System.out.println("Introduce AÑO de FUNDACIÓN.");
                    year_foundation = teclado.nextLine();
                    System.out.println("Introduce MATRIZ.");
                    matriz = teclado.nextInt();
                } else {
                    System.out.println("El número de la tabla introducido (" + tabla + ") no es válido.");
                }
            } while (tabla != 1 && tabla != 2);


            // construir orden INSERT
            String sql_smart = "INSERT INTO `smartphone`(`ID_MARCA`, `MODELO`, `PULGADAS_PANTALLA`, `PRECIO`) VALUES (?, ?, ?, ?)";
            String sql_fab = "INSERT INTO `fabricante`(`ID`, `NOMBRE`, `FUNDACION_YEAR`, `MATRIZ`) VALUES (?, ?, ?, ?)";

            //Hacemos el PreparedStatement para cada opción (fabricante o smartphone)
            PreparedStatement sentencia;
            if (tabla == 1) {
                System.out.println(sql_smart);
                sentencia = conexion.prepareStatement(sql_smart);
                sentencia.setInt(1, id_marca);
                sentencia.setString(2, modelo);
                sentencia.setString(3, pulgadas);
                sentencia.setInt(4, precio);
            } else {
                System.out.println(sql_fab);
                sentencia = conexion.prepareStatement(sql_fab);
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
                System.out.println("Mensaje:    " + e.getMessage());
                System.out.println("SQL estado: " + e.getSQLState());
                System.out.println("Cód error:  " + e.getErrorCode());
            }

            sentencia.close(); // Cerrar Statement
            conexion.close(); // Cerrar conexión

        } catch (ClassNotFoundException | SQLException cn) {
            cn.printStackTrace();
        }
    }

    private static void insertXMLDOM() {
        System.out.println("¿De qué fichero quieres insertar los datos?\n" +
                "-------------------- MENÚ -------------------\n" +
                "1 - Fichero smartphones.xml.\n" +
                "2 - Fichero fabricantes.xml.\n" +
                "NOTA:\n" +
                "Para insertar smartphones debe de haber datos\n" +
                "de fabricantes para que no den errores de clave foránea.\n" +
                "---------------------------------------------");
        int tabla = teclado.nextInt();
        switch (tabla) {
            case 1:
                LecturaDOM.leerXML("smartphones");
                break;
            case 2:
                LecturaDOM.leerXML("fabricantes");
                break;
            default:
                System.out.println("Valor erróneo.");
                break;
        }
    }

    private static void insertXMLSAX() {
        System.out.println("¿De qué fichero quieres insertar los datos?\n" +
                "-------------------- MENÚ -------------------\n" +
                "1 - Fichero smartphones.xml.\n" +
                "2 - Fichero fabricantes.xml.\n" +
                "NOTA:\n" +
                "Para insertar smartphones debe de haber datos\n" +
                "de fabricantes para que no den errores de clave foránea.\n" +
                "---------------------------------------------");
        int tabla = teclado.nextInt();
        switch (tabla) {
            case 1:
                LecturaSAX.leerXML("smartphones");
                break;
            case 2:
                LecturaSAX.leerXML("fabricantes");
                break;
            default:
                System.out.println("Valor erróneo.");
                break;
        }
    }

    public static void modificarDatos() {
        try {
            Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection
                    ("jdbc:mysql://localhost/smartphones", "ejemplo", "ejemplo");

            // Pedir la tabla para modificar sus datos
            teclado.nextLine();
            String nombre_marca = "";
            int id_smart = 0, id_fab = 0, tabla, precio = 0;
            do {
                System.out.println("¿Qué tabla quieres modificar?");
                System.out.println("-------------------- MENÚ -------------------\n" +
                        "1 - Tabla Smartphone.\n" +
                        "2 - Tabla Fabricante.\n" +
                        "---------------------------------------------");
                tabla = teclado.nextInt();
                if (tabla == 1) {
                    System.out.println("Introduce ID_SMARTPHONE.");
                    id_smart = teclado.nextInt();
                    teclado.nextLine();
                    System.out.println("Introduce el PRECIO.");
                    precio = teclado.nextInt();
                } else if (tabla == 2) {
                    System.out.println("Introduce ID.");
                    id_fab = teclado.nextInt();
                    teclado.nextLine();
                    System.out.println("Introduce NOMBRE.");
                    nombre_marca = teclado.nextLine();
                } else {
                    System.out.println("El número de la tabla introducido (" + tabla + ") no es válido.");
                }
            } while (tabla != 1 && tabla != 2);


            // construir orden INSERT
            String sql_smart = "UPDATE `smartphone` SET `PRECIO`= ? WHERE `ID_SMARTPHONE` = ?";
            String sql_fab = "UPDATE `fabricante` SET `NOMBRE`= ? WHERE `ID` = ?";

            //Hacemos el PreparedStatement para cada opción (fabricante o smartphone)
            PreparedStatement sentencia;
            if (tabla == 1) {
                System.out.println(sql_smart);
                sentencia = conexion.prepareStatement(sql_smart);
                sentencia.setInt(1, precio);
                sentencia.setInt(2, id_smart);
            } else {
                System.out.println(sql_fab);
                sentencia = conexion.prepareStatement(sql_fab);
                sentencia.setString(1, nombre_marca);
                sentencia.setInt(2, id_fab);

            }

            try {
                //Ejecutamos la setencia UPDATE y recogemos las filas afectadas
                boolean ex_bool = sentencia.execute();
                if (ex_bool) {
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
                System.out.println("Mensaje:    " + e.getMessage());
                System.out.println("SQL estado: " + e.getSQLState());
                System.out.println("Cód error:  " + e.getErrorCode());
            }

            sentencia.close(); // Cerrar Statement
            conexion.close(); // Cerrar conexión

        } catch (ClassNotFoundException | SQLException cn) {
            cn.printStackTrace();
        }
    }

    public static void eliminarDatos() {
        try {
            Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection
                    ("jdbc:mysql://localhost/smartphones", "ejemplo", "ejemplo");

            // Pedir la tabla para eliminar datos
            int id_smart = 0, id_fab = 0, tabla;
            do {
                System.out.println("¿De qué tabla quieres eliminar datos?");
                System.out.println("-------------------- MENÚ -------------------\n" +
                        "1 - Tabla Smartphone.\n" +
                        "2 - Tabla Fabricante.\n" +
                        "----------------------------------------------");
                tabla = teclado.nextInt();
                if (tabla == 1) {
                    System.out.println("Introduce ID_SMARTPHONE.");
                    id_smart = teclado.nextInt();
                } else if (tabla == 2) {
                    System.out.println("Introduce ID.");
                    id_fab = teclado.nextInt();
                } else {
                    System.out.println("El número de la tabla introducido (" + tabla + ") no es válido.");
                }
            } while (tabla != 1 && tabla != 2);


            // construir orden INSERT
            String sql_smart = "DELETE FROM `smartphone` WHERE `smartphone`.`ID_SMARTPHONE` = ?";
            String sql_fab = "DELETE FROM `fabricante` WHERE `fabricante`.`ID` = ?";

            //Hacemos el PreparedStatement para cada opción (fabricante o smartphone)
            PreparedStatement sentencia;
            if (tabla == 1) {
                System.out.println(sql_smart);
                sentencia = conexion.prepareStatement(sql_smart);
                sentencia.setInt(1, id_smart);
            } else {
                System.out.println(sql_fab);
                sentencia = conexion.prepareStatement(sql_fab);
                sentencia.setInt(1, id_fab);
            }

            try {
                //Ejecutamos la setencia UPDATE y recogemos las filas afectadas
                int filas = sentencia.executeUpdate();
                System.out.printf("Filas afectadas:%d %n", filas);
            } catch (SQLException e) {
                System.out.println("HA OCURRIDO UNA EXCEPCIÓN:");
                System.out.println("Mensaje:    " + e.getMessage());
                System.out.println("SQL estado: " + e.getSQLState());
                System.out.println("Cód error:  " + e.getErrorCode());
            }

            sentencia.close(); // Cerrar Statement
            conexion.close(); // Cerrar conexión

        } catch (ClassNotFoundException | SQLException cn) {
            cn.printStackTrace();
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
        String linea;
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

    private static void generarXMLDOM() {
        System.out.println("¿De qué tabla quieres extraer los datos?\n" +
                "-------------------- MENÚ -------------------\n" +
                "1 - Tabla Smartphone.\n" +
                "2 - Tabla Fabricante.\n" +
                "---------------------------------------------");
        int tabla = teclado.nextInt();
        switch (tabla) {
            case 1:
                EscrituraDOM.main("smartphones");
                break;
            case 2:
                EscrituraDOM.main("fabricantes");
                break;
            default:
                System.out.println("Valor erróneo.");
                break;
        }
    }

    public static void procedimientoAlmacenado() {
        try {
            Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection
                    ("jdbc:mysql://localhost/smartphones", "ejemplo", "ejemplo");

            // Pedir selección de un procedimiento almacenado
            int proc;
            do {
                System.out.println("-------------------- MENÚ -------------------\n" +
                        "1 - Aplicar descuento a los modelos de una marca.\n" +
                        "2 - Devolver información de una marca.\n" +
                        "----------------------------------------------");
                proc = teclado.nextInt();
                if (proc != 1 && proc != 2)
                    System.out.println("Número (" + proc + ") introducido NO válido.");
            } while (proc != 1 && proc != 2);

            String sql;
            CallableStatement llamada = null;

            switch (proc) {
                case 1:
                    // Construir orden de llamada
                    sql = "{ call aplicar_descuento ( ?, ?) }";
                    // Preparar la llamada
                    llamada = conexion.prepareCall(sql);
                    // Dar valor a los argumentos
                    System.out.println("Dame el ID de la marca.");
                    int id_marca = teclado.nextInt();
                    System.out.println("¿Qué descuento (en €) quiere aplicar?");
                    int descuento = teclado.nextInt();
                    llamada.setInt(1, id_marca);
                    llamada.setInt(2, descuento);
                    break;
                case 2:
                    // Construir orden de llamada
                    sql = "{call proc_comprob_marca (?, ?, ?) }";
                    // Preparar la llamada
                    llamada = conexion.prepareCall(sql);
                    // Dar valor a los argumentos de entrada y registrar los de salida
                    System.out.println("Dame el ID de la marca.");
                    int id = teclado.nextInt();
                    llamada.setInt(1, id); // Param entrada
                    llamada.registerOutParameter(2, Types.VARCHAR); //Param OUT nom
                    llamada.registerOutParameter(3, Types.INTEGER); //Param OUT id_m
                    break;
            }
            // Ejecutar procedimiento
            llamada.executeUpdate();

            // Imprimo los mensajes correspondientes a cada procedimiento
            switch (proc) {
                case 1:
                    System.out.println("Descuento aplicado.");
                    break;
                case 2:
                    System.out.printf("ID_Marca: %d\nNombre_Marca: %s\n",
                            llamada.getInt(3), llamada.getString(2));
                    break;
            }

            //Cerramos la conexión y la llamada
            llamada.close(); // Cerrar Statement
            conexion.close(); // Cerrar conexión

        } catch (ClassNotFoundException | SQLException cn) {
            cn.printStackTrace();
        }
    }

    public static void visualizarTabla() {
        try {
            Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection
                    ("jdbc:mysql://localhost/smartphones", "ejemplo", "ejemplo");

            // Pedir la tabla para mostrar sus datos
            int tabla;
            do {
                System.out.println("¿Qué tabla quieres visualizar?");
                System.out.println("-------------------- MENÚ -------------------\n" +
                        "1 - Tabla Smartphone.\n" +
                        "2 - Tabla Fabricante.\n" +
                        "---------------------------------------------");
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
                    if (tabla == 1) {
                        System.out.printf("ID: %d, ID_MARCA: %d, MODELO: %s, PULGADAS: %s'', PRECIO: %d€\n", rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                    } else {
                        System.out.printf("ID: %d, NOMBRE: %s, AÑO DE FUNDACIÓN: %s, ID_MATRIZ: %d\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                    }
                }

            } catch (SQLException e) {
                System.out.println("HA OCURRIDO UNA EXCEPCIÓN:");
                System.out.println("Mensaje:    " + e.getMessage());
                System.out.println("SQL estado: " + e.getSQLState());
                System.out.println("Cód error:  " + e.getErrorCode());
            }

            sentencia.close(); // Cerrar Statement
            conexion.close(); // Cerrar conexión

        } catch (ClassNotFoundException | SQLException cn) {
            cn.printStackTrace();
        }
    }

}