import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
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
	List<RankedScoreCard> allRankedScoreCards;
	
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
	String roundInfo = "";
	
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
		
		crossesToShow = true;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Boulder Score Board"); 
		//scoreBoard(true);
		this.fullscreen();
		this.setLayout(null);
		Color backGround = new Color(0);
		this.setBackground(backGround);
		this.setLocationRelativeTo(null); 
		this.setSize(800,600);
		
		//Toolkit tk = Toolkit.getDefaultToolkit();  
		
		//int xSize = ((int) tk.getScreenSize().getWidth());  
		//int ySize = ((int) tk.getScreenSize().getHeight()); 
		//rowCalculator = new UIRowCalculator(this.getHeight(), this.getWidth(),12);
		updateTimer = new UpdateScoreBoardTimer();
	    updateTimer.setBoard(this);
	    updateTimer.start();
	    this.setVisible(true);
	    //tickBitmaps = Collections.synchronizedList(new ArrayList<JLabel>());
	    tickBitmaps = Collections.synchronizedList(new ArrayList<JLabel>());
	    crossBitmaps = Collections.synchronizedList(new ArrayList<JLabel>());
	    staticLabels = Collections.synchronizedList(new ArrayList<JLabel>());
	    currentDynamicLabels = Collections.synchronizedList(new ArrayList<JLabel>());
	    nextDynamicLabels = Collections.synchronizedList(new ArrayList<JLabel>());
    
		//allClimbers = (new ArrayList<Climber>());
		allBoulderScores = (new ArrayList<BoulderScore>());
		allScoresCards = (new ArrayList<ScoreCard>());
		allRankedScoreCards = (new ArrayList<RankedScoreCard>());
		allClimbers = (new ArrayList<Climber>());

		try {
			eventInfo = dao.fillEventInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
		try {
			roundInfo = dao.fillRoundInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
	/*	try {
			dao.fillClimberList(allClimbers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		try {
			dao.fillBoulderScoreList(allBoulderScores);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}*/
		
		rowCalculator = new UIRowCalculator(this.getHeight(), this.getWidth(), allClimbers.size());
		rowCalculator.setInternationMode(eventInfo.getInternational());
	    fillStaticLabels();
	    showStaticLabels();		
		
	    repaint();
	}
	
	
	public void fullscreen()
	{
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		gd.setFullScreenWindow(this);
	}
	
	public void update()
	{		
		toggleTimer++;

		int toggleRound = 0;
		int activeRound = 0;
		int showRound = 0;
		try {
			activeRound = dao.getActiveRoundId();
			toggleRound = dao.getToggleRoundId();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		if (toggleTimer < 10)
		{
			showRound = activeRound;
		}
		else if (toggleTimer < 20)
		{
			if (activeRound == toggleRound)
			{
				showRound = activeRound;
			}
			else
			{
				showRound = toggleRound;
			}
		}
		else
		{
			toggleTimer = 0;
			showRound = activeRound;
		}
				
		allClimbers.clear();
		allBoulderScores.clear();
		allScoresCards.clear();
		allRankedScoreCards.clear();

	
		
		try {
			eventInfo = dao.fillEventInfo();
		} catch (Exception e) {			
			e.printStackTrace();
		}
					
		rowCalculator.setInternationMode(eventInfo.getInternational());
		
		try {
			dao.fillClimberList(allClimbers, showRound);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			dao.fillBoulderScoreList(allBoulderScores, showRound);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		try {
			roundInfo = dao.fillRoundInfo(showRound);
		} catch (Exception e) {
			e.printStackTrace();
		}

		int lineZero = 0;
		JLabel labelEvent = new JLabel();  
	    Color textColor = new Color(255,255,255);
	    labelEvent.setText(eventInfo.getName() + ", " + roundInfo);
	    labelEvent.setForeground(textColor);

	    Font curFont = labelEvent.getFont();
	    labelEvent.setFont(new Font(curFont.getFontName(), curFont.getStyle(), rowCalculator.getBigFontSize())); 
	    labelEvent.setBounds(rowCalculator.getPosition(lineZero), rowCalculator.getPosition(lineZero), this.getWidth(), rowCalculator.getRowHeigth());   	
	    addDynamicLabel(labelEvent);			
		
		for (Iterator<Climber> iClimber = allClimbers.iterator(); iClimber.hasNext();) 
		{
			Climber iteratedClimber = (Climber)iClimber.next();
			ScoreCard newScoreCard = new ScoreCard(iteratedClimber);
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
				
		Collections.sort(allScoresCards, new ScoreCardComparator());
		
		ScoreCard previousScoreCard = null;
		int index = 1;
		int lineNumber = 2;

	    
		for (Iterator<ScoreCard> i = allScoresCards.iterator(); i.hasNext();) 
		{
			ScoreCard currentScoreCard = (ScoreCard) i.next();	
			RankedScoreCard newRankedCard;
			
			if (allRankedScoreCards.isEmpty())
			{
				newRankedCard = new RankedScoreCard(currentScoreCard, index);
				allRankedScoreCards.add(newRankedCard);
			}
			else
			{
				if (currentScoreCard.getOverallScore().compare(previousScoreCard.getOverallScore()) != 0)
				{
					index++;	
				}
				newRankedCard = new RankedScoreCard(currentScoreCard, index);
				allRankedScoreCards.add(newRankedCard);
				
			}
			previousScoreCard = currentScoreCard;
			newRankedCard.displayOnFrame(this, lineNumber);
			lineNumber++;
			
		}		
		updateContents();
		repaint();	
		updateTimer.start();
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
		// TODO Auto-generated method stub
		
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
		largeBonus.setBounds(rowCalculator.getBonusIconXPos(), rowCalculator.getPosition(lineOne), rowCalculator.getRowHeigth(), rowCalculator.getRowHeigth());
		addStaticLabel(largeBonus);	
	
		JLabel labelHashBonusAttempts = new JLabel();  
		String hashString = new String("#");
	    Color hashColor = new Color(255,255,255);
	    labelHashBonusAttempts.setText(hashString);
	    labelHashBonusAttempts.setForeground(hashColor);	
	    labelHashBonusAttempts.setBounds(rowCalculator.getBonusIconXPos()+(int)(0.3*rowCalculator.getRowHeigth()), 
	    		rowCalculator.getPosition(lineOne)+(int)(0.2*rowCalculator.getRowHeigth()), rowCalculator.getRowHeigth(), rowCalculator.getRowHeigth());

	    Font curFont1 = labelHashBonusAttempts.getFont();
	    labelHashBonusAttempts.setFont(new Font(curFont1.getFontName(), curFont1.BOLD, rowCalculator.getFontSize()));    
	    
	    addStaticLabel(labelHashBonusAttempts);
		
		
		ImageIcon tickIcon = new ImageIcon("resources/" + rowCalculator.getRowHeigth() + "/Tick2.png");  
		largeTick = new JLabel();  
		largeTick.setIcon(tickIcon);
		largeTick.setBounds(rowCalculator.getTopIconXPos(), rowCalculator.getPosition(lineOne), rowCalculator.getRowHeigth(), rowCalculator.getRowHeigth());
		addStaticLabel(largeTick);
	
		JLabel labelHashTopAttempts = new JLabel();  
		
	   
		labelHashTopAttempts.setText(hashString);
		labelHashTopAttempts.setForeground(hashColor);	
		labelHashTopAttempts.setBounds(rowCalculator.getTopIconXPos()+(int)(0.5*rowCalculator.getRowHeigth()), 
	    		rowCalculator.getPosition(lineOne)+(int)(0.2*rowCalculator.getRowHeigth()), rowCalculator.getRowHeigth(), rowCalculator.getRowHeigth());

	    
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
    	    labelBoulderNumber.setBounds(rowCalculator.getBoulderIdXPos(boulderNr), rowCalculator.getPosition(lineOne), rowCalculator.getNameFieldWidth(), rowCalculator.getRowHeigth());   	
    	    addStaticLabel(labelBoulderNumber);
		}
		
//		JLabel labelEvent = new JLabel();  
//	    Color textColor = new Color(255,255,255);
//	    labelEvent.setText(eventInfo.getName() + ", " + roundInfo);
//	    labelEvent.setForeground(textColor);

//	    Font curFont = labelEvent.getFont();
//	    labelEvent.setFont(new Font(curFont.getFontName(), curFont.getStyle(), rowCalculator.getBigFontSize())); 
//	    labelEvent.setBounds(rowCalculator.getPosition(lineZero), rowCalculator.getPosition(lineZero), this.getWidth(), rowCalculator.getRowHeigth());   	
//	    addStaticLabel(labelEvent);
	}
	
	private void showStaticLabels()
	{
		for (Iterator<JLabel> i = staticLabels.iterator(); i.hasNext();) {
			JLabel currentStaticLabel = (JLabel) i.next();
			add(currentStaticLabel);
		}		
	}
}