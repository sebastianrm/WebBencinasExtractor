/**
 * 
 */
package cl.mobilLoyalti.webExtractor.main;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import cl.mobilLoyalti.webExtractor.logic.LogicUpdateWebExtractorManager;
import cl.mobilLoyalti.webExtractor.utils.Utiles;

/**
 * @author Sebastian Retamal
 * 
 */
public class ActualizaExistentesMain {


	public static void main(String[] args) {

		Logger log = Logger.getLogger(InsertaNuevosMain.class);
		int idRegion = 1;
		ArrayList<LogicUpdateWebExtractorManager> arrayList = new ArrayList<LogicUpdateWebExtractorManager>();
		int contadorHilos = 0;
		log.info("*******************************************************************");
		log.info("INICIO DE PROCESO DE ACTUALIZACION FECHA HORA:"
				+ Utiles.fechaHoraActual());

		while (idRegion <= 7) {

			/**
			 * para evitar el heap memory space
			 */
			if (contadorHilos >= 4) {
				log.info("ALCANSO MAXIMO DE HILOS PERMITIDOS EN EJECUCION");
				while (contadorHilos >= 4) {
					Iterator<LogicUpdateWebExtractorManager> iterator = arrayList
							.iterator();

					while (iterator.hasNext()) {
						LogicUpdateWebExtractorManager next = iterator.next();

						if (next.isTermine()) {
							contadorHilos--;
							// si termino entonces lo guardo en la base
							log.info("TERMINO UN HILO DESMINULLE CONTADOR DE HILOS A: "
									+ contadorHilos);
						}
					}

				}

			} else {
				LogicUpdateWebExtractorManager regionExtractor = new LogicUpdateWebExtractorManager();
				regionExtractor.setIdRegion(idRegion);
				regionExtractor.start();
				idRegion++;
				contadorHilos++;
				arrayList.add(regionExtractor);
			}

		}


		while (!arrayList.isEmpty()) {
			Iterator<LogicUpdateWebExtractorManager> iterator = arrayList
					.iterator();

			while (iterator.hasNext()) {
				LogicUpdateWebExtractorManager next = iterator.next();

				if (next.isTermine()) {
					// si termino entonces lo guardo en la base
					iterator.remove();
				}
			}
		}
		log.info("FIN PROCESO ACTUALIZACION FECHA HORA:"
				+ Utiles.fechaHoraActual());
		log.info("*******************************************************************");
	}

}
