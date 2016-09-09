package CARGO;

import java.io.Serializable;
import java.util.Date;

public class CalendarService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3409859632257727955L;
	private long id;
	private long staff_id;
	private long customer_id;
	private long service_id;
	private Date start_time;
	private Date end_time;
	private String note;
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
	public long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}
	public long getService_id() {
		return service_id;
	}
	public void setService_id(long service_id) {
		this.service_id = service_id;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
