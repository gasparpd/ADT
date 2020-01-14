package ejercicio11;

import java.sql.*;

public class InsertarEmpleado {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");// Cargar el driver
			// Establecemos la conexion con la BD
			Connection conexion = DriverManager.getConnection
					("jdbc:mysql://localhost/ejemplo", "ejemplo", "ejemplo");
										
			// recuperar argumentos de main
			String emp_no = args[0]; // num. departamento
			String apellido = args[1]; // nombre
			String oficio = args[2]; // localidad
			String dir = args[3]; // num. departamento
			String salario = args[4]; // nombre
			String comision = args[5]; // localidad
			String dept_no = args[6]; // localidad
			Date date = new Date(System.currentTimeMillis());
			System.out.print(date);
			
			//Comprobar que exista el departamento
			String sql_v_dept = String.format("SELECT * FROM departamentos WHERE dept_no = %s", dept_no);
			Statement sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery(sql_v_dept);
			if(!rs.next()) {
				System.out.println("El departamento no existe.");
			} else {
				//Comprobar que no exista el número de empleado
				String sql_v_numemp = String.format("SELECT * FROM empleados WHERE emp_no = %s", emp_no);
				rs= sentencia.executeQuery(sql_v_numemp);
				if(rs.next()) {
					System.out.println("El número de empleado ya existe.");
				} else {
					//Comprobar que el salario sea mayor a 0
					float sal = Float.parseFloat(salario);
					if (sal <= 0) {
						System.out.println("El salario es menor o igual a 0.");
					} else {
						//Comprobar que existe el director
						String sql_v_dir = String.format("SELECT * FROM empleados WHERE dir = %s", dir);
						rs= sentencia.executeQuery(sql_v_numemp);
						if(!rs.next()) {
							System.out.println("El director no existe.");
						} else {
							if (apellido == null || oficio == null){
								System.out.println("El apellido y el oficio no pueden ser nulos");
							} else {

							}
						}
					}
				}
				
			}
		} catch (Exception e) {
			
		}
	}

}
