package ejercicio13;

import java.sql.*;

public class ModificarSalario {

	
		public static void main(String[] args) {

			String dep = "30", subida ="100";
			
			try {
				Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
				// Establecemos la conexion con la BD
				Connection conexion = DriverManager.getConnection(
						"jdbc:mysql://localhost/ejemplo", "ejemplo", "ejemplo");

				String sql = "UPDATE empleados SET salario = salario + ? WHERE dept_no = ?";
				PreparedStatement sentencia = conexion.prepareStatement(sql);
			    System.out.println(sql);

			    sentencia.setFloat(1, Float.parseFloat(subida));
			    sentencia.setInt(2, Integer.parseInt(dep));
			    
				int filas = sentencia.executeUpdate();
				System.out.printf("Empleados modificados: %d %n", filas);
				
				sentencia.close(); // Cerrar Statement
				conexion.close(); // Cerrar conexión

			} catch (ClassNotFoundException cn) {
				cn.printStackTrace();
			} catch (SQLException e) {
				if (e.getErrorCode() == 1062)
					System.out.println("CLAVE PRIMARIA DUPLICADA");
				else 
					if (e.getErrorCode() == 1452)
					System.out.println("CLAVE AJENA "+ dep + " NO EXISTE");
				
				else {
					System.out.println("HA OCURRIDO UNA EXCEPCIÓN:");
					System.out.println("Mensaje:    " + e.getMessage());
					System.out.println("SQL estado: " + e.getSQLState());
					System.out.println("Cód error:  " + e.getErrorCode());
				}
			}

		}// fin de main
	}// fin de la clase
	
	