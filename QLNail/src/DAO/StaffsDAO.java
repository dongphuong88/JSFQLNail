package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.primefaces.json.JSONArray;

import CARGO.Staff;
import CARGO.StaffSchedule;

public class StaffsDAO {
	
	public static void main(String[] args) {
	}
	
	@SuppressWarnings("resource")
	public static ERROR_CODE setStaff( Staff staff) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		try {
			conn = UtilsDAO.getConnection();
			stmt = conn.createStatement();
			
			if( staff.getName().trim().equals(""))
				return ERROR_CODE.STAFF_NAME_EMPTY;
			
			rs = stmt.executeQuery("SELECT name FROM staffs WHERE name='" + staff.getName().trim() + "'");
			if( rs!= null && rs.next()) {
				rs.close();
				return ERROR_CODE.STAFF_ALREADY_EXISTED;
			}

			// Update staffs
			ptmt = conn.prepareStatement("INSERT INTO staffs(name,title,phone,commission,hourly_rate,permission) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS );
			ptmt.setString(1, staff.getName().trim());
			ptmt.setString(2, staff.getTitle().trim());
			ptmt.setString(3, staff.getPhone().trim());
			ptmt.setFloat(4, staff.getCommission());
			ptmt.setFloat(5, staff.getHourly_rate());
			ptmt.setString(6, staff.getPermission());
			ptmt.executeUpdate();
			
			rs = ptmt.getGeneratedKeys();
			long staffId = -1;
			if (rs != null && rs.next()) {
				staffId = rs.getLong(1);
			}
			rs.close();
			rs = null;
			
			// Update staff_skills
			long cate_id = -1;
			ptmt = conn.prepareStatement("INSERT INTO staff_skills(staff_id,service_group_id) VALUES (?,?)");
			ptmt.setLong(1, staffId);
			for( String s : staff.getAbilities()) {
				rs = stmt.executeQuery("SELECT id FROM service_categories WHERE name='" + s + "'");
				if (rs != null && rs.next()) {
					cate_id = rs.getLong(1);
				}
				ptmt.setLong(2, cate_id);
				ptmt.addBatch();
			}
			ptmt.executeBatch();
			
			// Update staff_availables
			ptmt = conn.prepareStatement("INSERT INTO staff_availables(staff_id,day,startHour,endHour,allday) VALUES (?,?,?,?,?)");
			ptmt.setLong(1, staffId);
			for( StaffSchedule s : staff.getSchedules()) {
				if( s.isAvailable() ) {
					ptmt.setString(2, s.getDay());
					ptmt.setString(3, s.getStartHour() + ":0:0");
					ptmt.setString(4, s.getEndHour() + ":0:0");
					ptmt.setBoolean(5, s.isAllday());
					ptmt.addBatch();
				}
			}
			ptmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			UtilsDAO.logMessage("Staffs", Level.ERROR, e);
			return UtilsDAO.rollbackConnection(conn);
		}
		finally {
			UtilsDAO.closeConnection(conn, stmt, ptmt);
		}
		
		return ERROR_CODE.SUCCEED;
	}
	
	public static List<Staff> getStaffs() {
		List<Staff> resultSet = new ArrayList<Staff>();
		Connection conn = null;
		Statement stmt = null;
		
		try{
			conn = UtilsDAO.getConnection(true);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT name FROM staffs");
			while (rs != null && rs.next()) {
				Staff s = new Staff();
				s.setName(rs.getString(1));
				resultSet.add(s);
			}
			rs.close();
			
		}catch (Exception e) {
			UtilsDAO.logMessage("Staffs", Level.ERROR, e);
		}
		finally {
			UtilsDAO.closeConnection(conn, stmt);
		}
		
		return resultSet;
	}
	
	public static String getStaffNameListJSONArray(){
		JSONArray resultSet = new JSONArray();
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = UtilsDAO.getConnection(true);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT name FROM staffs");
			while (rs != null && rs.next()) {
				resultSet.put(rs.getString("name"));
			}
		} catch (Exception e) {
			UtilsDAO.logMessage("Staffs", Level.ERROR, e);
		}
		finally {
			UtilsDAO.closeConnection(conn, stmt);
		}
		
		return resultSet.toString();
	}
	
	public static List<String> getStaffNameList(){
		List<String> resultSet = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = UtilsDAO.getConnection(true);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT name FROM staffs");
			while (rs != null && rs.next()) {
				resultSet.add(rs.getString("name"));
			}
		} catch (Exception e) {
			UtilsDAO.logMessage("Staffs", Level.ERROR, e);
		}
		finally {
			UtilsDAO.closeConnection(conn, stmt);
		}
		
		return resultSet;
	}
}
