package sipcoffee.rest.resources;

import org.glassfish.grizzly.Result;
import sipcoffee.models.Rol;
import sipcoffee.models.Connection;
import sipcoffee.App;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import java.net.URI;
import java.util.List;

/**
 * Created by victorm on 6/12/14.
 */

@Path("/roles")
@Produces(MediaType.APPLICATION_JSON)
public class RolResource {

	 /* Attrs */
    private EntityManager entityManager;

	 /* Construct */
	 public RolResource() {
	 	this.entityManager = Connection.getInstance();
	 }

    @GET
    public List<Rol> getRoles(){
        return this.entityManager.createNamedQuery("all-Rol").getResultList();
    }

	 @Path("/{id}")
	 @GET
	 public Response getRol(@PathParam("id")int id) {
	 	Rol rol = this.entityManager.find(Rol.class, id);
		if(rol == null)
			return Response.status(Status.NOT_FOUND).build();
		else
			return Response.ok(rol).build();
	 }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveRol(Rol rol, @Context UriInfo uriInfo){
		 this.entityManager.getTransaction().begin();
		 this.entityManager.persist(rol);
		 this.entityManager.getTransaction().commit();
		 
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uriCreated = uriBuilder.path(String.valueOf(rol.getId())).build();

        return Response.created(uriCreated).entity(rol).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Rol updateRol(Rol rol){
   	  this.entityManager.getTransaction().begin();
        this.entityManager.merge(rol);
		  this.entityManager.getTransaction().commit();

		  return rol;
    }

    @Path("/{id}")
    @DELETE
    public Response deleteRol(@PathParam("id")int id){
		  Rol rol = this.entityManager.find(Rol.class, id);

		  if(rol != null){
			  this.entityManager.getTransaction().begin();
   	     this.entityManager.remove(rol);
			  this.entityManager.getTransaction().commit();

			  return Response.noContent().build();
		  } else {
			  return Response.status(Status.NOT_FOUND).build();
		  }
    }

}
