

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
		connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		//connect = DriverManager.getConnection("jdbc:mysql://BoulderServer:8889/T1B1?" + "user=mark&password=mark");

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
	
	String ReplaceUnknownCharacters(String sourceString)
	{
		if (sourceString.contains("&#"))
		{
			String destination = "";

			destination = sourceString.replaceAll("&#193;", "Á");	sourceString = destination;
			destination = sourceString.replaceAll("&#225;", "á");	sourceString = destination;
			destination = sourceString.replaceAll("&#260;", "Ą");	sourceString = destination;
			destination = sourceString.replaceAll("&#261;", "ą");	sourceString = destination;
			destination = sourceString.replaceAll("&#196;", "Ä");	sourceString = destination;
			destination = sourceString.replaceAll("&#228;", "ä");	sourceString = destination;
			destination = sourceString.replaceAll("&#201;", "É");	sourceString = destination;
			destination = sourceString.replaceAll("&#233;", "é");	sourceString = destination;
			destination = sourceString.replaceAll("&#280;", "Ę");	sourceString = destination;
			destination = sourceString.replaceAll("&#281;", "ę");	sourceString = destination;
			destination = sourceString.replaceAll("&#282;", "Ě");	sourceString = destination;
			destination = sourceString.replaceAll("&#283;", "ě");	sourceString = destination;
			destination = sourceString.replaceAll("&#205;", "Í");	sourceString = destination;
			destination = sourceString.replaceAll("&#237;", "í");	sourceString = destination;
			destination = sourceString.replaceAll("&#211;", "Ó");	sourceString = destination;
			destination = sourceString.replaceAll("&#243;", "ó");	sourceString = destination;
			destination = sourceString.replaceAll("&#212;", "Ô");	sourceString = destination;
			destination = sourceString.replaceAll("&#244;", "ô");	sourceString = destination;
			destination = sourceString.replaceAll("&#218;", "Ú");	sourceString = destination;
			destination = sourceString.replaceAll("&#250;", "ú");	sourceString = destination;
			destination = sourceString.replaceAll("&#366;", "Ů");	sourceString = destination;
			destination = sourceString.replaceAll("&#367;", "ů");	sourceString = destination;
			destination = sourceString.replaceAll("&#221;", "Ý");	sourceString = destination;
			destination = sourceString.replaceAll("&#253;", "ý");	sourceString = destination;
			destination = sourceString.replaceAll("&#268;", "Č");	sourceString = destination;
			destination = sourceString.replaceAll("&#269;", "č");	sourceString = destination;
			destination = sourceString.replaceAll("&#271;", "ď");	sourceString = destination;
			destination = sourceString.replaceAll("&#357;", "ť");	sourceString = destination;
			destination = sourceString.replaceAll("&#313;", "Ĺ");	sourceString = destination;
			destination = sourceString.replaceAll("&#314;", "ĺ");	sourceString = destination;
			destination = sourceString.replaceAll("&#327;", "Ň");	sourceString = destination;
			destination = sourceString.replaceAll("&#328;", "ň");	sourceString = destination;
			destination = sourceString.replaceAll("&#340;", "Ŕ");	sourceString = destination;
			destination = sourceString.replaceAll("&#341;", "ŕ");	sourceString = destination;
			destination = sourceString.replaceAll("&#344;", "Ř");	sourceString = destination;
			destination = sourceString.replaceAll("&#345;", "ř");	sourceString = destination;
			destination = sourceString.replaceAll("&#352;", "Š");	sourceString = destination;
			destination = sourceString.replaceAll("&#353;", "š");	sourceString = destination;
			destination = sourceString.replaceAll("&#381;", "Ž");	sourceString = destination;
			destination = sourceString.replaceAll("&#382;", "ž");
			return destination;
		}
		else
		{
			return sourceString;
		}
	}
	
	@SuppressWarnings("deprecation")
	public void fillClimberList(List<Climber> climberList, int eventId, int phaseId) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?useUnicode=true&characterEncoding=utf-8" + "&user=mark&password=mark");
		//connect = DriverManager.getConnection("jdbc:mysql://BoulderServer:8889/T1B1?" + "user=mark&password=mark");
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

				name = ReplaceUnknownCharacters(name);			
				// creating a new string as my split logic is based on the string format	
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
		connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		//connect = DriverManager.getConnection("jdbc:mysql://BoulderServer:8889/T1B1?" + "user=mark&password=mark");
		
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
		connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		//connect = DriverManager.getConnection("jdbc:mysql://BoulderServer:8889/T1B1?" + "user=mark&password=mark");

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
		connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		//connect = DriverManager.getConnection("jdbc:mysql://BoulderServer:8889/T1B1?" + "user=mark&password=mark");

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
		connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		//connect = DriverManager.getConnection("jdbc:mysql://BoulderServer:8889/T1B1?" + "user=mark&password=mark");

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
		//connect = DriverManager.getConnection("jdbc:mysql://BoulderServer:8889/T1B1?" + "user=mark&password=mark");

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
		//connect = DriverManager.getConnection("jdbc:mysql://BoulderServer:8889/T1B1?" + "user=mark&password=mark");

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
	
	public String fillPhaseInfo() throws Exception
	{
		String roundInfo;
		
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		//connect = DriverManager.getConnection("jdbc:mysql://BoulderServer:8889/T1B1?" + "user=mark&password=mark");

		try {

			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from T1B1.activephase");	
			resultSet.next();
			int phaseId = resultSet.getInt("phaseId");
			
			resultSet.close();
			statement.close();
			
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from T1B1.phases WHERE phaseId = " + phaseId);
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
		connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/T1B1?" + "user=mark&password=mark");
		//connect = DriverManager.getConnection("jdbc:mysql://BoulderServer:8889/T1B1?" + "user=mark&password=mark");

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