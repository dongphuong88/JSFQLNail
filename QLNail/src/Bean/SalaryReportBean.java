package Bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.StaffsDAO;
import POJO.TransactionService;

@ManagedBean
@ViewScoped
public class SalaryReportBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6666728988146110996L;
	private List<TransactionService> transactionServices;
	private List<String> staffNames;
	private String selectedStaff;
	
	public SalaryReportBean() {
		staffNames = StaffsDAO.getStaffNameList();
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
	
}
