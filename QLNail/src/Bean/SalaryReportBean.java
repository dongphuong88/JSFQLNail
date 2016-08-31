package Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import CARGO.TransactionService;
import DAO.StaffsDAO;

@ManagedBean
@RequestScoped
public class SalaryReportBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666728988146110996L;
	private List<TransactionService> transactionServices = new ArrayList<>();
	private List<String> staffNames;
	private String selectedStaff;
	private Date fromDate;
	private Date toDate;
	private double oneStaffTotalAmount;
	private double oneStaffTotalTip;
	private double oneStaffTotal;
	private boolean showAllStaffs = true;
	
	public SalaryReportBean() {
		staffNames = StaffsDAO.getStaffNameList();
		
		for(int i = 0; i < 10; i++) {
			TransactionService a = new TransactionService();
			a.setStaff_name("Kelly");
			a.setService_group("Pedi");
			a.setService_name("Classic" + i);
			a.setAmount(40 - i);
			a.setCommission(30);
			a.setTip(i*2);
			a.setDate(new Date());
			a.setDiscount(i*3-10);
			transactionServices.add(a);
		}
	}
	
	public void calOneStaff() {
		if( selectedStaff.equals("All staffs")) {
			showAllStaffs = true;
		}
		else {
			showAllStaffs = false;
			calOneStaffTotalAmount();
			calOneStaffTotalTip();
			// cal total
			oneStaffTotal = oneStaffTotalAmount * 0.6 + oneStaffTotalTip * 0.8;
		}
		
	}
	
	public void calOneStaffTotalAmount() {
		oneStaffTotalAmount = 0;
		for( TransactionService s : transactionServices) {
			oneStaffTotalAmount += s.getAmount();
		}
	}
	public void calOneStaffTotalTip() {
		oneStaffTotalTip = 0;
		for( TransactionService s : transactionServices) {
			oneStaffTotalTip += s.getTip();
		}
	}
	
	public List<TransactionService> getTransactionServices() {
		return transactionServices;
	}
	public void setTransactionServices(List<TransactionService> transactionServices) {
		this.transactionServices = transactionServices;
	}
	public List<String> getStaffNames() {
		return staffNames;
	}
	public void setStaffNames(List<String> staffNames) {
		this.staffNames = staffNames;
	}

	public String getSelectedStaff() {
		return selectedStaff;
	}

	public void setSelectedStaff(String selectedStaff) {
		this.selectedStaff = selectedStaff;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public double getOneStaffTotalAmount() {
		return oneStaffTotalAmount;
	}

	public void setOneStaffTotalAmount(double oneStaffTotalAmount) {
		this.oneStaffTotalAmount = oneStaffTotalAmount;
	}

	public double getOneStaffTotalTip() {
		return oneStaffTotalTip;
	}

	public void setOneStaffTotalTip(double oneStaffTotalTip) {
		this.oneStaffTotalTip = oneStaffTotalTip;
	}

	public double getOneStaffTotal() {
		return oneStaffTotal;
	}

	public void setOneStaffTotal(double oneStaffTotal) {
		this.oneStaffTotal = oneStaffTotal;
	}

	public boolean isShowAllStaffs() {
		return showAllStaffs;
	}

	public void setShowAllStaffs(boolean showAllStaffs) {
		this.showAllStaffs = showAllStaffs;
	}
	
}
