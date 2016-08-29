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
	private Date startHour;
	private Date endHour;
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
	public Date getStartHour() {
		return startHour;
	}
	public void setStartHour(Date startHour) {
		this.startHour = startHour;
	}
	public Date getEndHour() {
		return endHour;
	}
	public void setEndHour(Date endHour) {
		this.endHour = endHour;
	}
	public boolean isAllday() {
		return allday;
	}
	public void setAllday(boolean allday) {
		this.allday = allday;
	}
	
}
