package POJO;

import java.io.Serializable;
import java.util.Date;

public class StaffAvailable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8733339035207425846L;
	private long id;
	private long staff_id;
	private String day; 	// Monday, Tuesday,... or day string
	private int startHour = 9;
	private int endHour = 20;
	private boolean allday;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(long staff_id) {
		this.staff_id = staff_id;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public boolean isAllday() {
		return allday;
	}
	public void setAllday(boolean allday) {
		this.allday = allday;
	}
	public int getStartHour() {
		return startHour;
	}
	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}
	public int getEndHour() {
		return endHour;
	}
	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}
	
}
