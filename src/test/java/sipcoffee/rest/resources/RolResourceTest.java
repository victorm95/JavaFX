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

	 /** Test for create a Rol */
    @Test
    public void createRol() {
		 Rol adminRol = new Rol("Admin");

		 Response response = target("roles").request().post(Entity.entity(adminRol, MediaType.APPLICATION_JSON));

		 adminRol = response.readEntity(Rol.class);

		 Assert.assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
		 Assert.assertEquals(target("roles/"+adminRol.getId()).getUri().toString(), response.getHeaderString("Location"));
    }

	 /** Test for remove a Rol */
	 @Test
	 public void removeRol(){
		 Rol testRol = new Rol("test");

		 Response saveResponse = target("roles").request().post(Entity.entity(testRol, MediaType.APPLICATION_JSON));
		 testRol = saveResponse.readEntity(Rol.class);

		 Response response = target("roles/"+testRol.getId()).request().delete();

		 Assert.assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
	 }

}
