package com.pofsoft.t1b1client;



import android.app.Application;

public class MatchData extends Application {
	
	private PolePositionedClimberList matchData = null; 

	int boulderId;
	
	public MatchData()
	{
		matchData = new PolePositionedClimberList();
		boulderId = 0;
	}
		
	public void setBoulderId(int boulderId)
	{
		this.boulderId = boulderId;
	}
	
	public int getBoulderId()
	{
		return boulderId;
	}
	
	public void addPolePositionedClimber(int startNumber, int polePosition, String firstName, String lastName)
	{
		matchData.addPolePositionedClimber(startNumber, polePosition, firstName, lastName);
	}
	
	public PolePositionedClimber getFirst()
	{
		return (matchData.getFirst());
	}
	
	public PolePositionedClimber getPrevious()
	{
		return (matchData.getPrevious());
	}
	
	public PolePositionedClimber getNext()
	{
		return (matchData.getNext());
	}
	
	public boolean hasNext()
	{
		return (matchData.hasNext());
	}
	
	public boolean isEmpty()
	{
		return (matchData.isEmpty());
	}
	
	public boolean hasPrevious()
	{
		return (matchData.hasPrevious());
	}
	
	public void sort()
	{
		matchData.sort();
	}
	
	public void clear()
	{
		matchData = new PolePositionedClimberList();
	}
	
	public void reset()
	{
		matchData.reset();
	}
}
	