/**
 * 
 */
package cl.mobilLoyalti.webExtractor.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import cl.mobilLoyalti.webExtractor.logic.WebLogicExtractorManager;

/**
 * @author Sebastian Retamal
 * 
 */
public class Main {

	protected static HashMap<Integer, String> bencinas = new HashMap<Integer, String>() {
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

	protected static HashMap<Integer, String> regiones = new HashMap<Integer, String>() {
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

	public static void main(String[] args) {
		Logger log = Logger.getLogger(Main.class);
		// Main main = new Main();
		// ArrayList<Region> arrayList = new ArrayList<Region>();
		//
		// int idRegion = 1;
		int idRegion = 1;
		ArrayList<WebLogicExtractorManager> arrayList = new ArrayList<WebLogicExtractorManager>();
//		Region region  = new Region();
		while (idRegion <= 7) {
			WebLogicExtractorManager regionExtractor = new WebLogicExtractorManager();
			regionExtractor.setIdRegion(idRegion);
			regionExtractor.start();
			idRegion++;
			arrayList.add(regionExtractor);			
		}
		boolean sw = false;
		
		while (!sw) {
			Iterator<WebLogicExtractorManager> iterator = arrayList.iterator();
			boolean sw2 = true;
			
			while (iterator.hasNext()){
				WebLogicExtractorManager next = iterator.next();
				
				sw2 = sw2 && next.isAlive();
				
				if (next.isTermine()){
					
//					si termino entonces lo guardo en la base
					log.info(next.getRegion().toString());
					iterator.remove();
				}
			}
			sw = sw2;

		}

//		log.info(Region.toString());

		// while (idRegion <= 15) {
		// int idBencina = 1;
		//
		//
		//
		// while (idBencina <= 7) {
		//
		// Region testActualizaComunasBen = main.testActualizaComunasBen(
		// idBencina, idRegion);
		// arrayList.add(testActualizaComunasBen);
		// main.dormir();
		// idBencina++;
		// }
		// idRegion++;
		// }
		// InsetsLogic.inserts(arrayList);
	}

}
