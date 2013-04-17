package com.pofsoft.t1b1client;



import android.app.Application;

public class MatchData extends Application {
	
	private PolePositionedClimberList matchData = null; 

	int boulderId;
	String eventId;
	String phaseId;
	
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
	
	public void setEventId(String eventId)
	{
		this.eventId = eventId;
	}
	
	public String getEventId()
	{
		return eventId;
	}	
	
	public void setPhaseId(String phaseId)
	{
		this.phaseId = phaseId;
	}
	
	public String getPhaseId()
	{
		return phaseId;
	}		
	
	public void addPolePositionedClimber(int startNumber, int polePosition, int sequence, String firstName, String lastName,
			int eventId, int phaseId, int roundId)
	{
		matchData.addPolePositionedClimber(startNumber, polePosition, sequence, firstName, lastName, eventId, phaseId, roundId);
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
	