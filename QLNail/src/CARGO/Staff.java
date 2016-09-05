package CARGO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Staff implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5002571083526165524L;
	
	private long id;
	private String name;
	private String title;
	private float commission;
	private String phone;
	private float hourly_rate;
	private String permission;
	private List<String> abilities = new ArrayList<>();
	private List<StaffSchedule> schedules = new ArrayList<>();
	
	public Staff() {
		schedules.add(new StaffSchedule("Monday", true, 9, 20));
		schedules.add(new StaffSchedule("Tuesday", true, 9, 20));
		schedules.add(new StaffSchedule("Wednesday", true, 9, 20));
		schedules.add(new StaffSchedule("Thursday", true, 9, 20));
		schedules.add(new StaffSchedule("Friday", true, 9, 20));
		schedules.add(new StaffSchedule("Saterday", true, 9, 20));
		schedules.add(new StaffSchedule("Sunday", true, 9, 20));
	}
	
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

	public float getHourly_rate() {
		return hourly_rate;
	}

	public void setHourly_rate(float hourly_rate) {
		this.hourly_rate = hourly_rate;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
}
