package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.json.JSONArray;

import CARGO.Staff;
import CARGO.StaffSchedule;

public class StaffsDAO {
	
	public static void main(String[] args) {
	}
	
	public static ERROR_CODE setStaff( Staff staff) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/qlnail?useSSL=false", "pduong", "2H@aclong");

			// Update staffs
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO staffs(name, title,phone) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS );
			stmt.setString(1, staff.getName().trim());
			stmt.setString(2, staff.getTitle().trim());
			stmt.setString(3, staff.getPhone().trim());
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			long staffId = -1;
			if (rs != null && rs.next()) {
				staffId = rs.getLong(1);
			}
			
			// Update staff_refs
			PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO staff_refs(staff_id,commision) VALUES (?,?)");
			stmt1.setLong(1, staffId);
			stmt1.setFloat(2, staff.getCommission());
			stmt1.executeUpdate();
			
			// Update staff_skills
			Statement st = conn.createStatement();
			long cate_id = -1;
			stmt = conn.prepareStatement("INSERT INTO staff_skills(staff_id,service_group_id) VALUES (?,?)");
			stmt.setLong(1, staffId);
			for( String s : staff.getAbilities()) {
				rs = st.executeQuery("SELECT id FROM service_categories WHERE name='" + s + "'");
				if (rs != null && rs.next()) {
					cate_id = rs.getLong(1);
				}
				stmt.setLong(2, cate_id);
				stmt.executeUpdate();
			}
			
			// Update staff_availables
			stmt = conn.prepareStatement("INSERT INTO staff_availables(staff_id,day,startHour,endHour,allday) VALUES (?,?,?,?,?)");
			stmt.setLong(1, staffId);
			for( StaffSchedule s : staff.getSchedules()) {
				if( s.isAvailable() ) {
					stmt.setString(2, s.getDay());
					stmt.setString(3, s.getStartHour() + ":0:0");
					stmt.setString(4, s.getEndHour() + ":0:0");
					stmt.setBoolean(5, s.isAllday());
					stmt.executeUpdate();
				}
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR_CODE.INVALID_SQL;
		}
		
		return ERROR_CODE.SUCCEED;
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
			while (rs != null && rs.next()) {
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
	
	public static List<String> getStaffNameList(){
		List<String> resultSet = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/qlnail?useSSL=false", "pduong", "2H@aclong");
			Statement stmt = conn.createStatement();
			String sql;
			sql = "SELECT name FROM staffs";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs != null && rs.next()) {
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
}
