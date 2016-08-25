package POJO;

import java.io.Serializable;

public class Service implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9156872095927167409L;
	private String name;
	private double price;
	private int duration;
	private float turn;
	private String description;
	private String group;
	private String additionalInfo;
	private String color;
	private String providers;
	
	@Override
	public String toString() {
		return name + ":" + price + ":" + turn + ":" + group + ":" + color;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public float getTurn() {
		return turn;
	}
	public void setTurn(float turn) {
		this.turn = turn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getProviders() {
		return providers;
	}
	public void setProviders(String providers) {
		this.providers = providers;
	}
}
