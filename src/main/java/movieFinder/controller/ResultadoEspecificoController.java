package movieFinder.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movieFinder.model.aliexpress.AliExpress;
import movieFinder.model.resource.AliExpressResource;
import movieFinder.model.resource.TmdbResource;
import movieFinder.model.resource.YouTubeResource;
import movieFinder.model.tmdb.login.TMDBLogin;
import movieFinder.model.tmdb.login.TMDBValoracion;
import movieFinder.model.tmdbMovies.Result;
import movieFinder.model.tmdbMovies.TmdbMovieStatus;
import movieFinder.model.tmdbMovies.TmdbMovies;
import movieFinder.model.tmdbMovies.TmdbMoviesDetail;
import movieFinder.model.youtubeplaylist.YouTubePlaylist;
import movieFinder.model.youtubevideo.Item;
import movieFinder.model.youtubevideo.Youtube;

@SuppressWarnings("serial")
public class ResultadoEspecificoController extends HttpServlet{
	
	private static final Logger log = Logger.getLogger(ResultadoEspecificoController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		
		
		
		String sessionID = req.getParameter("sessionID");
		req.setAttribute("sessionID", sessionID);
		req.setAttribute("estreno", req.getParameter("estreno"));
		
		String email = req.getParameter("email");
		String title = req.getParameter("title");
		req.setAttribute("title", title);
		String movieID = req.getParameter("movieID");
		String message = req.getParameter("message");
		TmdbResource tmdbResource = new TmdbResource();
		
		
		//Puntuar
		if(req.getParameter("puntuacion") != null && !req.getParameter("puntuacion").isEmpty()) {
			
			Double puntuacion = Double.parseDouble(req.getParameter("puntuacion"));
			String id = req.getParameter("movieID");
			TMDBValoracion puntuar = new TMDBValoracion();
			puntuar.setValue(puntuacion);
			tmdbResource.puntuarPelicula(sessionID, id, puntuar);
			message = "La película ha sido puntuada con éxito con un: " + puntuar.getValue();
		}
		//Fin Puntuar
		
		// Añadir Watchlist
		else if(req.getParameter("addwl") != null && !req.getParameter("addwl").isEmpty()) {
			
			Integer id = Integer.parseInt(req.getParameter("movieID"));
			tmdbResource.addWatchlist(sessionID, id);
			message = "Se ha añadido la película a su Watchlist";
		}
		//Fin Añadir Watchlist
		
		
    	TmdbMoviesDetail movie = tmdbResource.getMovie(movieID);
    	
    	//Para probar una parte específica descomentar lo que necesites y al acabar volver a comentarlo, son recursos limitados
    	//Solo descomentar las líneas necesarias, es decir resource y el get, en el if y el req.set
    	//Descomentar sólo la parte que vayais a utilizar y al acabar volver a comentar EXACTAMENTE igual
    	
		YouTubeResource ytResource = new YouTubeResource();
        Item videos = ytResource.getVideos(title+" trailer pelicula");
        movieFinder.model.youtubeplaylist.Item playlists = ytResource.getPlaylist(title);
        
        AliExpressResource aeResource = new AliExpressResource();
        AliExpress products = aeResource.getProducts(title);
        
        
        if (movie != null && products != null  && videos != null && playlists != null) {
            
        	req.setAttribute("movieID", movieID);
        	req.setAttribute("title", title);
        	req.setAttribute("movie", movie);
        	
        	req.setAttribute("videos",videos);
        	req.setAttribute("playlists",playlists);
        	req.setAttribute("products",products);
        	if(message!=null) {
        		req.setAttribute("message", message);
        	}
        	if(email!=null && !email.isEmpty()) {
        		req.setAttribute("email", email);
        	}
        	req.getRequestDispatcher("/resultadoEspecifico.jsp").forward(req, resp);
        	
        } else {
        	if(email!=null && !email.isEmpty()) {
        		req.setAttribute("email", email);
        	}
            log.info("The files returned are null...");
            req.setAttribute("message", "No se han podido obtener los resultados");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req, resp);
    }
}
