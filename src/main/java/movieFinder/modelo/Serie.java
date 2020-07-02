package movieFinder.modelo;

import java.util.ArrayList;
import java.util.List;

public class Serie {
	String id;
	Integer temporadas;
	String ultFechaEmision;
	String estreno;
	String canal;
	
	public Serie() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(Integer temporadas) {
		this.temporadas = temporadas;
	}

	public String getUltFechaEmision() {
		return ultFechaEmision;
	}

	public void setUltFechaEmision(String ultFechaEmision) {
		this.ultFechaEmision = ultFechaEmision;
	}

	public String getEstreno() {
		return estreno;
	}

	public void setEstreno(String estreno) {
		this.estreno = estreno;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}
	
}
