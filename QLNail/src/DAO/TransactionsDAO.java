package DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Bean.WorkingBean.PayBy;
import CARGO.Transaction;
import CARGO.TransactionService;

public class TransactionsDAO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8892117335504826904L;
	
	public static void main(String[] args) {
	}
	
	public static List<TransactionService> getSalaryReport( Date fromDate, Date toDate) {
		List<TransactionService> resultSet = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime( toDate);
			c.add(Calendar.DATE, 1);
			Date increaseDate = c.getTime();
			conn = UtilsDAO.getConnection(true);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT staff_name, SUM(amount) as amount, SUM(tip) as tip, SUM((amount - discount - service_deduction)* (1 - commission / 100) + tip) as Salary "
							+ "FROM transaction_services "
							+ "WHERE datetime BETWEEN '" + df.format(fromDate) + "' AND '" + df.format(increaseDate) + "' "
							+ "GROUP BY staff_name");
			while( rs != null && rs.next()) {
				TransactionService sr = new TransactionService();
				sr.setStaff_name(rs.getString(1));
				sr.setAmount(rs.getDouble(2));
				sr.setTip(rs.getDouble(3));
				sr.setSalary(rs.getDouble(4));
				resultSet.add(sr);
			}
			rs.close();
		} catch (Exception e) {
			UtilsDAO.logMessage("TransactionService", Level.ERROR, e);
		}
		finally {
			UtilsDAO.closeConnection(conn, stmt);
		}
		
		return resultSet;
	}
	public static TransactionService getSalaryReport( Date fromDate, Date toDate, String staff_name) {
		TransactionService resultSet = new TransactionService();
		Connection conn = null;
		Statement stmt = null;
		
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime( toDate);
			c.add(Calendar.DATE, 1);
			Date increaseDate = c.getTime();
			conn = UtilsDAO.getConnection(true);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT staff_name, SUM(amount) as amount, SUM(tip) as tip, SUM((amount - discount - service_deduction)* (1 - commission / 100) + tip) as Salary "
							+ "FROM transaction_services "
							+ "WHERE staff_name = '" + staff_name + "' AND datetime BETWEEN '" + df.format(fromDate) + "' AND '" + df.format(increaseDate) + "' "
							+ "GROUP BY staff_name");
			if( rs != null && rs.next()) {
				resultSet.setStaff_name(rs.getString(1));
				resultSet.setAmount(rs.getDouble(2));
				resultSet.setTip(rs.getDouble(3));
				resultSet.setSalary(rs.getDouble(4));
			}
			rs.close();
		} catch (Exception e) {
			UtilsDAO.logMessage("TransactionService", Level.ERROR, e);
		}
		finally {
			UtilsDAO.closeConnection(conn, stmt);
		}
		
		return resultSet;
	}
	
	public static List<TransactionService> getSalaryReportDetails( Date fromDate, Date toDate, String staff_name) {
		List<TransactionService> resultSet = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime( toDate);
			c.add(Calendar.DATE, 1);
			Date increaseDate = c.getTime();
			conn = UtilsDAO.getConnection(true);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT datetime, service_group, service_name, amount, tip "
												+ "FROM transaction_services "
												+ "WHERE staff_name = '" + staff_name + "' AND datetime BETWEEN '" + df.format(fromDate) + "' AND '" + df.format(increaseDate) + "';");
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			while( rs != null && rs.next()) {
				TransactionService sr = new TransactionService();
				sr.setDate( df1.parse(rs.getString(1)));
				sr.setService_group( rs.getString(2));
				sr.setService_name(rs.getString(3));
				sr.setAmount(rs.getDouble(4));
				sr.setTip(rs.getDouble(5));
				resultSet.add(sr);
			}
			rs.close();
		} catch (Exception e) {
			UtilsDAO.logMessage("TransactionService", Level.ERROR, e);
		}
		finally {
			UtilsDAO.closeConnection(conn, stmt);
		}
		
		return resultSet;
	}
	
	public static ERROR_CODE setTransactionFromServices( String servicesJSON, String transactionJSON) {
		JSONParser jsonParser = new JSONParser();
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		try {
			JSONObject transactionServices = (JSONObject)jsonParser.parse(servicesJSON);
			JSONObject transaction = (JSONObject)jsonParser.parse(transactionJSON);
			JSONArray services = (JSONArray)transactionServices.get("TransactionServices");
			if( 0 == services.size()) {
				return ERROR_CODE.SERVICE_EMPTY;
			}
			
			conn = UtilsDAO.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			double transactionTotal = Double.parseDouble(transaction.get("total").toString());
			if( 0 == transactionTotal) {
				return ERROR_CODE.SERVICE_EMPTY;
			}
			
			double transactionTip = Double.parseDouble(transaction.get("tip").toString());
			String datetime = transaction.get("datetime").toString();
			String sql = "insert into transactions (cash, total, tip, total_discount, datetime) values ("
					+Double.parseDouble(transaction.get("cash").toString())
					+"," + transactionTotal
					+"," + transactionTip
					+"," + Double.parseDouble(transaction.get("totalDiscount").toString())
					+",'" + datetime
					+"')";
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			rs = stmt.getGeneratedKeys();
			long transactionServiceID = -1;
			while (rs != null && rs.next()) {
				transactionServiceID = rs.getLong(1);
			}
			rs.close();
			rs = null;
			
			ptmt = conn.prepareStatement("insert into transaction_services (transaction_id, service_group, service_name, staff_name, amount, discount, service_deduction,tip,commission, datetime) values (?,?,?,?,?,?,?,?,?,?)");
			ptmt.setLong(1, transactionServiceID);
			for( int i = 0; i < services.size(); ++i) {
				JSONObject obj = (JSONObject)services.get(i);
				
				String serviceGroup = obj.get("serviceGroup").toString();
				String serviceName = obj.get("serviceName").toString();
				double serviceDeduction = 0;
				
				rs = stmt.executeQuery("SELECT supply_deduction from services where name='"+ serviceName +"' AND service_group_id = (SELECT id from service_categories where name='"+ serviceGroup +"')");
				if (rs != null && rs.next()) {
					serviceDeduction = rs.getDouble(1);
				}
				rs.close();
				rs = null;
				
				String staffName = obj.get("staffName").toString();
				float commission = 0f;
				rs = stmt.executeQuery("SELECT commission FROM staffs WHERE name = '" + staffName + "'");
				if (rs != null && rs.next()) {
					commission = rs.getFloat(1);
				}
				rs.close();
				rs = null;
				
				double serviceAmount = Double.parseDouble(obj.get("amount").toString());
				ptmt.setString(2, serviceGroup);
				ptmt.setString(3, serviceName);
				ptmt.setString(4, staffName);
				ptmt.setDouble(5, serviceAmount);
				ptmt.setDouble(6, Double.parseDouble(obj.get("discount").toString()));
				ptmt.setDouble(7, serviceDeduction);
				ptmt.setDouble(8, transactionTip * serviceAmount * 0.8 / transactionTotal); // tip
				ptmt.setFloat(9, commission); // commission
				ptmt.setString(10, datetime);
				ptmt.addBatch();
			}
			ptmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			UtilsDAO.logMessage("TransactionService", Level.ERROR, e);
			return UtilsDAO.rollbackConnection(conn);
		}
		finally {
			UtilsDAO.closeConnection(conn, stmt, ptmt);
		}
		
		return ERROR_CODE.SUCCEED;
	}
	
	public static ERROR_CODE setTransactionFromServices( Transaction transaction, List<TransactionService> services, List<PayBy> payBys) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		try {
			if( 0 == services.size()) {
				return ERROR_CODE.SERVICE_EMPTY;
			}
			
			conn = UtilsDAO.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			double transactionTotal = transaction.getTotal();
			if( 0 == transactionTotal) {
				return ERROR_CODE.SERVICE_EMPTY;
			}
			
			double transactionTip = transaction.getTip();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			String datetime = df.format(transaction.getDatetime());
			String sql = "insert into transactions (cash, total, tip, total_discount, datetime) values ("
					+payBys.get(0).getCash()
					+"," + transactionTotal
					+"," + transactionTip
					+"," + transaction.getTotal_discount()
					+",'" + datetime
					+"')";
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			rs = stmt.getGeneratedKeys();
			long transactionServiceID = -1;
			while (rs != null && rs.next()) {
				transactionServiceID = rs.getLong(1);
			}
			rs.close();
			rs = null;
			
			ptmt = conn.prepareStatement("insert into transaction_services (transaction_id, service_group, service_name, staff_name, amount, discount, service_deduction,tip,commission, datetime) values (?,?,?,?,?,?,?,?,?,?)");
			ptmt.setLong(1, transactionServiceID);
			for( TransactionService s : services) {
				
				String serviceGroup = s.getService_group();
				String serviceName = s.getService_name();
				double serviceDeduction = 0;
				
				rs = stmt.executeQuery("SELECT supply_deduction from services where name='"+ serviceName +"' AND service_group_id = (SELECT id from service_categories where name='"+ serviceGroup +"')");
				if (rs != null && rs.next()) {
					serviceDeduction = rs.getDouble(1);
				}
				rs.close();
				rs = null;
				
				String staffName = s.getStaff_name();
				float commission = 0f;
				rs = stmt.executeQuery("SELECT commission FROM staffs WHERE name = '" + staffName + "'");
				if (rs != null && rs.next()) {
					commission = rs.getFloat(1);
				}
				rs.close();
				rs = null;
				
				double serviceAmount = s.getAmount();
				ptmt.setString(2, serviceGroup);
				ptmt.setString(3, serviceName);
				ptmt.setString(4, staffName);
				ptmt.setDouble(5, serviceAmount);
				ptmt.setDouble(6, s.getDiscount());
				ptmt.setDouble(7, serviceDeduction);
				ptmt.setDouble(8, transactionTip * serviceAmount * 0.8 / transactionTotal); // tip
				ptmt.setFloat(9, commission); // commission
				ptmt.setString(10, datetime);
				ptmt.addBatch();
			}
			ptmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			UtilsDAO.logMessage("TransactionService", Level.ERROR, e);
			return UtilsDAO.rollbackConnection(conn);
		}
		finally {
			UtilsDAO.closeConnection(conn, stmt, ptmt);
		}
		
		return ERROR_CODE.SUCCEED;
	}
}
