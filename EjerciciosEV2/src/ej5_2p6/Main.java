package ej5_2p6;

import java.sql.*;

public class Main {
	public static void main(String[] args) {
		try {
			// Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");

			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "ejemplo", "ejemplo");

			// Preparamos la consulta
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT empleados.`apellido`, empleados.`salario`, departamentos.dnombre\r\n" + 
					"FROM `empleados`\r\n" + 
					"INNER JOIN departamentos \r\n" + 
					"	ON empleados.dept_no = departamentos.dept_no\r\n" + 
					"WHERE salario =(\r\n" + 
					"    SELECT MAX(empleados.salario)\r\n" + 
					"    FROM `empleados`\r\n" + 
					")";
			ResultSet resul = sentencia.executeQuery(sql);

			// Recorremos el resultado para visualizar cada fila
			// Se hace un bucle mientras haya registros y se van visualizando
			while (resul.next()) {
				System.out.printf("%s, %f€, %s %n", resul.getString(1), resul.getFloat(2), resul.getString(3));
			}

			resul.close(); // Cerrar ResultSet
			sentencia.close(); // Cerrar Statement
			conexion.close(); // Cerrar conexión

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// fin de main
}// fin de la clase
