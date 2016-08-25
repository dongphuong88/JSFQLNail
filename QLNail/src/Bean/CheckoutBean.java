package Bean;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import DAO.ServicesDAO;
import DAO.StaffsDAO;
import DAO.TransactionsDAO;

@ViewScoped
@ManagedBean
public class CheckoutBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -95083139120347266L;
	
	private String employeeNames;
	private String serviceGroups;
	private String transactionServiceData;
	private String transactionData;
	private Date currDate = new Date();

	public CheckoutBean() {
		employeeNames = StaffsDAO.getStaffNameListJSONArray();
		serviceGroups = ServicesDAO.getServiceGroupsJSONObject();
	}
	
	public void print() {
		if( TransactionsDAO.setTransactionFromServices(transactionServiceData, transactionData) ) {
			// succeed
			System.out.println("succeed");
		}
		// failed	
		System.out.println("failed");
		return;
	}
	
	public void email() {
	}

	public Date getCurrDate() {
		return currDate;
	}

	public void setCurrDate(Date currDate) {
		this.currDate = currDate;
	}

	public String getTransactionServiceData() {
		return transactionServiceData;
	}

	public void setTransactionServiceData(String transactionServiceData) {
		this.transactionServiceData = transactionServiceData;
	}

	public String getTransactionData() {
		return transactionData;
	}

	public void setTransactionData(String transactionData) {
		this.transactionData = transactionData;
	}

	public String getEmployeeNames() {
		return employeeNames;
	}

	public void setEmployeeNames(String employeeNames) {
		this.employeeNames = employeeNames;
	}

	public String getServiceGroups() {
		return serviceGroups;
	}

	public void setServiceGroups(String serviceGroups) {
		this.serviceGroups = serviceGroups;
	}
}
