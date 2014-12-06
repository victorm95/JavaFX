package sipcoffee.rest.resources;

import org.glassfish.grizzly.Result;

import sipcoffee.models.Usuario;
import sipcoffee.App;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.net.URI;
import java.util.List;

/**
 * Created by victorm on 6/12/14
 */

@Path("/usuarios")
public class UsuarioResource {

	@PersistenceContext(unitName = App.PERSISTENCE_UNIT)
	EntityManager entityManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getUsuarios() {
		return this.entityManager.createNamedQuery("all-Usuario").getResultList();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveUsuario(Usuario usuario, @Context UriInfo uriInfo) {
		this.entityManager.persist(usuario);

		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
		URI uriCreated = uriBuilder.path(String.valueOf(usuario.getId())).build();
		return Response.created(uriCreated).entity(usuario).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateUsuario(Usuario usuario) {
		this.entityManager.merge(usuario);
	}

	@Path("/{id}")
	@DELETE
	public void deleteRol(@PathParam("id")int id) {
		this.entityManager.remove(this.entityManager.find(Usuario.class, id));
	}

}
