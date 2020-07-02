package movieFinder.model.resource;

import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import movieFinder.model.tmdb.login.TMDBGuest;
import movieFinder.model.tmdb.login.TMDBLogin;
import movieFinder.model.tmdb.login.TMDBReqToken;
import movieFinder.model.tmdb.login.TMDBSession;
import movieFinder.model.tmdb.login.TMDBValoracion;
import movieFinder.model.tmdbMovies.TmdbMovieStatus;
import movieFinder.model.tmdbMovies.TmdbMovies;
import movieFinder.model.tmdbMovies.TmdbMoviesDetail;
import movieFinder.model.tmdbPerson.TmdbMoviePerson;
import movieFinder.model.tmdbWatchlist.TmdbAddWatchlist;
import movieFinder.model.tmdbWatchlist.TmdbWatchlist;

public class TmdbResource {

	private static final Logger log = Logger.getLogger(TmdbResource.class.getName());

    private final String uri = "https://api.themoviedb.org/3/";
    private final String apiKey = "425bc279cc1d2eb5637650f61e5fe6b9";
    
    public TmdbMovies searchMovie(String query) {
    	
    	ClientResource cr = null;
    	TmdbMovies res = null;
    	
    	try {
           cr = new ClientResource(uri+"search/movie?api_key="+apiKey+"&language="+"es-ES"+"&query="+query+"&page=1&include_adult=false");
           res= cr.get(TmdbMovies.class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving TmdbMovies from TMDB API: " + cr.getResponse().getStatus());
        }
    	
    	return res;
    }
    
    public TmdbMoviePerson getPerson(String query) {
    	
    	ClientResource cr = null;
    	TmdbMoviePerson res = null;
    	
    	try {
            cr = new ClientResource(uri+"search/person?api_key="+apiKey+"&query="+query+"&page=1");
            res= cr.get(TmdbMoviePerson.class);

         } catch (ResourceException re) {
             log.warning("Error when retrieving TmdbPerson from TMDB API: " + cr.getResponse().getStatus());
         }
    	
    	return res;
    }
    public TmdbMovies discoverMovie(String language,String page,String sortBy,String voteAverage,String personID,String genre,String year,String estreno) {
    	
    	String consulta = uri+"discover/movie?api_key="+apiKey;
    	ClientResource cr = null;
    	TmdbMovies res = null;
    	
    	if(page.equals("random")) {
    		Random r = new Random(); page = String.valueOf(r.nextInt((3 - 1) + 1) + 1) ;
    	}
    	
    	consulta += "&language="+language+"&page="+page;
    	
    	if(!sortBy.equals("noOrder"))
    		consulta += "&sort_by="+sortBy;
    	if(estreno.equals("estreno"))
    		consulta += "&primary_release_date.gte="+"2019-05-18";
    	if(sortBy.equals("vote_average.desc"))
    		consulta += "&vote_count.gte=1000";
    	if(!voteAverage.isEmpty())
    		consulta += "&vote_average.gte="+voteAverage;
    	if(!genre.equals("all"))
    		consulta += "&with_genres="+genre;
    	if(personID != null && !personID.isEmpty())
    		consulta += "&with_people="+personID;
    	if(!year.isEmpty())
    		consulta += "&year="+year;
    	
    	try {
           cr = new ClientResource(consulta);
           res= cr.get(TmdbMovies.class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving TmdbMovies from TMDB API: " + cr.getResponse().getStatus());
        }
    	
    	return res;
    }
    
    public TmdbWatchlist getWatchlist(String sessionID) {
    	
    	ClientResource cr = null;
    	TmdbWatchlist res = null;
    	
    	try {
           cr = new ClientResource(uri+"account/%7Baccount_id%7D/watchlist/movies?api_key="+apiKey+"&session_id="+sessionID+"&sort_by=created_at.asc");
           res= cr.get(TmdbWatchlist.class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving TmdbWatchlist from TMDB API: " + cr.getResponse().getStatus());
        }
    	
    	return res;
    }
    
    public void addWatchlist(String sessionID,Integer movieID) {
    	
    	ClientResource cr = null;
    	TmdbAddWatchlist body = new TmdbAddWatchlist();
    	body.setMediaType("movie");
    	body.setWatchlist(true);
    	body.setMediaId(movieID);
    	
    	try {
           cr = new ClientResource(uri+"account/%7Baccount_id%7D/watchlist?api_key="+apiKey+"&session_id="+sessionID);
           cr.post(body);

        } catch (ResourceException re) {
            log.warning("Error when adding TmdbWatchlist to TMDB API: " + cr.getResponse().getStatus());
        }
    	
    }
    
    public TMDBLogin requestToken() {
    	String consulta = uri+"authentication/token/new?api_key="+apiKey;
    	ClientResource cr = null;
    	TMDBLogin res = null;
    	
    	try {
    		cr = new ClientResource(consulta);
    		res = cr.get(TMDBLogin.class);
    	}catch(ResourceException re) {
    		log.warning("Error when retrieving Tmdb request token from TMDB API: " + cr.getResponse().getStatus());
    	}
    	
    	return res;
    }
    
    public String getSessionID(String reqTok){
    	
    	String res = null;
    	ClientResource cr = null;
    	TMDBSession tmdbSession = null;
    	TMDBReqToken reqToken = new TMDBReqToken();
		reqToken.setRequestToken(reqTok);
    	
    	try {
    		
    		cr = new ClientResource(uri + "authentication/session/new?api_key=" + apiKey);
    		Map<String, Object> headers = cr.getRequestAttributes();
            headers.put("Content-Type", "application/json");
            cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
            
    		tmdbSession = cr.post(reqToken,TMDBSession.class);
    		res = tmdbSession.getSessionId();
            
        } catch (ResourceException re) {
            log.warning("Error when Creating TMDB Session: " + cr.getResponse().getStatus());
        }
    	
    	return res;
    }
    
    public void puntuarPelicula(String sessionID, String id, TMDBValoracion cuerpo) {
    	ClientResource cr = null;
    	String url = uri + "movie/" + id + "/rating?api_key=" + apiKey + "&session_id=" + sessionID;
    	try {
    		cr = new ClientResource(url);
    		cr.post(cuerpo, TMDBValoracion.class);
    	} catch (ResourceException re) {
            log.warning("Error: " + cr.getResponse().getStatus());
        }
    }
    

	public TmdbMoviesDetail getMovie(String movieID) {
		
		ClientResource cr = null;
    	TmdbMoviesDetail res = null;
    	
    	try {
           cr = new ClientResource(uri+"movie/"+movieID+"?api_key="+apiKey+"&language=es-ES");
           res= cr.get(TmdbMoviesDetail.class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving TmdbMovies from TMDB API: " + cr.getResponse().getStatus());
        }
    	
    	return res;
	}
	
	public TmdbMovieStatus getMovieStatus(String sessionID,String movieID) {
		
		ClientResource cr = null;
		TmdbMovieStatus res = null;
    	
    	
    	try {
           cr = new ClientResource(uri+"movie/id/account_states?api_key="+apiKey+"&session_id="+sessionID);
           res = cr.get(TmdbMovieStatus.class);

        } catch (ResourceException re) {
            log.warning("Error when gettin TMDB movie status from TMDB API: " + cr.getResponse().getStatus());
        }
    	
    	return res;
	}
    
}
