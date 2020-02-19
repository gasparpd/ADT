import java.sql.*;

public class Ejercicio1 {
    public static void main (String [] args) {
        String cons1, cons2;
        cons1 = "SELECT D.apellido, D.Especialidad, H.Nombre, H.Direccion " +
                "FROM Doctor as D, Hospital as H " +
                "WHERE D.Hospital_cod=H.Hospital_cod " +
                "ORDER BY H.nombre, D.Apellido";
        cons2 = "UPDATE Hospital SET Num_cama=Num_cama+10";

        boolean b = ejecutarConsulta(cons1);
        System.out.println(b);
        b = ejecutarConsulta(cons2);
        System.out.println(b);
    }

    public static boolean ejecutarConsulta(String consulta) {
        try {
            Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection
                    ("jdbc:mysql://localhost/hospitales", "ejemplo", "ejemplo");

            Statement statement = conexion.createStatement();
            boolean res = statement.execute(consulta);
            if (res) {
                int cont = 0;
                ResultSet rs = statement.getResultSet();
                while (rs.next()) {
                    /*System.out.printf("%d, %s %n", rs.getInt(1), rs.getInt(2));*/
                    cont++;
                }
                System.out.printf("La consulta ha devuelto un resulset de %d filas.%n", cont);
                rs.close();
                statement.close(); // Cerrar Statement
                conexion.close(); // Cerrar conexión
                if (cont > 5)
                    return true;
                else
                    return false;
            } else {
                int f = statement.getUpdateCount();
                System.out.printf("Filas afectadas: %d %n", f);
                statement.close(); // Cerrar Statement
                conexion.close(); // Cerrar conexión
                if (f > 0) {
                    return true;
                }
                else {
                    return false;
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
