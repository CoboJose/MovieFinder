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
import movieFinder.modelo.Song;


@Path("/songs")
public class SongResource {

	public static SongResource _instance=null;
	AudioVisualRepository repository;
	
	private SongResource(){
		repository=MapAudioVisualRepository.getInstance();
	}
	
	public static SongResource getInstance()
	{
		if(_instance==null)
			_instance=new SongResource();
		return _instance; 
	}
	
	@GET
	@Produces("application/json")
	public Collection<Song> getAll()
	{
		return repository.getAllSongs();
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Song get(@PathParam("id") String songId)
	{
		Song song = repository.getSong(songId);
		if (song == null) {
			throw new NotFoundException("The song with id="+ songId +" was not found");			
		}
		return song;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addSong(@Context UriInfo uriInfo, Song song) {
		if (song.getTitle() == null || "".equals(song.getTitle()))
			throw new BadRequestException("The name of the song must not be null");
		
		if (song.getAlbum()==null && song.getArtist()==null && song.getYear()==null)
			throw new BadRequestException("The songs property is not editable.");

		repository.addSong(song);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(song.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(song);			
		return resp.build();
	}
	
	
	@PUT
	@Consumes("application/json")
	public Response updateSong(Song song) {
		Song oldsong = repository.getSong(song.getId());
		if (oldsong == null) {
			throw new NotFoundException("The playlist with id="+ song.getId() +" was not found");			
		}
		
		// Update name
		if (song.getTitle()!=null)
			oldsong.setTitle(song.getTitle());
		
		// Update description
		if (song.getArtist()!=null)
			oldsong.setArtist(song.getArtist());
		
		if (song.getAlbum()!=null)
			oldsong.setAlbum(song.getAlbum());
		
		if (song.getYear()!=null)
			oldsong.setYear(song.getYear());
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeSong(@PathParam("id") String songId) {
		Song toberemoved=repository.getSong(songId);
		if (toberemoved == null)
			throw new NotFoundException("The song with id="+ songId +" was not found");
		else
			repository.deleteSong(songId);
		
		return Response.noContent().build();
	}
	
}
