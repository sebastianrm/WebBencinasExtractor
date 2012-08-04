package cl.mobilLoyalti.webExtractor.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cl.mobilLoyalti.webExtractor.bean.bencineras.ServiCentro;
import cl.mobilLoyalti.webExtractor.db.ConnectionDB;
import cl.mobilLoyalti.webExtractor.db.MySQLConnectionDB;

public class ServiCentroDao extends ConnectionDB{
	private static final String SQL_INSERT = "INSERT INTO servicentros (empresa,direccion,latitud,longitud,fkregion) values (?,?,?,?,?)";

	public void insert(ServiCentro sc) {

		Connection conn = MySQLConnectionDB.getInstance().createConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(SQL_INSERT);

			ps.setString(1, sc.getEmpresa());
			ps.setString(2, sc.getDireccion());
			ps.setFloat(3, sc.getLatitud());
			ps.setFloat(4, sc.getLongitud());
			ps.setString(5, sc.getRegion().getNombre());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(ps);
			MySQLConnectionDB.getInstance().closeConnection();
		}

	}
}
