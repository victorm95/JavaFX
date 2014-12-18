package sipcoffee.rest.resources;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.Assert;
import sipcoffee.App;
import sipcoffee.models.Rol;
import sipcoffee.models.Connection;
import javax.ws.rs.core.*;
import javax.ws.rs.client.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victorm on 6/12/14.
 */
public class RolResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
		  Connection.setPersistenceUnit(Connection.TEST_UNIT);
        return new ResourceConfig(RolResource.class);
    }

    @Test
    public void createRol() {
		 Rol rol = new Rol("Admin");
		 Response response;

		 // Test for create a Rol
		 response = target("roles").request().post(Entity.entity(rol, MediaType.APPLICATION_JSON));
		 rol = response.readEntity(Rol.class);
		 Assert.assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
		 Assert.assertEquals(target("roles/"+rol.getId()).getUri().toString(), response.getHeaderString("Location"));

		 // Test for consult a Rol
		 response = target("roles/"+rol.getId()).request().get();
		 Assert.assertEquals(rol.toString(), (response.readEntity(Rol.class)).toString());

		 // Test for update a Rol
		 rol.setNombre("Test");
		 response = target("roles").request().put(Entity.entity(rol, MediaType.APPLICATION_JSON));		 
		 Assert.assertEquals(rol.toString(), (response.readEntity(Rol.class)).toString());

		 // Test for delete a Rol
		 response = target("roles/"+rol.getId()).request().delete();
		 Assert.assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

}
