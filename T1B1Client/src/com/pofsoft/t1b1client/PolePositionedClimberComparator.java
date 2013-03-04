package com.pofsoft.t1b1client;
import java.util.Comparator;

public class PolePositionedClimberComparator implements Comparator<PolePositionedClimber> 
{
	public int compare(PolePositionedClimber arg0, PolePositionedClimber arg1) 
	{
		return arg0.compareTo(arg1);
	}
}