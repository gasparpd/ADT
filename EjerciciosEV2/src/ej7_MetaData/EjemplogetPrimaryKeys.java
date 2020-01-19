package ej7_MetaData;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EjemplogetPrimaryKeys {

	public static void main(String[] args) {
		 try
		  {
			Class.forName("com.mysql.jdbc.Driver"); //Cargar el driver
			//Establecemos la conexion con la BD
		    Connection conexion = DriverManager.getConnection  
		    		("jdbc:mysql://localhost/smartphones","root", "root");   
	        
	        
	        
	        //Oracle 
		    /*Class.forName ("oracle.jdbc.driver.OracleDriver");	
		    Connection conexion = DriverManager.getConnection  
		          ("jdbc:oracle:thin:@localhost:1521:XE", "ejemplo", "ejemplo");
		    */
		    
	  
			DatabaseMetaData dbmd = conexion.getMetaData();//Creamos 
	                               //objeto DatabaseMetaData
	  		//ResultSet resul = null;
			 
	  		System.out.println("CLAVE PRIMARIA TABLA SMARTPHONE:");
	  		System.out.println("===================================");
	  		ResultSet pk = dbmd.getPrimaryKeys(null, "SMARTPHONES", "SMARTPHONE");
	  		String pkDep="", separador="";
	  		while (pk.next()) {
	  			   pkDep = pkDep + separador + 
	  		               pk.getString("COLUMN_NAME");//getString(4)
	  			   separador="+";
	  		 }
	  		System.out.println("Clave Primaria: " + pkDep);


				
	  		 conexion.close(); //Cerrar conexion   		  	   
		  } 
	   catch (ClassNotFoundException cn) {cn.printStackTrace();} 
				   catch (SQLException e) {e.printStackTrace();}		
		}//fin de main
	

}
