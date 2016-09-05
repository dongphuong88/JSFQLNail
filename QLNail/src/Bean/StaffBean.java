package Bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import CARGO.Staff;
import DAO.ERROR_CODE;
import DAO.ServicesDAO;
import DAO.StaffsDAO;

@ManagedBean
@ViewScoped
public class StaffBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7036839071853606019L;
	private Staff newStaff;
	private List<String> serviceCates;
	private boolean abilityAll;
	private boolean addShow = false;
	private List<Staff> staffs;
	
	public StaffBean() {
		serviceCates = ServicesDAO.getServiceCategoriesList();
		staffs = StaffsDAO.getStaffs();
	}
	
	public void newStaff() {
		newStaff = new Staff();
	}
	
	public void openAddStaff() {
		addShow = true;
		newStaff();
	}
	
	public void closeAddStaff() {
		addShow = false;
	}
	
	public void changedCheckAll() {
		newStaff.getAbilities().clear();
		if( abilityAll )
			for( String cate : serviceCates) {
				newStaff.getAbilities().add(cate);
			}
	}
	
	public void add() {
		FacesContext context = FacesContext.getCurrentInstance();
		ERROR_CODE err = StaffsDAO.setStaff(newStaff);
		if( ERROR_CODE.SUCCEED == err) {
			context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_INFO, "Staff added!", "INFO" ));
			// reload staff list
			staffs = StaffsDAO.getStaffs();
			closeAddStaff();
		}
		else	
			context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR, err.toString(), "ERROR" ));
	}

	public Staff getNewStaff() {
		return newStaff;
	}

	public void setNewStaff(Staff staff) {
		this.newStaff = staff;
	}

	public List<String> getServiceCates() {
		return serviceCates;
	}

	public void setServiceCates(List<String> serviceCates) {
		this.serviceCates = serviceCates;
	}

	public boolean isAbilityAll() {
		return abilityAll;
	}

	public void setAbilityAll(boolean abilityAll) {
		this.abilityAll = abilityAll;
	}

	public boolean isAddShow() {
		return addShow;
	}

	public void setAddShow(boolean addShow) {
		this.addShow = addShow;
	}

	public List<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}
	
	
}
