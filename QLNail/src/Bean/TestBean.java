package Bean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
public class TestBean {
	private Date date1;
	
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
      return "Hello World!";
   }
}
