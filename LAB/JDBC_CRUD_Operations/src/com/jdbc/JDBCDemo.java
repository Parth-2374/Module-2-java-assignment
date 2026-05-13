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
			Scanner sc = new Scanner(System.in);
            System.out.print("Enter First Name : ");
            fname = sc.next();

            System.out.print("Enter Last Name : ");
            lname = sc.next();

            System.out.print("Enter City : ");
            city = sc.next();
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","");
			PreparedStatement pst1=conn.prepareStatement("insert into stu (fname,lname,city) values(?,?,?)");
			pst1.setString(1, fname);
			pst1.setString(2, lname);
			pst1.setString(3, city);
			pst1.executeUpdate();
			System.out.println(" Inserted Successfully");
			PreparedStatement pst2=conn.prepareStatement("update stu set city=? where id=?");
			pst2.setString(1, city);
			pst2.setInt(2, id);
			pst2.executeUpdate();
			System.out.println(" Updated  Successfully");
			PreparedStatement pst3=conn.prepareStatement("select * from stu");
			ResultSet rs=pst3.executeQuery();
			while(rs.next()) {
				System.out.println("ID :- "+rs.getInt("id"));
				System.out.println("First Name :- "+rs.getString("fname"));
				System.out.println("Last Name :- "+rs.getString("lname"));	
			}
			PreparedStatement pst4=conn.prepareStatement("delete from stu where id=?");
			pst4.setInt(1, id);
			pst4.executeUpdate();
			System.out.println(" Deleted  Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	
	public static void main(String[] args) {
		createConnection();
	}
}

