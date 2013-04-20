package com.pofsoft.t1b1client;
import java.util.Comparator;

public class RoundComparator implements Comparator<Round> 
{
	public int compare(Round arg0, Round arg1) 
	{
		return arg0.compareTo(arg1);
	}
}
