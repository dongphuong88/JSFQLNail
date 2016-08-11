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
	private double totalServiceAmount;
	
	private boolean dcAmountShow = true;
	private boolean dcAmountBtnStatus;
	private double dcAmount;
	private boolean dcPercentShow = true;
	private boolean dcPercentBtnStatus;
	private double dcPercent;
	private double tip;
	private double cash;
	private List<Double> credit;
	private List<GiftCard> gifts;
	
	private boolean staffDiscount;
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
		
		updateServicesAmount();
	}
	
	public void addServiceRow() {
		TransactionService service = new TransactionService();
		service.setServiceName("Enter Service");
		service.setEmployeeName("Enter Staff Name");
		services.add(service);
	}
	public void deleteServiceRow( TransactionService deleteObj) {
		services.remove(deleteObj);
		updateServicesAmount();
	}
	
	public void updateServicesAmount() {
		totalServiceAmount = 0;
		for( TransactionService ser : services) {
			totalServiceAmount += ser.getAmount();
		}
		
		//updateTransactionAmount();
	}
	
//	public void updateTransactionAmount() {
//		total = totalServiceAmount;
//	}
	
	public void changeDCShow() {
		if( dcAmountBtnStatus ) {
			dcAmountShow = true;
			dcPercentShow = false;
		}
		else if( dcPercentBtnStatus) {
			dcPercentShow = true;
			dcAmountShow = false;
		}
		else {
			dcPercentShow = true;
			dcAmountShow = true;
		}
	}

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

	public boolean isDcAmountShow() {
		return dcAmountShow;
	}

	public void setDcAmountShow(boolean dcAmountShow) {
		this.dcAmountShow = dcAmountShow;
	}

	public boolean isDcPercentShow() {
		return dcPercentShow;
	}

	public void setDcPercentShow(boolean dcPercentShow) {
		this.dcPercentShow = dcPercentShow;
	}

	public boolean isStaffDiscount() {
		return staffDiscount;
	}

	public void setStaffDiscount(boolean staffDiscount) {
		this.staffDiscount = staffDiscount;
	}

	public boolean isDcAmountBtnStatus() {
		return dcAmountBtnStatus;
	}

	public void setDcAmountBtnStatus(boolean dcAmountBtnStatus) {
		this.dcAmountBtnStatus = dcAmountBtnStatus;
	}

	public boolean isDcPercentBtnStatus() {
		return dcPercentBtnStatus;
	}

	public void setDcPercentBtnStatus(boolean dcPercentBtnStatus) {
		this.dcPercentBtnStatus = dcPercentBtnStatus;
	}

}
