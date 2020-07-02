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
import movieFinder.modelo.Pelicula;

@Path("/pelicula")
public class PeliculaResource {
	
	public static PeliculaResource _instance=null;
	AudioVisualRepository repository;
	
	private PeliculaResource() {
		repository=MapAudioVisualRepository.getInstance();
	}
	
	public static PeliculaResource getInstance(){
		if(_instance==null)
			_instance=new PeliculaResource();
		return _instance; 
	}
	
	@GET
	@Produces("application/json")
	public Collection<Pelicula> getAll()
	{
		return repository.getAllPeliculas();
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Pelicula get(@PathParam("id") String peliculaId)
	{
		Pelicula pelicula = repository.getPelicula(peliculaId);
		if (pelicula == null) {
			throw new NotFoundException("The movie with id="+ peliculaId +" was not found");			
		}
		return pelicula;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addPelicula(@Context UriInfo uriInfo, Pelicula pelicula) {
		if (pelicula.getDirector() == null || "".equals(pelicula.getDirector()))
			throw new BadRequestException("The director must not be null");
		
		if (pelicula.getFechaEstreno()==null)
			throw new BadRequestException("The movies property is not editable.");

		repository.addPelicula(pelicula);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(pelicula.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(pelicula);			
		return resp.build();
	}
	
	
	@PUT
	@Consumes("application/json")
	public Response updatePelicula(Pelicula pelicula) {
		Pelicula oldpelicula = repository.getPelicula(pelicula.getId());
		if (oldpelicula == null) {
			throw new NotFoundException("The pelicula with id="+ pelicula.getId() +" was not found");			
		}
		
		// Update name
		if (pelicula.getDirector()!=null)
			oldpelicula.setDirector(pelicula.getDirector());
		
		// Update description
		if (pelicula.getFechaEstreno()!=null)
			oldpelicula.setFechaEstreno(pelicula.getFechaEstreno());
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removePelicula(@PathParam("id") String peliculaId) {
		Pelicula toberemoved=repository.getPelicula(peliculaId);
		if (toberemoved == null)
			throw new NotFoundException("The movie with id="+ peliculaId +" was not found");
		else
			repository.deletePelicula(peliculaId);
		
		return Response.noContent().build();
	}

}
