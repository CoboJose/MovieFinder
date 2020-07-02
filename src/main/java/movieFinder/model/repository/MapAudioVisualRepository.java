package movieFinder.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import movieFinder.modelo.Actor;
import movieFinder.modelo.AudioVisual;
import movieFinder.modelo.Pelicula;
import movieFinder.modelo.Playlist;
import movieFinder.modelo.Serie;
import movieFinder.modelo.Song;
import movieFinder.modelo.Tipo;

public class MapAudioVisualRepository implements AudioVisualRepository {
	
	Map<String, Playlist> playlistMap;
	Map<String, Song> songMap;
	Map<String, Actor> actorMap;
	Map<String, AudioVisual> audioVisualMap;
	Map<String, Pelicula> peliculaMap;
	Map<String, Serie> serieMap;
	Map<String, Tipo> tipoMap;
	private static MapAudioVisualRepository instance=null;
	private int index=0;			// Index to create identifiers.
	
	
	public static MapAudioVisualRepository getInstance() {
		
		if (instance==null) {
			instance = new MapAudioVisualRepository();
			instance.init();
		}
		
		return instance;
	}
	
public void init() {
		
		playlistMap = new HashMap<String,Playlist>();
		songMap = new HashMap<String,Song>();
		actorMap = new HashMap<String,Actor>();
		audioVisualMap = new HashMap<String,AudioVisual>();
		peliculaMap = new HashMap<String,Pelicula>();
		serieMap = new HashMap<String,Serie>();
		tipoMap = new HashMap<String,Tipo>();
		
		// Create songs
		Song someBodyToLove=new Song();
		someBodyToLove.setTitle("Some Body To Love");
		someBodyToLove.setArtist("Queen");
		someBodyToLove.setYear("1976");
		someBodyToLove.setAlbum("Bohemian Rhapsody Soundtrack");
		addSong(someBodyToLove);
		
		Song doingAllRightRevisited=new Song();
		doingAllRightRevisited.setTitle("Doing All Right...Revisited");
		doingAllRightRevisited.setArtist("Queen");
		doingAllRightRevisited.setYear("1975");
		doingAllRightRevisited.setAlbum("Bohemian Rhapsody Soundtrack");
		addSong(doingAllRightRevisited);
		
		Song keepYourselfAlive=new Song();
		keepYourselfAlive.setTitle("Keep Yourself Alive");
		keepYourselfAlive.setArtist("Queen");
		keepYourselfAlive.setYear("1974");
		keepYourselfAlive.setAlbum("Bohemian Rhapsody Soundtrack");
		addSong(keepYourselfAlive);
		
		Song killerQueen=new Song();
		killerQueen.setTitle("Killer Queen");
		killerQueen.setArtist("Queen");
		killerQueen.setAlbum("Bohemian Rhapsody Soundtrack");
		killerQueen.setYear("1974");
		addSong(killerQueen);
		
		Song fatBottomedGirls=new Song();
		fatBottomedGirls.setTitle("Fat Bottomed Girls");
		fatBottomedGirls.setArtist("Queen");
		fatBottomedGirls.setYear("1979");
		fatBottomedGirls.setAlbum("Bohemian Rhapsody Soundtrack");
		addSong(fatBottomedGirls);
		
		Song bohemianRhapsody=new Song();
		bohemianRhapsody.setTitle("Bohemian Rhapsody");
		bohemianRhapsody.setArtist("Queen");
		bohemianRhapsody.setYear("1975");
		bohemianRhapsody.setAlbum("Bohemian Rhapsody Soundtrack");
		addSong(bohemianRhapsody);
		
		Song weWillRockYou=new Song();
		weWillRockYou.setTitle("We Will Rock You");
		weWillRockYou.setArtist("Queen");
		weWillRockYou.setYear("1977");
		weWillRockYou.setAlbum("Bohemian Rhapsody Soundtrack");
		addSong(weWillRockYou);
		
		Song iWantToBreakFree=new Song();
		iWantToBreakFree.setTitle("I Want To Break Free");
		iWantToBreakFree.setArtist("Queen");
		iWantToBreakFree.setYear("1979");
		iWantToBreakFree.setAlbum("Bohemian Rhapsody Soundtrack");
		addSong(iWantToBreakFree);
		
		Song radioGaGa=new Song();
		radioGaGa.setTitle("Radio Ga Ga");
		radioGaGa.setArtist("Queen");
		radioGaGa.setYear("1985");
		radioGaGa.setAlbum("Bohemian Rhapsody Soundtrack");
		addSong(radioGaGa);
		
		Song weAreTheChampions=new Song();
		weAreTheChampions.setTitle("We Are The Champions");
		weAreTheChampions.setArtist("Queen");
		weAreTheChampions.setYear("1979");
		weAreTheChampions.setAlbum("Bohemian Rhapsody Soundtrack");
		addSong(weAreTheChampions);
		
		Song dontStopMeNow=new Song();
		dontStopMeNow.setTitle("Dont Stop Me Now");
		dontStopMeNow.setArtist("Queen");
		dontStopMeNow.setYear("1979");
		dontStopMeNow.setAlbum("Bohemian Rhapsody Soundtrack");
		addSong(dontStopMeNow);
		
		Song theShowMustGoOn=new Song();
		theShowMustGoOn.setTitle("The Show Must Go On");
		theShowMustGoOn.setArtist("Queen");
		theShowMustGoOn.setYear("1991");
		theShowMustGoOn.setAlbum("Bohemian Rhapsody Soundtrack");
		addSong(theShowMustGoOn);
		
		// Create playlists
		Playlist bohemianRhapsodyST=new Playlist();
		bohemianRhapsodyST.setName("Bohemian Rhapsody");
		bohemianRhapsodyST.setDescription("Queen");
		addPlaylist(bohemianRhapsodyST);
		
		Playlist playlist = new Playlist();
		playlist.setName("Favourites");
		playlist.setDescription("A sample playlist");
		addPlaylist(playlist);
		
		Playlist otra = new Playlist();
		otra.setName("Favourites");
		otra.setDescription("A sample playlist");
		addPlaylist(otra);
		
		// Add songs to playlists
		addSong(bohemianRhapsodyST.getId(), someBodyToLove.getId());
		addSong(bohemianRhapsodyST.getId(), doingAllRightRevisited.getId());
		addSong(bohemianRhapsodyST.getId(), killerQueen.getId());
		addSong(bohemianRhapsodyST.getId(), keepYourselfAlive.getId());
		addSong(bohemianRhapsodyST.getId(), fatBottomedGirls.getId());
		addSong(bohemianRhapsodyST.getId(), bohemianRhapsody.getId());
		addSong(bohemianRhapsodyST.getId(), weWillRockYou.getId());
		addSong(bohemianRhapsodyST.getId(), iWantToBreakFree.getId());
		addSong(bohemianRhapsodyST.getId(), radioGaGa.getId());
		addSong(bohemianRhapsodyST.getId(), weAreTheChampions.getId());
		addSong(bohemianRhapsodyST.getId(), dontStopMeNow.getId());
		addSong(bohemianRhapsodyST.getId(), theShowMustGoOn.getId());
		
		addSong(playlist.getId(), keepYourselfAlive.getId());
		addSong(playlist.getId(), fatBottomedGirls.getId());
		
		addSong(otra.getId(), keepYourselfAlive.getId());
		addSong(otra.getId(), fatBottomedGirls.getId());
		
		//Crear actores
		Actor ramiMalek=new Actor();
		ramiMalek.setNombre("Rami Malek");
		ramiMalek.setNacionalidad("Estadounidense");
		ramiMalek.setFechaNacimiento("12-5-1981");
		ramiMalek.setFechaMuerte("");
		ramiMalek.setPremios("Óscar y Globo de Oro");
		addActor(ramiMalek);
		
		Actor gwilymLee=new Actor();
		gwilymLee.setNombre("Gwilym Lee");
		gwilymLee.setNacionalidad("Estadounidense");
		gwilymLee.setFechaNacimiento("24-11-1983");
		gwilymLee.setFechaMuerte("");
		gwilymLee.setPremios("");
		addActor(gwilymLee);
		
		Actor lucyBoynton=new Actor();
		lucyBoynton.setNombre("Lucy Boynton");
		lucyBoynton.setNacionalidad("Estadounidense");
		lucyBoynton.setFechaNacimiento("17-1-1994");
		lucyBoynton.setFechaMuerte("");
		lucyBoynton.setPremios("");
		addActor(lucyBoynton);
		
		//Crear Pelicula
		Pelicula bohemianRhapsodyFilm=new Pelicula();
		bohemianRhapsodyFilm.setDirector("Bryan Singer");
		bohemianRhapsodyFilm.setFechaEstreno("24-10-2018");
		addPelicula(bohemianRhapsodyFilm);
		
		Pelicula endGame=new Pelicula();
		endGame.setDirector("Joe Russo");
		endGame.setFechaEstreno("22-04-2019");
		addPelicula(endGame);
		
		Pelicula avatar=new Pelicula();
		avatar.setDirector("James Cameron");
		avatar.setFechaEstreno("12-09-2011");
		addPelicula(avatar);
		
		//Crear Serie
		Serie mrRobot=new Serie();
		mrRobot.setTemporadas(4);
		mrRobot.setEstreno("25-5-2015");
		mrRobot.setCanal("Movistar Series");
		mrRobot.setUltFechaEmision("13-11-2017");
		addSerie(mrRobot);
		
		Serie skam=new Serie();
		skam.setTemporadas(2);
		skam.setEstreno("20-10-2018");
		skam.setCanal("Movistar Series");
		addSerie(skam);
		
		Serie alliAbajo=new Serie();
		alliAbajo.setTemporadas(5);
		alliAbajo.setEstreno("16-02-2014");
		alliAbajo.setCanal("Antena 3");
		alliAbajo.setUltFechaEmision("21-05-2019");
		addSerie(alliAbajo);
		
		//Crear Tipo
		Tipo pelicula=new Tipo();
		addTipo(pelicula);
		addPelicula(pelicula.getId(),bohemianRhapsodyFilm.getId());
		
		Tipo serie=new Tipo();
		addTipo(serie);
		addSerie(serie.getId(),mrRobot.getId());
		
		//AudioVisual
		AudioVisual bH=new AudioVisual();
		bH.setName("Bohemian Rhapsody");
		addAudioVisual(bH);
		addActor(bH.getId(),ramiMalek.getId());
		addActor(bH.getId(),gwilymLee.getId());
		addActor(bH.getId(),lucyBoynton.getId());
		addPlaylist(bH.getId(),bohemianRhapsodyST.getId());
		addTipo(bH.getId(),pelicula.getId());
		
		AudioVisual mR=new AudioVisual();
		mR.setName("Mr. Robot");
		addAudioVisual(mR);
		addActor(mR.getId(),ramiMalek.getId());
		addTipo(mR.getId(),serie.getId());
		
		AudioVisual mR2=new AudioVisual();
		mR2.setName("Mr. Robot");
		addAudioVisual(mR2);
		addActor(mR2.getId(),ramiMalek.getId());
		addTipo(mR2.getId(),serie.getId());
		
		
	}

