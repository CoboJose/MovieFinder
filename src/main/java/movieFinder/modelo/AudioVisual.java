package movieFinder.modelo;

import java.util.ArrayList;
import java.util.List;

public class AudioVisual {
	String id;
	String name;
	Tipo tipo;
	Playlist soundtrack;
	List<Actor> actores;
	public AudioVisual() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public Playlist getSoundtrack() {
		return soundtrack;
	}
	public void setSoundtrack(Playlist soundtrack) {
		this.soundtrack = soundtrack;
	}
	public List<Actor> getActores() {
		return actores;
	}
	public void setActores(List<Actor> actores) {
		this.actores = actores;
	}
	public Actor getActor(String id) {
		if (actores==null)
			return null;
		
		Actor actor =null;
		for(Actor a: actores)
			if (a.getId().equals(id))
			{
				actor=a;
				break;
			}
		
		return actor;
	}
	
	public void addActor(Actor a) {
		if (actores==null)
			actores = new ArrayList<Actor>();
		actores.add(a);
	}
	
	public void deleteActor(Actor a) {
		actores.remove(a);
	}
	
	public void deleteActor(String id) {
		Actor a = getActor(id);
		if (a!=null)
			actores.remove(a);
	}
	
	public Tipo getTipo(String id) {
		if(tipo==null) {
			return null;
		}else {
			if(tipo.getId()==id) {
				return tipo;
			}
			return null;
		}
	}
	
	public void addTipo(Tipo t) {
		tipo=t;
	}
	
	public void deleteTipo(String id) {
		Tipo t = getTipo(id);
		if (t!=null)
			tipo=null;
	}
	
	public Playlist getPlaylist(String id) {
		if(soundtrack==null) {
			return null;
		}else {
			if(soundtrack.getId()==id) {
				return soundtrack;
			}
			return null;
		}
	}
	
	public void addPlaylist(Playlist p) {
		soundtrack=p;
	}
	
	public void deletePlaylist(String id) {
		Playlist p = getPlaylist(id);
		if (p!=null)
			soundtrack=null;
	}

}
