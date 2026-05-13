package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCDemo {

	public static Connection createConnection() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","");
			 System.out.println("Connection Successfully...");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	public static void main(String[] args) {
		createConnection();
	}
}

