package Bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import CARGO.Service;
import DAO.ServicesDAO;

@ManagedBean
@ViewScoped
public class WorkingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3067542514277966855L;
	private List<List<Service>> services;
	private String accordionOpenAll = "";
	
	
	public WorkingBean() {
		services = ServicesDAO.getServices();
		for( int i = 0; i < services.size(); ++i)
			accordionOpenAll += i + ",";
	}

	public List<List<Service>> getServices() {
		return services;
	}

	public void setServices(List<List<Service>> services) {
		this.services = services;
	}

	public String getAccordionOpenAll() {
		return accordionOpenAll;
	}

	public void setAccordionOpenAll(String accordionOpenAll) {
		this.accordionOpenAll = accordionOpenAll;
	}

}
