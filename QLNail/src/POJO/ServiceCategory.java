package POJO;

import java.io.Serializable;

public class ServiceCategory implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6779111800325368437L;
	private long id;
	private String name;
	private String color;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
