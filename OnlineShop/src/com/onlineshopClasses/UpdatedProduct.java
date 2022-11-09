package com.onlineshopClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class UpdatedProduct extends ProductDetails {
	
	Connection connection = null;
	PreparedStatement ps = null;
	
	public void getUpdatedQuantity(ArrayList<Product> a1,Map<Integer, Integer> cartProduct) {
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce","root","12345");
		Set<Integer> carts = cartProduct.keySet();
		Integer pendingQty = 0;
		for (Integer cart : carts) {
			for(Integer cart1 : carts) {
				for (Product product : a1) {
					if(cart1 == product.getProductId()) {
						pendingQty = product.getQuantity() - cartProduct.get(cart1);
					}
				}
			}
			String query = "update product set quantity = "+pendingQty+" where productid = "+cart;
			PreparedStatement ps2 = connection.prepareStatement(query);
			ps2.executeUpdate();
		}
		
	}catch(Exception e) {
		
	}
	}
	public static void main(String[] args) {
		UpdatedProduct updated = new UpdatedProduct();
		//getUpdatedQuantity(a1, cartProduct);
	}
}
