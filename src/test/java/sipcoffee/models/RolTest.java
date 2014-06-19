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
	//@Ignore
	public void test(){
		assertTrue("Insertando Administrador",admin.save());
		assertTrue("Insertando Cosechador	",cosecha.save());
	
		assertTrue("Eliminando Administrador",Conexion.delete(admin));
		assertTrue("Eliminando Cosechador	",Conexion.delete(cosecha));
	}
	
	@After
	public void exit(){
		admin = null;
		cosecha = null;
	}
	
	
}