	//Actor 

	@Override
	public void addActor(Actor a) {
		String id = "a" + index++;	
		a.setId(id);
		actorMap.put(id,a);

	}

	@Override
	public Collection<Actor> getAllActores() {
		return actorMap.values();
	}

	@Override
	public Actor getActor(String actorId) {
		// TODO Auto-generated method stub
		return actorMap.get(actorId);
	}

	@Override
	public void updateActor(Actor a) {
		// TODO Auto-generated method stub
		actorMap.put(a.getId(), a);

	}

	@Override
	public void deleteActor(String actorId) {
		// TODO Auto-generated method stub
		actorMap.remove(actorId);

	}
	//Serie

	@Override
	public void addSerie(Serie s) {
		String id = "s" + index++;	
		s.setId(id);
		serieMap.put(id,s);

	}

	@Override
	public Collection<Serie> getAllSeries() {
		// TODO Auto-generated method stub
		return serieMap.values();
	}

	@Override
	public Serie getSerie(String serieId) {
		// TODO Auto-generated method stub
		return serieMap.get(serieId);
	}

	@Override
	public void updateSerie(Serie s) {
		// TODO Auto-generated method stub
		serieMap.put(s.getId(), s);

	}

	@Override
	public void deleteSerie(String serieId) {
		// TODO Auto-generated method stub
		serieMap.remove(serieId);

	}
	//Pelicula

