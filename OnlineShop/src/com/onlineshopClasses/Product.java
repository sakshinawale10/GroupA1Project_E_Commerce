package com.onlineshopClasses;

public class Product  {
	
	private int productId ;  
	private String description ;  
	private int price ;  
	private String name ;  
	private int quantity;
	public Product(int productId, String description, int price, String name, int quantity) {
		super();
		this.productId = productId;
		this.description = description;
		this.price = price;
		this.name = name;
		this.quantity = quantity;
	}
	
	
	
	public Product(int productId, int price, String name) {
		super();
		this.productId = productId;
		this.price = price;
		this.name = name;
	}



	public Product() {
		
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", description=" + description + ", price=" + price + ", name="
				+ name + ", quantity=" + quantity + "]";
	}
	
	
	
	
	

}
