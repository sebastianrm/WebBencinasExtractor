/**
 * 
 */
package cl.mobilLoyalti.webExtractor.main;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;

import cl.mobilLoyalti.webExtractor.logic.LogicUpdateWebExtractorManager;
import cl.mobilLoyalti.webExtractor.utils.Utiles;
import cl.mobilLoyalti.webExtractor.utils.extern.ParamConf;

/**
 * @author Sebastian Retamal
 * 
 */
public class ActualizaExistentesMain {


	public static void main(String[] args) {

		Logger log = Logger.getLogger(InsertaNuevosMain.class);
		
		ParamConf paramConf = new ParamConf();
		int idRegion = 1;
		List<LogicUpdateWebExtractorManager> arrayList = new CopyOnWriteArrayList<LogicUpdateWebExtractorManager>();
		
		
		int contadorHilos = 0;
		log.info("*******************************************************************");
		log.info("INICIO DE PROCESO DE ACTUALIZACION FECHA HORA:"
				+ Utiles.fechaHoraActual());
		
		while (idRegion <= 15) {

			/**
			 * para evitar el heap memory space
			 */
			if (contadorHilos >= paramConf.QTY_HILOS ) {
				
				log.info("ALCANSO MAXIMO DE HILOS PERMITIDOS EN EJECUCION");
				while (contadorHilos != 0) {

					for (LogicUpdateWebExtractorManager vo : arrayList) {

						if (vo.isTermine()) {
							contadorHilos--;
							arrayList.remove(vo);
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
				regionExtractor.setName("Region: "+idRegion);
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
				next.isAlive();
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
