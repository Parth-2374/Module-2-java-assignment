package com.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.Scanner;

public class JDBCDemo {

	public static Connection createConnection() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","");
			 CallableStatement cs = conn.prepareCall(
	                    "{call getEmployeeName(?,?)}");
	            cs.setInt(1, 1);
	            cs.registerOutParameter(2, Types.VARCHAR);
	            cs.execute();
	            String fullname = cs.getString(2);
	            System.out.println("Employee Full Name : "
	                    + fullname);
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

