package CARGO;

import java.io.Serializable;

public class GiftCard implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2617154254463332020L;
	private int id;
	private String giftCardCode;
	private double giftCardAmount;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGiftCardCode() {
		return giftCardCode;
	}
	public void setGiftCardCode(String giftCardCode) {
		this.giftCardCode = giftCardCode;
	}
	public double getGiftCardAmount() {
		return giftCardAmount;
	}
	public void setGiftCardAmount(double giftCardAmount) {
		this.giftCardAmount = giftCardAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
