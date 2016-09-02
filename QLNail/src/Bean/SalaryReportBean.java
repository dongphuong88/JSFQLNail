package Bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import CARGO.TransactionService;
import DAO.StaffsDAO;
import DAO.TransactionsDAO;

@ManagedBean
@ViewScoped
public class SalaryReportBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666728988146110996L;
	private List<TransactionService> transactionServices;
	private TransactionService staff;
	private List<String> staffNames;
	private String selectedStaff = "All staffs";
	private Date fromDate = new Date();
	private Date toDate = new Date();
	private boolean showAllStaffs = true;
	
	public SalaryReportBean() {
		staffNames = StaffsDAO.getStaffNameList();
		refresh();
		
	}
	
	public void refresh() {
		
		if( selectedStaff.equals("All staffs")) {
			showAllStaffs = true;
			transactionServices = TransactionsDAO.getSalaryReport(fromDate, toDate);
			for( TransactionService s : transactionServices) {
				System.out.println(s.getStaff_name() + " 1- " + s.getAmount());
			}
		}
		else {
			showAllStaffs = false;
			transactionServices = TransactionsDAO.getSalaryReportDetails(fromDate, toDate, selectedStaff);
			staff = TransactionsDAO.getSalaryReport(fromDate, toDate, selectedStaff);
			for( TransactionService s : transactionServices) {
				System.out.println(s.getStaff_name() + " -2 " + s.getAmount());
			}
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

	public boolean isShowAllStaffs() {
		return showAllStaffs;
	}

	public void setShowAllStaffs(boolean showAllStaffs) {
		this.showAllStaffs = showAllStaffs;
	}

	public TransactionService getStaff() {
		return staff;
	}

	public void setStaff(TransactionService staff) {
		this.staff = staff;
	}
	
}
