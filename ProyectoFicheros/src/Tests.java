
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
	Jugador jug;
	Jugador jug2;
	Jugador jug3;
	
	@Before
	public void setup() {
		jug=new Jugador();
		jug2=new Jugador();
		jug3=new Jugador();
		
		jug.setNombre("Andrés");
		jug.setApellidos("Iniesta");
		jug.setDorsal(5);
		jug.setPosicion("Delantero");
		
		jug2.setNombre("Cristiano");
		jug2.setApellidos("Ronaldo");
		jug2.setDorsal(9);
		jug2.setPosicion("Delantero");
		
		jug3.setNombre("Jordi");
		jug3.setApellidos("Alba");
		jug3.setDorsal(15);
		jug3.setPosicion("Defensa");
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
		Assert.assertEquals(true,fiche.escribirRegistro(jug));
		Assert.assertEquals(true,fiche.escribirRegistro(jug2));
		Assert.assertEquals(true,fiche.escribirRegistro(jug3));
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
		Jugador aux=new Jugador();
		aux.setNombre("Jordi");		
		Assert.assertEquals(3,fiche.buscarRegistro(aux));
		aux.setDorsal(10);
		Assert.assertEquals(-1,fiche.buscarRegistro(aux));
		aux.setDorsal(15);
		Assert.assertEquals(3,fiche.buscarRegistro(aux));
		aux.setNombre("Andrés");
		aux.setDorsal(5);
		aux.setApellidos("Iniesta");
		aux.setPosicion("Delantero");
		Assert.assertEquals(1,fiche.buscarRegistro(aux));
	}
	
	
	//Test para probar el borrado de registros
	@Test
	public void Test05BorrarRegistro() {
		Assert.assertEquals(true,fiche.borrarRegistro("Ronaldo"));
		Assert.assertEquals(2,fiche.numeroDeRegistros());
	}
	

	
	//Test para probar el borrado de un fichero
	@Test
	public void Test06BorrarFicheroDatos() {
		Assert.assertEquals(true,fiche.borrarFicheroDatos(rutaFich));
	}
	
	
}