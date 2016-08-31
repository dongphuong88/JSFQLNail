package CARGO;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8231308936851192669L;
	private long id;
	private Date datetime;
	private double total;
	private double total_discount;
	private double cash;
	private double tip;

	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getCash() {
		return cash;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
	public double getTip() {
		return tip;
	}
	public void setTip(double tip) {
		this.tip = tip;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public double getTotal_discount() {
		return total_discount;
	}
	public void setTotal_discount(double total_discount) {
		this.total_discount = total_discount;
	}
}
