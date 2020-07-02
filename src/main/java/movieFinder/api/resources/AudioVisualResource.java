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
import movieFinder.modelo.AudioVisual;
import movieFinder.modelo.Playlist;
import movieFinder.modelo.Tipo;

@Path("/audiovisual")
public class AudioVisualResource {
	
	private static AudioVisualResource _instance=null;
	AudioVisualRepository repository;
	
	private AudioVisualResource() {
		repository=MapAudioVisualRepository.getInstance();

	}
	
	public static AudioVisualResource getInstance()
	{
		if(_instance==null)
				_instance=new AudioVisualResource();
		return _instance;
	}
	
	@GET
	@Produces("application/json")
	public Collection<AudioVisual> getAll()
	{
		return repository.getAllAudioVisuales();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public AudioVisual get(@PathParam("id") String audioVisualId)
	{
		AudioVisual audioVisual = repository.getAudioVisual(audioVisualId);
		if (audioVisual == null) {
			throw new NotFoundException("The AudioVisual with id="+ audioVisualId +" was not found");			
		}
		return audioVisual;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addAudioVisual(@Context UriInfo uriInfo, AudioVisual audioVisual) {
		if (audioVisual.getName() == null)
			throw new BadRequestException("The name must not be null");
		
		if (audioVisual.getTipo() == null && audioVisual.getActores() == null && audioVisual.getSoundtrack() == null)
			throw new BadRequestException("The audiovisuals property is not editable.");

		repository.addAudioVisual(audioVisual);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(audioVisual.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(audioVisual);			
		return resp.build();
	}
	
	
	@PUT
	@Consumes("application/json")
	public Response updateAudioVisual(AudioVisual audioVisual) {
		AudioVisual oldaudiovisual = repository.getAudioVisual(audioVisual.getId());
		if (oldaudiovisual == null) {
			throw new NotFoundException("The audiovisual with id="+ audioVisual.getId() +" was not found");			
		}
		
		// Update name
		if (audioVisual.getName()!=null)
			oldaudiovisual.setName(audioVisual.getName());;
		
		// Update description
		if (audioVisual.getTipo()!=null)
			oldaudiovisual.setTipo(audioVisual.getTipo());;
		
		if (audioVisual.getActores()!=null)
			oldaudiovisual.setActores(audioVisual.getActores());;
		
		if (audioVisual.getSoundtrack()!=null)
			oldaudiovisual.setSoundtrack(audioVisual.getSoundtrack());;
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeAudioVisual(@PathParam("id") String audioVisualId) {
		AudioVisual toberemoved=repository.getAudioVisual(audioVisualId);
		if (toberemoved == null)
			throw new NotFoundException("The audio visual with id="+ audioVisualId +" was not found");
		else
			repository.deleteAudioVisual(audioVisualId);
		
		return Response.noContent().build();
	}
	
	@POST	
	@Path("/{audioVisualId}/{playlistId}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response addPlaylist(@Context UriInfo uriInfo,@PathParam("audioVisualId") String audioVisualId, @PathParam("playlistId") String playlistId)
	{				
		
		AudioVisual audioVisual = repository.getAudioVisual(audioVisualId);
		Playlist playlist = repository.getPlaylist(playlistId);
		
		if (audioVisual==null)
			throw new NotFoundException("The audio visual with id=" + audioVisualId + " was not found");
		
		if (playlist == null)
			throw new NotFoundException("The soundtrack with id=" + playlistId + " was not found");
		
		if (audioVisual.getPlaylist(playlistId)!=null)
			throw new BadRequestException("The playlist is already included in the audio visual.");
			
		repository.addPlaylist(audioVisualId, playlistId);		

		// Builds the response
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(audioVisualId);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(audioVisual);			
		return resp.build();
	}
	
	
	@DELETE
	@Path("/{audioVisualId}/{playlistId}")
	public Response removePlaylist(@PathParam("audioVisualId") String audioVisualId, @PathParam("playlistId") String playlistId) {
		AudioVisual audioVisual = repository.getAudioVisual(audioVisualId);
		Playlist playlist = repository.getPlaylist(playlistId);
		
		if (audioVisual==null)
			throw new NotFoundException("The audio visual with id=" + audioVisualId + " was not found");
		
		if (playlist == null)
			throw new NotFoundException("The soundtrack with id=" + playlistId + " was not found");
		
		
		repository.removePlaylist(audioVisualId, playlistId);		
		
		return Response.noContent().build();
	}
	
	@POST	
	@Path("/{audioVisualId}/{tipoId}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response addTipo(@Context UriInfo uriInfo,@PathParam("audioVisualId") String audioVisualId, @PathParam("tipoId") String tipoId)
	{				
		
		AudioVisual audioVisual = repository.getAudioVisual(audioVisualId);
		Tipo tipo = repository.getTipo(tipoId);
		
		if (audioVisual==null)
			throw new NotFoundException("The audio visual with id=" + audioVisualId + " was not found");
		
		if (tipo == null)
			throw new NotFoundException("The type with id=" + tipoId + " was not found");
		
		if (audioVisual.getTipo(tipoId)!=null)
			throw new BadRequestException("The type is already included in the audio visual.");
			
		repository.addTipo(audioVisualId, tipoId);		

		// Builds the response
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(audioVisualId);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(audioVisual);			
		return resp.build();
	}
	
	
	@DELETE
	@Path("/{audioVisualId}/{tipoId}")
	public Response removeTipo(@PathParam("audioVisualId") String audioVisualId, @PathParam("tipoId") String tipoId) {
		AudioVisual audioVisual = repository.getAudioVisual(audioVisualId);
		Tipo tipo = repository.getTipo(tipoId);
		
		if (audioVisual==null)
			throw new NotFoundException("The audio visual with id=" + audioVisualId + " was not found");
		
		if (tipo == null)
			throw new NotFoundException("The type with id=" + tipoId + " was not found");
		
		
		repository.removeTipo(audioVisualId, tipoId);		
		
		return Response.noContent().build();
	}
	
	@POST	
	@Path("/{audioVisualId}/{actorId}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response addActor(@Context UriInfo uriInfo,@PathParam("audioVisualId") String audioVisualId, @PathParam("actorId") String actorId)
	{				
		
		AudioVisual audioVisual = repository.getAudioVisual(audioVisualId);
		Actor actor = repository.getActor(actorId);
		
		if (audioVisual==null)
			throw new NotFoundException("The audio visual with id=" + audioVisualId + " was not found");
		
		if (actor == null)
			throw new NotFoundException("The actor with id=" + actorId + " was not found");
		
		if (audioVisual.getActor(actorId)!=null)
			throw new BadRequestException("The actor is already included in the audio visual.");
			
		repository.addActor(audioVisualId, actorId);		

		// Builds the response
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(audioVisualId);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(audioVisual);			
		return resp.build();
	}
	
	
	@DELETE
	@Path("/{audioVisualId}/{actorId}")
	public Response removeActor(@PathParam("audioVisualId") String audioVisualId, @PathParam("actorId") String actorId) {
		AudioVisual audioVisual = repository.getAudioVisual(audioVisualId);
		Actor actor = repository.getActor(actorId);
		
		if (audioVisual==null)
			throw new NotFoundException("The audio visual with id=" + audioVisualId + " was not found");
		
		if (actor == null)
			throw new NotFoundException("The actor with id=" + actorId + " was not found");
		
		
		repository.removeActor(audioVisualId, actorId);		
		
		return Response.noContent().build();
	}

}
