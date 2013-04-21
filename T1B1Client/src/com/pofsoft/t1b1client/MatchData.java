package com.pofsoft.t1b1client;



import android.app.Application;

public class MatchData extends Application {	
	private RoundList roundList = null; 
	int eventId;
	int phaseId;
	String eventName;
	String phaseName;
	
	public MatchData(int eventId, int phaseId)
	{
		this.eventId = eventId;
		this.phaseId = phaseId;
		roundList = new RoundList();
	}
	
	public MatchData()
	{

	}
		
	public void setEventId(int eventId)
	{
		this.eventId = eventId;
	}

	public void setPhaseId(int phaseId)
	{
		this.phaseId = phaseId;
	}	

	public void setEventName(String name)
	{
		this.eventName = name;
	}

	public void setPhaseName(String name)
	{
		this.phaseName = name;
	}		
	
	public int getEventId()
	{
		return eventId;
	}	
		
	public int getPhaseId()
	{
		return phaseId;
	}		

	public String getEventName()
	{
		return eventName;
	}	
		
	public String getPhaseName()
	{
		return phaseName;
	}		
	
	public void addRound(Round round)
	{
		roundList.add(round);
	}
	
	public Round getFirst()
	{
		return (roundList.getFirst());
	}
	
	public Round getPrevious()
	{
		return (roundList.getPrevious());
	}
	
	public Round getNext()
	{
		return (roundList.getNext());
	}
	
	public boolean hasNext()
	{
		return (roundList.hasNext());
	}
	
	public boolean isEmpty()
	{
		return (roundList.isEmpty());
	}
	
	public boolean hasPrevious()
	{
		return (roundList.hasPrevious());
	}
	
	public void sort()
	{
		roundList.sort();
	}
	
	public void clear()
	{
		roundList = new RoundList();
	}
	
	public void reset()
	{
		roundList.reset();
	}
	
	public int getSize()
	{
		return roundList.getSize();
	}

	public Round getRound(int index)
	{
		return roundList.getRound(index);
	}
}
	