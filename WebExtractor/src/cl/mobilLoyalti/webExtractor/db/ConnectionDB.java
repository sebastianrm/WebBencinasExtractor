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
	
	public ConnectionDB(){
		
	}
	
	/*
	 * Cierra el objeto Statement
	 * 
	 * @param ps Objeto Statement.
	 */	
	private void closeSt(Statement st){
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				logger.warn(e.getMessage(), e);
			}finally {
				st = null;
			}
		}
	}
	
	/*
	 * Cierra el objeto PreparedStatement
	 * 
	 * @param ps Objeto PreparedStatement.
	 */	
	private void closePs(PreparedStatement ps){
		if(ps != null){
			try {
				ps.close();
			} catch (SQLException e) {
				logger.warn(e.getMessage(), e);
			}finally {
				ps = null;
			}
		}
	}
	
	/*
	 * Cierra el objeto ResultSet
	 * 
	 * @param rs Objeto ResultSet.
	 */		
	private void closeRs(ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				logger.warn(e.getMessage(), e);
			}finally {
				rs = null;
			}
		}		
	}

	/*
	 * Cierra el objeto Connection
	 * 
	 * @param conn Objeto Connection.
	 */			
	private void closeConn(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				logger.warn(e.getMessage(), e);
			}finally {
				conn = null;
			}
		}	
	}
	
	/*
	 * Cierra los objetos PreparedStatement y Connection simultaneamente.
	 * 
	 * @param ps   Objeto PreparedStatement. 
	 * @param conn Objeto Connection.
	 */			
	public void closeAll(PreparedStatement ps, Connection conn){
		closePs(ps);
		closeConn(conn);
	}
	
	/*
	 * Cierra los objetos PreparedStatement.
	 * 
	 * @param ps Objeto PreparedStatement. 
	 */			
	public void closeAll(PreparedStatement ps){
		closePs(ps);
	}
	
	/*
	 * Cierra los objetos Statement.
	 * 
	 * @param ps Objeto Statement. 
	 */			
	public void closeAll(Statement st){
		closeSt(st);
	}
	
	/*
	 * Cierra los objetos PreparedStatement, ResultSet y Connection simultaneamente.
	 * 
	 * @param ps   Objeto PreparedStatement. 
	 * @param rs   Objeto ResultSet.
	 * @param conn Objeto Connection.
	 */			
	public void closeAll(PreparedStatement ps, ResultSet rs, Connection conn){
		closePs(ps);
		closeRs(rs);
		closeConn(conn);
	}
	
	/*
	 * Cierra los objetos PreparedStatement y ResultSet simultaneamente.
	 * 
	 * @param ps Objeto PreparedStatement. 
	 * @param rs Objeto ResultSet.
	 */	
	public void closeAll(PreparedStatement ps, ResultSet rs){
		closePs(ps);
		closeRs(rs);
	}
	
	
	/*
	 * Retorna el arreglo con los indices de las consultas que no retornaron resultados con el numero de filas actualizadas,
	 * lo que significa que en el proceso update no se encontro el id de la ruta.
	 */	
	public ArrayList<Integer> getIndexBatchUpdateException(BatchUpdateException bue){
		ArrayList<Integer> listaIndices = new ArrayList<Integer>();
		int[] counts = bue.getUpdateCounts();
		for (int i = 0; i < counts.length; i++) {
			if(counts[i] == Statement.SUCCESS_NO_INFO){
				listaIndices.add(new Integer(i));
			}
		}
	    return listaIndices;
	}
}
