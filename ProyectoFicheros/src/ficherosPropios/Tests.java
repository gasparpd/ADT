package ficherosPropios;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {
	
	String rutaFich= new String("./prueba.txt");
	FicheroRegTexto fiche=new FicheroRegTexto(rutaFich);
	Empleado emp1;
	Empleado emp2;
	Empleado emp3;
	
	@Before
	public void setup() {
		emp1=new Empleado();
		emp2=new Empleado();
		emp3=new Empleado();
		
		emp1.setNombre("Laura");
		emp1.setApellidos("Palacios");
		emp1.setEdad(25);
		emp1.setCargo("CTO");
		emp1.setDuracionContrato(4);
		emp1.setSexo('F');
		
		emp2.setNombre("Alberto");
		emp2.setApellidos("Prieto");
		emp2.setEdad(29);
		emp2.setCargo("DESARROLLADOR SENIOR");
		emp2.setDuracionContrato(3);
		emp2.setSexo('M');
		
		emp3.setNombre("Paula");
		emp3.setApellidos("Alonso");
		emp3.setEdad(28);
		emp3.setCargo("DIRECTORA DE MARKETING");
		emp3.setDuracionContrato(4);
		emp3.setSexo('F');
}

	
	//Test para probar la apertura y cierre de un fichero en modo escritura
	@Test
	public void Test01AbrirCerrarFicheroW() {
		Assert.assertEquals(true,fiche.abrirFicheroW());
		Assert.assertEquals(true,fiche.isOpenW());
		Assert.assertEquals(true,fiche.cerrarFicheroW());
	}
	
	
	//Test para probar la inserción de registros
	@Test
	public void Test02Insertar() {
		Assert.assertEquals(true,fiche.abrirFicheroW());
		Assert.assertEquals(true,fiche.escribirRegistro(emp1));
		Assert.assertEquals(true,fiche.escribirRegistro(emp2));
		Assert.assertEquals(true,fiche.escribirRegistro(emp3));
		Assert.assertEquals(true,fiche.cerrarFicheroW());
		Assert.assertEquals(3,fiche.numeroDeRegistros());
	}
	
	//Test para probar la apertura y cierre de un fichero en modo lectura
	@Test
	public void Test03AbrirCerrarFicheroR() {
		Assert.assertEquals(true,fiche.abrirFicheroR());
		Assert.assertEquals(true,fiche.isOpenR());
		Assert.assertEquals(true,fiche.cerrarFicheroR());
	}

	//Test para probar la búsqueda de un registro
	@Test
	public void Test04BuscarJugador() {
		Empleado aux=new Empleado();
		aux.setNombre("Paula");		
		Assert.assertEquals(3,fiche.buscarRegistro(aux));
		aux.setEdad(30);
		Assert.assertEquals(-1,fiche.buscarRegistro(aux));
		aux.setEdad(28);;
		Assert.assertEquals(3,fiche.buscarRegistro(aux));
		aux.setNombre("Laura");
		aux.setEdad(25);;
		aux.setApellidos("Palacios");
		aux.setCargo("CTO");
		Assert.assertEquals(1,fiche.buscarRegistro(aux));
	}
	
	
	//Test para probar el borrado de registros
	@Test
	public void Test05BorrarRegistro() {
		Assert.assertEquals(true,fiche.borrarRegistro("Prieto"));
		Assert.assertEquals(2,fiche.numeroDeRegistros());
	}
	

	
	//Test para probar el borrado de un fichero
	@Test
	public void Test06BorrarFicheroDatos() {
		Assert.assertEquals(true,fiche.borrarFicheroDatos(rutaFich));
	}
	
	
}