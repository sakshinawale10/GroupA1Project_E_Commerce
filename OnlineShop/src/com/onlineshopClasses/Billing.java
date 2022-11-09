package com.onlineshopClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class Billing {
	public void showBills() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshop", "root", "12345");
			// select query
			//ArrayList<Product> a1 = new ArrayList();
			System.out.println("Purchase Details :");
			System.out.println(
		
					"----------------------------------------------------------------------------------------------------");
			PreparedStatement ps = con.prepareStatement("select * from order1");
			
			ResultSet rs = ps.executeQuery();
			
			System.out.printf("%-20s %-20s", "total_bill" ,"Email");
			System.out.println();
			while (rs.next()) {

				/*a1.add(new Product((rs.getInt("productId")), rs.getString("description"), rs.getInt("price"),
						rs.getString("name"), rs.getInt("quantity")));*/
				System.out.format("%-20s,%-20s", rs.getInt(1),rs.getString(2));
				System.out.println();
			}
			
System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
}catch(Exception e) {
e.getStackTrace();	
}
		
}
	
	
	
	
	
}
