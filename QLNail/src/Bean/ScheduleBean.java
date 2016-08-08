package Bean;

import org.primefaces.model.ScheduleModel;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;

@ViewScoped
@ManagedBean
public class ScheduleBean {
	private ScheduleModel model;

	public ScheduleBean() {
		model = new DefaultScheduleModel();
		DefaultScheduleEvent event = new DefaultScheduleEvent("abc1", new Date(), new Date());
		event.setId("1");
		event.setData("test");
		model.addEvent(event);
		event = new DefaultScheduleEvent("abc2", new Date(), new Date());
		event.setId("2");
		model.addEvent(event);
		event = new DefaultScheduleEvent("abc1", new Date(), new Date());
		event.setId("1");
		model.addEvent(event);
	}

	public ScheduleModel getModel() {
		return model;
	}
}
