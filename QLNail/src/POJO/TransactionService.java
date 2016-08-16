package POJO;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class TransactionService implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1005108082893154569L;
	private int transactionServiceID;
	private int transactionID;
	private String serviceName = "";
	private String employeeName = "";
	private Time start;
	private Time finish;
	private double amount;
	private double discount;
	
	public int getTransactionServiceID() {
		return transactionServiceID;
	}
	public void setTransactionServiceID(int transactionServicesID) {
		this.transactionServiceID = transactionServicesID;
	}
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Time getStart() {
		return start;
	}
	public void setStart(Time start) {
		this.start = start;
	}
	public Time getFinish() {
		return finish;
	}
	public void setFinish(Time finish) {
		this.finish = finish;
	}
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
	
	
}
