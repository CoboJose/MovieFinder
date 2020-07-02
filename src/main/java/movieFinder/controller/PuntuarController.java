package movieFinder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movieFinder.model.resource.TmdbResource;
import movieFinder.model.tmdb.login.TMDBValoracion;

@SuppressWarnings("serial")
public class PuntuarController extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		/*
		 * NO HACE FALTA LO DEJO POR SI ACASO
		 */
		
		
		String id = req.getParameter("movieId");
		String user = req.getParameter("user");
		String title = req.getParameter("title");
		Double valor = Double.parseDouble(req.getParameter("valor"));
		TMDBValoracion puntuar = new TMDBValoracion();
		puntuar.setValue(valor);
		TmdbResource tmdbResource = new TmdbResource();
		tmdbResource.puntuarPelicula(user, id, puntuar);
		String message = "La película ha sido puntuada con éxito con un: " + puntuar.getValue();
		req.setAttribute("message", message);
		req.setAttribute("title", title);
		req.getRequestDispatcher("/resultadoEspecificoController").forward(req, resp);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req, resp);
	}
}
