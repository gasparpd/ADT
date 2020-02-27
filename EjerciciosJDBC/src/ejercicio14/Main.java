package ejercicio14;

import java.sql.*;
import java.text.DecimalFormat;

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
            String sql = " SELECT empleados.apellido, empleados.salario, empleados.oficio, departamentos.dnombre\n" +
                    " FROM empleados\n" +
                    " INNER JOIN departamentos\n" +
                    " ON empleados.dept_no = departamentos.dept_no\n" +
                    " WHERE empleados.dept_no = ?\n";

            String sql_sal_medio = "SELECT COUNT(apellido), AVG(salario)\n" +
                    "FROM empleados\n" +
                    "WHERE dept_no = ?\n";

            System.out.println(sql);
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            PreparedStatement sent_sal = conexion.prepareStatement(sql_sal_medio);
            sentencia.setInt(1, Integer.parseInt(dep_no));
            sent_sal.setInt(1, Integer.parseInt(dep_no));

            try {
                ResultSet rs = sentencia.executeQuery();
                while (rs.next()) {
                    System.out.printf("Apellido: %s, Salario: %f€, Oficio: %s, Nombre departamento: %s\n",
                            rs.getString(1),
                            rs.getFloat(2),
                            rs.getString(3),
                            rs.getString(4));
                }
                System.out.println("---------FIN EMPLEADOS---------");
                //Consulta de salario medio
                rs = sent_sal.executeQuery();
                while (rs.next()){
                    DecimalFormat formato = new DecimalFormat("##,##0.00");
                    String valorFormateado = formato.format(rs.getFloat(1));
                    System.out.printf("Número de empleados: %d, Salario medio: %s\n", rs.getInt(1), formato.format(rs.getFloat(2)));
                }
                System.out.println("---------FIN----------");
            }catch (SQLException e) {
                System.out.println("HA OCURRIDO UNA EXCEPCIÓN:");
                System.out.println("Mensaje:    "+ e.getMessage());
                System.out.println("SQL estado: "+ e.getSQLState());
                System.out.println("Cód error:  "+ e.getErrorCode());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
