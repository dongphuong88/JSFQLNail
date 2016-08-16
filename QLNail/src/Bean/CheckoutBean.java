package Bean;

import java.io.File;
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

import DAO.EmployeeDAO;
import POJO.GiftCard;
import POJO.TransactionService;

@ViewScoped
@ManagedBean
public class CheckoutBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -95083139120347266L;
	
	private String employeeNames;
	private String data = "test";

	public CheckoutBean() {
		employeeNames = EmployeeDAO.getStaffNames();
	}
	
	public void print() {
		System.out.println(data);
	}
	
	public void email() {
		System.out.println(data);
	}

	public String getEmployeeNames() {
		return employeeNames;
	}

	public void setEmployeeNames(String employeeNames) {
		this.employeeNames = employeeNames;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}



}
