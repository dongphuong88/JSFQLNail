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
	TransactionService newTransactionService = new TransactionService();
	TransactionService editTransactionService = new TransactionService();
	
	private List<String> discountTypes = new ArrayList<String>();
	private String discountSelected;
	
	private double totalServiceAmount;
	private String giftCardNo;
	private double giftCard;
	private double cash;
	private double credit;
	private double tip;
	private List<String> tipTypes = new ArrayList<String>();
	private String tipSelected;
	private double total;

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
		
		// 
		discountTypes.add("10%");
		discountTypes.add("20%");
		
		updateServicesAmount();
	}
	
	public void addServiceRow() {
		services.add(newTransactionService);
		newTransactionService = new TransactionService();
		
		updateServicesAmount();
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
		
		updateTipHint();
		updateTransactionAmount();
	}
	
	public void tipChange() {
		tip = Integer.parseInt(tipSelected.substring(0,2)) * totalServiceAmount / 100;
		total = totalServiceAmount + tip;
		
		RequestContext.getCurrentInstance().update("transactionForm");
		
		System.out.println( tip);
	}
	
	public void updateTipHint() {
		tipTypes.clear();
		DecimalFormat f = new DecimalFormat("$###,###,##0.00");
		tipTypes.add("15% - " + f.format(totalServiceAmount*15/100));
		tipTypes.add("18% - " + f.format(totalServiceAmount*18/100));
		tipTypes.add("20% - " + f.format(totalServiceAmount*20/100));
	}
	
	public void updateTransactionAmount() {
		total = totalServiceAmount;
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

	public String getTotalServiceAmount() {
		return new DecimalFormat("$###,###,##0.00").format(totalServiceAmount);
	}

	public void setTotalServiceAmount(double totalServiceAmount) {
		this.totalServiceAmount = totalServiceAmount;
	}

	public String getGiftCardNo() {
		return giftCardNo;
	}

	public void setGiftCardNo(String giftCardNo) {
		this.giftCardNo = giftCardNo;
	}

	public double getGiftCard() {
		return giftCard;
	}

	public void setGiftCard(double giftCard) {
		this.giftCard = giftCard;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public double getTip() {
		return tip;
	}

	public void setTip(double tip) {
		this.tip = tip;
	}

	public String getTipSelected() {
		return tipSelected;
	}

	public void setTipSelected(String tipSelected) {
		this.tipSelected = tipSelected;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<String> getTipTypes() {
		return tipTypes;
	}

	public void setTipTypes(List<String> tipTypes) {
		this.tipTypes = tipTypes;
	}

	public List<String> getDiscountTypes() {
		return discountTypes;
	}

	public void setDiscountTypes(List<String> discountTypes) {
		this.discountTypes = discountTypes;
	}

	public String getDiscountSelected() {
		return discountSelected;
	}

	public void setDiscountSelected(String discountSelected) {
		this.discountSelected = discountSelected;
	}
}
