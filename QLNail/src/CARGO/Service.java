package CARGO;

import java.io.Serializable;

public class Service implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9156872095927167409L;
	private long id;
	private String name;
	private double price;
	private int duration;
	private float turn;
	private String description;
	private String additionalInfo;
	private long service_category_id;
	private double service_deduction;
	
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
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public long getService_category_id() {
		return service_category_id;
	}
	public void setService_category_id(long service_category_id) {
		this.service_category_id = service_category_id;
	}
	public double getService_deduction() {
		return service_deduction;
	}
	public void setService_deduction(double service_deduction) {
		this.service_deduction = service_deduction;
	}
}
