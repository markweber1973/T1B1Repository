package com.pofsoft.t1b1client;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

class ScoreConsumer implements Runnable 
{
	private URL url = null;
	private String url_update_score;
    private BlockingQueue<ScoreProducerQueueEntry> queue;
    
    ScoreConsumer(BlockingQueue<ScoreProducerQueueEntry> q) 
    { 
        queue = q; 
    }
	   
	public void run() 
	{
		url_update_score = "http://BoulderServer:8888/update_score_use_get_extended.php";
		//url_update_score = "http://BoulderServer/update_score_use_get_extended.php";
		
		try 
		{
		    url = new URL(url_update_score);

		} 
		catch (MalformedURLException e1) 
		{
			e1.printStackTrace();
		}
		
	    try 
	    {
	        while (true) 
	        { 
	            consume(queue.take()); 
	        }
	     } 
	    catch (InterruptedException ex) 
	    { 
	    	
	    }
    }
	
	private boolean postToServer(String score)
	{
		String response= "";
		HttpURLConnection urlConnection = null;
		try 
		{
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setFixedLengthStreamingMode(score.getBytes().length);
			
			urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//send the POST out
			PrintWriter out = new PrintWriter(urlConnection.getOutputStream());
			out.print(score);
			out.close();
			
			Scanner inStream = new Scanner(urlConnection.getInputStream());
			while(inStream.hasNextLine())
			response+=(inStream.nextLine());	
			return true;
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
			return false;
		}			
	}
	
    void consume(ScoreProducerQueueEntry producedScore) 
    { 			
		int finished = 0;
        if (producedScore.getFinished()) finished = 1;

		int started = 0;
        if (producedScore.getStarted()) started = 1;        
        
        int topped = 0;
        if (producedScore.getTopped()) topped = 1;

        int bonussed = 0;
        if (producedScore.getBonussed()) bonussed = 1;
        
		String param = "";
		try 
		{
			param = "boulderNumber=" + URLEncoder.encode(String.valueOf(producedScore.getBoulderNumber()),"UTF-8")+
			"&startNumber="          + URLEncoder.encode(String.valueOf(producedScore.getStartNumber()),"UTF-8")+
			"&eventId="          + URLEncoder.encode(String.valueOf(producedScore.getEventId()),"UTF-8")+
			"&phaseId="          + URLEncoder.encode(String.valueOf(producedScore.getPhaseId()),"UTF-8")+
			"&roundId="          + URLEncoder.encode(String.valueOf(producedScore.getRoundId()),"UTF-8")+
			"&finished="             + URLEncoder.encode(String.valueOf(finished),"UTF-8")+
			"&started="             + URLEncoder.encode(String.valueOf(started),"UTF-8")+
			"&topped="               + URLEncoder.encode(String.valueOf(topped),"UTF-8")+
			"&topAttempts="          + URLEncoder.encode(String.valueOf(producedScore.getTopAttempts()),"UTF-8")+
			"&bonussed="             + URLEncoder.encode(String.valueOf(bonussed),"UTF-8")+
			"&bonusAttempts="        + URLEncoder.encode(String.valueOf(producedScore.getBonusAttempts()),"UTF-8")+				
			"&attempts="             + URLEncoder.encode(String.valueOf(producedScore.getAttempts()),"UTF-8");
		} 
		catch (UnsupportedEncodingException e2) 
		{
			e2.printStackTrace();
		}
		boolean postResult = false;
		
		postResult = postToServer(param);
		while (postResult == false)
		{
			try { Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			postResult = postToServer(param);
		}

	}
}