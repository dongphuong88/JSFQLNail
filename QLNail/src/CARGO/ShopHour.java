package CARGO;

import java.io.Serializable;
import java.util.Date;

public class ShopHour implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4468874636658068142L;
	private long id;
	private String day;
	private Date start_hour;
	private Date end_hour;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Date getStart_hour() {
		return start_hour;
	}
	public void setStart_hour(Date start_hour) {
		this.start_hour = start_hour;
	}
	public Date getEnd_hour() {
		return end_hour;
	}
	public void setEnd_hour(Date end_hour) {
		this.end_hour = end_hour;
	}
	
}
