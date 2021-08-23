package com.mvc.mudi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.mvc.mudi.model.Offer;

public class NewOfferRequest {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
	private Long orderId;
	
	@Pattern(regexp = "^\\d+(\\.\\d+{2})?$")
	@NotNull
	private String price;
	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
	@NotNull
	private String deliveryDate;
	private String comment;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Offer toOffer() {
		Offer offer = new Offer();
		offer.setComment(this.comment);
		offer.setDeliveryDate(LocalDate.parse(this.deliveryDate, formatter));
		offer.setPrice(new BigDecimal(this.price));
		return offer;
	}
	
	
}
