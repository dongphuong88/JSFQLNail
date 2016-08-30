package Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("TimeConverter")
public class TimeConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		String[] tx = arg2.split(" ");
		int fH = Integer.parseInt(tx[1]);
		if( 12 == fH) tx[1] = "12 PM";
		else if( 24 == fH) tx[1] = "12 AM";
		else tx[1] =( fH < 12) ? fH + " AM" : fH + " PM";
		int sH = Integer.parseInt(tx[3]);
		if( 12 == sH) tx[3] = "12 PM";
		else if( 24 == sH) tx[3] = "12 AM";
		else tx[3] =( sH < 12) ? sH + " AM" : sH + " PM";
		return tx[0] + " " + tx[1] + " " + tx[2] + " " + tx[3];
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String[] tx = arg2.toString().split(" ");
		int fH = Integer.parseInt(tx[1]);
		if( 12 == fH) tx[1] = "12 PM";
		else if( 24 == fH) tx[1] = "12 AM";
		else tx[1] =( fH < 12) ? fH + " AM" : fH + " PM";
		int sH = Integer.parseInt(tx[3]);
		if( 12 == sH) tx[3] = "12 PM";
		else if( 24 == sH) tx[3] = "12 AM";
		else tx[3] =( sH < 12) ? sH + " AM" : sH + " PM";
		return tx[0] + " " + tx[1] + " " + tx[2] + " " + tx[3];
	}
	
}
