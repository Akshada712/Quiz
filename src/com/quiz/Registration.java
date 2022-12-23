package com.quiz;

import java.sql.*;

import java.util.Scanner;

import com.admin.AdminOperations;
import com.quiz.CreateConnection;
import com.result.Result;
import com.student.*;

public interface Registration 
{
	AdminOperations adminOp=new AdminOperations();
	StudentOperations studOp=new StudentOperations();
	Student stud=new Student();
	
	Scanner sc=new Scanner(System.in);
	Result result=new Result();
	
	public static int login() throws Exception
	{
		boolean flag=false;
		int totalMarks = 0;
		int count=0;
		Connection con=CreateConnection.getCon();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		System.out.println("=========Login==========");
		System.out.println("Enter Email:");
		String userName=sc.nextLine();
		System.out.println("Enter password:");
		String password=sc.nextLine();
		
		if(userName.equals("admin@gmail.com") && password.equals("admin"))
		{
			System.out.println("Select your choice:");
			System.out.println("A:Add Question");
			System.out.println("B:Update Question");
			System.out.println("C:Delete Question");
			System.out.println("D:Display all student Result");
			
			String ch=sc.nextLine();
			switch(ch)
			{
				case "A":{
						adminOp.addQuestion();
						break;
					}
				case "B":{
						adminOp.updateQuestion();
						break;
					}
				case "C":{
						adminOp.deleteQuestion();
					}
				case "D":{
					adminOp.displayStudentResult();
				}
			}
		}
		else
		{
			try {
				ps = con.prepareStatement("select * from student where username='"+userName+"'");
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					if(rs.getString(2).equals(userName) && rs.getString(3).equals(password))
					{
						count=1;
						ps1 = con.prepareStatement("select * from result");
						ResultSet rs1=ps1.executeQuery();
						while(rs1.next())
						{
							if(rs.getInt(1)==rs1.getInt(2))
							{
								flag=true;;
								System.out.println("Your test is already submitted");
								break;
							}
						
						}
						if(flag==false)
						{
							totalMarks=studOp.displayQuestions();
							result.storeResult(rs.getInt(1), totalMarks);
						}
						
					}	
				}
				if(count==0)
				{
					System.out.println("Incorrect username or password");
					System.out.println("Please enter valid username and password");
					Registration.login();
				}
				
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				con.close();
				ps.close();
			}
			
		}
		return totalMarks;
	}
	
		
	public static void register() throws Exception 
	{
		Connection con=CreateConnection.getCon();
		PreparedStatement ps = null;
		int count=0;
		try
		{
			System.out.println("==========Registration=============");
			System.out.println("Enter name: ");
			String sName=sc.nextLine();
			stud.setStudName(sName);
			System.out.println("Enter Email:");
			String userName=sc.nextLine();
			stud.setUserName(userName);
			System.out.println("Enter password:");
			String password=sc.nextLine();
			stud.setPassword(password);
			
			ps = con.prepareStatement("select * from student");
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				if(rs.getString(2).equals(userName))
				{
					count++;
					break;
				}
			}
			if(count==1)
			{
				System.out.println("Username is already exist");
				Registration.login();
			}
			else
			{
				Registration.addStudent();
			}
			
					
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			ps.close();
			con.close();
		}
	}

	public static void addStudent() throws Exception
	{
		Connection con=CreateConnection.getCon();
		PreparedStatement stmt=null;
		try
		{
			String sName=stud.getStudName();
			String userName=stud.getUserName();
			String password=stud.getPassword();
			String query="insert into student (username,password,sname) values(?,?,?)";
			stmt = con.prepareStatement(query);
			stmt.setString(1, userName); 
			stmt.setString(2, password);
			stmt.setString(3, sName);
			
			stmt.execute();
			System.out.println("Registration successful.");
			Registration.login();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			stmt.close();
			con.close();
		}
	}
}
