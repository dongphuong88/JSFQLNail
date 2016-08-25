package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.json.JSONArray;

public class StaffsDAO {
	final static Logger log = LogManager.getLogger(StaffsDAO.class.getName());
	
	public static void main(String[] args) {
	}
	
	public static String getStaffNameListJSONArray(){
		JSONArray resultSet = new JSONArray();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/qlnail?useSSL=false", "pduong", "2H@aclong");
			Statement stmt = conn.createStatement();
			String sql;
			sql = "SELECT name FROM staffs";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				resultSet.put(rs.getString("name"));
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultSet.toString();
	}
}
