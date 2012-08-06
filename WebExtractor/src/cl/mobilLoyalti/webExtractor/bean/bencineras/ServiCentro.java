/**
 * 
 */
package cl.mobilLoyalti.webExtractor.bean.bencineras;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author Sebastian Retamal
 * 
 */
public class ServiCentro {

	private String empresa;
	private String direccion;
	private float latitud;
	private float longitud;

	private HashSet<Bencinas> Bencinas;

	private Region region;

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}


	public HashSet<Bencinas> getBencinas() {
		return Bencinas;
	}

	public void setBencinas(HashSet<Bencinas> bencinas) {
		Bencinas = bencinas;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	@Override
	public String toString() {
		return "ServiCentro [empresa=" + empresa + ", direccion=" + direccion
				+ ", latitud=" + latitud + ", longitud=" + longitud
				+ ", Bencinas=" + Bencinas + ", region=" + region + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + Float.floatToIntBits(latitud);
		result = prime * result + Float.floatToIntBits(longitud);
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
		ServiCentro other = (ServiCentro) obj;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (Float.floatToIntBits(latitud) != Float
				.floatToIntBits(other.latitud))
			return false;
		if (Float.floatToIntBits(longitud) != Float
				.floatToIntBits(other.longitud))
			return false;
		return true;
	}




}
