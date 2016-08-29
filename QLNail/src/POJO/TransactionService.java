package POJO;

import java.io.Serializable;
import java.util.Date;

public class TransactionService implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1005108082893154569L;
	private long id;
	private long transaction_id;
	private String service_group = "";
	private String service_name = "";
	private String staff_name = "";
	private double amount;
	private double discount;
	private double tip;
	private double commission;
	private double service_deduction;
	private Date date;
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
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
	public String getService_group() {
		return service_group;
	}
	public void setService_group(String service_group) {
		this.service_group = service_group;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public double getTip() {
		return tip;
	}
	public void setTip(double tip) {
		this.tip = tip;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	public double getService_deduction() {
		return service_deduction;
	}
	public void setService_deduction(double service_deduction) {
		this.service_deduction = service_deduction;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
