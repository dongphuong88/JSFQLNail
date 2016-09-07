package CARGO;

import java.io.Serializable;
import java.util.Date;

public class CalendarTransaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6981952424053767689L;
	private long id;
	private long transaction_id;
	private Date start_time;
	private Date end_time;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(long transaction_id) {
		this.transaction_id = transaction_id;
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
}
