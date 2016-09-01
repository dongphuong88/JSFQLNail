package DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import CARGO.Service;

public class ServicesDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6890530424599759955L;
	public static List<String> colors = Arrays.asList("Aqua","Beige","Chartreuse","CornflowerBlue","Cyan","DarkGreen","FireBrick","Fuchsia","Gold","Lavender","LightGreen",
			"LightSkyBlue", "MediumOrchid" , "PaleGreen", "SeaShell", "Wheat", "Yellow");

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
			e.printStackTrace();
		}
	}
	
	public static List<String> get9AvailableServiceGroupColors() {
		List<String> cates = ServicesDAO.getServiceCategoriesList();
		List<String> rs = new ArrayList<>();
		for( String s : ServicesDAO.colors) {
			if( !cates.contains(s)) {
				rs.add(s);
				if( 9 == rs.size())
					return rs;
			}
		}

		return rs;
	}

	public static ERROR_CODE setService( Service service) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/qlnail?useSSL=false", "pduong", "2H@aclong");

			// Update service_categories
			PreparedStatement stmt;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT id FROM service_categories WHERE name='" + service.getCate_name() + "'");
			long cate_id = -1;
			if( rs!= null && !rs.next()) {
				// new cate
				st.executeUpdate("INSERT INTO service_categories(name, color) VALUES ("+ service.getCate_name() + "," + service.getCate_color() + ")", Statement.RETURN_GENERATED_KEYS);
				rs = st.getGeneratedKeys();
				if( rs != null && rs.next())
					cate_id = rs.getLong(1);
			}
			else {
				cate_id = rs.getLong(1);
			}
			
			// Update services
			stmt = conn.prepareStatement("INSERT INTO services(name, price, duration, turn, description, additional_info, service_group_id, supply_deduction) VALUES (?,?,?,?,?,?,?,?)");
			stmt.setString(1, service.getName().trim());
			stmt.setDouble(2, service.getPrice());
			stmt.setString(3, service.getDuration_hour() + ":" + service.getDuration_minute() + ":0");
			stmt.setFloat(4, service.getTurn());
			stmt.setString(5, service.getDescription());
			stmt.setString(6, service.getAdditionalInfo());
			stmt.setLong(7, cate_id);
			stmt.setDouble(8, service.getService_deduction());
			stmt.executeUpdate();
			
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR_CODE.INVALID_SQL;
		}
		
		return ERROR_CODE.SUCCEED;
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
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	public static List<String> getServiceCategoriesList() {
		List<String> resultSet = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/qlnail?useSSL=false", "pduong", "2H@aclong");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from service_categories");
			while (rs.next()) {
				resultSet.add(rs.getString("name"));
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
		}

		return resultSet.toString();
	}
}
