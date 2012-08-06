package cl.mobilLoyalti.webExtractor.bean.bencineras;

import java.sql.Timestamp;

public class Bencinas implements Comparable<Bencinas>{

	public int compareTo(Bencinas o) {
		
		return descripcion.compareTo(o.getDescripcion());
	}

	private String descripcion;
	private float precios;
	private Timestamp fechaUlmtimaModificacion;
	private ServiCentro serviCentro;
	
		

	public Timestamp getFechaUlmtimaModificacion() {
		return fechaUlmtimaModificacion;
	}

	public void setFechaUlmtimaModificacion(Timestamp fechaUlmtimaModificacion) {
		this.fechaUlmtimaModificacion = fechaUlmtimaModificacion;
	}

	public ServiCentro getServiCentro() {
		return serviCentro;
	}

	public void setServiCentro(ServiCentro serviCentro) {
		this.serviCentro = serviCentro;
	}
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime
				* result
				+ ((fechaUlmtimaModificacion == null) ? 0
						: fechaUlmtimaModificacion.hashCode());
		result = prime * result + Float.floatToIntBits(precios);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bencinas other = (Bencinas) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (fechaUlmtimaModificacion == null) {
			if (other.fechaUlmtimaModificacion != null)
				return false;
		} else if (!fechaUlmtimaModificacion
				.equals(other.fechaUlmtimaModificacion))
			return false;
		if (Float.floatToIntBits(precios) != Float
				.floatToIntBits(other.precios))
			return false;
		return true;
	}

	public float getPrecios() {
		return precios;
	}

	public void setPrecios(float precios) {
		this.precios = precios;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Bencinas [descripcion=" + descripcion + ", precios=" + precios
				+ ", fechaUlmtimaModificacion=" + fechaUlmtimaModificacion
				+ ", serviCentro=" + serviCentro + "]";
	}

	
	
	
	
}
