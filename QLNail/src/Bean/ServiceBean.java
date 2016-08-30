package Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.ServicesDAO;
import POJO.Service;
import POJO.ServiceCategory;

@ManagedBean
@ViewScoped
public class ServiceBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4764379651377654313L;
	private Service addService = new Service();
	private ServiceCategory addServiceCate = new ServiceCategory();
	private String addServiceHour;
	private String addServiceMinute;
	private Map<String,String> groups;
	
	private List<Object> serviceGroups;
	private List<String> colors = new ArrayList<>(Arrays.asList("red", "green", "blue", "orange", "yellow", "cyan", "black"));
	
	public ServiceBean() {
		// Load groups
		groups = ServicesDAO.getServiceCategories();
		
		// Testing
		serviceGroups = new ArrayList<>();
		List<Service> pedi = new ArrayList<>();
		for(int i = 0; i < 5; ++i) {
			Service s = new Service();
			s.setName("pedi"+i);
			pedi.add(s);
		}
		List<Service> mani = new ArrayList<>();
		for(int i = 0; i < 5; ++i) {
			Service s = new Service();
			s.setName("mani"+i);
			mani.add(s);
		}
		serviceGroups.add(pedi);
		serviceGroups.add(mani);
	}
	
	public void changeServiceCate() {
		addServiceCate.setColor(groups.get(addServiceCate.getName()));
	}
	
	public void addService() {
		System.out.println("test");
		System.out.println(addService.toString());
	}
	
	public Service getAddService() {
		return addService;
	}
	public void setAddService(Service addService) {
		this.addService = addService;
	}

	public Map<String, String> getGroups() {
		return groups;
	}

	public List<Object> getServiceGroups() {
		return serviceGroups;
	}

	public void setServiceGroups(List<Object> serviceGroups) {
		this.serviceGroups = serviceGroups;
	}

	public void setGroups(Map<String, String> groups) {
		this.groups = groups;
	}

	public String getAddServiceHour() {
		return addServiceHour;
	}

	public void setAddServiceHour(String addServiceHour) {
		this.addServiceHour = addServiceHour;
	}

	public String getAddServiceMinute() {
		return addServiceMinute;
	}

	public void setAddServiceMinute(String addServiceMinute) {
		this.addServiceMinute = addServiceMinute;
	}

	public List<String> getColors() {
		return colors;
	}

	public void setColors(List<String> colors) {
		this.colors = colors;
	}

	public ServiceCategory getAddServiceCate() {
		return addServiceCate;
	}

	public void setAddServiceCate(ServiceCategory addServiceCate) {
		this.addServiceCate = addServiceCate;
	}
}
