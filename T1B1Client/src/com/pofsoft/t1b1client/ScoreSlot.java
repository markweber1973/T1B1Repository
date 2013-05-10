package com.pofsoft.t1b1client;

public class ScoreSlot {
	private PolePositionedClimber climber;
	private int boulderId;
	
	public ScoreSlot(PolePositionedClimber climber, int boulderId)
	{
		this.climber = climber;
		this.boulderId = boulderId;
	}
	
	public PolePositionedClimber getClimber()
	{
		return climber;
	}
	
	public int getBoulderId()
	{
		return boulderId;
	}

}
