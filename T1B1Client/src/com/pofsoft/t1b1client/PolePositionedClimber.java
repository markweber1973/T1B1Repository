package com.pofsoft.t1b1client;

@SuppressWarnings("rawtypes")
public class PolePositionedClimber implements Comparable
{
	private int eventId;
	private int phaseId;
	private int roundId;
	
	private int polePosition;
	private int sequence;
	private Climber climber;
	private ActiveScore activeScore;
	
	public PolePositionedClimber(Climber theClimber, int polePosition)
	{
		this.climber = theClimber;
		this.polePosition = polePosition;
		activeScore = new ActiveScore();
	}
	
	public PolePositionedClimber(int startNumber, int polePosition, int sequence, String firstName, String lastName, 
			int eventId, int phaseId, int roundId)
	{
		this.climber = new Climber(startNumber, firstName, lastName);
		this.polePosition = polePosition;
		this.sequence = sequence;
		this.eventId = eventId;
		this.phaseId = phaseId;
		this.roundId = roundId;
		activeScore = new ActiveScore();
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
	
	public int getPolePosition()
	{
		return polePosition;
	}
	
	public int getSequence()
	{
		return sequence;
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
		if (sequence == ((PolePositionedClimber)compareTarget).getSequence())
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
		else if (sequence < ((PolePositionedClimber)compareTarget).getSequence())
		{
			return -1;			
		}
		else
		{
			return 1;
		}

	}

}
