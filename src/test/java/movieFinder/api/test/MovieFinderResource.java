package movieFinder.api.test;

import java.util.logging.Logger;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import movieFinder.modelo.*;


public class MovieFinderResource {
	private static final Logger log = Logger.getLogger(MovieFinderResource.class.getName());

    private final String uri = "https://moviefinder-es.appspot.com/api/";
    
    public MovieFinderResource() {
    	
    }
    /* AudioVisual*/
    public AudioVisual[] getAudiovisuales() {
        ClientResource cr = null;
        AudioVisual[] audiovisuales = null;
        try {
            cr = new ClientResource(uri + "audiovisual");
            audiovisuales = cr.get(AudioVisual[].class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving all audiovisuals: " + cr.getResponse().getStatus());
        }

        return audiovisuales;

    }
    
    public AudioVisual getAudiovisuales(String id) {
        ClientResource cr = null;
        AudioVisual audiovisuales = null;
        try {
            cr = new ClientResource(uri + "audiovisual/" + id);
            audiovisuales = cr.get(AudioVisual.class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving audiovisual: " + cr.getResponse().getStatus());
        }

        return audiovisuales;

    }
    
    public String insertAudioVisual(AudioVisual av) {

        ClientResource cr = null;
        String newId = null;
        try {
            cr = new ClientResource(uri + "audiovisual");
            AudioVisual newAV = cr.post(av, AudioVisual.class);
            newId = newAV.getId();
        } catch (ResourceException re) {
            log.warning("Error when inserting audiovisual: " + cr.getResponse().getStatus());
        }
        return newId;
    }
    
    public String updateAudioVisual(AudioVisual av) {
    	
    	ClientResource cr = null;
        String id = null;
        try {
            cr = new ClientResource(uri + "audiovisual");
            cr.put(av, AudioVisual.class);
            id = av.getId();
        } catch (ResourceException re) {
            log.warning("Error when updating audiovisual: " + cr.getResponse().getStatus());
        }
        return id;
    }
    
    public String deleteAudioVisual(String id) {
    	ClientResource cr = null;
    	String res = null;
    	try {
            cr = new ClientResource(uri + "audiovisual/" + id);
            cr.delete(AudioVisual.class);
            res="";
        } catch (ResourceException re) {
            log.warning("Error when deleting audiovisual: " + cr.getResponse().getStatus());
        }
        return res;
    }
    /* Series */
    public Serie[] getSeries() {
        ClientResource cr = null;
        Serie[] series = null;
        try {
            cr = new ClientResource(uri + "serie");
            series = cr.get(Serie[].class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving all series: " + cr.getResponse().getStatus());
        }

        return series;

    }
    
    public Serie getSeries(String id) {
        ClientResource cr = null;
        Serie series = null;
        try {
            cr = new ClientResource(uri + "serie/" + id);
            series = cr.get(Serie.class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving serie: " + cr.getResponse().getStatus());
        }

        return series;

    }
    
    public String insertSerie(Serie s) {

        ClientResource cr = null;
        String newId = null;
        try {
            cr = new ClientResource(uri + "serie");
            Serie newSe = cr.post(s, Serie.class);
            newId = newSe.getId();
        } catch (ResourceException re) {
            log.warning("Error when inserting serie: " + cr.getResponse().getStatus());
        }
        return newId;
    }
    
    public String updateSerie(Serie s) {
    	
    	ClientResource cr = null;
        String id = null;
        try {
            cr = new ClientResource(uri + "serie");
            cr.put(s, Serie.class);
            id = s.getId();
        } catch (ResourceException re) {
            log.warning("Error when updating serie: " + cr.getResponse().getStatus());
        }
        return id;
    }
    
    public String deleteSerie(String id) {
    	ClientResource cr = null;
    	String res = null;
    	try {
            cr = new ClientResource(uri + "serie/" + id);
            cr.delete(Serie.class);
            res="";
        } catch (ResourceException re) {
            log.warning("Error when deleting serie: " + cr.getResponse().getStatus());
        }
        return res;
    }
    /* Peliculas */
    public Pelicula[] getPeliculas() {
        ClientResource cr = null;
        Pelicula[] peliculas = null;
        try {
            cr = new ClientResource(uri + "pelicula");
            peliculas = cr.get(Pelicula[].class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving all movies: " + cr.getResponse().getStatus());
        }

        return peliculas;

    }
    
    public Pelicula getPeliculas(String id) {
        ClientResource cr = null;
        Pelicula peliculas = null;
        try {
            cr = new ClientResource(uri + "pelicula/" + id);
            peliculas = cr.get(Pelicula.class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving movie: " + cr.getResponse().getStatus());
        }

        return peliculas;

    }
    
    public String insertPelicula(Pelicula p) {

        ClientResource cr = null;
        String newId = null;
        try {
            cr = new ClientResource(uri + "pelicula");
            Pelicula newPe = cr.post(p, Pelicula.class);
            newId = newPe.getId();
        } catch (ResourceException re) {
            log.warning("Error when inserting movie: " + cr.getResponse().getStatus());
        }
        return newId;
    }
    
    public String updatePelicula(Pelicula p) {
    	
    	ClientResource cr = null;
        String id = null;
        try {
            cr = new ClientResource(uri + "pelicula");
            cr.put(p, Pelicula.class);
            id = p.getId();
        } catch (ResourceException re) {
            log.warning("Error when updating movie: " + cr.getResponse().getStatus());
        }
        return id;
    }
    
    public String deletePelicula(String id) {
    	ClientResource cr = null;
    	String res = null;
    	try {
            cr = new ClientResource(uri + "pelicula/" + id);
            cr.delete(Pelicula.class);
            res="";
        } catch (ResourceException re) {
            log.warning("Error when deleting movie: " + cr.getResponse().getStatus());
        }
        return res;
    }
    /* Actor */
    public Actor[] getActores() {
        ClientResource cr = null;
        Actor[] actores = null;
        try {
            cr = new ClientResource(uri + "actor");
            actores = cr.get(Actor[].class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving all actors: " + cr.getResponse().getStatus());
        }

        return actores;

    }
    
    public Actor getActores(String id) {
        ClientResource cr = null;
        Actor actores = null;
        try {
            cr = new ClientResource(uri + "actor/" + id);
            actores = cr.get(Actor.class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving actor: " + cr.getResponse().getStatus());
        }

        return actores;

    }
    
    public String insertActor(Actor a) {

        ClientResource cr = null;
        String newId = null;
        try {
            cr = new ClientResource(uri + "actor");
            Actor newA = cr.post(a, Actor.class);
            newId = newA.getId();
        } catch (ResourceException re) {
            log.warning("Error when inserting actor: " + cr.getResponse().getStatus());
        }
        return newId;
    }
    
    public String updateActor(Actor a) {
    	
    	ClientResource cr = null;
        String id = null;
        try {
            cr = new ClientResource(uri + "actor");
            cr.put(a, Actor.class);
            id = a.getId();
        } catch (ResourceException re) {
            log.warning("Error when updating actor: " + cr.getResponse().getStatus());
        }
        return id;
    }
    
    public String deleteActor(String id) {
    	ClientResource cr = null;
    	String res = null;
    	try {
            cr = new ClientResource(uri + "actor/" + id);
            cr.delete(Actor.class);
            res="";
        } catch (ResourceException re) {
            log.warning("Error when deleting actor: " + cr.getResponse().getStatus());
        }
        return res;
    }
    /* Song */
    public Song[] getSongs() {
        ClientResource cr = null;
        Song[] songs = null;
        try {
            cr = new ClientResource(uri + "songs");
            songs = cr.get(Song[].class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving all songs: " + cr.getResponse().getStatus());
        }

        return songs;

    }
    
    public Song getSongs(String id) {
        ClientResource cr = null;
        Song songs = null;
        try {
            cr = new ClientResource(uri + "songs/" + id);
            songs = cr.get(Song.class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving song: " + cr.getResponse().getStatus());
        }

        return songs;

    }
    
    public String insertSong(Song s) {

        ClientResource cr = null;
        String newId = null;
        try {
            cr = new ClientResource(uri + "songs");
            Song newS = cr.post(s, Song.class);
            newId = newS.getId();
        } catch (ResourceException re) {
            log.warning("Error when inserting song: " + cr.getResponse().getStatus());
        }
        return newId;
    }
    
    public String updateSong(Song s) {
    	
    	ClientResource cr = null;
        String id = null;
        try {
            cr = new ClientResource(uri + "songs");
            cr.put(s, Song.class);
            id = s.getId();
        } catch (ResourceException re) {
            log.warning("Error when updating song: " + cr.getResponse().getStatus());
        }
        return id;
    }
    
    public String deleteSong(String id) {
    	ClientResource cr = null;
    	String res = null;
    	try {
            cr = new ClientResource(uri + "songs/" + id);
            cr.delete(Song.class);
            res="";
        } catch (ResourceException re) {
            log.warning("Error when deleting song: " + cr.getResponse().getStatus());
        }
        return res;
    }
    /* Playlist */
    public Playlist[] getPlaylists() {
        ClientResource cr = null;
        Playlist[] lists = null;
        try {
            cr = new ClientResource(uri + "lists");
            lists = cr.get(Playlist[].class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving all playlists: " + cr.getResponse().getStatus());
        }

        return lists;

    }
    
    public Playlist getPlaylists(String id) {
        ClientResource cr = null;
        Playlist lists = null;
        try {
            cr = new ClientResource(uri + "lists/" + id);
            lists = cr.get(Playlist.class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving playlist: " + cr.getResponse().getStatus());
        }

        return lists;

    }
    
    public String insertPlaylist(Playlist p) {

        ClientResource cr = null;
        String newId = null;
        try {
            cr = new ClientResource(uri + "lists");
            Playlist newP = cr.post(p, Playlist.class);
            newId = newP.getId();
        } catch (ResourceException re) {
            log.warning("Error when inserting playlist: " + cr.getResponse().getStatus());
        }
        return newId;
    }
    
    public String updatePlaylist(Playlist p) {
    	
    	ClientResource cr = null;
        String id = null;
        try {
            cr = new ClientResource(uri + "lists");
            cr.put(p, Playlist.class);
            id = p.getId();
        } catch (ResourceException re) {
            log.warning("Error when updating playlist: " + cr.getResponse().getStatus());
        }
        return id;
    }
    
    public String deletePlaylist(String id) {
    	ClientResource cr = null;
    	String res = null;
    	try {
            cr = new ClientResource(uri + "lists/" + id);
            cr.delete(Playlist.class);
            res="";
        } catch (ResourceException re) {
            log.warning("Error when deleting playlist: " + cr.getResponse().getStatus());
        }
        return res;
    }
    
    /* Tipo */
    public Tipo getTipo(String id) {
        ClientResource cr = null;
        Tipo tipo = null;
        try {
            cr = new ClientResource(uri + "tipo/" + id);
            tipo = cr.get(Tipo.class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving type: " + cr.getResponse().getStatus());
        }

        return tipo;

    }
    
    public String insertTipo(Tipo t) {

        ClientResource cr = null;
        String newId = null;
        try {
            cr = new ClientResource(uri + "tipo");
            Tipo newT = cr.post(t, Tipo.class);
            newId = newT.getId();
        } catch (ResourceException re) {
            log.warning("Error when inserting tipo: " + cr.getResponse().getStatus());
        }
        return newId;
    }

}
