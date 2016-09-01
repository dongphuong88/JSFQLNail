package Bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import CARGO.Staff;
import DAO.ERROR_CODE;
import DAO.ServicesDAO;
import DAO.StaffsDAO;

@ManagedBean
@RequestScoped
public class AddStaffBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7036839071853606019L;
	private Staff staff = new Staff();
	private List<String> serviceCates;
	private boolean abilityAll;
	
	public AddStaffBean() {
		serviceCates = ServicesDAO.getServiceCategoriesList();
	}
	
	public void changedCheckAll() {
		staff.getAbilities().clear();
		if( abilityAll )
			for( String cate : serviceCates) {
				staff.getAbilities().add(cate);
			}
	}
	
	public String add() {
		if( ERROR_CODE.SUCCEED == StaffsDAO.setStaff(staff))
			return "index?faces-redirect=true";
		return "";
	}
	public String cancel() {
		return "";
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
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
	
	
}
