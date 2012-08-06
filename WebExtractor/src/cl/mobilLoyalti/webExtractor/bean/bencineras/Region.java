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
public class Region implements Comparable<Region>{

	private String nombre;
	
	private HashSet<ServiCentro> serviCentros;
	
	
	public HashSet<ServiCentro> getServiCentros() {
		return serviCentros;
	}

	public void setServiCentros(HashSet<ServiCentro> serviCentros) {
		this.serviCentros = serviCentros;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	@Override
	public String toString() {
		return "Region [nombre=" + nombre + ", serviCentros=" + serviCentros
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((serviCentros == null) ? 0 : serviCentros.hashCode());
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
		Region other = (Region) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (serviCentros == null) {
			if (other.serviCentros != null)
				return false;
		} else if (!serviCentros.equals(other.serviCentros))
			return false;
		return true;
	}

	public int compareTo(Region o) {
		// TODO Auto-generated method stub
		
		return nombre.compareTo(o.getNombre());
	}




}
