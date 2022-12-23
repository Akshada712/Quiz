package com.student;

import java.sql.*;
import java.util.Scanner;

public class Student
{
	private String userName;
	private String password;
	private String studName;
		
	public void setUserName(String userName)
	{
		this.userName=userName;		
	}
	public void setPassword(String password)
	{
		this.password=password;		
	}
	public void setStudName(String studName)
	{
		this.studName=studName;		
	}
	
	public String getUserName()
	{
		return this.userName;
	}
	public String getPassword()
	{
		return this.password;
	}
	public String getStudName()
	{
		return this.studName;
	}
	@Override
	public String toString() {
		return "Student:userName=" + userName + 
				", password=" + password +
				", studName=" + studName ;
	}
	
	
}
