import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ScoreBoardJFrame extends JFrame //implements ScoreBoardEventListener
{
	JLabel largeBonus;
	JLabel largeTick;

	MySQLAccess dao;
	boolean crossesToShow;
	List<Climber> allClimbers;
	List<BoulderScore> allBoulderScores;
	List<ScoreCard> allScoresCards;
	List<ScoreCard> womenScoreCards;
	List<ScoreCard> menScoreCards;

	
	List<RankedScoreCard> menRankedScoreCards;
	List<RankedScoreCard> womenRankedScoreCards;
	List<RankedScoreCard> allRankedScoreCards;
	
	int totalNrOfMenToDisplay;
	int nrOfMenDisplayed;
	int maxNrOfMenToDisplay;
	int manNrOfScreens;
	int manCurrentScreen;
	
	int totalNrOfWomenToDisplay;
	int nrOfWomenDisplayed;
	int maxNrOfWomenToDisplay;
	int womanNrOfScreens;
	int womanCurrentScreen;
	
	
	boolean menDisplayTwoSets;
	boolean womenDisplayTwoSets;
	

	
	UpdateScoreBoardTimer updateTimer;
	List<JLabel> tickBitmaps;
	List<JLabel> crossBitmaps;
	int fontSize = 0;
	int flagXPos;
	UIRowCalculator rowCalculator;
	
	List<JLabel> staticLabels;
	List<JLabel> currentDynamicLabels;
	List<JLabel> nextDynamicLabels;
	EventInfo eventInfo;
	String roundInfoActiveRound = "";
	String roundInfoToggleRound = "";

	int activePhaseId = 0;
	int activeEventId = 0;
	
	int toggleTimer;
	
	public ScoreBoardJFrame()
	{	    
		
		toggleTimer = 0;
			try {
				dao = new MySQLAccess();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		maxNrOfMenToDisplay = 6;
		maxNrOfWomenToDisplay = 6;
		manNrOfScreens = 1;
		womanNrOfScreens = 1;
		manCurrentScreen = 1;
		womanCurrentScreen = 1;
	    totalNrOfMenToDisplay = 0;
		nrOfMenDisplayed = 0;
		menDisplayTwoSets = true;
		womenDisplayTwoSets = true;
		crossesToShow = true;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Boulder Score Board"); 
		//this.setUndecorated(true);
		//scoreBoard(true);
	//	this.fullscreen();
		this.setLayout(null);
		this.getContentPane().setBackground(Color.black);
		Color backGround = new Color(0);
		this.setBackground(backGround);
		//this.setLocationRelativeTo(null); 
		//this.setSize(1680,1050);
		this.setSize(800,600);

		//Toolkit tk = Toolkit.getDefaultToolkit();  
		
		//int xSize = ((int) tk.getScreenSize().getWidth());  
		//int ySize = ((int) tk.getScreenSize().getHeight()); 
		//rowCalculator = new UIRowCalculator(this.getHeight(), this.getWidth(),12);

	    this.setVisible(true);
	    //tickBitmaps = Collections.synchronizedList(new ArrayList<JLabel>());
	    tickBitmaps = Collections.synchronizedList(new ArrayList<JLabel>());
	    crossBitmaps = Collections.synchronizedList(new ArrayList<JLabel>());
	    staticLabels = Collections.synchronizedList(new ArrayList<JLabel>());
	    currentDynamicLabels = Collections.synchronizedList(new ArrayList<JLabel>());
	    nextDynamicLabels = Collections.synchronizedList(new ArrayList<JLabel>());
    
		allBoulderScores = (new ArrayList<BoulderScore>());
		allScoresCards = (new ArrayList<ScoreCard>());
		womenScoreCards =  (new ArrayList<ScoreCard>());
		menScoreCards =  (new ArrayList<ScoreCard>());

		allRankedScoreCards = (new ArrayList<RankedScoreCard>());
		menRankedScoreCards = (new ArrayList<RankedScoreCard>());
		womenRankedScoreCards = (new ArrayList<RankedScoreCard>());

		allClimbers = (new ArrayList<Climber>());

		try {
			eventInfo = dao.fillEventInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
		try {
			activeEventId = dao.getActiveEventId();
			activePhaseId = dao.getActivePhaseId();
		} catch (Exception e) {	
			e.printStackTrace();
		}

		try {
			dao.fillClimberList(allClimbers, activeEventId, activePhaseId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rowCalculator = new UIRowCalculator(this.getHeight(), this.getWidth(), allClimbers.size());
		rowCalculator.setInternationMode(eventInfo.getInternational());
	    fillStaticLabels();
	    showStaticLabels();		
	    
		updateTimer = new UpdateScoreBoardTimer();
	    updateTimer.setBoard(this);
	    updateTimer.start();
	    
	    repaint();
	}
	
	
	public void fullscreen()
	{
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		gd.setFullScreenWindow(this);
	}
	
	public void update()
	{		
		long startTime = System.currentTimeMillis();
					
		allClimbers.clear();
		allBoulderScores.clear();
		allScoresCards.clear();
		allRankedScoreCards.clear();
		menScoreCards.clear();
		menRankedScoreCards.clear();
		womenScoreCards.clear();
		womenRankedScoreCards.clear();
							
		rowCalculator.setInternationMode(eventInfo.getInternational());
		
		try {
			dao.fillClimberList(allClimbers, activeEventId, activePhaseId);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			dao.fillBoulderScoreList(allBoulderScores, activeEventId, activePhaseId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		int lineZero = 1;
		JLabel labelEvent = new JLabel();  
	    Color textColor = new Color(255,255,255);
	    labelEvent.setText(roundInfoActiveRound);
	    labelEvent.setForeground(textColor);

	    Font curFont = labelEvent.getFont();
	    labelEvent.setFont(new Font(curFont.getFontName(), curFont.getStyle(), rowCalculator.getBigFontSize())); 
	    labelEvent.setBounds(0, rowCalculator.getPosition(lineZero), this.getWidth(), rowCalculator.getRowHeigth());   	
	    addDynamicLabel(labelEvent);			
			    	
		JLabel labelEventA = new JLabel();  
	    labelEventA.setText(roundInfoToggleRound);
	    labelEventA.setForeground(textColor);

	    labelEventA.setFont(new Font(curFont.getFontName(), curFont.getStyle(), rowCalculator.getBigFontSize())); 
	    labelEventA.setBounds(0, rowCalculator.getPosition(8), this.getWidth(), rowCalculator.getRowHeigth());   	
	    addDynamicLabel(labelEventA);		    
	    	    	    
		for (Iterator<Climber> iClimber = allClimbers.iterator(); iClimber.hasNext();) 
		{
			Climber iteratedClimber = (Climber)iClimber.next();
			ScoreCard newScoreCard = new ScoreCard(iteratedClimber, iteratedClimber.getRound());
			for (Iterator<BoulderScore> iBoulderScore = allBoulderScores.iterator(); iBoulderScore.hasNext();) 
			{
				BoulderScore iteratedBoulderScore = (BoulderScore)iBoulderScore.next();
				if (iteratedBoulderScore.getStartNumber() == iteratedClimber.getStartNumber())
				{
					newScoreCard.addToScoreCard(iteratedBoulderScore);
				}
			}
			allScoresCards.add(newScoreCard);			
		}
		
		for (Iterator<ScoreCard> iScoreCard = allScoresCards.iterator(); iScoreCard.hasNext();)
		{
			ScoreCard currentScoreCard = (ScoreCard)iScoreCard.next();
			if (currentScoreCard.getRoundName().equals("Men"))
			{
				menScoreCards.add(currentScoreCard);
			}
			else
			{
				womenScoreCards.add(currentScoreCard);				
			}
		}

		int index = 1;
		ScoreCard previousScoreCard = null;

		Collections.sort(allScoresCards, new ScoreCardComparator());
		Collections.sort(menScoreCards, new ScoreCardComparator());
		Collections.sort(womenScoreCards, new ScoreCardComparator());

		Iterator<ScoreCard> iScoreCard = menScoreCards.iterator();
		while (iScoreCard.hasNext())
		{
			ScoreCard currentMenScoreCard = iScoreCard.next();
			RankedScoreCard newRankedCard;
			if (menRankedScoreCards.isEmpty())
			{
				newRankedCard = new RankedScoreCard(currentMenScoreCard, index);
				menRankedScoreCards.add(newRankedCard);
			}
			else
			{
				if (currentMenScoreCard.getOverallScore().compare(previousScoreCard.getOverallScore()) != 0)
				{
					index++;
				}
				newRankedCard = new RankedScoreCard(currentMenScoreCard, index);
				menRankedScoreCards.add(newRankedCard);
			}
			previousScoreCard = currentMenScoreCard;
		}
		
		totalNrOfMenToDisplay = menRankedScoreCards.size();
		
		if (nrOfMenDisplayed == 0)
		{
			manCurrentScreen = 1;
			if (totalNrOfMenToDisplay <= maxNrOfMenToDisplay)
			{
				for (int displayIndex = 0; displayIndex < totalNrOfMenToDisplay; displayIndex++)
				{
					menRankedScoreCards.get(displayIndex).displayOnFrame(this, displayIndex+2);					
				}
				nrOfMenDisplayed = 0;				
			}				
			else
			{
				for (int displayIndex = 0; displayIndex < maxNrOfMenToDisplay; displayIndex++)
				{
					menRankedScoreCards.get(displayIndex).displayOnFrame(this, displayIndex+2);					
				}	
				nrOfMenDisplayed = maxNrOfMenToDisplay;
			}		
		}
		else
		{
			manCurrentScreen++;
			int nrOfMenLeftToDisplay = totalNrOfMenToDisplay - nrOfMenDisplayed;
			if (nrOfMenLeftToDisplay <= maxNrOfMenToDisplay)
			{
				for (int displayIndex = 0; displayIndex < nrOfMenLeftToDisplay; displayIndex++)
				{
					menRankedScoreCards.get(nrOfMenDisplayed+displayIndex).displayOnFrame(this, displayIndex+2);					
				}
				nrOfMenDisplayed = 0;				
			}
			else
			{
				for (int displayIndex = 0; displayIndex < maxNrOfMenToDisplay; displayIndex++)
				{
					menRankedScoreCards.get(nrOfMenDisplayed+displayIndex).displayOnFrame(this, displayIndex+2);					
				}					
				nrOfMenDisplayed+=maxNrOfMenToDisplay;
			}
		}
		
		
		index = 1;
		iScoreCard = womenScoreCards.iterator();
		while (iScoreCard.hasNext())
		{
			ScoreCard currentWomenScoreCard = iScoreCard.next();
			RankedScoreCard newRankedCard;
			if (womenRankedScoreCards.isEmpty())
			{
				newRankedCard = new RankedScoreCard(currentWomenScoreCard, index);
				womenRankedScoreCards.add(newRankedCard);
			}
			else
			{
				if (currentWomenScoreCard.getOverallScore().compare(previousScoreCard.getOverallScore()) != 0)
				{
					index++;
				}
				newRankedCard = new RankedScoreCard(currentWomenScoreCard, index);
				womenRankedScoreCards.add(newRankedCard);
			}
			previousScoreCard = currentWomenScoreCard;
		}		
		
		totalNrOfWomenToDisplay = womenRankedScoreCards.size();
		
		if (nrOfWomenDisplayed == 0)
		{
			womanCurrentScreen = 1;
			if (totalNrOfWomenToDisplay <= maxNrOfWomenToDisplay)
			{
				for (int displayIndex = 0; displayIndex < totalNrOfWomenToDisplay; displayIndex++)
				{
					womenRankedScoreCards.get(displayIndex).displayOnFrame(this, displayIndex+9);					
				}
				nrOfWomenDisplayed = 0;				
			}				
			else
			{
				for (int displayIndex = 0; displayIndex < maxNrOfWomenToDisplay; displayIndex++)
				{
					womenRankedScoreCards.get(displayIndex).displayOnFrame(this, displayIndex+9);					
				}	
				nrOfWomenDisplayed = maxNrOfWomenToDisplay;
			}		
		}
		else
		{
			womanCurrentScreen++;

			int nrOfWomenLeftToDisplay = totalNrOfWomenToDisplay - nrOfWomenDisplayed;
			if (nrOfWomenLeftToDisplay <= maxNrOfWomenToDisplay)
			{
				for (int displayIndex = 0; displayIndex < nrOfWomenLeftToDisplay; displayIndex++)
				{
					womenRankedScoreCards.get(nrOfWomenDisplayed+displayIndex).displayOnFrame(this, displayIndex+9);					
				}
				nrOfWomenDisplayed = 0;				
			}
			else
			{
				for (int displayIndex = 0; displayIndex < maxNrOfWomenToDisplay; displayIndex++)
				{
					womenRankedScoreCards.get(nrOfWomenDisplayed+displayIndex).displayOnFrame(this, displayIndex+9);					
				}					
				nrOfWomenDisplayed+=maxNrOfWomenToDisplay;
			}
		}		

		manNrOfScreens = (totalNrOfMenToDisplay / maxNrOfMenToDisplay) + 1;
		if (totalNrOfMenToDisplay > maxNrOfMenToDisplay)
		{
			labelEvent = new JLabel();  
		    textColor = new Color(255,255,255);
		    labelEvent.setText("Men ( "+ manCurrentScreen + " of " + manNrOfScreens + " )");
		    labelEvent.setForeground(textColor);
	
		    curFont = labelEvent.getFont();
		    labelEvent.setFont(new Font(curFont.getFontName(), curFont.getStyle(), rowCalculator.getBigFontSize())); 
		    labelEvent.setBounds(0, rowCalculator.getPosition(lineZero), this.getWidth(), rowCalculator.getRowHeigth());   	
		    addDynamicLabel(labelEvent);	
		}

		womanNrOfScreens = (totalNrOfWomenToDisplay / maxNrOfWomenToDisplay) + 1;

		if (totalNrOfWomenToDisplay > maxNrOfWomenToDisplay)
		{
			labelEventA = new JLabel();  
		    labelEventA.setText("Women ( "+ womanCurrentScreen + " of " + womanNrOfScreens + " )");
		    labelEventA.setForeground(textColor);
	
		    labelEventA.setFont(new Font(curFont.getFontName(), curFont.getStyle(), rowCalculator.getBigFontSize())); 
		    labelEventA.setBounds(0, rowCalculator.getPosition(8), this.getWidth(), rowCalculator.getRowHeigth());   	
		    addDynamicLabel(labelEventA);	
		}
		
		
		updateContents();
		repaint();	
		updateTimer.start();
		System.out.println(System.currentTimeMillis() - startTime);
	}
	
	public void handleScoreBoardEvent(/*ScoreBoardEvent event*/)
	{
		System.out.println("Scoreboard received event");
		this.update();
	}

	public UIRowCalculator getUIRowCalculator()
	{
		return rowCalculator;
	}


	public void remove(UIBitmap bitmap) {
		remove(bitmap);		
	}
	
	public void addStaticLabel(JLabel staticLabel)
	{
		staticLabels.add(staticLabel);
		
	}
	
	public void addDynamicLabel(JLabel dynamicLabel)
	{
		nextDynamicLabels.add(dynamicLabel);
	}
	
	public void updateContents()
	{
		for (Iterator<JLabel> i = currentDynamicLabels.iterator(); i.hasNext();) {
			JLabel currentDynamicLabel = (JLabel) i.next();
			remove(currentDynamicLabel);
		}
		currentDynamicLabels.clear();
		
		for (Iterator<JLabel> i = nextDynamicLabels.iterator(); i.hasNext();) {
			JLabel nextDynamicLabel = (JLabel) i.next();
			add(nextDynamicLabel);
			currentDynamicLabels.add(nextDynamicLabel);
		}
		nextDynamicLabels.clear();
		
	}
	
	private void fillStaticLabels()
	{		
		int lineZero = 0;
		int lineOne = 1;

	    ImageIcon largeBonusIcon = new ImageIcon("resources/" + rowCalculator.getRowHeigth() + "/star.png");
		largeBonus = new JLabel();  
		largeBonus.setIcon(largeBonusIcon);
		largeBonus.setBounds(rowCalculator.getBonusIconXPos(), rowCalculator.getPosition(lineZero), rowCalculator.getRowHeigth(), rowCalculator.getRowHeigth());
		addStaticLabel(largeBonus);	
	
		JLabel labelHashBonusAttempts = new JLabel();  
		String hashString = new String("#");
	    Color hashColor = new Color(255,255,255);
	    labelHashBonusAttempts.setText(hashString);
	    labelHashBonusAttempts.setForeground(hashColor);	
	    labelHashBonusAttempts.setBounds(rowCalculator.getBonusIconXPos()+(int)(0.3*rowCalculator.getRowHeigth()), 
	    		rowCalculator.getPosition(lineZero)+(int)(0.2*rowCalculator.getRowHeigth()), rowCalculator.getRowHeigth(), rowCalculator.getRowHeigth());

	    Font curFont1 = labelHashBonusAttempts.getFont();
	    labelHashBonusAttempts.setFont(new Font(curFont1.getFontName(), curFont1.BOLD, rowCalculator.getFontSize()));    
	    
	    addStaticLabel(labelHashBonusAttempts);
		
		
		ImageIcon tickIcon = new ImageIcon("resources/" + rowCalculator.getRowHeigth() + "/Tick2.png");  
		largeTick = new JLabel();  
		largeTick.setIcon(tickIcon);
		largeTick.setBounds(rowCalculator.getTopIconXPos(), rowCalculator.getPosition(lineZero), rowCalculator.getRowHeigth(), rowCalculator.getRowHeigth());
		addStaticLabel(largeTick);
	
		JLabel labelHashTopAttempts = new JLabel();  
		
	   
		labelHashTopAttempts.setText(hashString);
		labelHashTopAttempts.setForeground(hashColor);	
		labelHashTopAttempts.setBounds(rowCalculator.getTopIconXPos()+(int)(0.5*rowCalculator.getRowHeigth()), 
	    		rowCalculator.getPosition(lineZero)+(int)(0.2*rowCalculator.getRowHeigth()), rowCalculator.getRowHeigth(), rowCalculator.getRowHeigth());
	    
	    labelHashTopAttempts.setFont(new Font(curFont1.getFontName(), curFont1.BOLD, rowCalculator.getFontSize()));    
	    
	    addStaticLabel(labelHashTopAttempts);		
		
		for (int boulderNr=1; boulderNr<=4;boulderNr++)
		{
    		JLabel labelBoulderNumber = new JLabel();  
    		String testString = new String("" + boulderNr);
    	    Color textColor = new Color(255,255,255);
    	    labelBoulderNumber.setText(testString);
    	    labelBoulderNumber.setForeground(textColor);
	  
    	    Font curFont = labelBoulderNumber.getFont();
    	    labelBoulderNumber.setFont(new Font(curFont.getFontName(), curFont.getStyle(), rowCalculator.getBigFontSize())); 
    	    labelBoulderNumber.setBounds(rowCalculator.getBoulderIdXPos(boulderNr), rowCalculator.getPosition(lineZero), rowCalculator.getNameFieldWidth(), rowCalculator.getRowHeigth());   	
    	    addStaticLabel(labelBoulderNumber);
		}

	    				
	}
	
	private void showStaticLabels()
	{
		for (Iterator<JLabel> i = staticLabels.iterator(); i.hasNext();) {
			JLabel currentStaticLabel = (JLabel) i.next();
			add(currentStaticLabel);
		}		
	}
}