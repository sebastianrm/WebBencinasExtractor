/**
 * 
 */
package cl.mobilLoyalti.webExtractor.bean.bencineras;

import java.util.Set;

/**
 * @author Administrador
 * 
 */
public class Region {

	private String nombre;
	
	private Set<ServiCentro> serviCentros;
	
	

	public Set<ServiCentro> getServiCentros() {
		return serviCentros;
	}

	public void setServiCentros(Set<ServiCentro> serviCentros) {
		this.serviCentros = serviCentros;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
