package CARGO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Staff implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5002571083526165524L;
	
	class StaffSchedule {
		public String day;
		public boolean allday;
		public int startHour;
		public int endHour;
	}
	
	private long id;
	private String name;
	private String title;
	private float commission;
	private String phone;
	private List<String> abilities = new ArrayList<>();
	private List<StaffSchedule> schedules = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public float getCommission() {
		return commission;
	}
	public void setCommission(float commission) {
		this.commission = commission;
	}
	public List<String> getAbilities() {
		return abilities;
	}
	public void setAbilities(List<String> abilities) {
		this.abilities = abilities;
	}
	public List<StaffSchedule> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<StaffSchedule> schedules) {
		this.schedules = schedules;
	}
	
}
