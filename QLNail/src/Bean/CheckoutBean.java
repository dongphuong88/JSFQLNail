package Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import CARGO.GiftCard;
import CARGO.Service;
import CARGO.Transaction;
import CARGO.TransactionService;
import DAO.ERROR_CODE;
import DAO.ServicesDAO;
import DAO.StaffsDAO;
import DAO.TransactionsDAO;

@ManagedBean
@ViewScoped
public class CheckoutBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3067542514277966855L;
	private List<TransactionService> transactionServices = new ArrayList<TransactionService>();
	private Map<String,List<Service>> serviceSelection;
	private List<String> staffSelection;
	private Transaction transaction = new Transaction();
	private double transactionServicesTotal;
	private boolean showTransactionDiscount;
	private boolean showTransactionDiscountPercent;
	private double transactionDiscount;
	private float transactionDiscountPercent;
	private List<PayBy> payBys = new ArrayList<>();
	
	public CheckoutBean() {
		serviceSelection = ServicesDAO.getServicesMap();
		staffSelection = StaffsDAO.getStaffNameList();
		payBys.add(new PayBy(true));
		transaction.setDatetime(new Date());
	}
	
	public String save() {
		FacesContext context = FacesContext.getCurrentInstance();
		ERROR_CODE err = TransactionsDAO.setTransactionFromServices(transaction, transactionServices, transactionServicesTotal, payBys);
		if( ERROR_CODE.SUCCEED == err) {
			context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_INFO, "Transaction checkouted!", "INFO" ));
			// reload staff list
			return "Checkout?faces-redirect=true";
		}
		else	{
			context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR, err.toString(), "ERROR" ));
			return "";
		}
			
	}
	
	public void updatePayBy( PayBy payBy) {
		double total = transaction.getTotal();
		for( PayBy p : payBys) {
			if( p.isShowCash()) 
				total -= p.getCash();
			else if( p.isShowCredit())
				total -= p.getCredit();
			else if( p.isShowGift())
				total -= p.getGift().getAmount();
		}
		payBy.setCash(0);
		payBy.setCredit(0);
		payBy.getGift().setAmount(0);
		if( payBy.isShowCash()) {
			payBy.setCash(total);
			
		}
		else if( payBy.isShowCredit()) {
			payBy.setCredit(total);
		}
		else if( payBy.isShowGift()) {
			payBy.getGift().setAmount(total);
		}
	}
	
	public void deletePayType( PayBy obj) {
		payBys.remove(obj);
	}
	
	public void addPayType() {
		payBys.add(new PayBy(false));
	}
	
	public void calServicesTotal() {
		transactionServicesTotal = 0;
		for( TransactionService ts : transactionServices) {
			transactionServicesTotal += ts.getAmount();
		}
		calDiscountsTotal();
	}
	
	public void calDiscountsTotal() {
		double dT = 0.0;
		for( TransactionService ts : transactionServices) {
			dT += ts.getDiscount();
		}
		if( showTransactionDiscount) {
			dT += transactionDiscount;
		}
		else if( showTransactionDiscountPercent) {
			dT += transactionServicesTotal * (transactionDiscountPercent/100);
		}
		transaction.setTotal_discount(dT);
		calTransactionTotal();
	}
	
	public void calTransactionTotal() {
		transaction.setTotal(transactionServicesTotal - transaction.getTotal_discount());
	}
	
	public void deleteTransactionService( TransactionService s) {
		transactionServices.remove(s);
		calServicesTotal();
	}
	
	public void updateServicePrice( TransactionService s) {
		for( Service ss : serviceSelection.get(s.getService_group())) {
			if( ss.getName().equals(s.getService_name())) {
				s.setAmount(ss.getPrice());
				break;
			}
		}
		calServicesTotal();
	}
	
	public void addTransactionService() {
		transactionServices.add(new TransactionService());
	}
	
	public List<TransactionService> getTransactionServices() {
		return transactionServices;
	}
	public void setTransactionServices(List<TransactionService> transactionServices) {
		this.transactionServices = transactionServices;
	}

	public Map<String, List<Service>> getServiceSelection() {
		return serviceSelection;
	}

	public void setServiceSelection(Map<String, List<Service>> serviceSelection) {
		this.serviceSelection = serviceSelection;
	}

	public List<String> getStaffSelection() {
		return staffSelection;
	}

	public void setStaffSelection(List<String> staffSelection) {
		this.staffSelection = staffSelection;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public double getTransactionServicesTotal() {
		return transactionServicesTotal;
	}

	public void setTransactionServicesTotal(double transactionServicesTotal) {
		this.transactionServicesTotal = transactionServicesTotal;
	}

	public double getTransactionDiscount() {
		return transactionDiscount;
	}

	public void setTransactionDiscount(double transactionDiscount) {
		this.transactionDiscount = transactionDiscount;
	}

	public float getTransactionDiscountPercent() {
		return transactionDiscountPercent;
	}

	public void setTransactionDiscountPercent(float transactionDiscountPercent) {
		this.transactionDiscountPercent = transactionDiscountPercent;
	}

	public class PayBy implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -8373249707905713874L;
		private boolean showCash;
		private boolean showCredit;
		private boolean showGift;
		private double cash;
		private double credit;
		private GiftCard gift;
		private boolean firstRow;
		private double receive;
		private double change;
		
		public PayBy( boolean firstRow) {
			this.firstRow = firstRow;
			gift = new GiftCard();
		}
		
		public boolean isShowCash() {
			return showCash;
		}
		public void setShowCash(boolean showCash) {
			this.showCash = showCash;
		}
		public boolean isShowCredit() {
			return showCredit;
		}
		public void setShowCredit(boolean showCredit) {
			this.showCredit = showCredit;
		}
		public boolean isShowGift() {
			return showGift;
		}
		public void setShowGift(boolean showGift) {
			this.showGift = showGift;
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

		public boolean isFirstRow() {
			return firstRow;
		}

		public void setFirstRow(boolean firstRow) {
			this.firstRow = firstRow;
		}

		public double getReceive() {
			return receive;
		}

		public void setReceive(double receive) {
			this.receive = receive;
		}

		public double getChange() {
			return change;
		}

		public void setChange(double change) {
			this.change = change;
		}

		public GiftCard getGift() {
			return gift;
		}

		public void setGift(GiftCard gift) {
			this.gift = gift;
		}
	}

	public List<PayBy> getPayBys() {
		return payBys;
	}

	public void setPayBys(List<PayBy> payBys) {
		this.payBys = payBys;
	}

	public boolean isShowTransactionDiscount() {
		return showTransactionDiscount;
	}

	public void setShowTransactionDiscount(boolean showTransactionDiscount) {
		this.showTransactionDiscount = showTransactionDiscount;
	}

	public boolean isShowTransactionDiscountPercent() {
		return showTransactionDiscountPercent;
	}

	public void setShowTransactionDiscountPercent(boolean showTransactionDiscountPercent) {
		this.showTransactionDiscountPercent = showTransactionDiscountPercent;
	}
}
