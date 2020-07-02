package movieFinder.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import movieFinder.api.resources.ActorResource;
import movieFinder.api.resources.AudioVisualResource;
import movieFinder.api.resources.PeliculaResource;
import movieFinder.api.resources.PlaylistResource;
import movieFinder.api.resources.SerieResource;
import movieFinder.api.resources.SongResource;
import movieFinder.api.resources.TipoResource;


public class FilmApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	// Loads all resources that are implemented in the application
	// so that they can be found by RESTEasy.
	public FilmApplication() {

		singletons.add(AudioVisualResource.getInstance());
		singletons.add(PlaylistResource.getInstance());
		singletons.add(SongResource.getInstance());
		singletons.add(TipoResource.getInstance());
		singletons.add(ActorResource.getInstance());
		singletons.add(SerieResource.getInstance());
		singletons.add(PeliculaResource.getInstance());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
