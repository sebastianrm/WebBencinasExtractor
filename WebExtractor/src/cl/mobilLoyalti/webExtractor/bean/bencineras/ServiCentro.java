/**
 * 
 */
package cl.mobilLoyalti.webExtractor.bean.bencineras;

import java.util.Set;

/**
 * @author Administrador
 * 
 */
public class ServiCentro {

	private String empresa;
	private String direccion;
	private float latitud;
	private float longitud;

	private Set<Bencinas> Bencinas;

	private Region region;

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Set<Bencinas> getBencinas() {
		return Bencinas;
	}

	public void setBencinas(Set<Bencinas> bencinas) {
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

}
