package movieFinder.resources.test;

import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import movieFinder.model.resource.YouTubeResource;
import movieFinder.model.youtubevideo.Item;


public class YoutubeResourcesTests {

	YouTubeResource youtube = new YouTubeResource();

	@Test
	public void testGetVideos() throws UnsupportedEncodingException {
		String busqueda = "EndGame";
		System.out.println("Test YouTube: Probando búsqueda de vídeos de " + busqueda);
		Item res = youtube.getVideos(busqueda);

		// La siguiente línea es para traducir cuando no hay resultados
		if (res.equals(null)) {
			res = null;
		}
		if (res != null) {
			System.out.println("Encontrado Correctamente");
		}

		assertNotNull("La lista de vídeos no puede ser null", res);

	}
	
	@Test
	public void testGetPlaylists() throws UnsupportedEncodingException {
		String busqueda = "EndGame";
		System.out.println("Test YouTube: Probando búsqueda de playlists de " + busqueda);
		movieFinder.model.youtubeplaylist.Item res = youtube.getPlaylist(busqueda);

		// La siguiente línea es para traducir cuando no hay resultados
		if (res.equals(null)) {
			res = null;
		}
		if (res != null) {
			System.out.println("Encontrado Correctamente");
		}

		assertNotNull("La playlist no puede ser null", res);

	}
	
}