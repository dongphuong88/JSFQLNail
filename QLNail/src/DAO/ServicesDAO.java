package DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import CARGO.ServiceCategory;

public class ServicesDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6890530424599759955L;
	private static Logger logger = LogManager.getLogger(ServicesDAO.class.getName());
	public enum ERROR_CODE { INVALID_SQL, SUCCEED};

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		JSONObject resultSet = new JSONObject();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/qlnail?useSSL=false", "pduong", "2H@aclong");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select distinct a.group from services as a");
			while (rs.next()) {
				resultSet.put(rs.getString("group"), new JSONArray());
			}
			rs = stmt.executeQuery("select a.name, a.group from services as a");
			while (rs.next()) {
				JSONArray arr = (JSONArray) resultSet.get(rs.getString("group"));
				arr.add(rs.getString("name"));
			}
			// Clean-up environment
			rs.close();
			stmt.close();
		} catch (Exception e) {
			logger.error("Exception", e.toString());
		}
		System.out.println(resultSet);
	}

	public static void saveService() {
	}
	
	public static Map<String,String> getServiceCategories() {
		Map<String, String> resultSet = new HashMap<String,String>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/qlnail?useSSL=false", "pduong", "2H@aclong");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from service_categories");
			while (rs.next()) {
				resultSet.put(rs.getString("name"), rs.getString("color"));
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
		} catch (Exception e) {
			logger.error("Exception", e.toString());
		}
		
		return resultSet;
	}
	
	public static List<ServiceCategory> getServiceCategoriesList() {
		List<ServiceCategory> resultSet = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/qlnail?useSSL=false", "pduong", "2H@aclong");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from service_categories");
			while (rs.next()) {
				ServiceCategory cate = new ServiceCategory();
				cate.setId(rs.getLong("id"));
				cate.setName(rs.getString("name"));
				cate.setColor(rs.getString("color"));
				resultSet.add(cate);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
		} catch (Exception e) {
			logger.error("Exception", e.toString());
		}
		
		return resultSet;
	}

	@SuppressWarnings("unchecked")
	public static String getServiceGroupsJSONObject() {
		JSONObject resultSet = new JSONObject();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/qlnail?useSSL=false", "pduong", "2H@aclong");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from service_categories");
			while (rs.next()) {
				resultSet.put(rs.getString("name"), new JSONArray());
			}
			rs = stmt.executeQuery("select a.name as n, b.name as g from services as a inner join service_categories as b on a.service_group_id = b.id");
			while (rs.next()) {
				JSONArray arr = (JSONArray) resultSet.get(rs.getString("g"));
				arr.add(rs.getString("n"));
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
		} catch (Exception e) {
			logger.error("Exception", e.toString());
		}

		return resultSet.toString();
	}
}
