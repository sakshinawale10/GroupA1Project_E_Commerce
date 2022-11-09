package com.onlineshopClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductTable {

	public void showProductsToAdmin() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshop", "root", "12345");
			// select query
			ArrayList<Product> a1 = new ArrayList();
			System.out.println("Product Details :");

			PreparedStatement ps = con.prepareStatement("select * from product");
			ResultSet rs = ps.executeQuery();
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-20s %-40s %-30s %-20s %-15s", "productId", "Description", "price", "name",
					" quantity");
			System.out.println();
			while (rs.next()) {

				/*a1.add(new Product((rs.getInt("productId")), rs.getString("description"), rs.getInt("price"),
						rs.getString("name"), rs.getInt("quantity")));*/
				System.out.format("%-20s %-40s %-25s %-30s %-40s", rs.getInt(1), rs.getNString(2), rs.getInt(3),
						rs.getNString(4), rs.getInt(5));
				System.out.println();
			}
			for (Product Product : a1) {
				System.out.println(Product);
			}
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
}catch(Exception e) {
e.getStackTrace();	
}
		
}





}