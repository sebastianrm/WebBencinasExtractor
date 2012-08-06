package cl.mobilLoyalti.webExtractor.logic;

import java.util.Iterator;

import org.apache.log4j.Logger;

import cl.mobilLoyalti.webExtractor.bean.bencineras.Bencinas;
import cl.mobilLoyalti.webExtractor.bean.bencineras.Region;
import cl.mobilLoyalti.webExtractor.bean.bencineras.ServiCentro;
import cl.mobilLoyalti.webExtractor.db.MySQLConnectionDB;
import cl.mobilLoyalti.webExtractor.db.dao.BencinasDao;
import cl.mobilLoyalti.webExtractor.db.dao.PreciosDao;
import cl.mobilLoyalti.webExtractor.db.dao.RegionDao;
import cl.mobilLoyalti.webExtractor.db.dao.ServiCentroDao;

public class InsetsLogic {
	
	private static Logger log = Logger.getLogger(InsetsLogic.class);

//	public static void inserts(ArrayList<Region> regiones) {
//
//		RegionDao regDao = new RegionDao();
//		ServiCentroDao scDao = new ServiCentroDao();
//		BencinasDao bencDao = new BencinasDao();
//		PreciosDao preDao = new PreciosDao();
//
//		Iterator<Region> regionesIter = regiones.iterator();
//
//		while (regionesIter.hasNext()) {
//			Region next = regionesIter.next();
//
//
//			if (next != null) {
//				
//				regDao.insert(next);
//				Iterator<ServiCentro> scIter = next.getServiCentros()
//						.iterator();
//				while (scIter.hasNext()) {
//
//					ServiCentro next2 = scIter.next();
//
//					scDao.insert(next2);
//
//					Iterator<Bencinas> bencinasiter = next2.getBencinas()
//							.iterator();
//
//					while (bencinasiter.hasNext()) {
//
//						Bencinas next3 = bencinasiter.next();
//						bencDao.insert(next3);
//
//						preDao.insert(next3);
//
//					}
//
//				}
//			}
//		}
//		MySQLConnectionDB.getInstance().closeConnection();
//	}

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
			MySQLConnectionDB.getInstance().closeConnection();
		}
	}
}
