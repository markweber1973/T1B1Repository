import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class RankedScoreCard {

	private ScoreCard scoreCard;
	private int rank;
	
	public RankedScoreCard(ScoreCard scoreCard, int rank)
	{
		this.rank = rank;
		this.scoreCard = scoreCard;
	}
	
	public void setRank(int rank)
	{
		this.rank = rank;
	}
	
	public int getRank()
	{
		return rank;
	}
	
	public ScoreCard getScoreCard()
	{
		return scoreCard;
	}
	
	public void setScoreCard(ScoreCard scoreCard)
	{
		this.scoreCard = scoreCard;
	}
	
	public int compare(RankedScoreCard cardToComparteWith)
	{
		if (this.rank == cardToComparteWith.getRank()) return 0;
		else if (this.rank > cardToComparteWith.getRank()) return 1;
		else return -1;
	}
	
	public void displayOnFrame(ScoreBoardJFrame theFrame, int row)
	{
	    if (scoreCard.cardContainsBoulderScores())
	    {
			UIRowCalculator localUICalculator = theFrame.getUIRowCalculator();
			JLabel labelRank = new JLabel();  
	
		    Color textColor = new Color(255,255,255);
		    labelRank.setText("" + rank);
		    labelRank.setForeground(textColor);
		    
		    Font curFont = labelRank.getFont();
		    labelRank.setFont(new Font(curFont.getFontName(), curFont.getStyle(), localUICalculator.getBigFontSize()));
		    labelRank.setBounds(localUICalculator.getRankFieldPos(), localUICalculator.getPosition(row), localUICalculator.getRankFieldWidth(), localUICalculator.getRowHeigth());
		    theFrame.addDynamicLabel(labelRank);	
	    }
		scoreCard.displayOnFrame(row, theFrame);
	}
}
