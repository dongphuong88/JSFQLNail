package Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.CalendarsDAO;
import DAO.StaffsDAO;

@ManagedBean
@ViewScoped
public class CalendarBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4781466166666432624L;
	private Date selectedDate;
	private List<String> staffNames;
	private List<String> selectedStaffs = new ArrayList<>();
	private String eventsJSON;
	
	public CalendarBean() {
		staffNames = StaffsDAO.getStaffNameList();
		System.out.println(staffNames);
		
		selectedDate = Calendar.getInstance().getTime();
		eventsJSON = CalendarsDAO.getEventsInDayJSON(selectedDate);
	}
	
	public void nextDate() {
		Calendar c = Calendar.getInstance();
		c.setTime(selectedDate);
		c.add(Calendar.DATE, 1);
		selectedDate = c.getTime();
	}
	
	public void currDate() {
		selectedDate = Calendar.getInstance().getTime();
	}
	
	public void prevDate() {
		Calendar c = Calendar.getInstance();
		c.setTime(selectedDate);
		c.add(Calendar.DATE, -1);
		selectedDate = c.getTime();
	}
	
	public Date getSelectedDate() {
		return selectedDate;
	}
	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public List<String> getStaffNames() {
		return staffNames;
	}

	public void setStaffNames(List<String> staffNames) {
		this.staffNames = staffNames;
	}

	public List<String> getSelectedStaffs() {
		return selectedStaffs;
	}

	public void setSelectedStaffs(List<String> selectedStaffs) {
		this.selectedStaffs = selectedStaffs;
	}

	public String getEventsJSON() {
		return eventsJSON;
	}

	public void setEventsJSON(String eventsJSON) {
		this.eventsJSON = eventsJSON;
	}
}
