

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
	private ResultSet resultSet2 = null;

	public MySQLAccess() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		//connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		connect = DriverManager.getConnection("jdbc:mysql://BoulderServer:8889/T1B1?" + "user=mark&password=mark");

		//statement = connect.prepareStatement("select * from T1B1.climbers");
	}
	
	
	
	private void readStream(InputStream in) 
	{
		BufferedReader reader = null;
		try 
		{
			StringBuilder sb = new StringBuilder();
			
		    reader = new BufferedReader(new InputStreamReader(in));
		    String line = "";
		    while ((line = reader.readLine()) != null) 
		    {
		    	sb.append(line + "\n");
		        System.out.println(line);
		    }

		    sb.toString();
		    int x = 0;
		    
		} catch (IOException e) 
		{
		    e.printStackTrace();

		} finally 
		{
		    if (reader != null) 
		    {
		        try 
		        {
		            reader.close();
		        } 
		        catch (IOException e) 
		        {
		            e.printStackTrace();
		        }
		    }
		}
	}
	
	public void fillClimberList(List<Climber> climberList, int eventId, int phaseId) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		//connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		connect = DriverManager.getConnection("jdbc:mysql://BoulderServer:8889/T1B1?" + "user=mark&password=mark");
		statement = connect.createStatement();

		System.out.println("B fillClimberList");    

		try {			
			statement = connect.createStatement();
					
			String roundsInPhaseQuery = 
				"SELECT RPE.roundId, R.name, EER.startNumber, C.lastname, C.firstname, C.nationality, RE.poleposition FROM roundphaseenrollment RPE " +
				"JOIN rounds R ON (RPE.eventId='"+eventId+"' AND RPE.phaseId='"+phaseId+"'AND RPE.roundId=R.roundId) JOIN roundenrollment RE ON RE.roundId=RPE.roundId " +
				"JOIN eventenrollment EER ON EER.startNumber=RE.startNumber JOIN climbers C on EER.climberId=C.climberId";			
			resultSet = statement.executeQuery(roundsInPhaseQuery);	

			while (resultSet.next()) {				
				String name = new String(resultSet.getString("lastname"));
				String nationality = new String(resultSet.getString("nationality"));
				String initials = new String(resultSet.getString("firstname"));
                int startNumber = resultSet.getInt("startnumber");
                int polePosition = resultSet.getInt("polePosition");
                int roundId = resultSet.getInt("roundId");
                String roundName = new String(resultSet.getString("name"));
				Climber localClimber = new Climber(startNumber, name, initials, nationality, polePosition, roundId, roundName);
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
	
	
	public void fillBoulderScoreList(List<BoulderScore> boulderScoreList, int eventId, int phaseId) throws Exception
	{       		
		Class.forName("com.mysql.jdbc.Driver");
		//connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		connect = DriverManager.getConnection("jdbc:mysql://BoulderServer:8889/T1B1?" + "user=mark&password=mark");
		
		try {			
			String scoresInPhaseQuery = 
				"SELECT S.boulderNumber, S.startNumber, S.finished, S.topped, S.topAttempts, S.bonussed, S.bonusAttempts, S.attempts " +
				"FROM roundphaseenrollment RPE JOIN rounds R ON (RPE.eventId='"+eventId+"' AND RPE.phaseId='"+phaseId+"'AND RPE.roundId=R.roundId) JOIN " +
				"scores S ON (S.eventId=RPE.eventId AND S.phaseId=RPE.phaseId AND S.roundId=RPE.roundId)";
			statement = connect.createStatement();
			resultSet = statement.executeQuery(scoresInPhaseQuery);
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
			}
		

		} catch (Exception e) {
			throw e;
		} finally {
			close();
			//System.out.println(System.currentTimeMillis() - startTime);
		}
	}

	public int getActivePhaseId() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		//connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		connect = DriverManager.getConnection("jdbc:mysql://BoulderServer:8889/T1B1?" + "user=mark&password=mark");

		int phaseId;
		
		try {

			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from T1B1.activephase");	
			resultSet.next();
			phaseId = resultSet.getInt("phaseId");
			
			resultSet.close();
			statement.close();
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
		return phaseId;			
	}		
	
	public int getActiveEventId() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		//connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		connect = DriverManager.getConnection("jdbc:mysql://BoulderServer:8889/T1B1?" + "user=mark&password=mark");

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
	
	public int getEventId() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		//connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		connect = DriverManager.getConnection("jdbc:mysql://BoulderServer:8889/T1B1?" + "user=mark&password=mark");

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
		//connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		connect = DriverManager.getConnection("jdbc:mysql://BoulderServer:8889/T1B1?" + "user=mark&password=mark");

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
		//connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		connect = DriverManager.getConnection("jdbc:mysql://BoulderServer:8889/T1B1?" + "user=mark&password=mark");

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
	
	public String fillRoundInfo(int roundId) throws Exception
	{
		String roundInfo;
		
		Class.forName("com.mysql.jdbc.Driver");
		//connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		connect = DriverManager.getConnection("jdbc:mysql://BoulderServer:8889/T1B1?" + "user=mark&password=mark");

		try {
			
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