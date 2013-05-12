
package com.pofsoft.t1b1client;
import android.app.Application;

public class MatchData extends Application {	
	LiveData liveData;

	public MatchData(int eventId, int phaseId)
	{
		liveData = new LiveData(eventId, phaseId);
	}
			
	public MatchData()
	{
		liveData = new LiveData();
	}
	
	public void fillScoreSlots()
	{
        liveData.fillScoreSlots();
	}
	
	public void setBoulderInfoDefinedOnServer()
	{
		liveData.setBoulderInfoDefinedOnServer();
	}

	public void resetBoulderInfoDefinedOnServer()
	{
		liveData.resetBoulderInfoDefinedOnServer();
	}	
	
	public void setBoulderId(int boulderId)
	{
		liveData.setBoulderId(boulderId);
	}
	
	public int getBoulderId()
	{
		return liveData.getBoulderId();
	}
		
	public void setEventId(int eventId)
	{
		liveData.setEventId(eventId);
	}

	public void setPhaseId(int phaseId)
	{
		liveData.setPhaseId(phaseId);
	}	

	public void setEventName(String name)
	{
		liveData.setEventName(name);
	}

	public void setPhaseName(String name)
	{
		liveData.setPhaseName(name);
	}		
	
	public int getEventId()
	{
		return liveData.getEventId();
	}	
		
	public int getPhaseId()
	{
		return liveData.getPhaseId();
	}		
		
	public String getPhaseName()
	{
		return liveData.getPhaseName();
	}		
	
	public void addRound(Round round)
	{
		liveData.addRound(round);
	}
	
	public void removeRound(Round round)
	{
		liveData.removeRound(round);
	}	
			
	public boolean isEmpty()
	{
		return (liveData.isEmpty());
	}
	
	public void sort()
	{
		liveData.sort();
	}
	
	public void clear()
	{
		liveData.clear();
	}
	
	public void reset()
	{
		liveData.reset();
	}
	
	public boolean hasNextRound()
	{
		return liveData.hasNextRound();
	}
	
	public Round getNextRound()
	{
		return liveData.getNextRound();
	}
		
	public boolean hasNextScoreSlot()
	{
		return liveData.hasNextScoreSlot();
	}	
	
	public boolean hasPreviousScoreSlot()
	{
		return liveData.hasPreviousScoreSlot();
	}	
	
	public ScoreSlot getNextScoreSlot()
	{
		return liveData.getNextScoreSlot();
	}		
	
	public ScoreSlot getPreviousScoreSlot()
	{
		return liveData.getPreviousScoreSlot();
	}			
	
}
	