package movieFinder.model.repository;

import java.util.Collection;

import movieFinder.modelo.Actor;
import movieFinder.modelo.AudioVisual;
import movieFinder.modelo.Pelicula;
import movieFinder.modelo.Playlist;
import movieFinder.modelo.Serie;
import movieFinder.modelo.Song;
import movieFinder.modelo.Tipo;

public interface AudioVisualRepository {

	// Actores
	public void addActor(Actor a);

	public Collection<Actor> getAllActores();

	public Actor getActor(String actorId);

	public void updateActor(Actor a);

	public void deleteActor(String actorId);
	
	//Series
	public void addSerie(Serie s);

	public Collection<Serie> getAllSeries();

	public Serie getSerie(String serieId);

	public void updateSerie(Serie s);

	public void deleteSerie(String serieId);
	
	//Peliculas
	public void addPelicula(Pelicula p);

	public Collection<Pelicula> getAllPeliculas();

	public Pelicula getPelicula(String peliculaId);

	public void updatePelicula(Pelicula p);

	public void deletePelicula(String peliculaId);
	
	//Tipo
	public void addTipo(Tipo t);
	
	public Tipo getTipo(String tipoId);
	
	public void addPelicula(String tipoId, String peliculaId);
	
	public void removePelicula(String tipoId, String peliculaId);
	
	public void addSerie(String tipoId, String serieId);
	
	public void removeSerie(String tipoId, String serieId);
	
	//Audiovisual
	public void addAudioVisual(AudioVisual av);

	public Collection<AudioVisual> getAllAudioVisuales();

	public AudioVisual getAudioVisual(String avId);

	public void updateAudioVisual(AudioVisual av);

	public void deleteAudioVisual(String avId);
	
	public void addPlaylist(String avId, String playlistId);
	
	public void removePlaylist(String avId, String playlistId);
	
	public void addTipo(String avId, String tipoId);

	public void removeTipo(String avId, String tipoId);

	public void addActor(String avId, String actorId);

	public void removeActor(String avId, String actorId);
	
	// Songs
	public void addSong(Song s);

	public Collection<Song> getAllSongs();

	public Song getSong(String songId);

	public void updateSong(Song s);

	public void deleteSong(String songId);

	// Playlists
	public void addPlaylist(Playlist p);

	public Collection<Playlist> getAllPlaylists();

	public Playlist getPlaylist(String playlistId);

	public void updatePlaylist(Playlist p);

	public void deletePlaylist(String playlistId);

	public Collection<Song> getAll(String playlistId);

	public void addSong(String playlistId, String songId);

	public void removeSong(String playlistId, String songId);

}
