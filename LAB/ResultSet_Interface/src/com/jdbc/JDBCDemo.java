package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                    "select * from stu",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            
            ResultSet rs = pst.executeQuery();

            System.out.println("===== Using next() =====");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + " "
                        + rs.getString("fname") + " "
                        + rs.getString("lname") + " "
                        + rs.getString("city"));
            }

            
            System.out.println("\n===== Using previous() =====");

            while (rs.previous()) {

                System.out.println(
                        rs.getInt("id") + " "
                        + rs.getString("fname"));
            }

            
            System.out.println("\n===== Using first() =====");

            rs.first();

            System.out.println(
                    rs.getInt("id") + " "
                    + rs.getString("fname"));

            
            System.out.println("\n===== Using last() =====");

            rs.last();

            System.out.println(
                    rs.getInt("id") + " "
                    + rs.getString("fname"));

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

