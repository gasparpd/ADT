package ficherosPropios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class FicheroRegTexto extends FicheroReg {

	PrintWriter fichEscr;
	FileWriter fichEscr2;
	FileReader fichLect1;
	BufferedReader fichLect2;
	
	public FicheroRegTexto() {
		this.fichEscr=null;
		this.fichEscr2=null;
		this.fichLect1=null;
		this.fichLect2=null;
	}
	
	public FicheroRegTexto(String fich) {
		super(fich);
		this.fichEscr=null;
		this.fichEscr2=null;
		this.fichLect1=null;
		this.fichLect2=null;
	}
	
	@SuppressWarnings("deprecation")
	protected void finalize() throws Throwable{
		
		super.finalize();
		try {
			if (this.fichEscr!=null)
				this.fichEscr.close();
			if (this.fichEscr2!=null)
				this.fichEscr2.close();
			if (this.fichLect1!=null)
				this.fichLect1.close();
			if (this.fichLect2!=null)
				this.fichLect2.close();             
		} 
		catch (IOException e) {
            
        }
		
	}
		
	//Este m�todo lee tantas l�neas del fichero como campos tenga nuestro registro y las va almacenando 
	//en el correspondiente campo del objeto recibido como par�metro. Tiene que realizar la conversi�n de tipo
	//necesaria si alg�n campo no es de tipo String
	//En la lectura de cada l�nea comprobar� que la funci�n de leer no devolvi� null
	//Si alguna lectura de l�nea devuelve null devolveremos false en la funci�n para indicar que no se pudo leer un registro
	//en caso contrario devolveremos true
	//Debe comprobar con el m�todo isOpenR si tenemos el fichero abierto para lectura y si no est� abierto abrirlo con la funci�n abrirFicheroR
	//Al terminar de leer cerrar� el fichero con la funci�n cerrarFicheroR
	public boolean leerRegistro(Empleado em) {
		
		if(!isOpenR()) {
			abrirFicheroR();
		}
		
		try {
			String s = fichLect2.readLine();
			
			if(s != null) {
				em.setNombre(s);
				s = fichLect2.readLine();
			}
			if(s != null) {
				em.setApellidos(s);
				s = fichLect2.readLine();
			}
			if(s != null) {
				em.setEdad(Integer.parseInt(s));
				s = fichLect2.readLine();
			}
			if(s != null) {
				em.setCargo(s);
				s = fichLect2.readLine();
			}
			if(s != null) {
				em.setDuracionContrato(Integer.parseInt(s));
				s = fichLect2.readLine();
			}
			if(s != null) {
				em.setSexo(s.charAt(0));
			}else {
				return false;
			}
		}
		catch (IOException e) {
			return false;
		}
		
		System.out.println("Fichero cerrado: " +cerrarFicheroR());
		return true;
	}

	
	//Est� m�todo es igual al anterior s�lo que si en la variable cerrar nos pasan el valor false no cerraremos el fichero de lectura al finalizar de leer un registro
	@Override
	public boolean leerRegistro(Empleado em, boolean cerrar) {
		
		if(!isOpenR()) {
			abrirFicheroR();
		}
		
		try {
			
			String s = fichLect2.readLine();
			
			if(s != null) {
				em.setNombre(s);
				s = fichLect2.readLine();
			}
			if(s != null) {
				em.setApellidos(s);
				s = fichLect2.readLine();
			}
			if(s != null) {
				em.setEdad(Integer.parseInt(s));
				s = fichLect2.readLine();
			}
			if(s != null) {
				em.setCargo(s);
				s = fichLect2.readLine();
			}
			if(s != null) {
				em.setDuracionContrato(Integer.parseInt(s));
				s = fichLect2.readLine();
			}
			if(s != null) {
				em.setSexo(s.charAt(0));
			}else {
				return false;
			}
		}
		catch (IOException e) {
			return false;
		}
		
		if(cerrar) {
			System.out.println("Fichero cerrado: " +cerrarFicheroR());
		}
		else {
			System.out.println("Fichero abierto.");
		}
		
		
		return true;
	}

	//Devuelve true si la variable fichLect2 es distinta de null y false en caso contrario
	@Override
	public boolean isOpenR() {
		if(fichLect2 != null) {
			return true;
		}
		else {
			return false;
		}
	}

    //Este m�todo borra el fichero al que apunta la variable de clase fichero usando el m�todo delete()
	//Devuelve true si el fichero existe y se pudo borrar y false en caso contrario
	@Override
	public boolean borrarFicheroDatos(String fichero) {
		File f = new File(fichero);
		if (f.exists()) {
			f.delete();
			return true;
		}
		else {
			return false;
		}
	}

	
	//Este m�todo comprueba si el fichero est� abierto para escritura comprobando el valor de la variable fichEscr
	//Si tiene un valor igual a null devuelve false en caso contrario devuelve true
	@Override
	public boolean isOpenW() {
		if(fichEscr != null) {
			return true;
		}
		else {
			return false;
		}
	}

	//Escribe cada uno de los campos del registro pasado como par�metro en una l�nea del fichero
	//Antes de escribir comprueba si el fichero est� abierto para escritura llamando al m�todo isOpenW
	//Devuelve true si se pudo escribir todo el registro y false en caso contrario
	@Override
	public boolean escribirRegistro(Empleado em) {
		
		if(isOpenW())
		{
			fichEscr.println(em.getNombre());//para escribir en una linea
			fichEscr.println(em.getApellidos());
			fichEscr.println(em.getEdad());
			fichEscr.println(em.getCargo());
			fichEscr.println(em.getDuracionContrato());
			fichEscr.println(em.getSexo());
			
			System.out.println("Usuario registrado");
			
			return true;
		}
		else
			return false;
	}


    //Este m�todo escribe en el fichero indicado en la ruta del segundo par�metro los datos del objeto Jugador recibido
	//En el tercer par�metro del m�todo indicamos si queremos agregar el registro a los existentes o borrar el contenido anterior y dejar s�lo este registro
	@Override
	public boolean escribirRegistro(Empleado em, String ruta, boolean append) {
		if (append) {
			fichero = new File(ruta);
			abrirFicheroWa�adir();
		}
		else {
			fichero = new File(ruta);
			abrirFicheroW();
		}
		fichEscr.println(em.getNombre());//para escribir en una linea
		fichEscr.println(em.getApellidos());
		fichEscr.println(em.getEdad());
		fichEscr.println(em.getCargo());
		fichEscr.println(em.getDuracionContrato());
		fichEscr.println(em.getSexo());
		
		System.out.println("Fichero cerrado: " +cerrarFicheroW());
		return true;
	}	
	
	
	//Abre en modo lectura el fichero indicado en la variable fichero
	//Inicializa para ello tanto fichLect1 como fichLect2
	//Devuelve true si se pudieron inicializar los dos correctamente y false en caso contrario
	@Override
	public boolean abrirFicheroR() {
		try {
			fichLect1 = new FileReader(fichero);
			fichLect2 = new BufferedReader(fichLect1);
			System.out.println("Se ha abierto el fichero de lectura.");
			return true;
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			return false;
		}
	}

    //Cierra los objetos de lectura tanto fichLect1 como fichLect2
	//Antes de cerrarlos comprueba que no tienen valor null
	//Despu�s de cerrarlos les asigna el valor null
	@Override
	public boolean cerrarFicheroR() {
		
		if (fichLect1 == null && fichLect2 == null) {
			System.out.println("Fichero lectura cerrado.");
			return true;
		}
		else {
			try{
				fichLect1.close();
				fichLect2.close();
				System.out.println("Fichero lectura cerrado.");
				return true;
			}
			catch(IOException e) {
				e.getCause();
				return false;
			}
		}
	}

    //Borra el registro cuyo apellido coincida con el apellido pasado como par�metro
	@Override
	public boolean borrarRegistro(String apellido) {
				
		Empleado empleado = new Empleado();
		empleado.setApellidos(apellido);
		int posicion = buscarRegistro(empleado) -1;
		List<Empleado>aux=new ArrayList<>();
		
		try {
			abrirFicheroR();
			
			String nom;
			while((nom = fichLect2.readLine())!=null)
			{
				
				String ape = fichLect2.readLine();
				int edad = Integer.parseInt(fichLect2.readLine());
				String cargo = fichLect2.readLine();
				int duraC = Integer.parseInt(fichLect2.readLine());
				char sexo = fichLect2.readLine().charAt(0);
				Empleado em = new Empleado();
				em.setNombre(nom);
				em.setApellidos(ape);
				em.setEdad(edad);
				em.setCargo(cargo);
				em.setDuracionContrato(duraC);
				em.setSexo(sexo);
				aux.add(em);
			}
			cerrarFicheroR();
			
	
			
			aux.remove(posicion);
			
			
			abrirFicheroW();
			for (int i = 0; i < aux.size(); i++) {
			
				escribirRegistro(aux.get(i));
			}
			cerrarFicheroW();

		
		
		} catch (IOException e) {return false;}	
		
		
		return true;
	}

	
	//Abre en modo escritura el archivo indicado en la variable de clase fichero
	//Devuelve true si la apertura fue correcta y false en caso contrario
	@Override
	public boolean abrirFicheroW() {
		try {
			fichEscr2 = new FileWriter(fichero);
			fichEscr = new PrintWriter(fichEscr2);
			System.out.println("Se ha abierto el fichero de escritura.");
			return true;
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			return false;
		}
	}

    //Cierra los objetos de escritura tanto fichEscr1 como fichEscr2
	//Antes de cerrarlos comprueba que no tienen valor null
	//Despu�s de cerrarlos les asigna el valor null
	@Override
	public boolean cerrarFicheroW() {
		if (fichEscr == null && fichEscr2 == null) {
			System.out.println("Fichero de escritura cerrado.");
			return true;
		}
		else {
			try{
				fichEscr.close();
				fichEscr2.close();
				System.out.println("Fichero de escritura cerrado.");
				return true;
			}
			catch(IOException e) {
				e.getCause();
				return false;
			}
		}
	}
	
	//Abre en modo escritura para a�adir el fichero indicado en la variable de clase fichero
	//Devuelve true si se pudo abrir correctamente y false en caso contrario
	public boolean abrirFicheroWa�adir(){
		try {
			fichEscr2 = new FileWriter(fichero, true);
		}
		catch (IOException e) {
			e.getCause();
			return false;
		}
	
		return true;
	}
	


	//Este m�todo va llamando a leerRegistro(Jugador jugador, boolean cerrar) con la variable cerrar a false
	//En cada registro obtenido invocar� al m�todo mostrarDatos para mostrar los datos le�dos
	public void mostrarRegistros() {
		Empleado em = new Empleado();
		int cont = 0;
		while (leerRegistro(em, false)) {
			cont++;
			System.out.println("---------Empleado " +cont +"---------");
			em.mostrarDatos();
		}
		
		cerrarFicheroR();
	}

	//Recorre el fichero de igual modo que el m�todo anterior pero va contando el n�mero de registros le�dos y al final devuelve el n�mero de registros le�dos
	public int numeroDeRegistros() {
		
		if(!abrirFicheroR()) {
			return -1;
		}
		
		Empleado em = new Empleado();
		int cont = 0;
		
		while (leerRegistro(em, false)) {
			cont++;
		}
		
		cerrarFicheroR();
		return cont;
	}
	
	
	//Este m�todo va leyendo los registros del fichero indicado en la variable de clase fichero
	//Por cada registro le�do comprueba si sus valores coinciden con los del registro jugador recibido como par�metro
	//Si el registro le�do es el buscado finaliza la b�squeda y establece los campos de la variable jugador con los valores encontrados
	//En los datos del objeto jugador recibido puede haber alg�n campo con valor sin establecer en ese caso no se tendr� en cuenta a la hora
	//de comprobar si el objeto buscado es igual al le�do
	//El m�todo devuelve la posici�n num�rica del registro encontrado en caso de encontrarlo o -1 si no se encuentra
	@Override
	public int buscarRegistro(Empleado empleado) {
		int cont = 1;
		String nombre = empleado.getNombre();
		String apellidos = empleado.getApellidos();
		int edad = empleado.getEdad();
		String cargo = empleado.getCargo();
		int duraC = empleado.getDuracionContrato();
		char sexo = empleado.getSexo();
		
		Empleado em = new Empleado();
		
		if (isOpenR()) {
			cerrarFicheroR();
		}
		
		abrirFicheroR();
		
		while (leerRegistro(em, false)) {
			
			if (em.getNombre().equalsIgnoreCase(nombre) || nombre.equals("")) {
				if (em.getApellidos().equalsIgnoreCase(apellidos) || apellidos.equals("")) {
					if (em.getEdad() == edad || edad == -1) {
						if (em.getCargo().equalsIgnoreCase(cargo) || cargo.equals("")) {
							if(em.getDuracionContrato() == duraC || duraC == -1) {
								if(em.getSexo() == sexo || sexo == ' ') {
									em.setNombre(nombre);
									em.setApellidos(apellidos);
									em.setEdad(edad);
									em.setCargo(cargo);
									em.setDuracionContrato(duraC);
									em.setSexo(sexo);
									
									cerrarFicheroR();
									return cont;
								}
							}
							
						}
					}
				}
			}
			
			cont++;
		}
		cerrarFicheroR();
		return -1;
	}
	
}