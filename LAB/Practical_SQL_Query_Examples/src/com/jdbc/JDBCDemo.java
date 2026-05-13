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
			 PreparedStatement pst1 = conn.prepareStatement(
	                    "insert into stu values(?,?,?,?)");

	            pst1.setInt(1, 3);
	            pst1.setString(2, "Parth");
	            pst1.setString(3, "chauhari");
	            pst1.setString(4, "Surat");

	            pst1.executeUpdate();

	            System.out.println(" Inserted Successfully");

	            PreparedStatement pst2 = conn.prepareStatement(
	                    "update stu set city=? where id=?");

	            pst2.setString(1, "Ahmedabad");
	            pst2.setInt(2, 1);

	            pst2.executeUpdate();

	            System.out.println(" Updated Successfully");
	            PreparedStatement pst3 = conn.prepareStatement(
	                    "select * from stu where city=?");

	            pst3.setString(1, "Ahmedabad");

	            ResultSet rs = pst3.executeQuery();
	            while (rs.next()) {

	                System.out.println(
	                        rs.getInt("id") + " "
	                        + rs.getString("fname") + " "
	                        +rs.getString("lname") + " "
	                        + rs.getString("city"));
	            }
	            PreparedStatement pst4 = conn.prepareStatement(
	                    "delete from stu where id=?");

	            pst4.setInt(1, 1);

	            pst4.executeUpdate();

	            System.out.println("\n Deleted Successfully");

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

