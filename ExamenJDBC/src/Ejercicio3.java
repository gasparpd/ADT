import java.sql.*;
import java.util.Scanner;

public class Ejercicio3 {
    private static Scanner teclado;
    public static void main(String[] args) {
        teclado = new Scanner(System.in);
        try {
            Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection
                    ("jdbc:mysql://localhost/hospitales", "ejemplo", "ejemplo");

            // Pedir selección de un procedimiento almacenado

            String sql;
            CallableStatement llamada = null;

            // Construir orden de llamada
            sql = "{ call datosDoctor (?, ?, ?, ?) }";
            // Preparar la llamada
            llamada = conexion.prepareCall(sql);
            System.out.println("Dame el ID del doctor.");
            int numDoctor = teclado.nextInt();

            llamada.setInt(1, numDoctor);
            llamada.registerOutParameter(2, Types.VARCHAR);
            llamada.registerOutParameter(3, Types.VARCHAR);
            llamada.registerOutParameter(4, Types.VARCHAR);

            // Ejecutar procedimiento
            llamada.executeUpdate();

            System.out.printf("Apellidos: %s\nEspecialidad: %s\nNombre Hospital: %s\n",
                    llamada.getString(2), llamada.getString(3), llamada.getString(4));

            //Cerramos la conexión y la llamada
            llamada.close(); // Cerrar Statement
            conexion.close(); // Cerrar conexión

        } catch (ClassNotFoundException | SQLException cn) {
            cn.printStackTrace();
        }
    }
}
