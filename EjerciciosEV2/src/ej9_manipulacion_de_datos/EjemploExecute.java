package ej9_manipulacion_de_datos;

import java.sql.*;

public class EjemploExecute {   
	
	public static void main(String[] args) throws 
	       ClassNotFoundException, SQLException {
	  
		//CONEXION A MYSQL  	       
	   Class.forName("com.mysql.jdbc.Driver");		 
	   Connection conexion = DriverManager.getConnection    
           ("jdbc:mysql://localhost/smartphones","root", "root");   		   
	   
	   String sql="SELECT * FROM smartphone";
	   Statement sentencia = conexion.createStatement();		   
	   boolean valor = sentencia.execute(sql);  
	   		   
	   if(valor){
	    	ResultSet rs = sentencia.getResultSet();
	   	 while (rs.next())
	   	    System.out.printf("%d, %d, %s, %s, %s %n",
	   	    		rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
	    	rs.close();
	   } else {
	    	int f = sentencia.getUpdateCount();
	    	System.out.printf("Filas afectadas:%d %n", f);
	   }
	   
		sentencia.close();
		conexion.close();
	}//main
}//
