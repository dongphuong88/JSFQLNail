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

public class TransactionsDAO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8892117335504826904L;
	private static Logger logger = LogManager.getLogger(TransactionsDAO.class.getName());
	public enum ERROR_CODE { SERVICE_EMPTY, INVALID_SQL, SUCCEED};
	
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
	
	public static ERROR_CODE setTransactionFromServices( String servicesJSON, String transactionJSON) {
		JSONParser jsonParser = new JSONParser();
		
		try {
			JSONObject transactionServices = (JSONObject)jsonParser.parse(servicesJSON);
			JSONObject transaction = (JSONObject)jsonParser.parse(transactionJSON);
			JSONArray services = (JSONArray)transactionServices.get("TransactionServices");
			
			if( 0 == services.size()) {
				return ERROR_CODE.SERVICE_EMPTY;
			}
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/qlnail?useSSL=false", "pduong", "2H@aclong");
			Statement stmt = conn.createStatement();
			String sql = "insert into transactions (cash, total, tip, total_discount, datetime) values ("
					+Double.parseDouble(transaction.get("cash").toString())
					+"," + Double.parseDouble(transaction.get("total").toString())
					+"," + Double.parseDouble(transaction.get("tip").toString())
					+"," + Double.parseDouble(transaction.get("totalDiscount").toString())
					+",'" + transaction.get("datetime").toString()
					+"')";
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			long transactionServiceID = -1;
			while (rs != null && rs.next()) {
				transactionServiceID = rs.getLong(1);
			}
			
			PreparedStatement ptmt = conn.prepareStatement("insert into transaction_services (transaction_id, service_group, service_name, staff_name, amount, discount) values (?,?,?,?,?,?)");
			ptmt.setLong(1, transactionServiceID);
			for( int i = 0; i < services.size(); ++i) {
				JSONObject obj = (JSONObject)services.get(i);
				ptmt.setString(2, obj.get("serviceGroup").toString());
				ptmt.setString(3, obj.get("serviceName").toString());
				ptmt.setString(4, obj.get("staffName").toString());
				ptmt.setDouble(5, Double.parseDouble(obj.get("amount").toString()));
				ptmt.setDouble(6, Double.parseDouble(obj.get("discount").toString()));
				ptmt.executeUpdate();
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			ptmt.close();
		} catch (Exception e) {
			logger.error("Exception", e.toString());
			return ERROR_CODE.INVALID_SQL;
		}
		
		return ERROR_CODE.SUCCEED;
	}
}
