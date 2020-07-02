package movieFinder.api.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import movieFinder.model.repository.AudioVisualRepository;
import movieFinder.model.repository.MapAudioVisualRepository;
import movieFinder.modelo.Actor;

@Path("/actor")
public class ActorResource {
	
	public static ActorResource _instance=null;
	AudioVisualRepository repository;
	
	private ActorResource() {
		repository=MapAudioVisualRepository.getInstance();
	}
	
	public static ActorResource getInstance(){
		if(_instance==null)
			_instance=new ActorResource();
		return _instance; 
	}
	
	@GET
	@Produces("application/json")
	public Collection<Actor> getAll()
	{
		return repository.getAllActores();
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Actor get(@PathParam("id") String actorId)
	{
		Actor actor = repository.getActor(actorId);
		if (actor == null) {
			throw new NotFoundException("The actor with id="+ actorId +" was not found");			
		}
		return actor;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addActor(@Context UriInfo uriInfo, Actor actor) {
		if (actor.getNombre() == null || "".equals(actor.getNombre()))
			throw new BadRequestException("The name must not be null");
		
		if (actor.getNacionalidad()==null && actor.getFechaNacimiento()==null && actor.getFechaMuerte()==null && actor.getPremios()==null)
			throw new BadRequestException("The actors property is not editable.");

		repository.addActor(actor);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(actor.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(actor);			
		return resp.build();
	}
	
	
	@PUT
	@Consumes("application/json")
	public Response updateActor(Actor actor) {
		Actor oldactor = repository.getActor(actor.getId());
		if (oldactor == null) {
			throw new NotFoundException("The actor with id="+ actor.getId() +" was not found");			
		}
		
		// Update name
		if (actor.getNombre()!=null)
			oldactor.setNombre(actor.getNombre());
		
		// Update description
		if (actor.getNacionalidad()!=null)
			oldactor.setNacionalidad(actor.getNacionalidad());
		
		if (actor.getFechaNacimiento()!=null)
			oldactor.setFechaNacimiento(actor.getFechaNacimiento());
		
		if (actor.getFechaMuerte()!=null)
			oldactor.setFechaMuerte(actor.getFechaMuerte());
		
		if (actor.getPremios()!=null)
			oldactor.setPremios(actor.getPremios());
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeActor(@PathParam("id") String actorId) {
		Actor toberemoved=repository.getActor(actorId);
		if (toberemoved == null)
			throw new NotFoundException("The actor with id="+ actorId +" was not found");
		else
			repository.deleteActor(actorId);
		
		return Response.noContent().build();
	}

}
