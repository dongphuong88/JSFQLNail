package DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ServicesDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6890530424599759955L;

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
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(resultSet);
	}

	public static void saveService() {
//		JSONParser parser = new JSONParser();
//		JSONArray empNames = new JSONArray();
//		try {
//			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(HelperDAO.DIRECTORY + "services.dat"));
//			JSONArray emps = (JSONArray) jsonObject.get("employees");
//			for (Object object : emps) {
//				empNames.add(((JSONObject) object).get("name").toString());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@SuppressWarnings("unchecked")
	public static String getServiceGroupsJSONObject() {
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
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultSet.toString();
	}
}
