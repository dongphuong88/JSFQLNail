package Bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import CARGO.Service;
import DAO.ERROR_CODE;
import DAO.ServicesDAO;

@ManagedBean
@ViewScoped
public class ServiceBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4764379651377654313L;
	private List<List<Service>> services;
	private String accordionOpenAll = "";
	private Service newService;
	private Map<String,String> groups;
	private List<String> colors;
	private boolean addShow = false;
	
	public ServiceBean() {
		// Load Services
		services = ServicesDAO.getServices();
		for( int i = 0; i < services.size(); ++i)
			accordionOpenAll += i + ",";
	}
	
	public void newService() {
		// Load groups
		groups = ServicesDAO.getServiceCategories();
		// Load Colors
		colors = ServicesDAO.get9AvailableServiceGroupColors();
		// Load Services
		services = ServicesDAO.getServices();
		for( int i = 0; i < services.size(); ++i)
			accordionOpenAll += i + ",";
		newService = new Service();
	}
	
	public String add() {
		FacesContext context = FacesContext.getCurrentInstance();
		System.out.println(newService.getCate_name() + " --- " + newService.getCate_color());
		ERROR_CODE err = ServicesDAO.setService(newService);

		if( ERROR_CODE.SUCCEED == err) {
			context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_INFO, "Service Added!", "INFO" ));
			closeAddService();
		}
		else
			context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR, err.toString(), "ERROR" ));
		
		return "";
	}
	
	public void openAddService() {
		addShow = true;
		newService();
	}
	
	public void closeAddService() {
		addShow = false;
	}

	public Map<String, String> getGroups() {
		return groups;
	}

	public void setGroups(Map<String, String> groups) {
		this.groups = groups;
	}

	public List<String> getColors() {
		return colors;
	}

	public void setColors(List<String> colors) {
		this.colors = colors;
	}

	public boolean isAddShow() {
		return addShow;
	}

	public void setAddShow(boolean addShow) {
		this.addShow = addShow;
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

	public Service getNewService() {
		return newService;
	}

	public void setNewService(Service newService) {
		this.newService = newService;
	}
	
	
}
