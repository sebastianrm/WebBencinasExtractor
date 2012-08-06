/**
 * 
 */
package cl.mobilLoyalti.webExtractor.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import cl.mobilLoyalti.webExtractor.bean.bencineras.Region;
import cl.mobilLoyalti.webExtractor.bean.bencineras.ServiCentro;

/**
 * @author sebastian
 * 
 */
public class WebLogicExtractorManager extends Thread {

	private static Logger log = Logger
			.getLogger(WebLogicExtractorManager.class);
	
	
	private boolean termine = false;

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

	private Integer idRegion;

	private Region region;

	public Integer getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(Integer idRegion) {
		this.idRegion = idRegion;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public boolean isTermine() {
		return termine;
	}

	public void setTermine(boolean termine) {
		this.termine = termine;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		int idBencina = 1;

		ArrayList<WebLogicExtractor> arrayList = new ArrayList<WebLogicExtractor>();
		Region region = new Region();

		while (idBencina <= 7) {
			WebLogicExtractor webLogicExtractor = new WebLogicExtractor();
			webLogicExtractor.setIdRegion(idRegion);
			webLogicExtractor.setIndiceBencina(idBencina);
			webLogicExtractor.start();
			idBencina++;
			arrayList.add(webLogicExtractor);
		}

		boolean sw = false;
		while (!sw) {
			Iterator<WebLogicExtractor> iterator = arrayList.iterator();
			boolean sw2 = true;

			while (iterator.hasNext()) {

				WebLogicExtractor next = iterator.next();

				sw2 = sw2 && next.isTermino();
				
				if (next.isTermino()) {
					if (region.getNombre() == null) {
						/**
						 * Es el primero
						 */
						region = next.getRegion();

					} else {
						/**
						 * si no es el primero combina las bencineras
						 */
						region.getServiCentros().addAll(
								next.getRegion().getServiCentros());

						/**
						 * y combina los combustibles por cada bencinera
						 */

						Iterator<ServiCentro> iterServicentro = next
								.getRegion().getServiCentros().iterator();

						while (iterServicentro.hasNext()) {
							ServiCentro next2 = iterServicentro.next();

							Iterator<ServiCentro> iterator2 = region
									.getServiCentros().iterator();

							while (iterator2.hasNext()) {
								ServiCentro next3 = iterator2.next();

								if (next3.equals(next2)) {
									next3.getBencinas().addAll(
											next2.getBencinas());
								}

							}

						}

					}

					iterator.remove();
//					log.info(next.getRegion());
				}
			}
			sw = sw2;

		}
		InsetsLogic inserts = new InsetsLogic();
		inserts.inserts(region);
		termine = true;
		
	}

}
