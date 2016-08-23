package Bean;

import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import DAO.EmployeeDAO;
import DAO.ServiceDAO;
import POJO.GiftCard;
import POJO.TransactionService;

@ViewScoped
@ManagedBean
public class CheckoutBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -95083139120347266L;
	
	private JSONArray employeeNames;
	private JSONObject serviceGroups;
	private String transactionServiceData;
	private String transactionData;
	private Date currDate = new Date();

	public CheckoutBean() {
		employeeNames = EmployeeDAO.getStaffNames();
		serviceGroups = ServiceDAO.getServiceGroups();
	}
	
	public void print() {
		JSONParser parser = new JSONParser();
		try {
			JSONObject jsonObject = (JSONObject)parser.parse(transactionServiceData);
			System.out.println(jsonObject);
			JSONObject jsonObject1 = (JSONObject)parser.parse(transactionData);
			System.out.println(jsonObject1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void email() {
		System.out.println(transactionServiceData);
	}

	public Date getCurrDate() {
		return currDate;
	}

	public void setCurrDate(Date currDate) {
		this.currDate = currDate;
	}

	public JSONArray getEmployeeNames() {
		return employeeNames;
	}

	public void setEmployeeNames(JSONArray employeeNames) {
		this.employeeNames = employeeNames;
	}

	public JSONObject getServiceGroups() {
		return serviceGroups;
	}

	public void setServiceGroups(JSONObject serviceGroups) {
		this.serviceGroups = serviceGroups;
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
}
