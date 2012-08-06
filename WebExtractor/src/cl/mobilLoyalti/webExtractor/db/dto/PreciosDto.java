/**
 * 
 */
package cl.mobilLoyalti.webExtractor.db.dto;

import java.sql.Timestamp;

/**
 * @author Sebastian Retamal
 * 
 */
public class PreciosDto {

	private float precios;
	private String fkBencina;
	private String fkempresa;
	private String fkdireccion;
	private String fkregion;
	private Timestamp fechaUlmtimaModificacion;
	public float getPrecios() {
		return precios;
	}
	public void setPrecios(float precios) {
		this.precios = precios;
	}
	public String getFkBencina() {
		return fkBencina;
	}
	public void setFkBencina(String fkBencina) {
		this.fkBencina = fkBencina;
	}
	public String getFkempresa() {
		return fkempresa;
	}
	public void setFkempresa(String fkempresa) {
		this.fkempresa = fkempresa;
	}
	public String getFkdireccion() {
		return fkdireccion;
	}
	public void setFkdireccion(String fkdireccion) {
		this.fkdireccion = fkdireccion;
	}
	public String getFkregion() {
		return fkregion;
	}
	public void setFkregion(String fkregion) {
		this.fkregion = fkregion;
	}
	public Timestamp getFechaUlmtimaModificacion() {
		return fechaUlmtimaModificacion;
	}
	public void setFechaUlmtimaModificacion(Timestamp fechaUlmtimaModificacion) {
		this.fechaUlmtimaModificacion = fechaUlmtimaModificacion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((fechaUlmtimaModificacion == null) ? 0
						: fechaUlmtimaModificacion.hashCode());
		result = prime * result
				+ ((fkBencina == null) ? 0 : fkBencina.hashCode());
		result = prime * result
				+ ((fkdireccion == null) ? 0 : fkdireccion.hashCode());
		result = prime * result
				+ ((fkempresa == null) ? 0 : fkempresa.hashCode());
		result = prime * result
				+ ((fkregion == null) ? 0 : fkregion.hashCode());
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
		PreciosDto other = (PreciosDto) obj;
		if (fechaUlmtimaModificacion == null) {
			if (other.fechaUlmtimaModificacion != null)
				return false;
		} else if (!fechaUlmtimaModificacion
				.equals(other.fechaUlmtimaModificacion))
			return false;
		if (fkBencina == null) {
			if (other.fkBencina != null)
				return false;
		} else if (!fkBencina.equals(other.fkBencina))
			return false;
		if (fkdireccion == null) {
			if (other.fkdireccion != null)
				return false;
		} else if (!fkdireccion.equals(other.fkdireccion))
			return false;
		if (fkempresa == null) {
			if (other.fkempresa != null)
				return false;
		} else if (!fkempresa.equals(other.fkempresa))
			return false;
		if (fkregion == null) {
			if (other.fkregion != null)
				return false;
		} else if (!fkregion.equals(other.fkregion))
			return false;
		if (Float.floatToIntBits(precios) != Float
				.floatToIntBits(other.precios))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PreciosDto [precios=" + precios + ", fkBencina=" + fkBencina
				+ ", fkempresa=" + fkempresa + ", fkdireccion=" + fkdireccion
				+ ", fkregion=" + fkregion + ", fechaUlmtimaModificacion="
				+ fechaUlmtimaModificacion + "]";
	}

	
	
	
}
