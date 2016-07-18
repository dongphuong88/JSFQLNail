package Bean;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;

import POJO.TransactionService;

@ViewScoped
@ManagedBean
public class CheckoutBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4510301457190253664L;
	private ArrayList<TransactionService> services = new ArrayList<>();
	TransactionService newTransactionService = new TransactionService();
	TransactionService editTransactionService = new TransactionService();
	
	private String currDateTime = (new SimpleDateFormat("MM/dd/yyyy - hh:mm aa")).format(new Date());
	private double totalServiceAmount;

	private double totalTransaction;
	private String giftCardNo;
	private double giftCardAmount;
	private double creditCardAmount;
	private double cashAmount;
	private double tipAmount;

	public CheckoutBean() {
		
		// 
		for(int i = 0; i < 5; ++i) {
			TransactionService ser = new TransactionService();
			ser.setTransactionServiceID(i);
			ser.setServiceName("service name " + i);
			ser.setEmployeeName("Employee Name " + i);
			ser.setAmount(i*i);
			services.add(ser);
			
		}
		updateServicesAmount();
	}
	
	public void addServiceRow() {
		services.add(newTransactionService);
		newTransactionService = new TransactionService();
		
		updateServicesAmount();
	}
	
	public void updateServicesAmount() {
		totalServiceAmount = 0;
		for( TransactionService ser : services) {
			totalServiceAmount += ser.getAmount();
		}
		updateTransactionAmount();
	}
	
	public void updateTransactionAmount() {
		totalTransaction = totalServiceAmount;
		RequestContext.getCurrentInstance().execute("updateAmount()");
	}
	
	public void deleteTransactionService( TransactionService deleteObj) {
		services.remove(deleteObj);
		updateServicesAmount();
	}
	
	public void clickedPrint( ) {
		services.forEach( service-> {
			totalServiceAmount += service.getAmount();
		}); 
	}

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

	public String getTotalTransaction() {
		return new DecimalFormat("$###,##0.00").format(totalTransaction);
	}

	public void setTotalTransaction(double totalTransaction) {
		this.totalTransaction = totalTransaction;
	}

	public String getTotalServiceAmount() {
		return new DecimalFormat("$###,##0.00").format(totalServiceAmount);
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

	public TransactionService getNewTransactionService() {
		return newTransactionService;
	}

	public void setNewTransactionService(TransactionService newTransactionService) {
		this.newTransactionService = newTransactionService;
	}

	public TransactionService getEditTransactionService() {
		return editTransactionService;
	}

	public void setEditTransactionService(TransactionService editTransactionService) {
		this.editTransactionService = editTransactionService;
	}
}
