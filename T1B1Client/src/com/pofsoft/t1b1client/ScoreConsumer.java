package com.pofsoft.t1b1client;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

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
	
	private boolean postToServer(ScoreProducerQueueEntry producedScore)
	{
		try 
		{
			HttpClient httpclient = new DefaultHttpClient(); 
			HttpPost httppost = new HttpPost(url_update_score); 
			//the year data to send
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
						
			nameValuePairs.add(new BasicNameValuePair("boulderNumber",String.valueOf(producedScore.getBoulderNumber())));										
			nameValuePairs.add(new BasicNameValuePair("startNumber",  String.valueOf(producedScore.getStartNumber  ())));			
			nameValuePairs.add(new BasicNameValuePair("eventId",      String.valueOf(producedScore.getEventId      ())));
			nameValuePairs.add(new BasicNameValuePair("phaseId",      String.valueOf(producedScore.getPhaseId      ())));
			nameValuePairs.add(new BasicNameValuePair("roundId",      String.valueOf(producedScore.getRoundId      ())));
			nameValuePairs.add(new BasicNameValuePair("topAttempts",  String.valueOf(producedScore.getTopAttempts  ())));
			nameValuePairs.add(new BasicNameValuePair("bonusAttempts",String.valueOf(producedScore.getBonusAttempts())));
			nameValuePairs.add(new BasicNameValuePair("attempts",     String.valueOf(producedScore.getAttempts     ())));
			
			int finished = 0;
	        if (producedScore.getFinished()) finished = 1;
			nameValuePairs.add(new BasicNameValuePair("finished",     String.valueOf(finished                        )));
	        int topped = 0;
	        if (producedScore.getTopped()) topped = 1;
			nameValuePairs.add(new BasicNameValuePair("topped",       String.valueOf(topped                          )));
	        int bonussed = 0;
	        if (producedScore.getBonussed()) bonussed = 1;	
			nameValuePairs.add(new BasicNameValuePair("bonussed",     String.valueOf(bonussed                        )));
	    								
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs)); 

	        HttpResponse response = httpclient.execute(httppost); 	        	        
	        HttpEntity entity = response.getEntity();
	        String responseString = EntityUtils.toString(entity);
	       
	        if (responseString.contains("404 Not Found"))
	        {
	        	return false;
	        }
	        
	    	String TAG_QUERY_SUCCESS       = "success";
    		    	
			int querySuccess = 0;
			
	    	JSONObject jObj = null;
			try {
				jObj = new JSONObject(responseString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			try {
				querySuccess   = jObj.getInt(TAG_QUERY_SUCCESS);			
			} catch (JSONException e) {
				e.printStackTrace();
			}	    		
				
			if (querySuccess == 1)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		} 
		catch (IOException e1) 
		{
			//e1.printStackTrace();
			return false;
		}	
		catch (IllegalArgumentException e1)
		{
			//e1.printStackTrace();
			return false;			
		}
	}
	
    void consume(ScoreProducerQueueEntry producedScore) 
    { 			      
		boolean postResult = false;
		
		postResult = postToServer(producedScore);
		while (postResult == false)
		{
			try { Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			postResult = postToServer(producedScore);
		}

	}
}