package com.pofsoft.t1b1client;

@SuppressWarnings("rawtypes")
public class PolePositionedClimber implements Comparable
{	
	private int polePosition;
	private Climber climber;
	private ActiveScore activeScore;
	
	public PolePositionedClimber(Climber theClimber, int polePosition)
	{
		this.climber = theClimber;
		this.polePosition = polePosition;
		activeScore = new ActiveScore();
	}
	
	public PolePositionedClimber(int startNumber, int polePosition, int sequence, String firstName, String lastName)		
	{
		this.climber = new Climber(startNumber, firstName, lastName);
		this.polePosition = polePosition;
		activeScore = new ActiveScore();
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
	
	public void startedAttempt()
	{
		activeScore.addAttempt();
	}
	
	public void reachedBonus()
	{
		activeScore.setBonusReached();
	}
	
	public boolean bonusReached()
	{
		return activeScore.getBonusReached();
	}
	
	public int attemptsToBonus()
	{
		return activeScore.getScore().getAttemptsToBonus();
	}
	
	public void reachedTop()
	{
		activeScore.setTopReached();
	}
	
	public boolean topReached()
	{
		return activeScore.getTopReached();
	}
	
	public int attemptsToTop()
	{
		return activeScore.getScore().getAttemptsToTop();
	}
	
	public void finished()
	{
		activeScore.setFinished();
	}
	
	public boolean isFinished()
	{
		return activeScore.getFinished();
	}
		
	public void started()
	{
		activeScore.setStarted();
	}
	
	public boolean isStarted()
	{
		return activeScore.getStarted();
	}
	
	public void undo()
	{
		activeScore.undo();
	}
	
	public Score getScore()
	{
		return activeScore.getScore();
	}
	
	public int attempts()
	{
		return activeScore.getAttempts();
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
}
