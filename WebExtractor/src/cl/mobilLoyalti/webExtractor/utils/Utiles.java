/**
 * 
 */
package cl.mobilLoyalti.webExtractor.utils;

import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * @author Administrador
 *
 */
public class Utiles {
	
	public static HashMap<Integer, String> bencinas = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5503416114647714917L;

		{
			put(1, "Gasolina 93");
			put(2, "Gasolina 97");
			put(3, "Petroleo Diesel");
			put(4, "Kerosene");
			put(5, "GNC");
			put(6, "GLP Vehicular");
			put(7, "Gasolina 95");

		}
	};

	public static HashMap<Integer, String> regiones = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5503416114647714917L;

		{
			put(1, "Arica y Parinacota");
			put(2, "Tarapacá");
			put(3, "Antofagasta");
			put(4, "Atacama");
			put(5, "Coquimbo");
			put(6, "Valparaíso");
			put(7, "Metropolitana");
			put(8, "Gral. Bernardo O'Higgins");
			put(9, "Maule");
			put(10, "Bío Bío");
			put(11, "Aracucanía");
			put(12, "Los Ríos");
			put(13, "Los Lagos");
			put(14, "Aysén Gral. C. Ibáñez del Campo");
			put(15, "Magallanes y la Antártida Chilena");
		}
	};

	public static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss");
	
	public static String fechaHoraActual(){
		
		return sdf.format(System.currentTimeMillis());
		
	}
}
