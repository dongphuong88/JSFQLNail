package CARGO;

import java.io.Serializable;

public class StaffSkill implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7956986408363258303L;
	private long staff_id;
	private long service_group_id;
	public long getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(long staff_id) {
		this.staff_id = staff_id;
	}
	public long getService_group_id() {
		return service_group_id;
	}
	public void setService_group_id(long service_group_id) {
		this.service_group_id = service_group_id;
	}
}
