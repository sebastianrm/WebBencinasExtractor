package cl.mobilLoyalti.webExtractor.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import org.apache.log4j.Logger;

import cl.mobilLoyalti.webExtractor.bean.bencineras.Bencinas;
import cl.mobilLoyalti.webExtractor.bean.bencineras.Region;
import cl.mobilLoyalti.webExtractor.bean.bencineras.ServiCentro;
import cl.mobilLoyalti.webExtractor.db.ConnectionDB;
import cl.mobilLoyalti.webExtractor.db.MySQLConnectionDB;
import cl.mobilLoyalti.webExtractor.db.dto.PreciosDto;

/**
 * 
 * @author Sebastian Retamal
 * 
 */
public class PreciosDao extends ConnectionDB {

	private static Logger log = Logger.getLogger(PreciosDao.class);
	private static final String SQL_INSERT = "INSERT INTO precios (precio,fkbencina,fkempresa,fkdireccion,fkregion,fecha_actualizacion) values (?,?,?,?,?,?)";
	private static final String SQL_LOAD_ALL = "SELECT precios.precio, precios.fkbencina, precios.fkempresa, precios.fkdireccion, precios.fkregion, precios.fecha_actualizacion FROM bencineras.precios";
	private static final String SQL_UPDATE = "UPDATE bencineras.precios SET precio = ? ,fecha_actualizacion = ? WHERE fkbencina = ? and fkempresa = ? and fkdireccion = ? and fkregion = ? and fecha_actualizacion <> ?";

	public void insert(Bencinas precios, ServiCentro sc, Region region) {

		Connection conn = MySQLConnectionDB.getInstance().createConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(SQL_INSERT);

			ps.setFloat(1, precios.getPrecios());
			ps.setString(2, precios.getDescripcion());
			ps.setString(3, sc.getEmpresa());
			ps.setString(4, sc.getDireccion());
			ps.setString(5, region.getNombre());
			ps.setTimestamp(6, precios.getFechaUlmtimaModificacion());

			ps.execute();
		} catch (SQLException e) {
			log.error(e);
		} finally {
			close(ps);
			MySQLConnectionDB.getInstance().closeConnection();
		}

	}

	public HashSet<PreciosDto> loadAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = MySQLConnectionDB.getInstance().createConnection();
			ps = conn.prepareStatement(SQL_LOAD_ALL);

			rs = ps.executeQuery();

			HashSet<PreciosDto> hs = new HashSet<PreciosDto>();
			while (rs.next()) {

				PreciosDto precio = new PreciosDto();

				precio.setFkBencina(rs.getString("fkbencina"));
				precio.setFechaUlmtimaModificacion(rs
						.getTimestamp("fecha_actualizacion"));
				precio.setPrecios(rs.getFloat("precio"));

				precio.setFkempresa(rs.getString("fkempresa"));
				precio.setFkdireccion(rs.getString("fkdireccion"));

				precio.setFkregion(rs.getString("fkregion"));

				hs.add(precio);

			}
			return hs;

		} catch (SQLException e) {
			log.error(e);
		} finally {
			close(ps, rs, conn);
		}
		return null;
	}

	/**
	 * 
	 * @param nextBencina
	 * @param nextSc
	 * @param region
	 */
	public void update(Bencinas nextBencina, ServiCentro nextSc, Region region) {
		// precio = ? ,fecha_actualizacion = ?
		// WHERE fkbencina = ? and fkempresa = ? and fkdireccion = ? and
		// fkregion = ? and fecha_actualizacion <> ?";
		Connection conn = MySQLConnectionDB.getInstance().createConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(SQL_UPDATE);

			ps.setFloat(1, nextBencina.getPrecios());
			ps.setTimestamp(2, nextBencina.getFechaUlmtimaModificacion());

			ps.setString(3, nextBencina.getDescripcion());
			ps.setString(4, nextSc.getEmpresa());
			ps.setString(5, nextSc.getDireccion());
			ps.setString(6, region.getNombre());
			ps.setTimestamp(7, nextBencina.getFechaUlmtimaModificacion());

			ps.execute();
			int updateCount = ps.getUpdateCount();
			nextBencina.setServiCentro(nextSc);
			if (updateCount > 0){
				log.info("ACTUALIZA REGISTRO :" + nextBencina.toString() + "REGION: "+region.toStringCorto());
			}
			
			
		} catch (SQLException e) {
			log.error(e);
		} finally {
			close(ps);
			MySQLConnectionDB.getInstance().closeConnection();
		}
	}

}
