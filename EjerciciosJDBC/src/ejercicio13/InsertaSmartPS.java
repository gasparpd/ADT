package ejercicio13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertaSmartPS {
	public static void main (String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection
					("jdbc:mysql://localhost/smartphones", "root", "root");
										
			// recuperar argumentos de main
			String id_marca = args[0]; //ID_MARCA
			String modelo = args[1]; //Modelo
			String pulgadas_p = args[2]; //Pulgadas de pantalla
			String precio = args[3]; //Precio
			
			// construir orden INSERT
			String sql = "INSERT INTO smartphone VALUES "
					+ "( ?, ?, ?, ? )";
			    
			System.out.println(sql);
			PreparedStatement sentencia = conexion.prepareStatement(sql);			
			sentencia.setInt(1, Integer.parseInt(id_marca));
			sentencia.setString(2, modelo);
			sentencia.setString(3, pulgadas_p);
			sentencia.setString(4, precio);
			
			int filas;//
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
