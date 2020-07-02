package movieFinder.api.resources;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import movieFinder.modelo.Serie;
import movieFinder.modelo.Tipo;

@Path("/tipo")
public class TipoResource {
	
	public static TipoResource _instance=null;
	AudioVisualRepository repository;
	
	private TipoResource() {
		repository=MapAudioVisualRepository.getInstance();
	}
	
	public static TipoResource getInstance(){
		if(_instance==null)
			_instance=new TipoResource();
		return _instance; 
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addTipo(@Context UriInfo uriInfo, Tipo tipo) {
		if (tipo.getPelicula() == null || tipo.getSerie() == null)
			throw new BadRequestException("The movies or series must not be null");

		repository.addTipo(tipo);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(tipo.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(tipo);			
		return resp.build();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Tipo get(@PathParam("id") String tipoId)
	{
		Tipo tipo = repository.getTipo(tipoId);
		if (tipo == null) {
			throw new NotFoundException("The tipo with id="+ tipoId +" was not found");			
		}
		return tipo;
	}
	
	@POST	
	@Path("/{tipoId}/{peliculaId}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response addPelicula(@Context UriInfo uriInfo,@PathParam("tipoId") String tipoId, @PathParam("peliculaId") String peliculaId)
	{				
		
		Tipo tipo = repository.getTipo(tipoId);
		Pelicula pelicula = repository.getPelicula(peliculaId);
		
		if (tipo==null)
			throw new NotFoundException("The type with id=" + tipoId + " was not found");
		
		if (pelicula == null)
			throw new NotFoundException("The movie with id=" + peliculaId + " was not found");
		
		if (tipo.getPelicula(peliculaId)!=null)
			throw new BadRequestException("The movie is already included in the type.");
			
		repository.addPelicula(tipoId, peliculaId);		

		// Builds the response
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(tipoId);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(tipo);			
		return resp.build();
	}
	
	
	@DELETE
	@Path("/{tipoId}/{peliculaId}")
	public Response removePelicula(@PathParam("tipoId") String tipoId, @PathParam("peliculaId") String peliculaId) {
		Tipo tipo = repository.getTipo(tipoId);
		Pelicula pelicula = repository.getPelicula(peliculaId);
		
		if (tipo==null)
			throw new NotFoundException("The type with id=" + tipoId + " was not found");
		
		if (pelicula == null)
			throw new NotFoundException("The movie with id=" + peliculaId + " was not found");
		
		
		repository.removePelicula(tipoId, peliculaId);		
		
		return Response.noContent().build();
	}
	
	@POST	
	@Path("/{tipoId}/{serieId}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response addSerie(@Context UriInfo uriInfo,@PathParam("tipoId") String tipoId, @PathParam("serieId") String serieId)
	{				
		
		Tipo tipo = repository.getTipo(tipoId);
		Serie serie = repository.getSerie(serieId);
		
		if (tipo==null)
			throw new NotFoundException("The type with id=" + tipoId + " was not found");
		
		if (serie == null)
			throw new NotFoundException("The serie with id=" + serieId + " was not found");
		
		if (tipo.getPelicula(serieId)!=null)
			throw new BadRequestException("The serie is already included in the type.");
			
		repository.addSerie(tipoId, serieId);		

		// Builds the response
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(tipoId);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(tipo);			
		return resp.build();
	}
	
	
	@DELETE
	@Path("/{tipoId}/{serieId}")
	public Response removeSerie(@PathParam("tipoId") String tipoId, @PathParam("serieId") String serieId) {
		Tipo tipo = repository.getTipo(tipoId);
		Serie serie = repository.getSerie(serieId);
		
		if (tipo==null)
			throw new NotFoundException("The type with id=" + tipoId + " was not found");
		
		if (serie == null)
			throw new NotFoundException("The serie with id=" + serieId + " was not found");
		
		
		repository.removeSerie(tipoId, serieId);		
		
		return Response.noContent().build();
	}

}
