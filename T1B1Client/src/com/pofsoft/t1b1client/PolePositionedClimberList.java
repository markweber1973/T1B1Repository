package com.pofsoft.t1b1client;

import java.util.Collections;
import java.util.Vector;

public class PolePositionedClimberList {
	private Vector<PolePositionedClimber> startList;
	private int currentIndex;
	
	public PolePositionedClimberList()
	{
		startList = new Vector<PolePositionedClimber>();
		currentIndex = 0;
	}
	
	public void add(PolePositionedClimber climber)
	{
		startList.add(climber);
		Collections.sort(startList, new PolePositionedClimberComparator());
	}
		
	public Vector<PolePositionedClimber> getList()
	{
		return startList;
	}

	public PolePositionedClimber getFirst()
	{
		PolePositionedClimber returnValue = null;
		if (startList.isEmpty()) 
		{
			returnValue = null;
		}
		else
		{
			returnValue = startList.elementAt(0);
		}
		return returnValue;
	}	
	
	public boolean isEmpty()
	{
		return startList.isEmpty();
	}
	
	public PolePositionedClimber getCurrent()
	{
		PolePositionedClimber returnValue = null;
		if (startList.isEmpty()) 
		{
			returnValue = null;
		}
		else
		{
			returnValue = startList.elementAt(currentIndex);
		}
		return returnValue;
	}

	public boolean hasNext()
	{
		return ((currentIndex+1) < startList.size());
	}
	
	public PolePositionedClimber getNext()
	{
		PolePositionedClimber returnValue = null;
		if (startList.isEmpty()) 
		{
			returnValue = null;
		}
		else
	    {
			if (hasNext()) 
			{
				currentIndex++;
				returnValue = startList.elementAt(currentIndex);
			}
			else
			{
				returnValue = null;
			}
		}
		return returnValue;
	}
	
	public boolean hasPrevious()
	{
		return (currentIndex > 0);
	}
	
	public PolePositionedClimber getPrevious()
	{
		PolePositionedClimber returnValue = null;
		if (startList.isEmpty()) 
		{
			returnValue = null;
		}
		else
	    {
			if (hasPrevious()) 
			{
				currentIndex--;
				returnValue = startList.elementAt(currentIndex);
			}
			else
			{
				returnValue = null;
			}
		}
		return returnValue;
	}
	
	public void sort()
	{
		Collections.sort(startList, new PolePositionedClimberComparator());		
	}	
	
	public void clear()
	{
		startList.clear();
	}
	
	public void reset()
	{
		currentIndex = 0;
	}
}
