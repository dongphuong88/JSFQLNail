package DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TransactionsDAO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8892117335504826904L;
	
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
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
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
			double transactionTotal = Double.parseDouble(transaction.get("total").toString());
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
			ResultSet rs = stmt.getGeneratedKeys();
			long transactionServiceID = -1;
			while (rs != null && rs.next()) {
				transactionServiceID = rs.getLong(1);
			}
			
			PreparedStatement ptmt = conn.prepareStatement("insert into transaction_services (transaction_id, service_group, service_name, staff_name, amount, discount, service_deduction,tip,commission, datetime) values (?,?,?,?,?,?,?,?,?,?)");
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
				
				String staffName = obj.get("staffName").toString();
				float commission = 0f;
				rs = stmt.executeQuery("SELECT commision FROM staff_refs WHERE staff_id = (SELECT id from staffs WHERE name = '" + staffName + "')");
				if (rs != null && rs.next()) {
					commission = rs.getFloat(1);
				}
				
				double serviceAmount = Double.parseDouble(obj.get("amount").toString());
				ptmt.setString(2, serviceGroup);
				ptmt.setString(3, serviceName);
				ptmt.setString(4, staffName);
				ptmt.setDouble(5, serviceAmount);
				ptmt.setDouble(6, Double.parseDouble(obj.get("discount").toString()));
				ptmt.setDouble(7, serviceDeduction);
				ptmt.setDouble(8, transactionTip * serviceAmount / transactionTotal); // tip
				ptmt.setFloat(9, commission); // commission
				ptmt.setString(10, datetime);
				ptmt.executeUpdate();
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			ptmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR_CODE.INVALID_SQL;
		}
		
		return ERROR_CODE.SUCCEED;
	}
}
