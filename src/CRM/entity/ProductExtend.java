package CRM.entity;

import com.google.gson.annotations.Expose;

public class ProductExtend {
	@Expose
	private int product_id;
	@Expose
	private String details;
	private Product product;
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
