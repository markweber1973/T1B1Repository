import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardData {
	List<Climber> climbersData;
	List<Score> scoreData;
	
	public BoardData(){
		climbersData = Collections.synchronizedList(new ArrayList<Climber>());
		scoreData = Collections.synchronizedList(new ArrayList<Score>());

	}
	
	public void addClimber(Climber theClimber)
	{
		climbersData.add(theClimber);
	}
	
	public void addScore(Score theScore)
	{
		scoreData.add(theScore);
	}
}
