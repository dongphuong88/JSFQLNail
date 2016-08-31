package Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import CARGO.ServiceCategory;
import CARGO.Staff;
import CARGO.StaffAvailable;
import CARGO.StaffRef;
import CARGO.StaffSkill;
import DAO.ServicesDAO;
import DAO.ShopInfoDAO;

@ManagedBean
@RequestScoped
public class AddStaffBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7036839071853606019L;
	private Staff staff = new Staff();
	private StaffRef staffRef = new StaffRef();
	private List<StaffSkill> staffSkills = new ArrayList<>(); 
	private List<ServiceCategory> serviceCates;
	private List<StaffAvailable> staffAvailables = new ArrayList<>();
	private boolean abilityAll;
	
	public AddStaffBean() {
		serviceCates = ServicesDAO.getServiceCategoriesList();
		
		StaffAvailable sA = new StaffAvailable();
		sA.setDay("Monday");
		addStaffAvailables.add(sA);
		sA = new StaffAvailable();
		sA.setDay("Tuesday");
		addStaffAvailables.add(sA);
		sA = new StaffAvailable();
		sA.setDay("Wednesday");
		addStaffAvailables.add(sA);
		sA = new StaffAvailable();
		sA.setDay("Thursday");
		addStaffAvailables.add(sA);
		sA = new StaffAvailable();
		sA.setDay("Friday");
		addStaffAvailables.add(sA);
		sA = new StaffAvailable();
		sA.setDay("Saterday");
		addStaffAvailables.add(sA);
		sA = new StaffAvailable();
		sA.setDay("Sunday");
		addStaffAvailables.add(sA);
	}
	
	public void changedCheckAll() {
		selectedCates.clear();
		if( abilityAll )
			for( ServiceCategory cate : serviceCates) {
				selectedCates.add(cate.getName());
			}
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

	public List<StaffAvailable> getAddStaffAvailables() {
		return addStaffAvailables;
	}

	public void setAddStaffAvailables(List<StaffAvailable> addStaffAvailables) {
		this.addStaffAvailables = addStaffAvailables;
	}

	public List<String> getWorkHours() {
		return workHours;
	}

	public void setWorkHours(List<String> workHours) {
		this.workHours = workHours;
	}

	public List<String> getSelectedCates() {
		return selectedCates;
	}

	public void setSelectedCates(List<String> selectedCates) {
		this.selectedCates = selectedCates;
	}

	public float getAddStaffCommission() {
		return addStaffCommission;
	}

	public void setAddStaffCommission(float addStaffCommission) {
		this.addStaffCommission = addStaffCommission;
	}

	public int getNumber6() {
		return number6;
	}

	public void setNumber6(int number6) {
		this.number6 = number6;
	}

	public int getNumber7() {
		return number7;
	}

	public void setNumber7(int number7) {
		this.number7 = number7;
	}

	public String getNumber8() {
		return number8;
	}

	public void setNumber8(String number8) {
		this.number8 = number8;
	}

	public boolean isAbilityAll() {
		return abilityAll;
	}

	public void setAbilityAll(boolean abilityAll) {
		this.abilityAll = abilityAll;
	}
	
	
}
