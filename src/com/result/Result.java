package com.result;

import java.sql.*;
import com.quiz.CreateConnection;
import com.student.StudentOperations;

public class Result
{
	Connection con=CreateConnection.getCon();
	
	PreparedStatement ps;
	Statement stmt;
	StudentOperations studOp=new StudentOperations();
	String grade = null;
	public String getResult(int totalMarks) throws Exception
	{
		
		try {
			
			if(totalMarks>=8)
			{
				grade="A";
				
			}
			else if(totalMarks>=6 && totalMarks<8)
			{
				grade="B";
				
			}
			else if(totalMarks==5)
			{
				grade="C";
				
			}
			else if(totalMarks<5)
			{
				grade="Fail";
				
			}		
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return grade;
	}
	public void storeResult(int sId,int totalMarks) throws Exception
	{
		try {

				String grade=getResult(totalMarks);
				String query="insert into result (sid,totalmarks,grade) values(?,?,?)";
				ps = con.prepareStatement(query);
				ps.setInt(1,sId);
				ps.setInt(2,totalMarks);
				ps.setString(3,grade);
				ps.execute();
				
			
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
	
}
