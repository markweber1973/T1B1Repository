package com.pofsoft.t1b1client;

import java.util.Collections;
import java.util.Vector;

public class RoundList {
	private Vector<Round> roundList;
	private int currentIndex;
	
	public RoundList()
	{
		roundList = new Vector<Round>();
		currentIndex = 0;
	}
	
	public void add(Round round)
	{
		roundList.add(round);
		Collections.sort(roundList, new RoundComparator());
	}
		
	public Vector<Round> getList()
	{
		return roundList;
	}

	public Round getFirst()
	{
		Round returnValue = null;
		if (roundList.isEmpty()) 
		{
			returnValue = null;
		}
		else
		{
			returnValue = roundList.elementAt(0);
		}
		return returnValue;
	}	
	
	public boolean isEmpty()
	{
		return roundList.isEmpty();
	}
	
	public Round getCurrent()
	{
		Round returnValue = null;
		if (roundList.isEmpty()) 
		{
			returnValue = null;
		}
		else
		{
			returnValue = roundList.elementAt(currentIndex);
		}
		return returnValue;
	}

	public boolean hasNext()
	{
		return ((currentIndex+1) < roundList.size());
	}
	
	public Round getNext()
	{
		Round returnValue = null;
		if (roundList.isEmpty()) 
		{
			returnValue = null;
		}
		else
	    {
			if (hasNext()) 
			{
				currentIndex++;
				returnValue = roundList.elementAt(currentIndex);
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
	
	public Round getPrevious()
	{
		Round returnValue = null;
		if (roundList.isEmpty()) 
		{
			returnValue = null;
		}
		else
	    {
			if (hasPrevious()) 
			{
				currentIndex--;
				returnValue = roundList.elementAt(currentIndex);
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
		Collections.sort(roundList, new RoundComparator());		
	}	
	
	public void clear()
	{
		roundList.clear();
	}
	
	public void reset()
	{
		currentIndex = 0;
	}
	
	public int getSize()
	{
		return roundList.size();
	}
	
	public Round getRound(int index)
	{
		return roundList.get(index);
	}
}
