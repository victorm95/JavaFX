package sipcoffee.models;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class UsuarioTest {
	
	Usuario user1;
	
	@Before
	public void config(){
		user1 = new Usuario();
		user1.setNombre("User");
		user1.setActivo(true);
		user1.setCedula(123456789l);
		user1.setTelefono(12456l);
		user1.setFechaRegistro( new Date() );
	}
	
	@Test
	//@Ignore
	public void test(){
		assertTrue("Insertando User",Conexion.persist(user1));		
		//assertTrue("Eliminando User",Conexion.delete(user1));
	}
	
	@After
	public void exit(){
		user1 = null;
	}
	
	
}
