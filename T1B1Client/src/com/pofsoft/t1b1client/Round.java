package com.pofsoft.t1b1client;

@SuppressWarnings("rawtypes")
public class Round implements Comparable {
	
	private PolePositionedClimberList climbersInRound = null; 
	
	private int roundId;
	private int sequence;
	private String name;
	private String boulderprefix;
	private int nrofboulders;
	private boolean enabled;
	
	int activeBoulderId;
	
	public Round (int sequence, String name, int roundId, int nrofboulders, String boulderprefix)
	{
		climbersInRound = new PolePositionedClimberList();
		this.sequence = sequence;
		this.name = name;		
		this.roundId = roundId;
		this.nrofboulders = nrofboulders;
		this.boulderprefix = boulderprefix;
		activeBoulderId = 0;
		enabled = false;
	}
	
	
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
	
	public boolean getEnabled()
	{
		return enabled;
	}
	
	public void setBoulderId(int boulderId)
	{
		this.activeBoulderId = boulderId;
	}
	
	public int getBoulderId()
	{
		return activeBoulderId;
	}
	
	public String name()
	{
		return name;
	}

	public String boulderPrefix()
	{
		return boulderprefix;
	}	
	
	public int getSequence()
	{
		return sequence;
	}
	
	public int getRoundId()
	{
		return roundId;
	}	
	
	public int getNrOfBoulders()
	{
		return nrofboulders;
	}	
	
	public int getSize()
	{
		return climbersInRound.getSize();
	}
	
	public PolePositionedClimber getPolePositionedClimber(int index)
	{
		return climbersInRound.getPolePositionedClimber(index);
	}
	
	public void addPolePositionedClimber(PolePositionedClimber climber)
	{
		climbersInRound.add(climber);
	}
	
	public PolePositionedClimber getFirst()
	{
		return (climbersInRound.getFirst());
	}
	
	public PolePositionedClimber getPrevious()
	{
		return (climbersInRound.getPrevious());
	}
	
	public PolePositionedClimber getNext()
	{
		return (climbersInRound.getNext());
	}
	
	public boolean hasNext()
	{
		return (climbersInRound.hasNext());
	}
	
	public boolean isEmpty()
	{
		return (climbersInRound.isEmpty());
	}
	
	public boolean hasPrevious()
	{
		return (climbersInRound.hasPrevious());
	}
	
	public void sort()
	{
		climbersInRound.sort();
	}
	
	public void clear()
	{
		climbersInRound = new PolePositionedClimberList();
	}
	
	public void reset()
	{
		climbersInRound.reset();
	}	
	
	@Override
	public int compareTo(Object compareTarget)
	{
		if (sequence < ((Round)compareTarget).getSequence())
		{
			return -1;			
		}
		else if (sequence > ((Round)compareTarget).getSequence())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}	
}
