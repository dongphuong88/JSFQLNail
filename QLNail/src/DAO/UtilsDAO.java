package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilsDAO {
	public static final String CONN_STRING = "";
	private static Logger logger = LogManager.getLogger( TransactionsDAO.class.getName());
	
	public static Connection getConnection( boolean autoCommit) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/qlnail?useSSL=false", "pduong", "2H@aclong");
			conn.setAutoCommit( autoCommit);
		} catch (ClassNotFoundException e) {
			UtilsDAO.logMessage( "Database Connection", Level.ERROR, e);
		}
		catch (SQLException e) {
			UtilsDAO.logMessage( "Database Connection", Level.ERROR, e);
		}
		return conn;
	}
	public static Connection getConnection() {
		return UtilsDAO.getConnection(false);
	}
	
	public static ERROR_CODE rollbackConnection( Connection conn) {
		try {
			if( conn != null ) {
				conn.rollback();
			}
		}
		catch ( SQLException ee) {
			UtilsDAO.logMessage("TransactionService", Level.ERROR, ee);
		}
		return ERROR_CODE.INVALID_SQL;
	}
	
	public static void closeConnection( Connection conn, Statement... statements) {
		try{
			for( Statement s : statements) {
				if( s != null)
					s.close();
			}
			if( conn != null)
				conn.close();
		}
		catch( SQLException e) {
			UtilsDAO.logMessage( "Database Connection", Level.ERROR, e);
		}
	}
	
	public static void logMessage( String location, Level lv, String msg) {
		logger = LogManager.getLogger( location);
		logger.log( lv, msg);
	}
	
	public static void logMessage( String location, Level lv, Exception msg) {
		logger = LogManager.getLogger( location);
		logger.log( lv, msg, msg);
	}
}
