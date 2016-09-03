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
public class AddServiceBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4764379651377654313L;
	private Service service = new Service();
	private Map<String,String> groups;
	private List<String> colors;
	
	public AddServiceBean() {
		// Load groups
		groups = ServicesDAO.getServiceCategories();
		// Load Colors
		colors = ServicesDAO.get9AvailableServiceGroupColors();
	}
	
	public String add() {
		FacesContext context = FacesContext.getCurrentInstance();
		ERROR_CODE err = ServicesDAO.setService(service);

		if( ERROR_CODE.SUCCEED == err)
			return "Service?faces-redirect=true";

		context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR, err.toString(), "ERROR" ));
		return "";
	}
	
	public String cancel() {
		return "";
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
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
	
	
}
