
public class UIRowCalculator {
	private int fontsize;
	private int rowHeight;
	private int verticalResolution;
	private int horizontalResolution;
	private int nrOfLines;
	private int bonusIconPos;
	private int topIconPos;
	private int boulder1IconPos;
	private int boulder2IconPos;	
	private int boulder3IconPos;
	private int boulder4IconPos;	
	private int boulder5IconPos;	

	private int flagPosition;
	private int nameFieldWidth;
	private int nameFieldPos;
	private int bonusAttemptsPos;
	private int topAttemptsPos;
	private int nationalityFieldPos;
	private int startNumberFieldPos;
	private int nationalityFieldWidth;
	private int startNumberFieldWidth;
	private boolean internationMode;
	
	public UIRowCalculator(int verticalResolution, int horizontalResolution, int nrOfLines)
	{
		double nameFieldWidthPart = 0;
		internationMode = false;
		//this.nrOfLines = nrOfLines +=2;
		this.nrOfLines = 17;

		this.horizontalResolution = horizontalResolution;
		this.verticalResolution = verticalResolution;
	
		calculateAllUIGeometry();
		if (internationMode)
		{
			nameFieldWidthPart = 0.3;
		}
		else
		{
			nameFieldWidthPart = 0.4;
		}
		while (nameFieldWidth < (nameFieldWidthPart*horizontalResolution))
		{
			this.nrOfLines++;
			calculateAllUIGeometry();
		}
			

	}
	
	public void setInternationMode(boolean enabled)
	{
		internationMode = enabled;
		calculateAllUIGeometry();
	}
	
	public boolean isInternationalModeEnabled()
	{
		return internationMode;
	}
	
	private void calculateAllUIGeometry()
	{
		this.rowHeight = (int) ((1.0/this.nrOfLines) * (verticalResolution));
		
		if (rowHeight >= 128)
		{
			rowHeight = 128;
			fontsize = 60;
		}
		else if (rowHeight >= 64)
		{
			rowHeight = 64;
			fontsize = 30;
		}
		else if (rowHeight >= 48 )
		{
			rowHeight = 48; 
			fontsize = 22;
		}
		else if (rowHeight >= 32)
		{
			rowHeight = 32;
			fontsize = 14;
		}
		else if (rowHeight >= 24)
		{
			rowHeight = 24;
			fontsize = 10;
		}
		else
		{
			rowHeight = 16;
			fontsize = 8;
		}
		
		bonusIconPos = horizontalResolution - (int)(0.8*rowHeight);
		bonusAttemptsPos = horizontalResolution - (int)(rowHeight*1.1);
		
		topIconPos = bonusIconPos - (int)(rowHeight*1.2) ;
		topAttemptsPos = bonusIconPos - (int)(rowHeight*1.4);		
		boulder5IconPos = topIconPos - (int)(rowHeight*1.4);
		boulder4IconPos = boulder5IconPos - (int)(rowHeight*1.1);
		boulder3IconPos = boulder4IconPos - (int)(rowHeight*1.1);
		boulder2IconPos = boulder3IconPos - (int)(rowHeight*1.1);
		boulder1IconPos = boulder2IconPos - (int)(rowHeight*1.1);		
		
		if (internationMode)
		{
			flagPosition = rowHeight;
			nationalityFieldPos = flagPosition + (int)(1.2*rowHeight);
			nationalityFieldWidth = (int)(1.1*rowHeight);
			startNumberFieldPos = nationalityFieldPos + nationalityFieldWidth;
		}
		else
		{
			nationalityFieldPos = 0;
			nationalityFieldWidth = 0;
			startNumberFieldPos = (int)(0.4*rowHeight);
		}
				
		startNumberFieldWidth = (int)(0.9*rowHeight);			
		nameFieldPos = startNumberFieldPos + rowHeight;
		nameFieldWidth  = boulder1IconPos - nameFieldPos;	
	}
	
	public int getPosition(int rowNumber)
	{
		return (rowNumber * (int)(1.3*rowHeight));
	}
	
	public int getRowHeigth()
	{
		return (rowHeight);
	}
	
	public int getFontSize()
	{
		return (fontsize);
	}
	
	public int getBigFontSize()
	{
		return ((int)(1.5*fontsize));
	}
	
	public int getBitmapWidth()
	{
		return getRowHeigth();
	}
	
	public int getFlagXPos()
	{
		return flagPosition;
	}
	
	public int getBonusIconXPos()
	{
		return bonusIconPos;
	}

	public int getTopIconXPos()
	{
		return topIconPos;
	}	
	
	public int getBoulder1XPos()
	{
		return boulder1IconPos;
	}	

	public int getBoulder2XPos()
	{
		return boulder2IconPos;
	}
	
	public int getBoulder3XPos()
	{
		return boulder3IconPos;
	}
	
	public int getBoulder4XPos()
	{
		return boulder4IconPos;
	}
	
	public int getBoulderXPos(int boulderId)
	{
		if (boulderId == 1) return boulder1IconPos;
		if (boulderId == 2) return boulder2IconPos;
		if (boulderId == 3) return boulder3IconPos;
		if (boulderId == 4) return boulder4IconPos;
		if (boulderId == 5) return boulder5IconPos;

		return 0;
	}
	
	public int getBoulderIdXPos(int boulderId)
	{
		return (getBoulderXPos(boulderId) + (int)(0.3 * rowHeight));
	}
	

	
	public int getNameFieldPos()
	{
		return nameFieldPos;
	}
	
	public int getNameFieldWidth()
	{
		return nameFieldWidth;
	}
	
	public int getNationalityFieldPos()
	{
		return nationalityFieldPos;
	}
	
	public int getNationalityFieldWidth()
	{
		return nationalityFieldWidth;
	}
	
	public int getRankFieldPos()
	{
		return 0;
	}
	
	public int getRankFieldWidth()
	{
		return rowHeight;
	}	
	
	public int getStartNumberFieldPos()
	{
		return startNumberFieldPos;
	}

	public int getStartNumberFieldWidth()
	{
		return startNumberFieldWidth;
	}
	
	public int getTextPosition(int rowNumber)
	{
		return (rowNumber * (int)(1.3*rowHeight) );
	}
	
	public int getCountryTextXPosition()
	{
		return ((int)(1.5 * rowHeight));
	}
	
	public int getBonusAttemptsPos()
	{
		return bonusAttemptsPos;
	}
	
	public int getTopAttemptsPos()
	{
		return topAttemptsPos;
	}
}
