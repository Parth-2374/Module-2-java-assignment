package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

public class JDBCDemo {

	static int id;
	static String fname,lname,city;
	
	public static Connection createConnection() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","");
			 PreparedStatement pst = conn.prepareStatement(
	                    "select * from stu");

	            ResultSet rs = pst.executeQuery();

	            // Get ResultSetMetaData Object
	            ResultSetMetaData rsmd = rs.getMetaData();

	         
	            int count = rsmd.getColumnCount();

	            System.out.println("Total Columns : " + count);

	            System.out.println("\nColumn Details");

	            for (int i = 1; i <= count; i++) {

	                System.out.println(
	                        "Column Name : "
	                        + rsmd.getColumnName(i));

	                System.out.println(
	                        "Column Type : "
	                        + rsmd.getColumnTypeName(i));

	                System.out.println("----------------------");
	            }

	            conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	
	public static void main(String[] args) {
		createConnection();
	}
}

