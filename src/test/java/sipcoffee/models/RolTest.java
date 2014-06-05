package sipcoffee.models;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class RolTest {
	
	private Rol admin ,cosecha;
	
	@Before
	public void config(){
		admin = new Rol();
		admin.setNombre("Administrador");
		
		cosecha = new Rol("Cosechador");
	}
	
	@Test
	public void test(){
		assertTrue("Insertando Administrador",Conexion.persist(admin));
		assertTrue("Insertando Cosechador	",Conexion.persist(cosecha));
	
		assertTrue("Eliminando Administrador",Conexion.delete(admin));
		assertTrue("Eliminando Cosechador	",Conexion.delete(cosecha));
	}
	
	@After
	public void exit(){
		admin = null;
		cosecha = null;
	}
	
	
}
