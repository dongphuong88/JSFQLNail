package Bean;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;

import POJO.TransactionService;

@ViewScoped
@ManagedBean
public class CheckoutBean {
	private ArrayList<TransactionService> services = new ArrayList<>();
	private String currDateTime;
	private double totalServiceAmount;
	private double totalTransaction;
	private String giftCardNo;
	private double giftCardAmount;
	private double creditCardAmount;
	private double cashAmount;
	private double tipAmount;

	public String getGiftCardNo() {
		return giftCardNo;
	}

	public void setGiftCardNo(String giftCardNo) {
		this.giftCardNo = giftCardNo;
	}

	public double getGiftCardAmount() {
		return giftCardAmount;
	}

	public void setGiftCardAmount(double giftCardAmount) {
		this.giftCardAmount = giftCardAmount;
	}

	public double getCreditCardAmount() {
		return creditCardAmount;
	}

	public void setCreditCardAmount(double creditCardAmount) {
		this.creditCardAmount = creditCardAmount;
	}

	public double getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(double cashAmount) {
		this.cashAmount = cashAmount;
	}

	public double getTipAmount() {
		return tipAmount;
	}

	public void setTipAmount(double tipAmount) {
		this.tipAmount = tipAmount;
	}

	public double getTotalTransaction() {
		return totalTransaction;
	}

	public void setTotalTransaction(double totalTransaction) {
		this.totalTransaction = totalTransaction;
	}

	public String getTotalServiceAmount() {
		return new DecimalFormat("$###,###.00").format(totalServiceAmount);
	}

	public void setTotalServiceAmount(double totalServiceAmount) {
		this.totalServiceAmount = totalServiceAmount;
	}

	public ArrayList<TransactionService> getServices() {
		return services;
	}

	public void setServices(ArrayList<TransactionService> services) {
		this.services = services;
	}

	public String getCurrDateTime() {
		return currDateTime;
	}

	public void setCurrDateTime(String currDateTime) {
		this.currDateTime = currDateTime;
	}

	public CheckoutBean() {
		// get currDateTime
		currDateTime = (new SimpleDateFormat("MM/dd/yyyy - hh:mm aa")).format(new Date());
	}

	public void addNewServiceRow() {
		services.add( new TransactionService()); 
		RequestContext.getCurrentInstance().execute("updateServiceTotal()");
	}
	
	public void deleteTransactionRow( TransactionService ser) {
		services.remove(ser);
		RequestContext.getCurrentInstance().execute("updateServiceTotal()");
	}
	
	public void clickedPrint( ) {
		totalServiceAmount = 0;
		services.forEach( service-> {
			totalServiceAmount += service.getAmount();
		}); 
	}
	
}
