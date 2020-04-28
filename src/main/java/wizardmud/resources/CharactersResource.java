package wizardmud.resources;

import wizardmud.api.MudCharacter;
import wizardmud.api.MudCharacterUri;
import wizardmud.dao.MudCharacterDao;
import com.codahale.metrics.annotation.Timed;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.Handle;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.validation.Valid;

import java.util.Optional;
import java.util.ArrayList;

@Path("/characters")
@Produces(MediaType.APPLICATION_JSON)
public class CharactersResource {
	final Jdbi jdbi;
	public CharactersResource(Jdbi jdbi) {
		this.jdbi = jdbi;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postCharacter(@Valid MudCharacter character, @Context UriInfo uri) {
		int max_id = jdbi.withExtension(MudCharacterDao.class, dao -> dao.maxId());
		int id = max_id+1;
		jdbi.useExtension(MudCharacterDao.class, dao -> dao.insert(id, character));
		return Response.status(201).entity(uri.getBaseUri().toASCIIString() + uri.getPath() + "/" + id).build();
	}

	@GET
	public Response getCharacters(@Context UriInfo uri) {
		ArrayList<MudCharacterUri> uri_list = jdbi.withExtension(MudCharacterDao.class, dao -> dao.getCharacters());
		for(MudCharacterUri mcu : uri_list) {
			mcu.uri(uri.getBaseUri().toASCIIString() + uri.getPath() + "/" + mcu.uri());
		}
		return Response.ok(uri_list).build();
	}

	@GET
	@Path("/{id}")
	public Response getCharacter(@PathParam("id") int id) {
		MudCharacter mc = jdbi.withExtension(MudCharacterDao.class, dao -> dao.getCharacter(id));
		if(mc == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(mc).build();
	}
}
