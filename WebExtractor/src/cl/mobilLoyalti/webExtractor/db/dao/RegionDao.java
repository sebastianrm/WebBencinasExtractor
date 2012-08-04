package cl.mobilLoyalti.webExtractor.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cl.mobilLoyalti.webExtractor.bean.bencineras.Region;
import cl.mobilLoyalti.webExtractor.db.ConnectionDB;
import cl.mobilLoyalti.webExtractor.db.MySQLConnectionDB;

public class RegionDao extends ConnectionDB{

	private static final String SQL_INSERT = "INSERT INTO regiones (nombre) values (?)";
	
	
	public void insert (Region region){
		
		Connection conn = MySQLConnectionDB.getInstance().createConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(SQL_INSERT);

		ps.setString(1, region.getNombre());
		
		ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll(ps);
			MySQLConnectionDB.getInstance().closeConnection();
		}
		
	}
	
	
}
