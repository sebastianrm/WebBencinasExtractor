package cl.mobilLoyalti.webExtractor.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cl.mobilLoyalti.webExtractor.bean.bencineras.Bencinas;
import cl.mobilLoyalti.webExtractor.db.ConnectionDB;
import cl.mobilLoyalti.webExtractor.db.MySQLConnectionDB;

public class PreciosDao extends ConnectionDB{

	private static final String SQL_INSERT = "INSERT INTO precios (precio,fkbencina,fkempresa,fkdireccion,fkregion,fecha_actualizacion) values (?,?,?,?,?,?)";

	public void insert(Bencinas precios) {

		Connection conn = MySQLConnectionDB.getInstance().createConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(SQL_INSERT);

			ps.setFloat(1, precios.getPrecios());
			ps.setString(2, precios.getDescripcion());
			ps.setString(3, precios.getServiCentro().getEmpresa());
			ps.setString(4, precios.getServiCentro().getDireccion());
			ps.setString(5, precios.getServiCentro().getRegion().getNombre());
			ps.setTimestamp(6, precios.getFechaUlmtimaModificacion());

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
