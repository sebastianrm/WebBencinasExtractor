package cl.mobilLoyalti.webExtractor.logic;

import java.util.ArrayList;
import java.util.Iterator;

import cl.mobilLoyalti.webExtractor.bean.bencineras.Bencinas;
import cl.mobilLoyalti.webExtractor.bean.bencineras.Region;
import cl.mobilLoyalti.webExtractor.bean.bencineras.ServiCentro;
import cl.mobilLoyalti.webExtractor.db.MySQLConnectionDB;
import cl.mobilLoyalti.webExtractor.db.dao.BencinasDao;
import cl.mobilLoyalti.webExtractor.db.dao.PreciosDao;
import cl.mobilLoyalti.webExtractor.db.dao.RegionDao;
import cl.mobilLoyalti.webExtractor.db.dao.ServiCentroDao;

public class InsetsLogic {

	public static void inserts(ArrayList<Region> regiones) {

		RegionDao regDao = new RegionDao();
		ServiCentroDao scDao = new ServiCentroDao();
		BencinasDao bencDao = new BencinasDao();
		PreciosDao preDao = new PreciosDao();

		Iterator<Region> regionesIter = regiones.iterator();

		while (regionesIter.hasNext()) {
			Region next = regionesIter.next();


			if (next != null) {
				
				regDao.insert(next);
				Iterator<ServiCentro> scIter = next.getServiCentros()
						.iterator();
				while (scIter.hasNext()) {

					ServiCentro next2 = scIter.next();

					scDao.insert(next2);

					Iterator<Bencinas> bencinasiter = next2.getBencinas()
							.iterator();

					while (bencinasiter.hasNext()) {

						Bencinas next3 = bencinasiter.next();
						bencDao.insert(next3);

						preDao.insert(next3);

					}

				}
			}
		}
		MySQLConnectionDB.getInstance().closeConnection();
	}
}
