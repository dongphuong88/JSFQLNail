package DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import CARGO.Service;

public class ServicesDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6890530424599759955L;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		JSONObject resultSet = new JSONObject();
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = UtilsDAO.getConnection(true);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select a.group from services as a");
			while (rs.next()) {
				resultSet.put(rs.getString("group"), new JSONArray());
			}
			rs = stmt.executeQuery("select a.name, a.group from services as a");
			while (rs.next()) {
				JSONArray arr = (JSONArray) resultSet.get(rs.getString("group"));
				arr.add(rs.getString("name"));
			}
			rs.close();
		} catch (Exception e) {
			UtilsDAO.logMessage("TransactionService", Level.ERROR, e);
		}
		finally {
			UtilsDAO.closeConnection(conn, stmt);
		}
	}
	
	public static List<String> get9AvailableServiceGroupColors() {
		List<String> resultSet = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = UtilsDAO.getConnection(true);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT name FROM color_refs WHERE name NOT IN ( SELECT color FROM service_categories) LIMIT 9");
			while( rs != null && rs.next())
				resultSet.add( rs.getString(1));
			rs.close();
		} catch (Exception e) {
			UtilsDAO.logMessage("TransactionService", Level.ERROR, e.getMessage());
		}
		finally {
			UtilsDAO.closeConnection(conn, stmt);
		}
		System.out.println(resultSet);
		return resultSet;
	}

	public static ERROR_CODE setService( Service service) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ptmt = null;
		
		try {
			conn = UtilsDAO.getConnection();
			stmt = conn.createStatement();
			
			if( service.getCate_name().trim().equals(""))
				return ERROR_CODE.SERVICE_GROUP_EMPTY;
			if( service.getName().trim().equals(""))
				return ERROR_CODE.SERVICE_NAME_EMPTY;

			// Update service_categories
			ResultSet rs = stmt.executeQuery("SELECT id FROM service_categories WHERE name='" + service.getCate_name().trim() + "'");
			long cate_id = -1;
			if( rs!= null && !rs.next()) {
				// new cate
				stmt.executeUpdate("INSERT INTO service_categories(name, color) VALUES ('"+ service.getCate_name().trim() + "','" + service.getCate_color().trim() + "')", Statement.RETURN_GENERATED_KEYS);
				rs = stmt.getGeneratedKeys();
				if( rs != null && rs.next())
					cate_id = rs.getLong(1);
			}
			else {
				// update color
				cate_id = rs.getLong(1);
				if( !service.getCate_color().trim().equals(""))
					stmt.executeUpdate("UPDATE service_categories SET color='" + service.getCate_color().trim() + "' WHERE id=" + cate_id);
			}
			rs.close();
			rs = null;
			
			rs = stmt.executeQuery("SELECT name FROM services WHERE service_group_id =" + cate_id + " AND name='" + service.getName().trim() + "'");
			if( rs != null && rs.next()) {
				rs.close();
				return ERROR_CODE.SERVICE_ALREADY_EXIST;
			}
			
			// Update services
			ptmt = conn.prepareStatement("INSERT INTO services(name, price, duration, turn, description, additional_info, service_group_id, supply_deduction) VALUES (?,?,?,?,?,?,?,?)");
			ptmt.setString(1, service.getName().trim());
			ptmt.setDouble(2, service.getPrice());
			ptmt.setString(3, service.getDuration_hour() + ":" + service.getDuration_minute() + ":0");
			ptmt.setFloat(4, service.getTurn());
			ptmt.setString(5, service.getDescription());
			ptmt.setString(6, service.getAdditionalInfo());
			ptmt.setLong(7, cate_id);
			ptmt.setDouble(8, service.getService_deduction());
			ptmt.executeUpdate();
			
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
	
	public static List<List<Service>> getServices() {
		Map<String, List<Service>> services = new HashMap<String, List<Service>>();
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = UtilsDAO.getConnection(true);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select c.name as cate, s.name as service, price FROM services as s LEFT JOIN service_categories as c ON s.service_group_id = c.id;");
			while (rs.next()) {
				if( null == services.get(rs.getString(1)))
					services.put(rs.getString(1), new ArrayList<Service>());
				Service s = new Service();
				s.setName(rs.getString(2));
				s.setPrice(rs.getDouble(3));
				s.setCate_name(rs.getString(1));
				services.get(rs.getString(1)).add(s);
			}
			rs.close();
		} catch (Exception e) {
			UtilsDAO.logMessage("Services", Level.ERROR, e);
		}
		finally {
			UtilsDAO.closeConnection(conn, stmt);
		}
		List<List<Service>> resultSet = new ArrayList<List<Service>>();
		for(String k : services.keySet()) {
			resultSet.add(services.get(k));
		}
		
		return resultSet;
	}
	
	public static Map<String,String> getServiceCategories() {
		Map<String, String> resultSet = new HashMap<String,String>();
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = UtilsDAO.getConnection(true);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from service_categories");
			while (rs.next()) {
				resultSet.put(rs.getString("name"), rs.getString("color"));
			}
			rs.close();
		} catch (Exception e) {
			UtilsDAO.logMessage("Services", Level.ERROR, e);
		}
		finally {
			UtilsDAO.closeConnection(conn, stmt);
		}
		
		return resultSet;
	}
	
	public static List<String> getServiceCategoriesList() {
		List<String> resultSet = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = UtilsDAO.getConnection(true);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from service_categories");
			while (rs.next()) {
				resultSet.add(rs.getString("name"));
			}
			rs.close();
		} catch (Exception e) {
			UtilsDAO.logMessage("Services", Level.ERROR, e);
		}
		finally {
			UtilsDAO.closeConnection(conn, stmt);
		}
		
		return resultSet;
	}

	@SuppressWarnings("unchecked")
	public static String getServiceGroupsJSONObject() {
		JSONObject resultSet = new JSONObject();
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = UtilsDAO.getConnection(true);
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select s.name as n, c.name as g, price FROM services as s LEFT JOIN service_categories as c ON s.service_group_id = c.id;");
			while (rs.next()) {
				if( null == resultSet.get( rs.getString(2)))
					resultSet.put(rs.getString(2), new JSONArray());
				JSONArray arr = (JSONArray) resultSet.get(rs.getString(2));
				JSONObject obj = new JSONObject();
				obj.put( "serviceName", rs.getString(1));
				obj.put( "price", rs.getDouble(3));
				arr.add(obj);
			}
			rs.close();
		} catch (Exception e) {
			UtilsDAO.logMessage("Services", Level.ERROR, e);
		}
		finally {
			UtilsDAO.closeConnection(conn, stmt);
		}

		return resultSet.toString();
	}
}
