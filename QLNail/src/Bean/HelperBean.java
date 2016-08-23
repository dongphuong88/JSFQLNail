package Bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ApplicationScoped
public class HelperBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3450553123297343475L;
	private Map<String,String> colors = new HashMap<String, String>();

	public HelperBean() {
		colors.put("Yellow", "Yellow");
		colors.put("Red", "Red");
		colors.put("Green", "Green");
		colors.put("Blue", "Blue");
	}

	public Map<String, String> getColors() {
		return colors;
	}

	public void setColors(Map<String, String> colors) {
		this.colors = colors;
	}
}
