package sipcoffee.rest.resources;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import sipcoffee.models.Rol;

import javax.ws.rs.core.Application;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victorm on 6/12/14.
 */
public class RolResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(RolResource.class);
    }

    @Test
    public void test1() {
        List<Rol> roles = target("roles").request().get(List.class);
        System.out.println(roles.toArray().toString());
    }

}
