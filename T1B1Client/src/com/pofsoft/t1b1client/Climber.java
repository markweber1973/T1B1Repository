package com.pofsoft.t1b1client;

@SuppressWarnings("rawtypes")
public class Climber implements Comparable {
	
	private int startNumber;
	private String firstName;
	private String lastName;
	
	public Climber (int startNumber, String firstName, String lastName)
	{
		this.startNumber = startNumber;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public void startNumber(int startNumber)
	{
		this.startNumber = startNumber;
	}
	
	public int startNumber()
	{
		return startNumber;
	}
	
	public void firstName(String name)
	{
		this.firstName = name;
	}

	public void lastName(String name)
	{
		this.lastName = name;
	}
	
	public String firstName()
	{
		return this.firstName;
	}
	
	public String lastName()
	{
		return this.lastName;
	}	
	
	@Override
	public int compareTo(Object compareTarget)
	{
		if (startNumber < ((Climber)compareTarget).startNumber())
		{
			return (-1);
		}
		else if (startNumber == ((Climber)compareTarget).startNumber())
		{
			return (0);
		}
		else
		{
			return 1;
		}			
	}
}
