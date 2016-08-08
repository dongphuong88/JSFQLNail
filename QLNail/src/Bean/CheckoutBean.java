package Bean;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import POJO.GiftCard;
import POJO.TransactionService;

@ViewScoped
@ManagedBean
public class CheckoutBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -95083139120347266L;

	private Date currDateTime = new Date();
	
	private ArrayList<TransactionService> services = new ArrayList<>();
	
	private double dcAmount;
	private double dcPercent;
	private double tip;
	private double cash;
	private List<Double> credit;
	private List<GiftCard> gifts;
	
	private boolean isStaffDiscount;
	private double totalServiceAmount;
	private double total;

	public CheckoutBean() {
		
		// 
		for(int i = 0; i < 5; ++i) {
			TransactionService ser = new TransactionService();
			ser.setTransactionServiceID(i);
			ser.setServiceName("service name " + i);
			ser.setEmployeeName("Employee Name " + i);
			ser.setAmount(i*i + 1000);
			services.add(ser);
		}
		
		//updateServicesAmount();
	}
	
	public void addServiceRow() {
		TransactionService service = new TransactionService();
		service.setServiceName("Enter Service");
		service.setEmployeeName("Enter Staff Name");
		services.add(service);
	}
	public void deleteServiceRow( TransactionService deleteObj) {
		services.remove(deleteObj);
		//updateServicesAmount();
	}
	
//	public void updateServicesAmount() {
//		totalServiceAmount = 0;
//		for( TransactionService ser : services) {
//			totalServiceAmount += ser.getAmount();
//		}
//		
//		updateTransactionAmount();
//	}
	
//	public void updateTransactionAmount() {
//		total = totalServiceAmount;
//	}

	public Date getCurrDateTime() {
		return currDateTime;
	}

	public void setCurrDateTime(Date currDateTime) {
		this.currDateTime = currDateTime;
	}

	public ArrayList<TransactionService> getServices() {
		return services;
	}

	public void setServices(ArrayList<TransactionService> services) {
		this.services = services;
	}

	public double getDcAmount() {
		return dcAmount;
	}

	public void setDcAmount(double dcAmount) {
		this.dcAmount = dcAmount;
	}

	public double getDcPercent() {
		return dcPercent;
	}

	public void setDcPercent(double dcPercent) {
		this.dcPercent = dcPercent;
	}

	public double getTip() {
		return tip;
	}

	public void setTip(double tip) {
		this.tip = tip;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public List<Double> getCredit() {
		return credit;
	}

	public void setCredit(List<Double> credit) {
		this.credit = credit;
	}

	public List<GiftCard> getGifts() {
		return gifts;
	}

	public void setGifts(List<GiftCard> gifts) {
		this.gifts = gifts;
	}

	public boolean isStaffDiscount() {
		return isStaffDiscount;
	}

	public void setStaffDiscount(boolean isStaffDiscount) {
		this.isStaffDiscount = isStaffDiscount;
	}

	public double getTotalServiceAmount() {
		return totalServiceAmount;
	}

	public void setTotalServiceAmount(double totalServiceAmount) {
		this.totalServiceAmount = totalServiceAmount;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
