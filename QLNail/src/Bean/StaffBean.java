package Bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DAO.ServicesDAO;
import POJO.ServiceCategory;
import POJO.Staff;

@ManagedBean
@RequestScoped
public class StaffBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7036839071853606019L;
	private Staff addStaff = new Staff();
	private List<ServiceCategory> serviceCates;
	private String[] selectedCates;
	private String[] selectedSchedules;
	private String startWorkHour;
	private String endWorkHour;
	
	public StaffBean() {
		serviceCates = ServicesDAO.getServiceCategoriesList();
		System.out.println(serviceCates);
	}
	
	public Staff getAddStaff() {
		return addStaff;
	}
	public void setAddStaff(Staff addStaff) {
		this.addStaff = addStaff;
	}

	public List<ServiceCategory> getServiceCates() {
		return serviceCates;
	}

	public void setServiceCates(List<ServiceCategory> serviceCates) {
		this.serviceCates = serviceCates;
	}

	public String[] getSelectedCates() {
		return selectedCates;
	}

	public void setSelectedCates(String[] selectedCates) {
		this.selectedCates = selectedCates;
	}

	public String[] getSelectedSchedules() {
		return selectedSchedules;
	}

	public void setSelectedSchedules(String[] selectedSchedules) {
		this.selectedSchedules = selectedSchedules;
	}

	public String getStartWorkHour() {
		return startWorkHour;
	}

	public void setStartWorkHour(String startWorkHour) {
		this.startWorkHour = startWorkHour;
	}

	public String getEndWorkHour() {
		return endWorkHour;
	}

	public void setEndWorkHour(String endWorkHour) {
		this.endWorkHour = endWorkHour;
	}
	
	
}
