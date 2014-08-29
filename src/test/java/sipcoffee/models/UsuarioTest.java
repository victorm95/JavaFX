package sipcoffee.models;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class UsuarioTest {
	
	Usuario user1;
	Rol admin;
	
	@Before
	public void config(){
		
		admin = new Rol("Admin");
		//admin.save();
		
		user1 = new Usuario();
		user1.setNombre("User");
		user1.setActivo(true);
		user1.setCedula(123456789l);
		user1.setTelefono(12456l);
		user1.setDireccion("Calle 100");
		user1.setFechaRegistro( new Date() );
		user1.setRol( admin );
		
	}
	
	@Test
	@Ignore
	public void test(){
		//assertTrue("Insertando User", user1.save());		
		//assertTrue("Eliminando User", user1.delete());
		//assertTrue("Eliminando Rol", admin.delete());
	}
	
	@After
	public void exit(){
		/*admin.delete();
		user1.delete();
		user1 = null;*/
	}
	
	
}
