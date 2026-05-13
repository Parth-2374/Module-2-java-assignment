package com.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

import java.sql.ResultSet;


public class JDBCDemo {

	static int id;
	static String fname,lname,city;
	
	public static Connection createConnection() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","");
			 DatabaseMetaData dbmd = conn.getMetaData();

	            // Display Database Information
	            System.out.println("Database Name : "
	                    + dbmd.getDatabaseProductName());

	            System.out.println("Database Version : "
	                    + dbmd.getDatabaseProductVersion());

	            System.out.println("Driver Name : "
	                    + dbmd.getDriverName());

	            System.out.println("Driver Version : "
	                    + dbmd.getDriverVersion());

	            // Display Supported SQL Features
	            System.out.println("\nSupports Transactions : "
	                    + dbmd.supportsTransactions());

	            System.out.println("Supports Stored Procedures : "
	                    + dbmd.supportsStoredProcedures());

	            System.out.println("Supports Batch Updates : "
	                    + dbmd.supportsBatchUpdates());

	            // Display List of Tables
	            System.out.println("\nList of Tables");

	            ResultSet rs = dbmd.getTables(
	                    null,
	                    null,
	                    "%",
	                    new String[] {"TABLE"});

	            while (rs.next()) {

	                System.out.println(
	                        rs.getString("TABLE_NAME"));
	            }

	            // Close Connection
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

