package Bean;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
	
	private double totalServiceAmount;

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
		//totalTransaction = totalServiceAmount;
		RequestContext.getCurrentInstance().execute("updateAmount()");
	}
	
	public void deleteTransactionService( TransactionService deleteObj) {
		services.remove(deleteObj);
		updateServicesAmount();
	}

	public void update() {
		Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		double tip = Double.parseDouble(map.get("tip") != "" ? map.get("tip") : "0");
		String giftCardNo = map.get("giftCardNo" )!= "" ? map.get("giftCardNo") : "0";
		double giftCardAmount = Double.parseDouble(map.get("giftCard") != "" ? map.get("giftCard") : "0");
		double creditCardAmount = Double.parseDouble(map.get("credit") != "" ? map.get("credit") : "0");
		double cashAmount = Double.parseDouble(map.get("cash") != "" ? map.get("cash") : "0");
		
		System.out.println(tip + "-" + giftCardNo + "-" + giftCardAmount + "-" + creditCardAmount + "-" + cashAmount );
	}
	
	public void clickedPrint( ) {
		services.forEach( service-> {
			System.out.println(service.getAmount());
		}); 
		System.out.println("total services:" + totalServiceAmount);
	}
	
	public void clickedEmail( ) {
		services.forEach( service-> {
			System.out.println(service.getAmount());
		}); 
		System.out.println("total services:" + totalServiceAmount);
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
