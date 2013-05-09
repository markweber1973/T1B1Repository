package com.pofsoft.t1b1client;
import java.util.Comparator;

public class ActiveScoreComparator implements Comparator<ActiveScore> 
{
	public int compare(ActiveScore arg0, ActiveScore arg1) 
	{
		return arg0.compareTo(arg1);
	}
}