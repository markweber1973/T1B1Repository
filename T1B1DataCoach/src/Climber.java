import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class Climber {

	private int startNumber;
	private String name;
	private String initials;
	private String roundName;
	private String nationality;
	private int polePosition;
	private int round;

	public Climber(int theStartNumber, String theName, String theInitials, String theNationality, int polePosition, int round, String roundName)
	{
		startNumber = theStartNumber;
		name = theName;
		initials = theInitials;
		nationality = theNationality;
		this.polePosition = polePosition;
		this.round = round;
		this.roundName = roundName;
	};
	
	public Climber() {}
		
	public String getName() {
		return name;
	}
	
	public int getRound()
	{
		return round;
	}

	public String getRoundName()
	{
		return roundName;
	}	
	
	public int getPolePosition(){
		return polePosition;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getStartNumber()
	{
		return startNumber;
	}
	
	public String getInitials()
	{
		return initials;
	}
	
	public String getNationality()
	{
		return nationality;
	}
	
	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return name + " " + initials;
	}
	
	public void log()
	{
		System.out.println("startNumber: " + startNumber);
		System.out.println("name: " + name);
		System.out.println("initials: " + initials);
		System.out.println("nationality: " + nationality);	
	}
	
	public void displayOnFrame(int row, ScoreBoardJFrame targetFrame)
	{
		UIRowCalculator localUICalculator = targetFrame.getUIRowCalculator();
		JLabel labelName = new JLabel();  
		JLabel labelNationality = new JLabel();
		JLabel labelStartNumber = new JLabel();
		
	    Color nameColor = new Color(255,255,255);
	    
	    labelName.setText(name.toUpperCase() + ", " + initials);
	    labelName.setForeground(nameColor);
	    
	    labelNationality.setText(nationality);
	    labelNationality.setForeground(nameColor);
	    
	    labelStartNumber.setText(""+startNumber);
	    labelStartNumber.setHorizontalAlignment(4);	 
	    labelStartNumber.setForeground(nameColor);
	    
	    Font curFont = labelName.getFont();
	    labelName.setFont(new Font(curFont.getFontName(), curFont.getStyle(), localUICalculator.getFontSize()));
	    labelName.setBounds(localUICalculator.getNameFieldPos(), localUICalculator.getPosition(row), localUICalculator.getNameFieldWidth(), localUICalculator.getRowHeigth());
	    targetFrame.addDynamicLabel(labelName);
	
	    labelStartNumber.setFont(new Font(curFont.getFontName(), curFont.getStyle(), localUICalculator.getFontSize()));
	    labelStartNumber.setBounds(localUICalculator.getStartNumberFieldPos(), localUICalculator.getPosition(row), localUICalculator.getStartNumberFieldWidth(), localUICalculator.getRowHeigth());
	    targetFrame.addDynamicLabel(labelStartNumber);	
	    
	    if (localUICalculator.isInternationalModeEnabled())
	    {
		    labelNationality.setFont(new Font(curFont.getFontName(), curFont.getStyle(), localUICalculator.getFontSize()));
		    labelNationality.setBounds(localUICalculator.getNationalityFieldPos(), localUICalculator.getPosition(row), localUICalculator.getNationalityFieldWidth(), localUICalculator.getRowHeigth());
		    targetFrame.addDynamicLabel(labelNationality);	    
		
 
		    
			ImageIcon flagIcon = new ImageIcon("resources/" + localUICalculator.getRowHeigth() + "/" + nationality + ".png");
			JLabel flagLabel = new JLabel();
			flagLabel.setIcon(flagIcon);
			flagLabel.setBounds(localUICalculator.getFlagXPos(), localUICalculator.getPosition(row), localUICalculator.getRowHeigth(), localUICalculator.getRowHeigth());
			targetFrame.addDynamicLabel(flagLabel);
	    }
	}
}
