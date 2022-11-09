package com.onlineshopClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.onlineshopConnection.ConnectionTest;

public class MainPage implements OnlineShopDemo {

	public static final String Aname = "Admin";
	public static final String pass = "admin";
	 static String Continue = "N";
	 

	Connection connection = null;
	PreparedStatement ps = null;

	Scanner sc = new Scanner(System.in);
	

	@Override
	public void adminLogin() {
		// TODO Auto-generated method stub

		System.out.println("Please Enter Username:");
		String name = sc.next();
		System.out.println("Please Enter Password:");
		String password = sc.next();

		if (name.equals(Aname) && password.equals(pass)) {
			System.out.println("Login Successfully");

		} else {
			System.out.println("Invalid Credentials");
		}

	}

	public void registerUser(String userName, String email, String password) {

		try {
			ConnectionTest connectionTest = new ConnectionTest();
			connection = connectionTest.getConnectionDetails();
			ps = connection.prepareStatement("insert user(userName,email,password)values(?,?,?)");
			ps.setString(1, userName);
			ps.setString(2, email);
			ps.setString(3, password);
            
			int i = ps.executeUpdate();

			// TODO Auto-generated method stub

			System.out.println("Register Successfully");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void allowLogin() throws SQLException {

		System.out.println("Enter Your Registered Email");
		String email = sc.next();
		System.out.println("Enter your password:");
		String pass = sc.next();

		ConnectionTest connectionTest = new ConnectionTest();
		connection = connectionTest.getConnectionDetails();

		// Boolean allowLogin = Boolean.TRUE;
		ps = connection.prepareStatement("select * from user where email = ?");
		ps.setString(1, email);
		ResultSet i = ps.executeQuery();
		while (i.next()) {
			if (email.equals(i.getString("email")) && pass.equals(i.getString("password"))) {
				// allowLogin = Boolean.FALSE;
				System.out.println("Successfully Login");
				System.out.println("WELCOME"+" "+i.getString("userName"));
				ProductDetails pd = new ProductDetails();
				pd.showProducts();
                 
			}

		}

	}

	public static void main(String[] args) {
		MainPage mp = new MainPage();
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------------------------------------------WELCOME TO E-COMMERCE PLATFORM--------------------------------------------------------------------------------------------------");
		System.out.println("1.ADMIN:");
		System.out.println("2.USER");
		System.out.println("3.EXIT");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Please Enter your Choice:");
		int ch = sc.nextInt();

		if (ch == 1) {
			mp.adminLogin();
			do {
				
			System.out.println("Please Enter your Choice:");
			System.out.println("1.USER List");
			System.out.println("2.PRODUCT List");
			System.out.println("3.PURCHASE TABLE");
			System.out.println("4.EXIT");
			
			
           
			int ch2 = sc.nextInt();
			
			switch (ch2) {
			case 1:
				User user = new User();
				user.getUserDetails();
				break;

			case 2:
				ProductTable table= new ProductTable();
				table.showProductsToAdmin();
				break;
				
			case 3 :
				 Billing billing = new Billing();
				 billing.showBills();
				 break;

			case 4:
				System.out.println("You Are Exited From System!!! VISIT AGAIN");
				break;
			
				   
			}
			 
					System.out.println("Do you want to Continue ? \nPress Y for Yes \nPress N For No");
					Continue = sc.next();
					if(Continue.equals("N")) {
						System.out.println("THANK YOU FOR THE RESPONSE");
					}
					}while(Continue.equals("Y")); 
			 }
		
		

		if (ch == 2) {
			do {
			System.out.println("------------------------------------------------------------------------------------");
			System.out.println("1.REGISTER");
			System.out.println("2.LOGIN");
			System.out.println("------------------------------------------------------------------------------------");
			System.out.println("Please Enter Your Choice:");
			int ch1 = sc.nextInt();
			switch (ch1) {
			case 1:
				
				System.out.println("Enter your Name:");
				
				String uName = sc.next();
				
				System.out.println("Enter your Email");
				String email = sc.next();

				System.out.println(" Enter your Password");
				String pass = sc.next();

				mp.registerUser(uName, email, pass);
				break;
			case 2:

				try {
					mp.allowLogin();
                    
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			}
			System.out.println("Do you want to Continue ? \nPress Y for Yes \nPress N For No");
			Continue = sc.next();
			if(Continue.equals("N")) {
				System.out.println("THANK YOU FOR THE RESPONSE");
			}
			}while(Continue.equals("Y"));
			}
		
		if (ch == 3) {
			System.out.println("You are Exited From System!! VISIT AGAIN");
		}
	}

}

