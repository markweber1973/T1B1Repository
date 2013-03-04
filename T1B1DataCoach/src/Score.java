import javax.swing.JFrame;


public class Score {
	
	private boolean topped;
	private boolean bonussed;
	private int attemptsToTop;
	private int attemptsToBonus;
	private int totalAttempts;
	
	public Score(boolean topped, int attemptsToTop, boolean bonussed, int attemptsToBonus)
	{
		this.topped = topped;
		this.bonussed = bonussed;
		this.attemptsToTop = attemptsToTop;
		this.attemptsToBonus = attemptsToBonus;
		totalAttempts = 0;
	}
	
	public Score(boolean topped, int attemptsToTop, boolean bonussed, int attemptsToBonus, int totalAttempts)
	{
		this.topped = topped;
		this.bonussed = bonussed;
		this.attemptsToTop = attemptsToTop;
		this.attemptsToBonus = attemptsToBonus;
		this.totalAttempts = totalAttempts;
	}
	
	public boolean getTopReached(){
		return topped;
	}
	
	public boolean getBonusReached(){
		return bonussed;
	}	
	
	public int getAttemptsToTop(){
		return attemptsToTop;
	}
	
	public int getAttemptsToBonus(){
		return attemptsToBonus;
	}		
	
	public int getTotalAttempts(){
		return totalAttempts;
	}
	
	public void log()
	{
		System.out.println("topped: " + topped);
		System.out.println("attemptsToTop: " + attemptsToTop);
		System.out.println("bonussed: " + bonussed);
		System.out.println("attemptsToBonus: " + attemptsToBonus);
		System.out.println("totalAttempts: " + totalAttempts);
	}
		
}
