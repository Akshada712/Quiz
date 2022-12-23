package com.admin;

import java.sql.*;
import java.util.Scanner;

import com.quiz.CreateConnection;

public class AdminOperations 
{
	Connection con=CreateConnection.getCon();
	Scanner sc=new Scanner(System.in);
	Questions questions=new Questions();
	PreparedStatement ps;
	public void addQuestion() throws Exception 
	{
		try
		{
			System.out.println("Enter the number of questions:");
			int n=Integer.parseInt(sc.nextLine());
			for(int i=1;i<=n;i++)
			{
				System.out.println("Enter the question:");
				String que=sc.nextLine();
				questions.setQuestion(que);
				System.out.println("Enter the option1:");
				String opt1=sc.nextLine();
				questions.setOption1(opt1);
				System.out.println("Enter the option2:");
				String opt2=sc.nextLine();
				questions.setOption2(opt2);
				System.out.println("Enter the option3:");
				String opt3=sc.nextLine();
				questions.setOption2(opt3);
				System.out.println("Enter the option4:");
				String opt4=sc.nextLine();
				questions.setOption4(opt4);
				System.out.println("Enter the correct option:");
				String correctOpt=sc.nextLine();
				questions.setCorrectOpt(correctOpt);
				
				String query="insert into questions (que,option1,option2,option3,option4,correctOpt) values(?,?,?,?,?,?)";
				ps = con.prepareStatement(query);
				ps.setString(1, que);
				ps.setString(2, opt1);
				ps.setString(3, opt2);
				ps.setString(4, opt3);
				ps.setString(5, opt4);
				ps.setString(6, correctOpt);
				ps.execute();
				System.out.println("Question is added successfully");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			con.close();
			ps.close();
		}
		
	}
	
	public void updateQuestion() throws Exception
	{
		try {
			System.out.println("Enter question id which is to be updated:");
			int id=Integer.parseInt(sc.nextLine());
			
			String query="select * from questions where qid="+id;
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next()) 
			{
				
				System.out.println("Enter the question:");
				String que=sc.nextLine();
				questions.setQuestion(que);
				System.out.println("Enter the option1:");
				String opt1=sc.nextLine();
				questions.setOption1(opt1);
				System.out.println("Enter the option2:");
				String opt2=sc.nextLine();
				questions.setOption2(opt2);
				System.out.println("Enter the option3:");
				String opt3=sc.nextLine();
				questions.setOption2(opt3);
				System.out.println("Enter the option4:");
				String opt4=sc.nextLine();
				questions.setOption4(opt4);
				System.out.println("Enter the correct option:");
				String correctOpt=sc.nextLine();
				questions.setCorrectOpt(correctOpt);
				
				ps=con.prepareStatement("update questions set que=?,option1=?,option2=?,option3=?,option4=?,correctOpt=? where qid=?");
				ps.setString(1,que);
				ps.setString(2,opt1);
				ps.setString(3,opt2);
				ps.setString(4,opt3);
				ps.setString(5,opt4);
				ps.setString(6,correctOpt);
				ps.setInt(7,id);
				ps.executeUpdate();
				System.out.println("Question is updated successfully");
			}
			else
			{
				System.out.println("Question id does not exist");
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			con.close();
			ps.close();
		}
		
	}
	public void deleteQuestion() throws Exception
	{
		System.out.println("Enter question id which is to be deleted:");
		int id=Integer.parseInt(sc.nextLine());
		
		String query="select * from questions where qid="+id;
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next()) 
			{
				PreparedStatement ps=con.prepareStatement("delete from questions where qid="+id);
				ps.executeUpdate();
				System.out.println("Question deleted successfully");
			}
			else
			{
				System.out.println("Question id is not exist");
			}
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
		finally
		{
			con.close();
			ps.close();
		}
		
	}
	
	public void displayStudentResult()
	{
		try {
			System.out.println("=======Result of all Students=======");
			ps=con.prepareStatement("select sname,totalMarks,grade from student inner join result on student.sid=result.sid order by totalMarks DESC");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				System.out.println("Student Name:"+rs.getString(1));
				System.out.println("Total Marks:"+rs.getInt(2));
				System.out.println("Grade:"+rs.getString(3));
			}
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
		//
	}
}
