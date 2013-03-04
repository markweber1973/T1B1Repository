import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;



public class OverallScore {
	private int numberOfTops;
	private int attemptsToTops;	
	private int numberOfBonusses;
	private int attemptsToBonusses;

	public OverallScore(int tops, int atotops, int bonusses, int atobonusses)
	{
		numberOfTops = tops;
		attemptsToTops = atotops;
		numberOfBonusses = bonusses;
		attemptsToBonusses = atobonusses;
	}
	
	public OverallScore()
	{
		numberOfTops = 0;
		attemptsToTops = 0;
		numberOfBonusses = 0;
		attemptsToBonusses = 0;
	}
	
	public void addSuccesfullTop(int nrOfAttemtps)
	{
		numberOfTops++;
		attemptsToTops += nrOfAttemtps;
	}
	
	public void addSuccesfullBonus(int nrOfAttemtps)
	{
		numberOfBonusses++;
		attemptsToBonusses += nrOfAttemtps;
	}	
	
	int getNumberOfTops() 
	{
		return numberOfTops;
	}
	
	int getAttemptsToTops() 
	{
		return attemptsToTops;
	}
	
	int getNumberOfBonusses() 
	{
		return numberOfBonusses;
	}
	
	int getAttemptsToBonusses() 
	{
		return attemptsToBonusses;
	}
	
	private int compareScore(int nrOfHits1, int nrOfAttempts1, int nrOfHits2, int nrOfAttempts2)
	{
		if ((nrOfHits1 == 0) && (nrOfHits2 == 0)) 
		{
			return 0;
		}
		else if (nrOfHits1 < nrOfHits2) 
		{
			return 1;
		}
		else if (nrOfHits1 > nrOfHits2)
		{
			return -1;
		}
		else 
		{
			if (nrOfAttempts1 > nrOfAttempts2)
			{
				return 1;
			}
			else if (nrOfAttempts1 < nrOfAttempts2)
			{
				return -1;
			}
			else
			{
				return 0;
			}
		}
	}
	
	public int compare(OverallScore scoreToCompareWith) 
	{
		int compareResult = 
			compareScore(numberOfTops, attemptsToTops, 
					scoreToCompareWith.getNumberOfTops(), scoreToCompareWith.getAttemptsToTops());
		
		if (compareResult == 0)
		{
			compareResult = compareScore(numberOfBonusses, attemptsToBonusses, 
					scoreToCompareWith.getNumberOfBonusses(), scoreToCompareWith.getAttemptsToBonusses());
		}
		
		return compareResult;
	}
		
	public String toString() {
		String overallScore = new String("");
		
		if (numberOfTops > 0) overallScore += numberOfTops + "T" + attemptsToTops;
		else overallScore += "-";
		if (numberOfBonusses > 0) overallScore += " " + numberOfBonusses + "B" + attemptsToBonusses;
		else overallScore += "-";
		
		return overallScore;
	}
	
	public void displayOnFrame(int row, ScoreBoardJFrame targetFrame)
	{
		UIRowCalculator localCalculator = targetFrame.getUIRowCalculator();
		
		
		if (attemptsToTops > 0)
		{
			JLabel labelTopAttempts = new JLabel();  
			String topString = new String("" + attemptsToTops);
		    Color topColor = new Color(255,255,255);
		    labelTopAttempts.setText(topString);
		    labelTopAttempts.setForeground(topColor);


		    
		    Font curFont = labelTopAttempts.getFont();
		    labelTopAttempts.setFont(new Font(curFont.getFontName(), curFont.BOLD, localCalculator.getBigFontSize()));
		    labelTopAttempts.setBounds(localCalculator.getTopAttemptsPos(), localCalculator.getPosition(row), localCalculator.getRowHeigth(), localCalculator.getRowHeigth());
		    labelTopAttempts.setHorizontalAlignment(4);
		    targetFrame.addDynamicLabel(labelTopAttempts);
		}
		
	    if (attemptsToBonusses > 0)
	    {
			JLabel labelBonusAttempts = new JLabel();  
			String bonusString = new String("" + attemptsToBonusses);
		    Color bonusColor = new Color(255,255,255);
		    labelBonusAttempts.setText(bonusString);
		    labelBonusAttempts.setForeground(bonusColor);
		    Font curFont = labelBonusAttempts.getFont();
		    labelBonusAttempts.setFont(new Font(curFont.getFontName(), curFont.getStyle(), localCalculator.getBigFontSize()));
		    labelBonusAttempts.setBounds(localCalculator.getBonusAttemptsPos(), localCalculator.getPosition(row), localCalculator.getRowHeigth(), localCalculator.getRowHeigth());
		    labelBonusAttempts.setHorizontalAlignment(4);	   
		    targetFrame.addDynamicLabel(labelBonusAttempts);
	    }
	}
}
