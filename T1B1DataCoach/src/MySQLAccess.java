

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MySQLAccess {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public MySQLAccess() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		//statement = connect.prepareStatement("select * from T1B1.climbers");
	}
	
	public void fillClimberList(List<Climber> climberList) throws Exception
	{
		int eventId = getEventId();
		int roundId = getRoundId();
		
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		System.out.println("B fillClimberList");    

		try {			
			statement = connect.createStatement();
			
			String query = "SELECT roundenrollment.startnumber, climbers.firstname, climbers.lastname, roundenrollment.polePosition, climbers.nationality" +
			        " FROM roundenrollment INNER JOIN eventenrollment ON (roundenrollment.startnumber=eventenrollment.startnumber) " +
			        " INNER JOIN climbers ON (eventenrollment.climberId=climbers.climberId) " +
			        " WHERE (roundenrollment.eventId='" + eventId + "' AND roundenrollment.roundId='" + roundId + "') " +
			        " ORDER BY roundenrollment.startnumber";			
			
			resultSet = statement.executeQuery(query);	
		
			while (resultSet.next()) {
				
				//int startNumber = resultSet.getInt("startNumber");
				String name = new String(resultSet.getString("lastname"));
				String nationality = new String(resultSet.getString("nationality"));
				String initials = new String(resultSet.getString("firstname"));
                int startNumber = resultSet.getInt("startnumber");
                int polePosition = resultSet.getInt("polePosition");
				Climber localClimber = new Climber(startNumber, name, initials, nationality, polePosition);
				climberList.add(localClimber);
				localClimber.log();

			}

				
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
		System.out.println("E fillClimberList");    

	}
	

	
	
	public void fillBoulderScoreList(List<BoulderScore> boulderScoreList) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		try {

			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from T1B1.scores");	
			while (resultSet.next()) {
				
				int startNumber = resultSet.getInt("startNumber");
				int boulderNumber = resultSet.getInt("boulderNumber");
				int topped = resultSet.getInt("topped");
				int topAttempts = resultSet.getInt("topAttempts");
				int bonussed = resultSet.getInt("bonussed");
				int bonusAttempts = resultSet.getInt("bonusAttempts");
				int finished = resultSet.getInt("finished");
				int attempts = resultSet.getInt("attempts");
			
				Score localScore = new Score((topped==1), topAttempts, (bonussed==1), bonusAttempts, attempts);
				BoulderScore localBoulderScore = new BoulderScore(localScore, boulderNumber, startNumber, (finished == 1));
				boulderScoreList.add(localBoulderScore);
				localScore.log();
			}
		

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}

	public int getRoundId() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		int roundId;
		
		try {

			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from T1B1.activeround");	
			resultSet.next();
			roundId = resultSet.getInt("roundId");
			
			resultSet.close();
			statement.close();
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
		return roundId;			
	}	
	
	public int getEventId() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		int eventId;
		
		try {

			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from T1B1.activeevent");	
			resultSet.next();
			eventId = resultSet.getInt("eventId");
			
			resultSet.close();
			statement.close();
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
		return eventId;			
	}
	
	public EventInfo fillEventInfo() throws Exception
	{
		EventInfo eventInfo;
		
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		try {

			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from T1B1.activeevent");	
			resultSet.next();
			int eventId = resultSet.getInt("eventId");
			
			resultSet.close();
			statement.close();
			
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from T1B1.events WHERE eventId = " + eventId);
			resultSet.next();
			
			//public EventInfo(String name, String date, String place, String location, boolean international)
			boolean international = false;
			if (resultSet.getInt("international") == 1)
			{
				international = true;
			}
			else
			{
				international = false;
			}
			
			
			eventInfo = new EventInfo(resultSet.getString("name"), resultSet.getString("date"), 
					resultSet.getString("place"), resultSet.getString("location"), international);
			eventInfo.log();
			
			resultSet.close();
			statement.close();

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
		return eventInfo;
	}
	
	public String fillRoundInfo() throws Exception
	{
		String roundInfo;
		
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		try {

			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from T1B1.activeround");	
			resultSet.next();
			int roundId = resultSet.getInt("roundId");
			
			resultSet.close();
			statement.close();
			
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from T1B1.rounds WHERE roundId = " + roundId);
			resultSet.next();
			roundInfo = resultSet.getString("name");
			
			resultSet.close();
			statement.close();

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
		return roundInfo;
	}
	// You need to close the resultSet
	private void close() {
	    try {
	      if (resultSet != null) {
	        resultSet.close();
	      }

	      if (statement != null) {
	        statement.close();
	      }

	      if (connect != null) {
	        connect.close();
	      }
	    } catch (Exception e) {

	    }
	  }

}