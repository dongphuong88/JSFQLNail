package Bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class HelperBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3450553123297343475L;
	private Date currentDate = new Date();

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

}
