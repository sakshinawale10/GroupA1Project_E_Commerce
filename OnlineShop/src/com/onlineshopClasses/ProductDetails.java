package com.onlineshopClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ProductDetails extends Product {
	Scanner sc = new Scanner(System.in);
   
	Integer pendingQty = 0;
	Connection connection = null;
	PreparedStatement ps = null;
	Integer bill = 0;
	String email;

	public void showProducts() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshop", "root", "12345");
			// select query
			ArrayList<Product> a1 = new ArrayList();
			System.out.println("Product Details :");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------------------");
			PreparedStatement ps = con.prepareStatement("select * from product");
			ResultSet rs = ps.executeQuery();
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-20s %-40s %-30s %-20s %-15s", "productId", "Description", "price", "name",
					" quantity");
			System.out.println();
			while (rs.next()) {

				a1.add(new Product((rs.getInt("productId")), rs.getString("description"), rs.getInt("price"),
						rs.getString("name"), rs.getInt("quantity")));

				System.out.format("%-20s %-40s %-25s %-30s %-40s", rs.getInt(1), rs.getNString(2), rs.getInt(3),
						rs.getNString(4), rs.getInt(5));
				System.out.println();
			}

			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------------------------------------");

			String doUwantToPurchase = "N";
			Map<Integer, Integer> cartProduct = new HashMap<>();
			do {
				System.out.println("Which Book you want to purchase? Enter product id and qty");
				int product_id = sc.nextInt();
				int product_qty = sc.nextInt();
				cartProduct.put(product_id, product_qty);
				System.out.println("Do you want to purchase anything else? \nPress Y for Yes \nPress N For No");
				doUwantToPurchase = sc.next();
			} while (doUwantToPurchase.equals("Y"));
			billingMethod(a1, cartProduct);
            getOrder(con, bill, doUwantToPurchase);
			decreaseQtyInProductTable(con, a1, cartProduct);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void decreaseQtyInProductTable(Connection conn, List<Product> a1, Map<Integer, Integer> cartProduct)
			throws SQLException {
		Set<Integer> carts = cartProduct.keySet();

		for (Integer cart : carts) {
			for (Integer cart1 : carts) {
				for (Product product : a1) {
					if (cart1 == product.getProductId()) {
						pendingQty = product.getQuantity() - cartProduct.get(cart1);
					}
					
				}
			}
			String query = "update product set quantity = " + pendingQty + " where productid = " + cart;
			PreparedStatement ps2 = conn.prepareStatement(query);
			ps2.executeUpdate();
		}

	}

	public void billingMethod(List<Product> a1, Map<Integer, Integer> cartProduct) throws SQLException {
		
		Set<Integer> carts = cartProduct.keySet();
		for (Integer cart : carts) {
			for (Product product : a1) {
				if (cart == product.getProductId()) {
					bill += product.getPrice() * cartProduct.get(cart);
				}
			}
		}
		
		

		System.out.println("YOUR CART:");
		System.out.println("The Book id and quantity which you have selected:" + cartProduct);
		
		 System.out.println("Please confirm your email:"); 
		email = sc.next();
		 //obj.getEmail(email);
		 
		System.out.println("The final total of your purchase:" + bill);
        
	}
	
	public void getOrder(Connection conn,int a,String Email ) throws SQLException
	{
		Email = email;
		String query1 = "select * from user where total_bill=?";
		PreparedStatement ps1 = conn.prepareStatement(query1);
		
		
		a=bill;
		String query = "insert into order1(total_bill,email)values(?,?)";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, a);
		ps.setString(2, Email);
		int i = ps.executeUpdate();
		
		System.out.println("Bill Generated Succssfully");
	}

}