	@Override
	public void addPelicula(Pelicula p) {
		String id = "pe" + index++;	
		p.setId(id);
		peliculaMap.put(id,p);

	}

	@Override
	public Collection<Pelicula> getAllPeliculas() {
		// TODO Auto-generated method stub
		return peliculaMap.values();
	}

	@Override
	public Pelicula getPelicula(String peliculaId) {
		// TODO Auto-generated method stub
		return peliculaMap.get(peliculaId);
	}

	@Override
	public void updatePelicula(Pelicula p) {
		// TODO Auto-generated method stub
		peliculaMap.put(p.getId(),p);

	}

	@Override
	public void deletePelicula(String peliculaId) {
		// TODO Auto-generated method stub
		peliculaMap.remove(peliculaId);

	}
	//AudioVisual

	@Override
	public void addAudioVisual(AudioVisual av) {
		String id = "av" + index++;	
		av.setId(id);
		audioVisualMap.put(id,av);

	}

	@Override
	public Collection<AudioVisual> getAllAudioVisuales() {
		// TODO Auto-generated method stub
		return audioVisualMap.values();
	}

	@Override
	public AudioVisual getAudioVisual(String avId) {
		// TODO Auto-generated method stub
		return audioVisualMap.get(avId);
	}

	@Override
	public void updateAudioVisual(AudioVisual av) {
		// TODO Auto-generated method stub
		audioVisualMap.put(av.getId(), av);

	}

