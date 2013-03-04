package com.pofsoft.t1b1client;

import java.util.Stack;

public class ClimberActionStack {
	public static final int NothingToUndo = 0;
	public static final int AttemptStarted = 1;
	public static final int BonusReached = 2;
	public static final int TopReached = 3;
	public static final int Finished = 4;
	
	Stack<Integer> performedActions;
	
	public ClimberActionStack()
	{
		performedActions = new Stack<Integer>();		
	};
	
	public void addAttempt()
	{
		performedActions.push(AttemptStarted);
	}
	
	public void addBonus()
	{
		performedActions.push(BonusReached);
	}

	public void addTop()
	{
		performedActions.push(TopReached);
	}
	
	public void addFinished()
	{
		performedActions.push(Finished);
	}
	
	public void clear()
	{
		performedActions.clear();
	}
	
	public int undo()
	{
		if (performedActions.isEmpty())
		{
			return NothingToUndo;
		}
		else
		{
			return performedActions.pop();
		}
	}
}
