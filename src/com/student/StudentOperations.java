package com.student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import com.quiz.CreateConnection;

public class StudentOperations
{
	Connection con=CreateConnection.getCon();
	Scanner sc=new Scanner(System.in);
	PreparedStatement ps;
	PreparedStatement stmt;
	public int displayQuestions() throws Exception
	{
		try
		{
				int count=0;
				String query="select * from questions order by RAND()";
				stmt = con.prepareStatement(query);
				ResultSet rs=stmt.executeQuery();
				System.out.println("Test is Started");
				
				while(rs.next())
				{
					System.out.println("Question:"+rs.getString(2));
					System.out.println("Option A:"+rs.getString(3));
					System.out.println("Option B:"+rs.getString(4));
					System.out.println("Option C:"+rs.getString(5));
					System.out.println("Option D:"+rs.getString(6));
					System.out.println("Enter your answer:");
					String ans=sc.nextLine();
					
					ps = con.prepareStatement("select * from questions where qid="+rs.getInt(1));
					ResultSet rs1=ps.executeQuery();
					while(rs1.next())
					{
						if(rs1.getString(7).equalsIgnoreCase(ans))
						{
							count++;
						}
						break;
					}	
					
				}
				System.out.println("Test Submitted successfully");
				return count;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return 0;
		}
		finally
		{
			stmt.close();
			ps.close();
			con.close();
		}
	}

}
