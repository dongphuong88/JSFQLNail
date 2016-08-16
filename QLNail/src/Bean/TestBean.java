package Bean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.push.inject.ServletContextInjectable;

import DAO.EmployeeDAO;

@RequestScoped
@ManagedBean
public class TestBean {
	private Date date1;
	private String val;
	
   public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

public TestBean() {
      System.out.println("HelloWorld started!");
   }
	
   public String getMessage() {
	   System.out.println(val);
      return "Hello World!";
   }

public String getVal() {
	return val;
}

public void setVal(String val) {
	this.val = val;
}
}
