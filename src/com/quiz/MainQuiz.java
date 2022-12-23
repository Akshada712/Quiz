package com.quiz;

import java.util.Scanner;

public class MainQuiz implements Registration
{	
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("For login press 1 and for registration press 2");
		int ch=sc.nextInt();
		
		try {
			if(ch==1)
			{
				Registration.login();
			}
			else if(ch==2)
			{
				Registration.register();
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
