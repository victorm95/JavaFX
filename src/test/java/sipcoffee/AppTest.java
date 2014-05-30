package sipcoffee;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import sipcoffee.models.Conexion;
import sipcoffee.models.Rol;


public class AppTest {
	
	private Rol rol1 ,rol2;
	
	@Before
	public void config(){
		rol1 = new Rol();
		rol1.setNombre("Administrador");
		
		rol2 = new Rol("Cosechadr");
	}
	
	@Test
	@Ignore
	public void test1(){
		assertTrue("Insertando Administrador",Conexion.persist(rol1));
		assertTrue("Insertando Cosechador	",Conexion.persist(rol2));
	}
	
	@After
	public void exit(){
		
	}
	
}
