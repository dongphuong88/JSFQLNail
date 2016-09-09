package Bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import CARGO.CalendarService;

@ManagedBean
@ViewScoped
public class WorkingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3067542514277966855L;
	//private List<String> hours = Arrays.asList("8AM", "9AM", "10AM", "11AM", "12PM", "1PM", "2PM", "3PM", "4PM");
	private List<String> hours = Arrays.asList("8AM");
	private List<String> staffs = Arrays.asList("Kelly","Nancy","Tina","Kelly","Nancy","Tina");
	private List<CalendarService> events;
	
	public WorkingBean() throws ParseException {
		events = new ArrayList<CalendarService>();
		SimpleDateFormat df = new SimpleDateFormat("hh:mm");
		CalendarService s = new CalendarService();
		s.setStart_time(df.parse("08:00"));
		s.setEnd_time(df.parse("8:30"));
		s.setStaff_id(1);
		events.add(s);
		
		s = new CalendarService();
		s.setStart_time(df.parse("09:00"));
		s.setEnd_time(df.parse("10:00"));
		s.setStaff_id(2);
		events.add(s);
		
		s = new CalendarService();
		s.setStart_time(df.parse("09:00"));
		s.setEnd_time(df.parse("14:00"));
		s.setStaff_id(3);
		events.add(s);
		
		s = new CalendarService();
		s.setStart_time(df.parse("12:00"));
		s.setEnd_time(df.parse("1:00"));
		s.setStaff_id(2);
		events.add(s);
	}
	
	public List<String> getHours() {
		return hours;
	}
	public void setHours(List<String> hours) {
		this.hours = hours;
	}
	public List<String> getStaffs() {
		return staffs;
	}
	public void setStaffs(List<String> staffs) {
		this.staffs = staffs;
	}

	public List<CalendarService> getEvents() {
		return events;
	}

	public void setEvents(List<CalendarService> events) {
		this.events = events;
	}
	
}
