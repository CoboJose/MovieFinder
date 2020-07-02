package movieFinder.modelo;

public class Tipo {
	String id;
	Serie serie;
	Pelicula pelicula;

	public Tipo() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Serie getSerie() {
		return serie;
	}
	public void setSerie(Serie serie) {
		this.serie = serie;
	}
	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	
	public Pelicula getPelicula(String id) {
		if(pelicula==null) {
			return null;
		}else {
			if(pelicula.getId()==id) {
				return pelicula;
			}
			return null;
		}
	}
	
	public void addPelicula(Pelicula p) {
		pelicula=p;
	}
	
	public void deletePelicula(String id) {
		Pelicula p = getPelicula(id);
		if (p!=null)
			pelicula=null;
	}
	
	public Serie getSerie(String id) {
		if(serie==null) {
			return null;
		}else {
			if(serie.getId()==id) {
				return serie;
			}
			return null;
		}
	}
	
	public void addSerie(Serie s) {
		serie=s;;
	}
	
	public void deleteSerie(String id) {
		Serie s = getSerie(id);
		if (s!=null)
			serie=null;
	}

}
