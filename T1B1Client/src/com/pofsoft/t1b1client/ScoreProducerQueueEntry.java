package com.pofsoft.t1b1client;

public class ScoreProducerQueueEntry {
	
	private int boulderNumber;
	private int startNumber;
	private boolean finished;
	private boolean topped;
	private int topAttempts;
	private boolean bonussed;
	private int bonusAttempts;
	private int attempts;
	
	public ScoreProducerQueueEntry(int boulderNumber, int startNumber, boolean finished, 
			boolean topped, int topAttempts, boolean bonussed, int bonusAttempts, int attempts)
	{
		this.boulderNumber = boulderNumber;
		this.startNumber = startNumber;
		this.finished = finished;
		this.topped = topped;
		this.topAttempts = topAttempts;
		this.bonussed = bonussed;
		this.bonusAttempts = bonusAttempts;
		this.attempts = attempts;
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
}
