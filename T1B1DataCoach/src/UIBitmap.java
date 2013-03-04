import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class UIBitmap {

	private int xpos;
	private int ypos;
	private int xwidth;
	private int ywidth;
	
	public UIBitmap(BoulderScore theScore, int width, int height, int xpos, int ypos, ScoreBoardJFrame theFrame)
	{
		if (theScore.getScore().getTopReached())
		{			
			JLabel labelTopAttempts = new JLabel();  
			String topAttemptsString = new String("" + theScore.getScore().getAttemptsToTop());
		    Color hashColor = new Color(255,255,255);
		    labelTopAttempts.setText(topAttemptsString);
		    labelTopAttempts.setForeground(hashColor);	
		    
		    labelTopAttempts.setBounds(xpos+(int)(0.6*theFrame.getUIRowCalculator().getRowHeigth()), 
		    		ypos+(int)(0.3*theFrame.getUIRowCalculator().getRowHeigth()), theFrame.getUIRowCalculator().getRowHeigth(), theFrame.getUIRowCalculator().getRowHeigth());		    
		    
		    Font curFont1 = labelTopAttempts.getFont();
		    labelTopAttempts.setFont(new Font(curFont1.getFontName(), curFont1.BOLD, (int) (0.8*(theFrame.getUIRowCalculator().getFontSize()))));    
		    
		    theFrame.addDynamicLabel(labelTopAttempts);			
			
			ImageIcon tickIcon = new ImageIcon("resources/" + height + "/tick2.png");
			JLabel tickLabel = new JLabel();
			tickLabel.setIcon(tickIcon);
			tickLabel.setBounds(xpos, ypos, width, height);
			theFrame.addDynamicLabel(tickLabel);	
			
		}
		else if (theScore.getFinished())
		{
			if (theScore.getScore().getBonusReached())
			{
				JLabel labelBonusAttempts = new JLabel();  
				String bonusAttemptsString = new String("" + theScore.getScore().getAttemptsToBonus());
			    Color hashColor = new Color(255,255,255);
			    labelBonusAttempts.setText(bonusAttemptsString);
			    labelBonusAttempts.setForeground(hashColor);	
			    
			    labelBonusAttempts.setBounds(xpos+(int)(0.6*theFrame.getUIRowCalculator().getRowHeigth()), 
			    		ypos+(int)(0.3*theFrame.getUIRowCalculator().getRowHeigth()), theFrame.getUIRowCalculator().getRowHeigth(), theFrame.getUIRowCalculator().getRowHeigth());		    
			    
			    Font curFont1 = labelBonusAttempts.getFont();
			    labelBonusAttempts.setFont(new Font(curFont1.getFontName(), curFont1.BOLD, (int) (0.8*(theFrame.getUIRowCalculator().getFontSize()))));    
			    
			    theFrame.addDynamicLabel(labelBonusAttempts);		
			    
				ImageIcon starIcon = new ImageIcon("resources/" + (height/2) + "/star.png");
				JLabel starLabel = new JLabel();
				starLabel.setIcon(starIcon);
				//starLabel.setBounds(xpos + (width/2), ypos + (height/2), width/2, height/2);
				starLabel.setBounds(xpos + (width/2), ypos, width/2, height/2);
				theFrame.addDynamicLabel(starLabel);
				
				ImageIcon tickIcon = new ImageIcon("resources/" + height + "/cross.png");
				JLabel tickLabel = new JLabel();
				tickLabel.setIcon(tickIcon);
				tickLabel.setBounds(xpos, ypos, width, height);
				theFrame.addDynamicLabel(tickLabel);	

			}
			else 
			{
		
				ImageIcon tickIcon = new ImageIcon("resources/" + height + "/cross.png");
				JLabel tickLabel = new JLabel();
				tickLabel.setIcon(tickIcon);
				tickLabel.setBounds(xpos, ypos, width, height);
				theFrame.addDynamicLabel(tickLabel);			
			}
		}
		else
		{
	/*		JLabel labelTotalAttempts = new JLabel();  			
			String totalAttemptsString = new String("" + theScore.getScore().getTotalAttempts());
			
		    Color hashColor = new Color(255,255,0);
		    labelTotalAttempts.setText(totalAttemptsString);
		    labelTotalAttempts.setForeground(hashColor);	
	
		    labelTotalAttempts.setBounds( theFrame.getUIRowCalculator().getBoulderIdXPos(theScore.getBoulderId()), 
		    		ypos, theFrame.getUIRowCalculator().getRowHeigth(), theFrame.getUIRowCalculator().getRowHeigth());		    
		    
		    Font curFont1 = labelTotalAttempts.getFont();
		    labelTotalAttempts.setFont(new Font(curFont1.getFontName(), curFont1.BOLD, theFrame.getUIRowCalculator().getFontSize())); 		    
		    
		    
		    theFrame.addDynamicLabel(labelTotalAttempts);	*/	
		    if (theScore.getScore().getBonusReached())
			{
		    	ImageIcon starIcon = new ImageIcon("resources/" + (height/2) + "/star.png");
		    	JLabel starLabel = new JLabel();
		    	starLabel.setIcon(starIcon);
		    	starLabel.setBounds(xpos + (width/2), ypos + (height/2), width/2, height/2);
		    	starLabel.setBounds(xpos + (width/2), ypos, width/2, height/2);
		    	theFrame.addDynamicLabel(starLabel);
		    	
				JLabel labelBonusAttempts = new JLabel();  			
				String bonusAttemptsString = new String("" + theScore.getScore().getAttemptsToBonus());
				
			    Color hashColor = new Color(255,255,255);
			    labelBonusAttempts.setText(bonusAttemptsString);
			    labelBonusAttempts.setForeground(hashColor);	
		
 		    
		    
			    labelBonusAttempts.setBounds(xpos+(int)(0.6*theFrame.getUIRowCalculator().getRowHeigth()), 
			    		ypos+(int)(0.3*theFrame.getUIRowCalculator().getRowHeigth()), theFrame.getUIRowCalculator().getRowHeigth(), theFrame.getUIRowCalculator().getRowHeigth());		    
			    
			    Font curFont1 = labelBonusAttempts.getFont();
			    labelBonusAttempts.setFont(new Font(curFont1.getFontName(), curFont1.BOLD, (int) (0.8*(theFrame.getUIRowCalculator().getFontSize()))));  			    
			    
			    
			    theFrame.addDynamicLabel(labelBonusAttempts);
	    		    	
			}
		}	
	}	
}
