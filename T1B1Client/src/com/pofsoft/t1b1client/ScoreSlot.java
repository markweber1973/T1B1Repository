package com.pofsoft.t1b1client;

public class ScoreSlot {
	private PolePositionedClimber climber;
	private int boulderId;
	private int eventId;
	private int phaseId;
	private int roundId;
	String boulderPrefix;
	
	
	public ScoreSlot(PolePositionedClimber climber, int boulderId, int eventId, int phaseId, int roundId, String boulderPrefix)
	{
		this.climber = climber;
		this.boulderId = boulderId;
		this.eventId = eventId;
		this.phaseId = phaseId;
		this.roundId = roundId;
		this.boulderPrefix = boulderPrefix;
		
	}
	
	public String getBoulderPrefix()
	{
		return boulderPrefix;
	}
	
	public PolePositionedClimber getClimber()
	{
		return climber;
	}
	
	public int getBoulderId()
	{
		return boulderId;
	}

	public int getEventId()
	{
		return eventId;
	}
	
	public int getPhaseId()
	{
		return phaseId;
	}

	public int getRoundId()
	{
		return roundId;
	}	
}
