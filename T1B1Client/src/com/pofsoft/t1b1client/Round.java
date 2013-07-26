package com.pofsoft.t1b1client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("rawtypes")
public class Round implements Comparable {
		
	private ArrayList<PolePositionedClimber> polePositionedClimbers;
	private Iterator<PolePositionedClimber> polePositionedClimbersIterator;
	private int roundId;
	private int sequence;
	private String name;
	private String boulderprefix;
	private int nrofboulders;
	private boolean enabled;
	
	int activeBoulderId;
	
	public Round (int sequence, String name, int roundId, int nrofboulders, String boulderprefix)
	{
		polePositionedClimbers = new ArrayList<PolePositionedClimber>();
		polePositionedClimbersIterator = polePositionedClimbers.iterator();
		this.sequence = sequence;
		this.name = name;		
		this.roundId = roundId;
		this.nrofboulders = nrofboulders;
		this.boulderprefix = boulderprefix;
		activeBoulderId = 0;
		enabled = false;
	}		
				
	public void setEnabled()
	{
		enabled = true;
	}

	public void setDisabled()
	{
		enabled = false;
	}	
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	public int getBoulderId()
	{
		return activeBoulderId;
	}
	
	public int getNrOfClimbers()
	{
		return polePositionedClimbers.size();
	}
	
	public String getName()
	{
		return name;
	}

	public String getBoulderPrefix()
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
		return polePositionedClimbers.size();
	}
		
	public void addPolePositionedClimber(PolePositionedClimber climber)
	{
		polePositionedClimbers.add(climber);
		sort();
	}
			
	public PolePositionedClimber getNextClimber() throws RuntimeException
	{
		try
		{
			return (polePositionedClimbersIterator.next());
		}
		catch (NoSuchElementException e)
		{
			throw new RuntimeException("No next climber in round " + this.name);
		}			
	}
	
	public boolean hasNextClimber()
	{
		return (polePositionedClimbersIterator.hasNext());
	}
			
	public void sort()
	{
		Collections.sort(polePositionedClimbers, new PolePositionedClimberComparator());
	}
	
	public void clear()
	{
		polePositionedClimbers = new ArrayList<PolePositionedClimber>();
	}
	
	public void reset()
	{
		polePositionedClimbersIterator = polePositionedClimbers.iterator();
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
