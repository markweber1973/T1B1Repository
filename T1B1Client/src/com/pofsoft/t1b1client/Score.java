package com.pofsoft.t1b1client;

public class Score {
	
	private boolean topped;
	private boolean bonussed;
	private int attemptsToTop;
	private int attemptsToBonus;
		
	public Score()
	{
		this.topped = false;
		this.bonussed = false;
		this.attemptsToTop = 0;
		this.attemptsToBonus = 0;
	}	
	
	public boolean getTopReached(){
		return topped;
	}
	
	public void setTopReached(){
		if (attemptsToTop == 0) throw new RuntimeException("Reaching top without any attempt impossible");
		topped =  true;
	}	
	
	public void resetTopReached(){
		topped =  false;
	}		
	
	public boolean getBonusReached(){
		return bonussed;
	}	

	public void setBonusReached(){
		if (attemptsToBonus == 0) throw new RuntimeException("Reaching bonus without any attempt impossible");
		bonussed =  true;
	}	
	
	public void resetBonusReached(){
		bonussed = false;
	}		
	
	public int getAttemptsToTop(){ 
		return attemptsToTop;
	}
	
	public int getAttemptsToBonus(){
		return attemptsToBonus;
	}
					
	public void setAttemptsToBonus(int attempts)
	{
		if (attempts < 0) throw new IllegalArgumentException("attempts shall be >= 0");
		attemptsToBonus = attempts;	
	}
	
	public void setAttemptsToTop(int attempts)
	{
		if (attempts < 0) throw new IllegalArgumentException("attempts shall be >= 0");
		attemptsToTop = attempts;	
	}	
	
	public String toString()
	{
	    String scoreString = "T";
	    if (topped) scoreString += attemptsToTop;
	    else scoreString += "-";
	    scoreString+= "B";
	    if (bonussed) scoreString += attemptsToBonus;
	    else scoreString += "-";	    
	    return scoreString;
	}
}