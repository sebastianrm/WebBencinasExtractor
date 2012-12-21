/**
 * 
 */
package cl.mobilLoyalti.webExtractor.main;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;

import cl.mobilLoyalti.webExtractor.logic.LogicNewWebExtractorManager;
import cl.mobilLoyalti.webExtractor.utils.Utiles;
import cl.mobilLoyalti.webExtractor.utils.extern.ParamConf;

/**
 * @author Sebastian Retamal
 * 
 */
public class InsertaNuevosMain {

	public static void main(String[] args) {

		ParamConf paramConf = new ParamConf();

		Logger log = Logger.getLogger(InsertaNuevosMain.class);
		int idRegion = 0;

		List<LogicNewWebExtractorManager> arrayList = new CopyOnWriteArrayList<LogicNewWebExtractorManager>();
		int contadorHilos = 0;
		log.info("*******************************************************************");
		log.info("INICIO DE PROCESO DE CREACION FECHA HORA:"
				+ Utiles.fechaHoraActual());

		while (idRegion <= 15) {

			/**
			 * para evitar el heap memory space
			 */
			if (contadorHilos >= paramConf.QTY_HILOS ) {
				
				log.info("ALCANSO MAXIMO DE HILOS PERMITIDOS EN EJECUCION");
				
				while (contadorHilos != 0) {
					
					for (LogicNewWebExtractorManager vo : arrayList) {
						

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
				LogicNewWebExtractorManager regionExtractor = new LogicNewWebExtractorManager();
				regionExtractor.setIdRegion(idRegion);
				regionExtractor.setName("Region: "+idRegion);
				regionExtractor.start();
				idRegion++;
				contadorHilos++;
				arrayList.add(regionExtractor);
			}

		}

		while (!arrayList.isEmpty()) {
			Iterator<LogicNewWebExtractorManager> iterator = arrayList
					.iterator();

			while (iterator.hasNext()) {
				LogicNewWebExtractorManager next = iterator.next();

				if (next.isTermine()) {
					// si termino entonces lo guardo en la base
					iterator.remove();
				}
			}
		}
		log.info("FIN PROCESO CREACION FECHA HORA:" + Utiles.fechaHoraActual());
		log.info("*******************************************************************");

	}

}
