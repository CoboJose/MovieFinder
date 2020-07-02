package movieFinder.controller;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movieFinder.model.google.calendar.GoogleCalendar;
import movieFinder.model.resource.GoogleCalendarResource;
import movieFinder.model.resource.TmdbResource;
import movieFinder.model.tmdb.login.TMDBLogin;
import movieFinder.model.tmdbWatchlist.TmdbWatchlist;

@SuppressWarnings("serial")
public class PaginaPrincipalController extends HttpServlet {

	private static final Logger log = Logger.getLogger(PaginaPrincipalController.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		//Login TMDB
		String tmdbLogin = req.getParameter("tmdbLogin");
		String reqToken = req.getParameter("request_token");
		String sessionID = req.getParameter("SessionID");
		TmdbResource tmdbResource = new TmdbResource();
		
		if(tmdbLogin != null) {
			
			TMDBLogin tmdbLog = tmdbResource.requestToken();
			reqToken = tmdbLog.getRequestToken();
			
			String callback = "https://moviefinder-es.appspot.com/paginaPrincipalController";
			String redirect = "https://www.themoviedb.org/authenticate/"+reqToken+"?redirect_to="+callback;
			resp.sendRedirect(redirect);
		}
		else if(reqToken != null)
			sessionID = tmdbResource.getSessionID(reqToken);
		
		req.setAttribute("sessionID", sessionID);
		//Fin Login TMDB
		
		//Prueba Watchlist
		if(sessionID!=null) {
			TmdbWatchlist watchlist = tmdbResource.getWatchlist(sessionID);
			req.setAttribute("watchlist", watchlist);
		}
		//Fin Prueba
		
		String accessToken = (String) req.getSession().getAttribute("GoogleCalendar-token");
		String email = req.getParameter("email");
		if (email != null && !"".equals(email)) {
			if (accessToken != null && !"".equals(accessToken)) {
				GoogleCalendarResource gr = new GoogleCalendarResource(accessToken);
				GoogleCalendar calendar = gr.getCalendar(email);
				String message = "Has sido logeado con éxito";
				if (calendar != null) {
					req.setAttribute("calendar", calendar);
					req.setAttribute("message", message);
					req.setAttribute("sessionID", sessionID);
					req.setAttribute("email", email);
					req.getRequestDispatcher("paginaPrincipal.jsp").forward(req, resp);
				} else {
					log.info(
							"The calendar returned are null... probably your token has experied. Redirecting to OAuth servlet.");
					req.getRequestDispatcher("/AuthController/GoogleCalendar").forward(req, resp);
				}
			} else {
				log.info("Redirecting to OAuth servlet.");
				req.getRequestDispatcher("/AuthController/GoogleCalendar").forward(req, resp);
			}
		} else {
			log.info("Redirecting to pagina principal");
			req.getRequestDispatcher("paginaPrincipal.jsp").forward(req, resp);
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req, resp);
	}

}
