package com.onlineshopClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.onlineshopConnection.ConnectionTest;

public class User {

	Connection connection =null;
	PreparedStatement ps = null;
	
	public void getUserDetails() {
		try {
			ConnectionTest connectionTest = new ConnectionTest();
			connection = connectionTest.getConnectionDetails();
			ps = connection.prepareStatement("select * from user");
			
		
			ResultSet rs = ps.executeQuery();
			System.out.println("USERID"+"\t\t"+"USERNAME"+"\t"+"EMAIL");
			while(rs.next()) {
				System.out.println(+rs.getInt("userId")+"\t\t"+rs.getString("userName")+"\t\t"+rs.getString("email"));
				
				/*
				 * System.out.println(rs.getInt("userId"));
				 * System.out.println(rs.getString("userName"));
				 * System.out.println(rs.getString("email"));
				 * 
				 * System.out.printf("%-20s \t %-20s \t %-20s \t %-20s \t %-20s\n",
				 * "User_ID","User_Name","Email");
				 * System.out.printf("%-20d \t %-20s \t %-20s \t %-20d \t %-20f\n",rs.getInt(
				 * "userId"),rs.getString("userName"),rs.getString("email") );
				 */
			}
		
		// TODO Auto-generated method stub
		
		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	}


