package CARGO;

import java.io.Serializable;

public class GiftCard implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2617154254463332020L;
	private long id;
	private String no;
	private double amount;
	private String status;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

}
