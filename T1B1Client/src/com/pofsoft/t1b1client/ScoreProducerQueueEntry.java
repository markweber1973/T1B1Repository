package com.pofsoft.t1b1client;

public class ScoreProducerQueueEntry {
	
	private int eventId;
	private int phaseId;
	private int roundId;
	private int boulderNumber;
	private int startNumber;
	private boolean finished;
	private boolean started;
	private boolean topped;
	private int topAttempts;
	private boolean bonussed;
	private int bonusAttempts;
	private int attempts;
	
	public ScoreProducerQueueEntry(int boulderNumber, int startNumber, boolean finished, boolean started, 
			boolean topped, int topAttempts, boolean bonussed, int bonusAttempts, int attempts,  
			int eventId, int phaseId, int roundId)
	{
		this.boulderNumber = boulderNumber;
		this.startNumber = startNumber;
		this.finished = finished;
		this.topped = topped;
		this.topAttempts = topAttempts;
		this.bonussed = bonussed;
		this.bonusAttempts = bonusAttempts;
		this.attempts = attempts;
		this.eventId = eventId;
		this.phaseId = phaseId;
		this.roundId = roundId;		
	}	
	
	public int getBoulderNumber()
	{
		return boulderNumber;
	}

	public int getStartNumber()
	{
		return startNumber;
	}	

	public boolean getFinished()
	{
		return finished;
	}		

	public boolean getStarted()
	{
		return started;
	}		
	
	public boolean getTopped()
	{
		return topped;
	}	
	
	public int getTopAttempts()
	{
		return topAttempts;
	}

	public boolean getBonussed()
	{
		return bonussed;
	}	
	
	public int getBonusAttempts()
	{
		return bonusAttempts;
	}

	public int getAttempts()
	{
		return attempts;
	}
	
	public void setEventId(int eventId)
	{
		this.eventId = eventId;
	}
	
	public void setPhaseId(int phaseId)
	{
		this.phaseId = phaseId;
	}

	public void setRoundId(int roundId)
	{
		this.roundId = roundId;
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
