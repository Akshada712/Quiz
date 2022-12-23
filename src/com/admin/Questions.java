package com.admin;

public class Questions
{
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String correctOpt;
	
	
	public void setQuestion(String que)
	{
		this.question=que;		
	}
	public void setOption1(String opt1)
	{
		this.option1=opt1;		
	}
	public void setOption2(String opt2)
	{
		this.option2=opt2;		
	}
	public void setOption3(String opt3)
	{
		this.option3=opt3;		
	}
	public void setOption4(String opt4)
	{
		this.option4=opt4;		
	}
	public void setCorrectOpt(String correctOpt)
	{
		this.correctOpt=correctOpt;		
	}
	public String getQuestion()
	{
		return this.question;
	}
	public String getOption1()
	{
		return this.option1;
	}
	public String getOption2()
	{
		return this.option2;
	}
	public String getOption3()
	{
		return this.option3;
	}
	public String getOption4()
	{
		return this.option4;
	}
	public String getCorrectOpt()
	{
		return this.correctOpt;
	}
	public String toString()
	{
		return "Questions{" +
				" Question='" + question +'\''+
				", option1='" + option1+'\''+
				", option2='" + option2+'\''+
				", option2='" + option3+'\''+
				", option4='" + option4+'\''+
				'}';
	}
}