	@Override
	public void deleteAudioVisual(String avId) {
		// TODO Auto-generated method stub
		audioVisualMap.remove(avId);

	}

	@Override
	public void addActor(String avId, String actorId) {
		// TODO Auto-generated method stub
		AudioVisual av = getAudioVisual(avId);
		av.addActor(actorMap.get(actorId));

	}

	@Override
	public void removeActor(String avId, String actorId) {
		// TODO Auto-generated method stub
		getAudioVisual(avId).deleteActor(actorId);

	}
	
	@Override
	public void addTipo(String avId, String tipoId) {
		// TODO Auto-generated method stub
		AudioVisual av = getAudioVisual(avId);
		av.addTipo(tipoMap.get(tipoId));
		
	}

	@Override
	public void removeTipo(String avId, String tipoId) {
		// TODO Auto-generated method stub
		getAudioVisual(avId).deleteTipo(tipoId);

	}

	@Override
	public void addPlaylist(String avId, String playlistId) {
		// TODO Auto-generated method stub
		AudioVisual av = getAudioVisual(avId);
		av.addPlaylist(playlistMap.get(playlistId));
		
	}

	@Override
	public void removePlaylist(String avId, String playlistId) {
		// TODO Auto-generated method stub
		getAudioVisual(avId).deletePlaylist(playlistId);
		
	}
	
	//Song

	@Override
	public void addSong(Song s) {
		String id = "so" + index++;	
		s.setId(id);
		songMap.put(id,s);

	}

	@Override
	public Collection<Song> getAllSongs() {
		// TODO Auto-generated method stub
		return songMap.values();
	}

	@Override
	public Song getSong(String songId) {
		// TODO Auto-generated method stub
		return songMap.get(songId);
	}

	@Override
	public void updateSong(Song s) {
		// TODO Auto-generated method stub
		songMap.put(s.getId(),s);

	}

	@Override
	public void deleteSong(String songId) {
		// TODO Auto-generated method stub
		songMap.remove(songId);

	}
	//Playlist

	@Override
	public void addPlaylist(Playlist p) {
		String id = "p" + index++;	
		p.setId(id);
		playlistMap.put(id,p);

	}

	@Override
	public Collection<Playlist> getAllPlaylists() {
		// TODO Auto-generated method stub
		return playlistMap.values();
	}

	@Override
	public Playlist getPlaylist(String playlistId) {
		// TODO Auto-generated method stub
		return playlistMap.get(playlistId);
	}

	@Override
	public void updatePlaylist(Playlist p) {
		// TODO Auto-generated method stub
		playlistMap.put(p.getId(), p);

	}

	@Override
	public void deletePlaylist(String playlistId) {
		// TODO Auto-generated method stub
		playlistMap.remove(playlistId);

	}

	@Override
	public Collection<Song> getAll(String playlistId) {
		// TODO Auto-generated method stub
		return getPlaylist(playlistId).getSongs();
	}

	@Override
	public void addSong(String playlistId, String songId) {
		// TODO Auto-generated method stub
		Playlist playlist = getPlaylist(playlistId);
		playlist.addSong(songMap.get(songId));

	}

	@Override
	public void removeSong(String playlistId, String songId) {
		// TODO Auto-generated method stub
		getPlaylist(playlistId).deleteSong(songId);

	}
	
	//Tipo

	@Override
	public void addTipo(Tipo t) {
		String id = "t" + index++;	
		t.setId(id);
		tipoMap.put(id,t);
		
	}
	
	public Tipo getTipo(String tipoId) {
		return tipoMap.get(tipoId);
	}

	@Override
	public void addPelicula(String tipoId, String peliculaId) {
		// TODO Auto-generated method stub
		Tipo tipo = getTipo(tipoId);
		tipo.addPelicula(peliculaMap.get(peliculaId));
		
	}

	@Override
	public void removePelicula(String tipoId, String peliculaId) {
		// TODO Auto-generated method stub
		getTipo(tipoId).deletePelicula(peliculaId);
		
	}

	@Override
	public void addSerie(String tipoId, String serieId) {
		// TODO Auto-generated method stub
		Tipo tipo = getTipo(tipoId);
		tipo.addSerie(serieMap.get(serieId));
		
	}

	@Override
	public void removeSerie(String tipoId, String serieId) {
		// TODO Auto-generated method stub
		getTipo(tipoId).deleteSerie(serieId);
		
	}

}
