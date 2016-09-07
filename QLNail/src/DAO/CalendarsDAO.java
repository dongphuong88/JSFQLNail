package DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CalendarsDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 573316043725066996L;
	public static void main(String[] args) {
		
	}
	
	@SuppressWarnings("unchecked")
	public static String getEventsInDayJSON( Date date) {
		// Array to keep JSON Order
		JSONArray resultSet = new JSONArray();
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = UtilsDAO.getConnection(true);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT name FROM staffs");
			while( rs!= null && rs.next()) {
				JSONObject staff = new JSONObject();
				JSONObject staffDetail = new JSONObject();
				JSONArray events = new JSONArray();
				for( int i = 0 ; i < 5; i++) {
					JSONObject event = new JSONObject();
					event.put("eventName", rs.getString(1) + i);
					event.put("startHour", 10 + i);
					event.put("startMinute", (8 + i*3)%60);
					event.put("endHour", 12 + i);
					event.put("endMinute", (8 + i*3)%60);
					events.add(event);
				}
				staffDetail.put("events", events);
				staffDetail.put("startHour", 10);
				staffDetail.put("endHour", 16);
				staff.put(rs.getString(1), staffDetail);
				resultSet.add(staff);
			}
			
		} catch (Exception e) {
			UtilsDAO.logMessage("TransactionService", Level.ERROR, e);
		}
		finally {
			UtilsDAO.closeConnection(conn, stmt);
		}

		System.out.println(resultSet);
		return resultSet.toString();
	}

}
