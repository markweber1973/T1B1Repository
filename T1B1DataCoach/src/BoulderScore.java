
public class BoulderScore {
	private Score score;
	private int boulderId;
	private int startNumber;
	private boolean finished;
	UIBitmap bitmap;
	
	public BoulderScore(Score score, int boulderId, int startNumber, boolean finished)
	{
		this.score = score;
		this.boulderId = boulderId;
		this.startNumber = startNumber;
		this.finished = finished;
	}
	
	public Score getScore()
	{
		return score;
	}

	public int getBoulderId()
	{
		return boulderId;
	}
	
	public int getStartNumber()
	{
		return startNumber;
	}
	
	public boolean getFinished()
	{
		System.out.println("finished: " + finished);
		return finished;
	}
	
	public void displayOnFrame(int row, ScoreBoardJFrame targetFrame)
	{
		if (bitmap != null)  targetFrame.remove(bitmap);
		UIRowCalculator localCalculator = targetFrame.getUIRowCalculator();
		bitmap = new UIBitmap(this, localCalculator.getBitmapWidth(), localCalculator.getBitmapWidth(), localCalculator.getBoulderXPos(boulderId), localCalculator.getPosition(row), targetFrame);	
	}
	
	public void log()
	{
		score.log();
		System.out.println("boulderId: " + boulderId);
		System.out.println("startNumber: " + startNumber);
		System.out.println("finished: " + finished);		
	}
}
