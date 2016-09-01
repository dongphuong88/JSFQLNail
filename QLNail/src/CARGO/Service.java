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
	private int duration_hour;
	private int duration_minute;
	private float turn;
	private String description;
	private String additionalInfo;
	private String cate_name;
	private String cate_color;
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
	public double getService_deduction() {
		return service_deduction;
	}
	public void setService_deduction(double service_deduction) {
		this.service_deduction = service_deduction;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	public String getCate_color() {
		return cate_color;
	}
	public void setCate_color(String cate_color) {
		this.cate_color = cate_color;
	}
	public int getDuration_hour() {
		return duration_hour;
	}
	public void setDuration_hour(int duration_hour) {
		this.duration_hour = duration_hour;
	}
	public int getDuration_minute() {
		return duration_minute;
	}
	public void setDuration_minute(int duration_minute) {
		this.duration_minute = duration_minute;
	}
}
