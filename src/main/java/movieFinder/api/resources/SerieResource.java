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
import movieFinder.modelo.Serie;

@Path("/serie")
public class SerieResource {
	public static SerieResource _instance=null;
	AudioVisualRepository repository;
	
	private SerieResource() {
		repository=MapAudioVisualRepository.getInstance();
	}
	
	public static SerieResource getInstance(){
		if(_instance==null)
			_instance=new SerieResource();
		return _instance; 
	}
	
	@GET
	@Produces("application/json")
	public Collection<Serie> getAll()
	{
		return repository.getAllSeries();
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Serie get(@PathParam("id") String serieId)
	{
		Serie serie = repository.getSerie(serieId);
		if (serie == null) {
			throw new NotFoundException("The serie with id="+ serieId +" was not found");			
		}
		return serie;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addSerie(@Context UriInfo uriInfo, Serie serie) {
		if (serie.getTemporadas() == null)
			throw new BadRequestException("The seasons must not be null");
		
		if (serie.getEstreno()==null && serie.getUltFechaEmision()==null && serie.getCanal()==null)
			throw new BadRequestException("The series property is not editable.");

		repository.addSerie(serie);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(serie.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(serie);			
		return resp.build();
	}
	
	
	@PUT
	@Consumes("application/json")
	public Response updateSerie(Serie serie) {
		Serie oldserie = repository.getSerie(serie.getId());
		if (oldserie == null) {
			throw new NotFoundException("The serie with id="+ serie.getId() +" was not found");			
		}
		
		// Update name
		if (serie.getTemporadas()!=null)
			oldserie.setTemporadas(serie.getTemporadas());
		
		// Update description
		if (serie.getUltFechaEmision()!=null)
			oldserie.setUltFechaEmision(serie.getUltFechaEmision());
		
		if (serie.getEstreno()!=null)
			oldserie.setEstreno(serie.getEstreno());
		
		if (serie.getCanal()!=null)
			oldserie.setCanal(serie.getCanal());
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeSerie(@PathParam("id") String serieId) {
		Serie toberemoved=repository.getSerie(serieId);
		if (toberemoved == null)
			throw new NotFoundException("The serie with id="+ serieId +" was not found");
		else
			repository.deleteSerie(serieId);
		
		return Response.noContent().build();
	}

}
