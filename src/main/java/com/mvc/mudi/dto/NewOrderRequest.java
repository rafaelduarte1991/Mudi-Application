package com.mvc.mudi.dto;

import javax.validation.constraints.NotBlank;
import com.mvc.mudi.model.OrderStatus;
import com.mvc.mudi.model.Orders;

public class NewOrderRequest {
	 
	@NotBlank
	private String productName;
	@NotBlank
	private String urlProduct;
	@NotBlank
	private String urlImage;
	private String description;
	
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getUrlProduct() {
		return urlProduct;
	}
	public void setUrlProduct(String urlProduct) {
		this.urlProduct = urlProduct;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Orders toOrder() {
		Orders order = new Orders();
		order.setProductName(productName);
		order.setDescription(description);
		order.setUrlImage(urlImage);
		order.setUrlProduct(urlProduct);
		order.setStatus(OrderStatus.PENDING);
		return order;
	}
	
	

}
