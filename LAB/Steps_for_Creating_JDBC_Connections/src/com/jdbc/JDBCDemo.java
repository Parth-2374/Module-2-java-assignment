package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JDBCDemo {

	static int id;
	static String fname,lname;
	
	public static Connection createConnection() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	public static void insertStudent() {
		Scanner sc =new Scanner(System.in);
		System.out.print("Enter Your First Name :- ");
		fname = sc.next();
		System.out.print("Enter Your Last Name :- ");
		lname=sc.next();
		
		try {
			Connection conn=createConnection();
			String sql="insert into student(fname,lname) values(?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, fname);
			pst.setString(2, lname);
			pst.executeUpdate();
			System.out.println("Data Inserted SuccessFully...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		public static void serchAllStudent() {
			Scanner sc=new Scanner(System.in);
			System.out.println("enter Your ID :- ");
			id=sc.nextInt();
			try {
				Connection conn=createConnection();
				String sql="select * from Student where id=?";
				PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, id);
				ResultSet rs=pst.executeQuery();
				if(rs.next()) {
					System.out.println("ID :- "+rs.getInt("id"));
					System.out.println("First Name :- "+rs.getString("fname"));
					System.out.println("Last Name :- "+rs.getString("lname"));
				}else {
					System.out.println("ID Not Registered..");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}		
	}
		public static void updateStudent() {
			Scanner sc=new Scanner(System.in);
			System.out.print("Enter Your ID :- ");
			id=sc.nextInt();
			try {
				Connection conn=createConnection();
				String sql="select * from student where id=?";
				PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, id);
				ResultSet rs=pst.executeQuery();
				if(rs.next()) {
					System.out.println("ID :- "+rs.getInt("id"));
					System.out.println("First Name :- "+rs.getString("fname"));
					System.out.println("Last Name :- "+rs.getString("lname"));
					System.out.print("Enter Your New First Name :-");
					fname=sc.next();
					System.out.print("Enter Your New Last Name :-");
					lname=sc.next();
					String sql1="update student set fname=? ,lname=? where id=?";
					PreparedStatement pst1=conn.prepareStatement(sql1);
					pst1.setString(1, fname);
					pst1.setString(2, lname);
					pst1.setInt(3, id);
					pst1.executeUpdate();
					System.out.println("Data Update SuccessFully ");
				}else {
					System.out.println("ID Not Registered..");	
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public static void ShowAllStudent() {
			try {
				Connection conn=createConnection();
				String sql="select * from student";
				PreparedStatement pst=conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				while(rs.next()) {
					System.out.println("********************************************");
					System.out.println("ID :- "+rs.getInt("id"));
					System.out.println("First Name :- "+rs.getString("fname"));
					System.out.println("Last Name :- "+rs.getString("lname"));
					System.out.println("********************************************");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public static void Deletestudent() {
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Your Id :-");
			id=sc.nextInt();
			try {
				Connection conn= createConnection();
				String sql="delete * from student where id=?";
				PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, id);
				pst.executeUpdate();
				System.out.println("Data Delete Successfully... ");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public static void main(String[] args) {
			while(true) {
				System.out.println("******************************************");
				System.out.println("1.Insert Student");
				System.out.println("2.Serch Student");
				System.out.println("3.Update Student");
				System.out.println("4.Delete Student");
				System.out.println("5.Show All Student");
				System.out.println("6.Exit ");
				System.out.println("******************************************");
				System.out.print("Enter Your Choice :- ");
				Scanner sc =new Scanner(System.in);
				int choice = sc.nextInt();
				
				if(choice==1) {
					insertStudent();
				}
				else if(choice==2) {
					serchAllStudent();
				}
				else if(choice==3) {
					updateStudent();
				}
				else if(choice==4) {
					Deletestudent();
				}
				else if(choice==5) {
					ShowAllStudent();
				}
				else if(choice==6) {
					System.out.println("Thank You.");
					System.out.println("******************************************");
					break;
				}
			}
		}
}

