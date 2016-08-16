package Bean;

import java.io.File;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import POJO.GiftCard;
import POJO.TransactionService;

@ViewScoped
@ManagedBean
public class CheckoutBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -95083139120347266L;
	
	private ArrayList<String> empNames;

	public CheckoutBean() {
		
	}

	public ArrayList<String> getEmpNames() {
		return empNames;
	}

	public void setEmpNames(ArrayList<String> empNames) {
		this.empNames = empNames;
	}

}
