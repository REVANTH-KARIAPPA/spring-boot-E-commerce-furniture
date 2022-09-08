package com.furniture.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Payment {
	
	@Id
	@GeneratedValue
	private Integer paymentId;


	private Float paymentAmount;
	private String method;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Payment() { super(); }

	public Payment(Integer paymentId, Float paymentAmount, String method) {
		this.paymentId = paymentId;
		this.paymentAmount = paymentAmount;
		this.method = method;
	}

	public Integer getPaymentId() { return paymentId; }

	public void setPaymentId(Integer paymentId) { this.paymentId = paymentId; }

	public Float getPaymentAmount() { return paymentAmount; }

	public void setPaymentAmount(Float paymentAmount) { this.paymentAmount = paymentAmount; }

	public String getMethod() { return method; }

	public void setMethod(String method) { this.method = method; }

	@Override
	public String toString() {
		return  "\n thank you for purchasing from WOODO \n Your total Purchasing cost" +
				",\n paymentAmount=" + paymentAmount +
				",\n method='" + method ;
	}
}
