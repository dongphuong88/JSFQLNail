package POJO;

import java.sql.Date;
import java.sql.Time;

public class TransactionService {
	private int transactionServicesID;
	private int transactionID;
	private String serviceName;
	private String employeeName;
	private Date date;
	private Time start;
	private Time finish;
	private double amount;
	public int getTransactionServicesID() {
		return transactionServicesID;
	}
	public void setTransactionServicesID(int transactionServicesID) {
		this.transactionServicesID = transactionServicesID;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	
	
}
