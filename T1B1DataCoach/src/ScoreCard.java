

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ScoreCard {
	private Climber climber;
	private List<BoulderScore> hitlist;
	private OverallScore overallScore;
	
	public ScoreCard(Climber theClimber)
	{
		overallScore = new OverallScore();
		hitlist = Collections.synchronizedList(new ArrayList<BoulderScore>());
		climber = theClimber;
	}
	
	public void addToScoreCard(BoulderScore theScore)
	{
		if (theScore.getScore().getBonusReached())
		{
			overallScore.addSuccesfullBonus(theScore.getScore().getAttemptsToBonus());
		}
		if (theScore.getScore().getTopReached())
		{
			overallScore.addSuccesfullTop(theScore.getScore().getAttemptsToTop());
		}
		hitlist.add(theScore);
	}
	
	public int getNumberOfTops()
	{
		return (overallScore.getNumberOfTops());
	}
	
	public int getNumberOfTopAttemtps()
	{
		return (overallScore.getAttemptsToTops());
	}
	
	public int getNumberOfBonusses()
	{
		return (overallScore.getNumberOfBonusses());	
	}
	
	public int getNumberOfBonusAttemtps()
	{
		return (overallScore.getAttemptsToBonusses());
	}
	
	public OverallScore getOverallScore()
	{
		return overallScore;
	}
	
	public int compare(ScoreCard scoreToCompareWith) 
	{
		int scoreCardCompare = 0;
		
		OverallScore overallScore = 
			new OverallScore(getNumberOfTops(), getNumberOfTopAttemtps(), 
				getNumberOfBonusses(), getNumberOfBonusAttemtps());
		
		scoreCardCompare = overallScore.compare(scoreToCompareWith.getOverallScore());
		
		if (scoreCardCompare == 0)
		{
			if (climber.getPolePosition() == scoreToCompareWith.climber.getPolePosition())
			{
				return 0;
			}
			else if (climber.getPolePosition() > scoreToCompareWith.climber.getPolePosition())
			{
				return 1;
			}
			else
			{
				return -1;
			}
		}
		else
		{
			return scoreCardCompare;
		}
	}
	
	public String getClimberName()
	{
		return climber.getName();
	}
		
	public void displayOnFrame(int row, ScoreBoardJFrame targetFrame)
	{
		for (Iterator<BoulderScore> i = hitlist.iterator(); i.hasNext();) {
			BoulderScore currentBoulderScore = (BoulderScore) i.next();
			currentBoulderScore.displayOnFrame(row, targetFrame);
		}
		overallScore.displayOnFrame(row, targetFrame);
		climber.displayOnFrame(row, targetFrame);
	}
	
	public boolean cardContainsBoulderScores()
	{
		for (Iterator<BoulderScore> i = hitlist.iterator(); i.hasNext();) {
			BoulderScore currentBoulderScore = (BoulderScore) i.next();
			if (currentBoulderScore.getFinished()) return true;
		}
		return false;
	}
}
