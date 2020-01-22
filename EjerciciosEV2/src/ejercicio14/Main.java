package ejercicio14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String [] args){
        try {
            Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection
                    ("jdbc:mysql://localhost/ejemplo", "ejemplo", "ejemplo");

            // recuperar argumentos de main
            String dep_no = args[0]; // departamento

            // construir orden INSERT
            String sql = " SELECT apellido, salario, oficio FROM `empleados` WHERE dept_no = ?";

            System.out.println(sql);
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, Integer.parseInt(dep_no));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
