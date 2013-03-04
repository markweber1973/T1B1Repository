package com.pofsoft.datastruct;

import com.pofsoft.t1b1client.ClimberActionStack;
import com.pofsoft.t1b1client.Score;

public class ActiveScore {

	private Score score;
	private ClimberActionStack climberActionStack;
	private int nrOfAttempts;
	private boolean finished;
	
	public ActiveScore()
	{
		nrOfAttempts = 0;
		score = new Score();
		finished = false;
		climberActionStack = new ClimberActionStack();
	}

	public void setFinished()
	{
		finished = true;
		climberActionStack.addFinished();
	}
	
	public boolean getFinished()
	{
		return finished;
	}
	
	public void addAttempt()
	{
		if (score.getTopReached()) return;
		handleAddAttempt();
	}
	
	public int getAttempts()
	{
		return nrOfAttempts;
	}
	
	public void setBonusReached()
	{
		if (score.getBonusReached() || score.getTopReached()) 
		{
			return;
		}
		else
		{
			if (nrOfAttempts == 0) 
			{
				handleAddAttempt();
			}
			setBonus();
		}
	}
	
	public int getAttemptsToBonus()
	{
		return score.getAttemptsToBonus();
	}
	
	public boolean getBonusReached()
	{
		return score.getBonusReached();
	}

	public void setTopReached()
	{
		if (score.getTopReached())
		{
			return;
		}
		else
		{			
			if (nrOfAttempts == 0) 
			{
				handleAddAttempt();
			}

			if (score.getBonusReached() == false)
			{
				setBonus();
			}
			setTop();
		}
	}	
	
	public boolean getTopReached()
	{
		return score.getTopReached();
	}

	public int getAttemptsToTop()
	{
		return score.getAttemptsToTop();
	}
	
	public Score getScore()
	{
		return score;
	}
	
	public void undo()
	{
		int lastAction = climberActionStack.undo();
		switch (lastAction)
		{
			case ClimberActionStack.NothingToUndo:
				break;
			case ClimberActionStack.AttemptStarted:
				handleRemoveAttempt();
				break;
			case ClimberActionStack.BonusReached:
				resetBonus();
				break;	
			case ClimberActionStack.Finished:
				handleRemoveFinished();
				break;		
			case ClimberActionStack.TopReached:
				resetTop();
				break;					
			default:
				throw new RuntimeException("Unknown climber action on stack");
		}
	}
	
	
	// Private stuff starts here.
	
	private void resetBonus()
	{
		score.setAttemptsToBonus(0);
		score.resetBonusReached();					
	}
	
	private void setBonus()
	{
		score.setAttemptsToBonus(nrOfAttempts);
		score.setBonusReached();			
		climberActionStack.addBonus();		
	}
	
	private void setTop()
	{
		score.setAttemptsToTop(nrOfAttempts);
		score.setTopReached();			
		climberActionStack.addTop();	
		setFinished();
	}	
	
	private void resetTop()
	{
		score.setAttemptsToTop(0);
		score.resetTopReached();					
	}
	
	private void handleAddAttempt()
	{
		nrOfAttempts++;
		climberActionStack.addAttempt();		
	}
	
	private void handleRemoveAttempt()
	{
		nrOfAttempts--;	
	}
	
	private void handleRemoveFinished()
	{
		finished = false;
	}
	
	
}
