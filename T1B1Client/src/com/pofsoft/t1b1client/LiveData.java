package com.pofsoft.t1b1client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

public class LiveData {
	int eventId;
	int phaseId;
	int boulderId;
	String eventName;
	String phaseName;
	boolean boulderInfoDefinedOnServer;
	ArrayList<ScoreSlot> scoreSlots;
	ListIterator<ScoreSlot> scoreSlotIterator;
	boolean goingForward;
	ArrayList<Round> roundList;
	Iterator<Round> roundIterator;

	
	public LiveData(int eventId, int phaseId)
	{
		roundList = new ArrayList<Round>();
		scoreSlots = new ArrayList<ScoreSlot>();
		this.eventId = eventId;
		this.phaseId = phaseId;
		goingForward = true;
		scoreSlotIterator = scoreSlots.listIterator();
		roundIterator = roundList.iterator();
	}
		
	public LiveData() {
		// TODO Auto-generated constructor stub
	}

	public void fillScoreSlots(int boulderId)
	{
		Iterator<Round> roundIterator;
		roundIterator = roundList.iterator();
		scoreSlots = new ArrayList<ScoreSlot>();

		while (roundIterator.hasNext())
		{
			Round currentRound = roundIterator.next();
			currentRound.reset();
			
			if (currentRound.isEnabled())
			{
				int nrOfBoulders = currentRound.getNrOfBoulders();
				int index = 0;
				
				if (0 == boulderId)
				{
				
					for (index=1;index<=nrOfBoulders;index++)
					{
						while (currentRound.hasNextClimber())
						{
							PolePositionedClimber ppc = currentRound.getNextClimber();
							scoreSlots.add(new ScoreSlot(ppc, index, eventId, phaseId, currentRound.getRoundId(), currentRound.getBoulderPrefix()));
						}	
						currentRound.reset();
					}
				}
				else
				{
					while (currentRound.hasNextClimber())
					{
						PolePositionedClimber ppc = currentRound.getNextClimber();
						scoreSlots.add(new ScoreSlot(ppc, boulderId, eventId, phaseId, currentRound.getRoundId(), currentRound.getBoulderPrefix()));
					}	
					currentRound.reset();					
				}
			}
		}
		
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
		
	public String getPhaseName()
	{
		return phaseName;
	}		
	
	public void addRound(Round round)
	{
		roundList.add(round);
	}
	
	public void removeRound(Round round)
	{
		roundList.remove(round);
	}	
		
	public Round getNext()
	{
		return (roundIterator.next());
	}
		
	public boolean isEmpty()
	{
		return (roundList.isEmpty());
	}
	
	public void sort()
	{
		Collections.sort(roundList, new RoundComparator());	
	}
	
	public void clear()
	{
		roundList = new ArrayList<Round>();
		roundIterator = roundList.iterator();

		scoreSlots = new ArrayList<ScoreSlot>();
		scoreSlotIterator = scoreSlots.listIterator();		

	//	boulderInfoDefinedOnServer = false;
	//	boulderId = 0;		
	}
	
	public void reset()
	{
		if (roundList != null)
		{
			roundIterator = roundList.iterator();
		}
		if (roundList != null)
		{
			scoreSlotIterator = scoreSlots.listIterator();
		}
		goingForward = true;
	}
	
	public boolean hasNextRound()
	{
		return roundIterator.hasNext();
	}

	public Round getNextRound()
	{
		return roundIterator.next();
	}	
	
	public Round getRound(int index)
	{
		return roundList.get(index);
	}
	
	public int getNumberOfScoreSlots()
	{
		return scoreSlots.size();
	}
		
	public boolean hasNextScoreSlot()
	{
		return scoreSlotIterator.hasNext();
	}	
	
	public boolean hasPreviousScoreSlot()
	{
		if ((scoreSlotIterator.nextIndex() == 1) && goingForward) 
		{
			return false;
		}
		else
		{
			return scoreSlotIterator.hasPrevious();
		}
	}	
	
	public ScoreSlot getNextScoreSlot()
	{
		if (!goingForward)
		{
			scoreSlotIterator.next();
			goingForward = true;
		}
		return scoreSlotIterator.next();
	}
	
	public ScoreSlot getPreviousScoreSlot()
	{
		if (goingForward)
		{
			scoreSlotIterator.previous();
			goingForward = false;
		}		
		return scoreSlotIterator.previous();
	}			
}
