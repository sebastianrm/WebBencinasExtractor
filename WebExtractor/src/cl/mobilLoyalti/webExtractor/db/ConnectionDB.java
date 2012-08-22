package cl.mobilLoyalti.webExtractor.db;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class ConnectionDB {
	Logger logger = Logger.getLogger(ConnectionDB.class);

	public ConnectionDB() {

	}

	/*
	 * Cierra el objeto ResultSet
	 * 
	 * @param rs Objeto ResultSet.
	 */
	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.warn(e.getMessage(), e);
			} finally {
				rs = null;
			}
		}
	}

	/*
	 * Cierra el objeto Connection
	 * 
	 * @param conn Objeto Connection.
	 */
	private void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.warn(e.getMessage(), e);
			} finally {
				conn = null;
			}
		}
	}

	/*
	 * Cierra los objetos PreparedStatement y Connection simultaneamente.
	 * 
	 * @param ps Objeto PreparedStatement.
	 * 
	 * @param conn Objeto Connection.
	 */
	public void close(PreparedStatement ps, Connection conn) {
		close(ps);
		close(conn);
	}

	/*
	 * Cierra los objetos PreparedStatement.
	 * 
	 * @param ps Objeto PreparedStatement.
	 */
	public void close(PreparedStatement ps) {
		try {
			ps.close();
		} catch (SQLException e) {
			logger.error(e);
		}catch (NullPointerException e) {
			logger.error(e);
		}
	}

	/*
	 * Cierra los objetos Statement.
	 * 
	 * @param ps Objeto Statement.
	 */
	public void close(Statement st) {
		close(st);
	}

	/*
	 * Cierra los objetos PreparedStatement, ResultSet y Connection
	 * simultaneamente.
	 * 
	 * @param ps Objeto PreparedStatement.
	 * 
	 * @param rs Objeto ResultSet.
	 * 
	 * @param conn Objeto Connection.
	 */
	public void close(PreparedStatement ps, ResultSet rs, Connection conn) {
		close(ps);
		close(rs);
		close(conn);
	}

	/*
	 * Cierra los objetos PreparedStatement y ResultSet simultaneamente.
	 * 
	 * @param ps Objeto PreparedStatement.
	 * 
	 * @param rs Objeto ResultSet.
	 */
	public void close(PreparedStatement ps, ResultSet rs) {
		close(ps);
		close(rs);
	}

	/*
	 * Retorna el arreglo con los indices de las consultas que no retornaron
	 * resultados con el numero de filas actualizadas, lo que significa que en
	 * el proceso update no se encontro el id de la ruta.
	 */
	public ArrayList<Integer> getIndexBatchUpdateException(
			BatchUpdateException bue) {
		ArrayList<Integer> listaIndices = new ArrayList<Integer>();
		int[] counts = bue.getUpdateCounts();
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] == Statement.SUCCESS_NO_INFO) {
				listaIndices.add(new Integer(i));
			}
		}
		return listaIndices;
	}
}
