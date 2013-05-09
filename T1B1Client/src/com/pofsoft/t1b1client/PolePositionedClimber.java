package com.pofsoft.t1b1client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

@SuppressWarnings("rawtypes")
public class PolePositionedClimber implements Comparable
{	
	private int polePosition;
	private Climber climber;
	private ArrayList<ActiveScore> activeScore;
	
	public PolePositionedClimber(Climber theClimber, int polePosition, int nrOfBoulders)
	{
		this.climber = theClimber;
		this.polePosition = polePosition;
		activeScore = new ArrayList<ActiveScore>(nrOfBoulders);

		int index = 0;
		for (index = 0; index<nrOfBoulders; index++)
		{
			activeScore.add(index, new ActiveScore());		
		}
	}
	
	public PolePositionedClimber(int startNumber, int polePosition, String firstName, String lastName)		
	{
		this.climber = new Climber(startNumber, firstName, lastName);
		this.polePosition = polePosition;
		activeScore = new ArrayList<ActiveScore>();
	}
		
	private ActiveScore getActiveScoreForBoulder(int boulderId) throws Exception
	{
		System.out.print("getActiveScoreForBoulder=" + boulderId);
		throwExceptionWhenIllegalBoulderId(boulderId);
		
		int index = boulderId-1;
		return activeScore.get(index);
	}
	
	public int getPolePosition()
	{
		return polePosition;
	}
			
	public int getStartNumber()
	{
		return climber.startNumber();
	}
	
	public Climber getClimber()
	{
		return climber;
	}
	
	public void startedAttempt(int boulderId) throws Exception
	{
		getActiveScoreForBoulder(boulderId).addAttempt();		
	}
	
	public void reachedBonus(int boulderId) throws Exception
	{
		getActiveScoreForBoulder(boulderId).setBonusReached();
	}
	
	public boolean bonusReached(int boulderId) throws Exception
	{
		return getActiveScoreForBoulder(boulderId).getBonusReached();
	}
	
	public int attemptsToBonus(int boulderId) throws Exception
	{
		return getActiveScoreForBoulder(boulderId).getAttemptsToBonus();
	}
	
	public void reachedTop(int boulderId) throws Exception
	{
		getActiveScoreForBoulder(boulderId).setTopReached();
	}
	
	public boolean topReached(int boulderId) throws Exception
	{
		return getActiveScoreForBoulder(boulderId).getTopReached();
	}
	
	public int attemptsToTop(int boulderId) throws Exception
	{
		return getActiveScoreForBoulder(boulderId).getAttemptsToTop();
	}
	
	public void finished(int boulderId) throws Exception
	{
		getActiveScoreForBoulder(boulderId).setFinished();
	}
	
	public boolean isFinished(int boulderId) throws Exception
	{
		return getActiveScoreForBoulder(boulderId).getFinished();
	}
		
	public void started(int boulderId) throws Exception
	{
		getActiveScoreForBoulder(boulderId).setStarted();
	}
	
	public boolean isStarted(int boulderId) throws Exception
	{
		return getActiveScoreForBoulder(boulderId).getStarted();
	}
	
	public void undo(int boulderId) throws Exception
	{
		getActiveScoreForBoulder(boulderId).undo();
	}
	
	public Score getScore(int boulderId) throws Exception
	{
		return getActiveScoreForBoulder(boulderId).getScore();
	}
	
	public int attempts(int boulderId) throws Exception
	{
		return getActiveScoreForBoulder(boulderId).getAttempts();
	}
	
	public String firstName()
	{
		return climber.firstName();
	}
	
	public String lastName()
	{
		return climber.lastName();
	}		
	
	@Override
	public int compareTo(Object compareTarget)
	{
		if (polePosition < ((PolePositionedClimber)compareTarget).getPolePosition())
		{
			return (-1);
		}
		else if (polePosition == ((PolePositionedClimber)compareTarget).getPolePosition())
		{
			return (0);
		}
		else
		{
			return 1;
		}			
	}
	
	private void throwExceptionWhenIllegalBoulderId(int boulderId) throws Exception
	{
		if (boulderId <= 0)
		{
			throw new Exception("Boulder Id <= 0 is invalid");
		}		
	}
}
