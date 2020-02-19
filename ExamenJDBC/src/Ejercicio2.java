import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ejercicio2 {
    private static Scanner teclado;

    public static void main(String[] args) {
        teclado = new Scanner(System.in);
        String consulta = "UPDATE Doctor set Especialidad='Urología' " +
                "WHERE Especialidad " +
                "LIKE 'Psiquiatría' AND Hospital_cod=22";
        String consultaPreparada = "UPDATE Doctor set Especialidad=? " +
                "WHERE Especialidad " +
                "LIKE ? AND Hospital_cod=?";

        try {
            Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection
                    ("jdbc:mysql://localhost/hospitales", "ejemplo", "ejemplo");

            /*String setEsp = "", likeEsp = "";
            int hosp_cod = 0;
            System.out.println("Introduce la especialidad nueva.");
            setEsp = teclado.nextLine();
            System.out.println("Introduce la especialidad vieja.");
            likeEsp = teclado.nextLine();
            System.out.println("Introduce el código del hospital.");
            hosp_cod = teclado.nextInt();*/

            //Hacemos el PreparedStatement para cada opción (fabricante o smartphone)
            PreparedStatement sentencia;
            System.out.println(consultaPreparada);
            sentencia = conexion.prepareStatement(consultaPreparada);
            sentencia.setString(1, args[0]);
            sentencia.setString(2, args[1]);
            sentencia.setInt(3, Integer.valueOf(args[2]));

            //Ejecutamos la setencia INSERT y recogemos las filas afectadas
            int filas_afectadas = sentencia.executeUpdate();
            System.out.println("Filas afectadas: " + filas_afectadas);

            sentencia.close(); // Cerrar Statement
            conexion.close(); // Cerrar conexión
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
