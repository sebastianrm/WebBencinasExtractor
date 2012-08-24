package cl.mobilLoyalti.webExtractor.logic;

import java.sql.SQLException;
import java.util.Iterator;

import org.apache.log4j.Logger;

import cl.mobilLoyalti.webExtractor.bean.bencineras.Bencinas;
import cl.mobilLoyalti.webExtractor.bean.bencineras.Region;
import cl.mobilLoyalti.webExtractor.bean.bencineras.ServiCentro;
import cl.mobilLoyalti.webExtractor.db.PoolingDataSource;
import cl.mobilLoyalti.webExtractor.db.dao.BencinasDao;
import cl.mobilLoyalti.webExtractor.db.dao.PreciosDao;
import cl.mobilLoyalti.webExtractor.db.dao.RegionDao;
import cl.mobilLoyalti.webExtractor.db.dao.ServiCentroDao;
import cl.mobilLoyalti.webExtractor.utils.Utiles;

/**
 * 
 * @author Sebastian Retamal
 *
 */
public class BdLogicManager {
	
	private static Logger log = Logger.getLogger(BdLogicManager.class);

	/**
	 * 
	 * @param region
	 */
	public void inserts(Region region) {
		if (region != null && region.getNombre() != null) {
			RegionDao regDao = new RegionDao();
			ServiCentroDao scDao = new ServiCentroDao();
			BencinasDao bencDao = new BencinasDao();
			PreciosDao preDao = new PreciosDao();
			regDao.insert(region);
			Iterator<ServiCentro> scIter = region.getServiCentros().iterator();
			log.info("COMIENSA INSERT DE INFORMACION REGION: "+region.getNombre()+" FECHA HORA INICIO:"+Utiles.fechaHoraActual());
			while (scIter.hasNext()) {

				ServiCentro next2 = scIter.next();

				scDao.insert(next2,region);

				Iterator<Bencinas> bencinasiter = next2.getBencinas()
						.iterator();

				while (bencinasiter.hasNext()) {

					Bencinas next3 = bencinasiter.next();
					bencDao.insert(next3);

					preDao.insert(next3,next2,region);

				}

			}
//			PoolingDataSource.getInstance().closeConnection();
			log.info("FINALIZA INSERT DE INFORMACION REGION: "+region.getNombre()+" FECHA HORA INICIO:"+Utiles.fechaHoraActual());
		}
	}
	
	
	public void updates(Region region) {
		if (region != null && region.getNombre() != null) {
			PreciosDao preDao = new PreciosDao();
			Iterator<ServiCentro> scIter = region.getServiCentros().iterator();
			log.info("COMIENSA UPDATE DE INFORMACION REGION: "+region.getNombre()+" FECHA HORA INICIO:"+Utiles.fechaHoraActual());
			while (scIter.hasNext()) {

				ServiCentro nextSc = scIter.next();

				Iterator<Bencinas> bencinasiter = nextSc.getBencinas()
						.iterator();

				while (bencinasiter.hasNext()) {

					Bencinas nextBencina = bencinasiter.next();
					preDao.update(nextBencina,nextSc,region);

				}

			}
			log.info("FINALIZA UPDATE DE INFORMACION REGION: "+region.getNombre()+" FECHA HORA INICIO:"+Utiles.fechaHoraActual());
//			MySQLConnectionDB.getInstance().closeConnection();
//			try {
//				PoolingDataSource.getInstance().getConnection().close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
	
	
}
