package Bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
		if( ERROR_CODE.SUCCEED == ServicesDAO.setService(service))
			return "index?faces-redirect=true";
		
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
