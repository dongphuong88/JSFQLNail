package Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import DAO.StaffsDAO;
import POJO.TransactionService;

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
	
}
