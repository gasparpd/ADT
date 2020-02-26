package ej10_manipulacion_de_datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertarSmartphone {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection
					("jdbc:mysql://localhost/smartphones", "root", "root");
			
			
		  /*Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conexion = DriverManager
					.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
							"ejemplo", "ejemplo");
          */ 
			// recuperar argumentos de main
			String id_smart = args[0]; // num. departamento
			String id_fab = args[1]; // nombre
			String model = args[2]; // localidad
			String pulgadas = args[3]; // nombre
			String precio = args[4]; // nombre
			
			//construir orden INSERT	        
	        String sql = String.format("INSERT INTO smartphone VALUES (%s, %s, '%s', '%s', '%s')",
	        		id_smart, id_fab, model, pulgadas, precio);
	        
	        System.out.println(sql);

	        
			//https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html
	        
			System.out.println(sql);
			Statement sentencia = conexion.createStatement();
			int filas=0;
			try {
			  filas = sentencia.executeUpdate(sql.toString());
			  System.out.println("Filas afectadas: " + filas);
			} catch (SQLException e) {
				//e.printStackTrace();
				   System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n"); 
				   System.out.printf("Mensaje   : %s %n", e.getMessage()); 
				   System.out.printf("SQL estado: %s %n", e.getSQLState()); 
				   System.out.printf("Cód error : %s %n", e.getErrorCode());	    	
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
