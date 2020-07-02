package movieFinder.controller;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import movieFinder.model.resource.TmdbResource;
import movieFinder.model.tmdbMovies.TmdbMovies;

@SuppressWarnings("serial")
public class SearchMoviesController extends HttpServlet{
	
	private static final Logger log = Logger.getLogger(SearchMoviesController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    		

			String sessionID = req.getParameter("sessionID");
        	String query = (String) req.getParameter("query");
			String email = req.getParameter("email");

        	req.setAttribute("sessionID", sessionID);
        	
        	// Si es una busqueda al azar entra aquí
        	if(query == null) {
        		
        		String lang = req.getParameter("languages");
        		String sortBy = req.getParameter("sortBy");
        		String voteAverage = req.getParameter("voteAverage");
        		String person = req.getParameter("person");
        		String genre = req.getParameter("genre");
        		String year = req.getParameter("year");
        		String estreno = req.getParameter("estreno");
            	TmdbResource tmdbResource = new TmdbResource();
            	
            	String personID = "";
            	if(person != null && !person.isEmpty()) {
            		personID = String.valueOf(tmdbResource.getPerson(person).getResults().get(0).getId());
            	}
            	
            	TmdbMovies movies = tmdbResource.discoverMovie(lang,"random",sortBy,voteAverage,personID,genre,year,estreno);
            

                if (movies != null) {
                	if(movies.getTotalPages()==1) {
                		movies = tmdbResource.discoverMovie(lang,"1",sortBy,voteAverage,personID,genre,year,estreno);
                		if(movies.getTotalResults()==0) {
                			movies = tmdbResource.discoverMovie(lang,"1",sortBy,"",personID,genre,year,estreno);
                		}
                	}
                	req.setAttribute("movies", movies);
                	req.setAttribute("email", email);
                	req.setAttribute("languages", lang);
                	req.setAttribute("sortBy", sortBy);
                	req.setAttribute("voteAverage", voteAverage);
                	req.setAttribute("person", person);
                	req.setAttribute("genre", genre);
                	req.setAttribute("year", year);
                	req.setAttribute("estreno", estreno);
                	req.getRequestDispatcher("/resultadosGenerales.jsp").forward(req, resp);
                	
                } else {
                    log.info("The files returned are null...");
                    req.setAttribute("message", "No se han podido obtener las películas");
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                }
        	}
        	
        	// Si ha sido una busqueda normal entra aquí
        	else {
        		
        		TmdbResource tmdbResource = new TmdbResource();
            	TmdbMovies movies = tmdbResource.searchMovie(query);

                if (movies != null) {
                    
                	req.setAttribute("movies", movies);
                	req.setAttribute("query", query);
                	req.setAttribute("email", email);
                    req.getRequestDispatcher("/resultadosGenerales.jsp").forward(req, resp);
                } else {
                	req.setAttribute("email", email);
                    log.info("The files returned are null...");
                    req.setAttribute("message", "No se han podido obtener las películas");
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                }
        	}
        	
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req, resp);
    }

}
