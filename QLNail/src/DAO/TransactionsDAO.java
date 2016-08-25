package DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class TransactionsDAO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8892117335504826904L;
	private static Logger logger = LogManager.getLogger(TransactionsDAO.class.getName());
	
	public static void main(String[] args) {
		JSONArray resultSet = new JSONArray();
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/qlnail?useSSL=false", "pduong", "2H@aclong");
			Statement stmt = conn.createStatement();
			String sql;
			sql = "INSERT INTO services (services.name, services.price, services.group) VALUES ('Pedi Deluxe', '45', 'Pedi');SELECT LAST_INSERT_ID();";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				//resultSet.put(rs.getString("name"));
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
		} catch (Exception e) {
			logger.error("Exception", e.toString());
		}
		
		System.out.println(resultSet);
	}
	
	public static boolean setTransactionFromServices( String servicesJSON, String transactionJSON) {
//		Services: {"TransactionServices":[{"serviceName":"Pedi Classic","employeeName":"Kelly","amount":321,"discount":321},{"serviceName":"Pedi Deluxe","employeeName":"Nancy","amount":321,"discount":121},]}
//		TransactionJSON: {"totalDiscount":32,"total":168,"tip":0,"cash":100}
		JSONParser jsonParser = new JSONParser();
		
		System.out.println("Services: " + servicesJSON);
		System.out.println("TransactionJSON: " + transactionJSON);
		
		try {
			JSONObject transactionServices = (JSONObject)jsonParser.parse(servicesJSON);
			JSONObject transaction = (JSONObject)jsonParser.parse(transactionJSON);
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/qlnail?useSSL=false", "pduong", "2H@aclong");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("insert into transactions (transactions.cash, transactions.total, transactions.tip, transactions.total_discount, transactions.datetime) values "
					+ "(" + transactionServices.get("cash")
					+ "," + transactionServices.get("total")
					+ "," + transactionServices.get("tip")
					+ "," + transactionServices.get("totalDiscount")
					+ "," + transactionServices.get("datetime") + ");SELECT LAST_INSERT_ID();");
			int transactionServiceID = -1;
			while (rs.next()) {
				transactionServiceID = rs.getInt(0);
			}
			
			PreparedStatement ptmt = conn.prepareStatement("insert into transaction_services (transaction_id, service_group, service_name, staff_name, amount, discount) values (?,?,?,?,?,?)");
			ptmt.setInt(1, transactionServiceID);
			JSONArray arr = (JSONArray)transaction.get("TransactionsServices");
			for( int i = 0; i < arr.size(); ++i) {
				JSONObject obj = (JSONObject)arr.get(i);
				ptmt.setString(2, obj.get("serviceGroup").toString());
				ptmt.setString(3, obj.get("serviceName").toString());
				ptmt.setString(4, obj.get("staffName").toString());
				ptmt.setDouble(5, (double)obj.get("amount"));
				ptmt.setDouble(6, (double)obj.get("discount"));
				ptmt.executeQuery();
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			ptmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception", e.toString());
			return false;
		}
		
		
		return true;
	}
}
