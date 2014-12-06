package sipcoffee.rest.resources;

import org.glassfish.grizzly.Result;
import sipcoffee.models.Rol;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

/**
 * Created by victorm on 6/12/14.
 */

@Path("/roles")
public class RolResource {

    @PersistenceContext(unitName = "dev")
    EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rol> getRoles(){
        return this.entityManager.createNamedQuery("all-Rol").getResultList();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveRol(Rol rol, @Context UriInfo uriInfo){
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uriCreated = uriBuilder.path(String.valueOf(rol.getId())).build();

        this.entityManager.persist(rol);

        return Response.created(uriCreated).entity(rol).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateRol(Rol rol){
        this.entityManager.merge(rol);
    }

    @Path("/{id}")
    @DELETE
    public void deleteRol(@PathParam("id")int id){
        this.entityManager.remove(this.entityManager.find(Rol.class, id));
    }

}
