package sipcoffee.rest.resources;

import org.glassfish.grizzly.Result;

import org.h2.engine.User;
import sipcoffee.models.Usuario;
import sipcoffee.App;
import sipcoffee.models.Connection;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.net.URI;
import java.util.List;

/**
 * Created by victorm on 6/12/14
 */

@Path("/usuarios")
public class UsuarioResource {

	// Attrs
	private EntityManager entityManager;

	// Construct
	public UsuarioResource() {
		this.entityManager = Connection.getInstance();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getUsuarios() {
		return this.entityManager.createNamedQuery("all-Usuario").getResultList();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveUsuario(Usuario usuario, @Context UriInfo uriInfo) {
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(usuario);
			this.entityManager.getTransaction().commit();
		} catch(RollbackException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

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


	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Usuario usuario) {
		try {
			Usuario temp = (Usuario) this.entityManager.createNamedQuery("login-Usuario")
					.setParameter("usuario", usuario.getUsuario())
					.setParameter("clave", usuario.getClave())
					.getSingleResult();

			if(temp.getActivo())
				return Response.ok(temp).build();
			else
				return Response.status(Response.Status.UNAUTHORIZED).build();
		} catch(NoResultException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

}
