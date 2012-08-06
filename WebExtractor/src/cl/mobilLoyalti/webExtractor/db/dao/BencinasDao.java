package cl.mobilLoyalti.webExtractor.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import cl.mobilLoyalti.webExtractor.bean.bencineras.Bencinas;
import cl.mobilLoyalti.webExtractor.db.ConnectionDB;
import cl.mobilLoyalti.webExtractor.db.MySQLConnectionDB;

public class BencinasDao extends ConnectionDB {

	private static Logger log = Logger.getLogger(BencinasDao.class);

	private static final String SQL_INSERT = "INSERT INTO bencinas (nombre) values (?)";

	public void insert(Bencinas bencinas) {

		Connection conn = MySQLConnectionDB.getInstance().createConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(SQL_INSERT);

			ps.setString(1, bencinas.getDescripcion());

			ps.execute();
		} catch (SQLException e) {
			log.error(e);
		} finally {
			closeAll(ps);
			MySQLConnectionDB.getInstance().closeConnection();
		}

	}

}
