/**
 * 
 */
package cl.mobilLoyalti.webExtractor.logic;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import cl.mobilLoyalti.webExtractor.bean.bencineras.Region;
import cl.mobilLoyalti.webExtractor.bean.bencineras.ServiCentro;
import cl.mobilLoyalti.webExtractor.utils.Utiles;

/**
 * @author Administrador
 * 
 */
public class LogicUpdateWebExtractorManager extends Thread {
	private static Logger log = Logger
			.getLogger(LogicUpdateWebExtractorManager.class);

	private boolean termine = false;

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
		super.run();

		int idBencina = 1;

		ArrayList<WebLogicExtractor> arrayList = new ArrayList<WebLogicExtractor>();
		Region region = new Region();

		
		log.info("COMIENSA EXTRACCION DE INFORMACION REGION: "+Utiles.regiones.get(idRegion)+" FECHA HORA INICIO:"+Utiles.fechaHoraActual());
		
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
				}
			}
			sw = sw2;

		}
		BdLogicManager inserts = new BdLogicManager();
		log.info("FINALIZA EXTRACCION DE INFORMACION REGION: "+Utiles.regiones.get(idRegion)+" FECHA HORA INICIO:"+Utiles.fechaHoraActual());
		inserts.updates(region);
		termine = true;

	}
}
