package movieFinder.resources.test;

import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import movieFinder.model.resource.TmdbResource;
import movieFinder.model.resource.YouTubeResource;
import movieFinder.model.tmdbWatchlist.TmdbWatchlist;
import movieFinder.model.youtubevideo.Item;


public class TMDBResourcesTests {

	TmdbResource tmdb = new TmdbResource();

	@Test
	public void testSearchMovie() throws UnsupportedEncodingException {
		String busqueda = "EndGame";
		System.out.println("Test TMDB: Probando búsqueda de películas de " + busqueda);
		movieFinder.model.tmdbMovies.TmdbMovies res = tmdb.searchMovie(busqueda);

		// La siguiente línea es para traducir cuando no hay resultados
		if (res.equals(null)) {
			res = null;
		}
		if (res != null) {
			System.out.println("Encontrado Correctamente");
		}

		assertNotNull("La lista de películas no puede ser null", res);

	}
	
	@Test
	public void testDiscoverMovie() throws UnsupportedEncodingException {
		String busqueda = "es-Es";
		System.out.println("Test TMDB: Probando búsqueda de películas en " + busqueda);
		movieFinder.model.tmdbMovies.TmdbMovies res = tmdb.discoverMovie(busqueda, "random", "noOrder", "", null, "all", "", "estreno");

		// La siguiente línea es para traducir cuando no hay resultados
		if (res.equals(null)) {
			res = null;
		}
		if (res != null) {
			System.out.println("Encontrado Correctamente");
		}

		assertNotNull("La lista de películas no puede ser null", res);

	}
	
}