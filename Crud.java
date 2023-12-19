package com.pro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Crud {
	 static String url = "jdbc:mysql://localhost:3306/company";
     static String Urname= "root";
     static String urpassword = "urpwd";
     
     
     public static void insert(Connection con,String name,int pass) throws SQLException {
    	 String insert="insert into info values(?,?)";
//using ? is to parameterize SQL when we create a PreparedStatement in prgm ? as placeholders in SQLquery by using setString, setInt, setDouble 
    	 PreparedStatement s= con.prepareStatement(insert);
    	 s.setString(1,name);
    	 s.setInt(2, pass);
    	 s.executeUpdate();
    	 System.out.println(" Record inserted");
     }
     public static void update(Connection con,String newname,int pass) throws SQLException {
    	 String update="update info set name=? where password=?";
    	 PreparedStatement s= con.prepareStatement(update);
    	 s.setString(1,newname);
    	 s.setInt(2, pass);
    	 s.executeUpdate();
    	 System.out.println("updated");
    	 
     }
     public static void delete(Connection con,int pass) throws SQLException {
    	 String delete="delete from info where password=?";
    	 PreparedStatement s= con.prepareStatement(delete);
    	 s.setInt(1,pass);
    	 s.executeUpdate();
    	 System.out.println("deleted");
     }
     public static void view(Connection con) throws SQLException {
    	 String query="select*from info";
    	 Statement s= con.createStatement();
 		ResultSet j=s.executeQuery(query);
 		while(j.next()) {
 			String name=j.getString(1);
 			int pass=j.getInt(2);
 			System.out.println("Name:\s"+name+"\tPasswrd:\s"+pass);
 		}
 			
 		
     }
     


	public static void main(String[] args) throws SQLException {
		Connection con=DriverManager.getConnection(url, Urname, urpassword);
		Scanner od=new Scanner(System.in);
		
		
		System.out.println("Enter name to insert:\s");
		String n=od.next();
		System.out.println("Enter password to insert:\s");
		int p=od.nextInt();
		insert(con,n,p);
		view(con);
		
		System.out.println("Enter new name to update:\s");
		String x=od.next();
		System.out.println("Enter password to change it\'s name:\s");
		int y=od.nextInt();
		update(con,x,y);
		view(con);
		
		System.out.println("Enter password to delete that column:\s");
		int s=od.nextInt();
		delete(con,s);
		view(con);
	
		System.out.println("********Here the table you had done in MYSQL*******");
		view(con);
	}
}
