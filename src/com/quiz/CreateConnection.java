package com.quiz;

import java.sql.*;
public class CreateConnection
{
	public static Connection getCon()
	{
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "root");
			return con;
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		
	}
}
