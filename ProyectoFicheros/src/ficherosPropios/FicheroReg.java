package ficherosPropios;

import java.io.File;

public abstract class FicheroReg {
	File fichero;
	
	FicheroReg(String fich){
		this.fichero = new File(fich);	    
	}
		
	FicheroReg(){
		this.fichero=null;
	}

	boolean crearFichero(String fich) {
		
	    this.fichero = new File(fich);
	    if (this.fichero==null)
	    		return false;
	    	else return true;
	}
	
	boolean ficheroCreado() {
		return this.fichero!=null;
	}
	
	public abstract boolean leerRegistro(Empleado em); //Lee un registro y cierra el fichero
	public abstract boolean leerRegistro(Empleado em,boolean cerrar); //Lee un registro y cierra el fichero si la variable cerrar está a true
	public abstract boolean escribirRegistro(Empleado em); //Escribe en el fichero el registro Jugador
	public abstract boolean escribirRegistro(Empleado em,String ruta,boolean append); //Escribe en el fichero el registro Jugador
	public abstract void mostrarRegistros(); //Recorre el fichero leyendo los registros y mostrándolos por pantalla
	public abstract int numeroDeRegistros(); //Devuelve el número de registros que tiene el fichero
	public abstract boolean abrirFicheroR(); //Abre el fichero en modo lectura
	public abstract boolean cerrarFicheroR(); //Cierra el fichero abierto en modo lectura
	public abstract boolean abrirFicheroW(); //Abre el fichero en modo escritura
	public abstract boolean cerrarFicheroW();  //Cierra el fichero abierto en modo lectura
	public abstract boolean isOpenR(); //Devuelve true si tenemos abierto el fichero en modo lectura y false en caso contrario
	public abstract boolean isOpenW(); //Devuelve true si tenemos abierto el fichero en modo lectura y false en caso contrario
	public abstract boolean borrarRegistro(String apellido);
	public abstract boolean borrarFicheroDatos(String fichero);
	public abstract int buscarRegistro(Empleado em);
	
}