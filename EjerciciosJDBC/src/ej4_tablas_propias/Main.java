package ej4_tablas_propias;

import java.sql.*;

public class Main {
	public static void main(String[] args) {
		try {
			// Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");

			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/smartphones", "root", "root");

			// Preparamos la consulta
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * FROM fabricante";
			ResultSet resul = sentencia.executeQuery(sql);
			
			
			resul.last();
			System.out.println("NÚMERO DE FILAS: " +resul.getRow());
			resul.beforeFirst(); //Nos situamos antes del primer registro.
			

			// Recorremos el resultado para visualizar cada fila
			// Se hace un bucle mientras haya registros y se van visualizando
			while (resul.next()) {
				System.out.printf("%d, %s, %s, %d %n", resul.getInt(1), resul.getString(2), resul.getString(3), resul.getInt(4));
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
