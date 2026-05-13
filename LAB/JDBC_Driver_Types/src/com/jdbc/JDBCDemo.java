package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCDemo {

	
	public static Connection createConnection() {
		Connection conn=null;
		try {
			 String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			System.out.println("JDBC Driver Loaded Successfully");
            System.out.println("Driver Used : " + driver);
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","");
			System.out.println("Database Connected Successfully...");
			conn.close();
			System.out.println("Connection Closed..");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	
	public static void main(String[] args) {
		createConnection();
	}
}

