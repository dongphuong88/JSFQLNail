package CARGO;

import java.io.Serializable;

public class StaffSchedule implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2529155677305287037L;
	private String day;
	private boolean allday;
	private int startHour;
	private int endHour;
	private boolean available;
	
	public StaffSchedule( String day, boolean allday, int startHour, int endHour) {
		this.day = day;
		this.allday = allday;
		this.startHour = startHour;
		this.endHour = endHour;
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

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
}
