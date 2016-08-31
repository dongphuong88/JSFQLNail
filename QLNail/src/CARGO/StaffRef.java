package CARGO;

import java.io.Serializable;

public class StaffRef implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1241077679288666422L;
	private long id;
	private long staff_id;
	private float turn;
	private float commission;
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
	public float getTurn() {
		return turn;
	}
	public void setTurn(float turn) {
		this.turn = turn;
	}
	public float getCommission() {
		return commission;
	}
	public void setCommission(float commission) {
		this.commission = commission;
	}
}
