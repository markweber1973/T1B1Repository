package com.pofsoft.t1b1client;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.RadioGroup;

import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class GetClimberInfoOnStartnumber extends Activity
{
    private ExpandableListView mExpandableList;
    
	private String lastName;
	private String firstName;
	private int activePhaseId;
	private int activeEventId;
	private Button startButton;
	private Button reloadButton;
	private TextView firstNameTV;
	private TextView lastNameTV;
	private RadioGroup numberOfBouldersGroup;

	// Progress Dialog
	private ProgressDialog pDialog;
	

	static String json = "";
	static JSONObject jObj = null;
	boolean sex = false;
	
	private static final String url_get_climberinfo = "http://BoulderServer:8888/get_climber_info_on_startnumber.php";
			
	private static final String TAG_QUERY_RESULT = "queryresult";

	private static final String TAG_STARTNUMBER = "startnumber";
	private static final String TAG_FIRSTNAME = "firstname";
	private static final String TAG_LASTNAME = "lastname";	

	private int activeBoulder;
	private MatchData globalMatchData;
	private boolean getClimberInfoFailed;
	private String errorDescription;
	private void startButtonClicked()
	{
		MyCustomAdapter myAdapter;
		Parent myParent;
		boolean noRoundSelected = true;
		
		for (int index = 0; index < mExpandableList.getExpandableListAdapter().getGroupCount(); index++)
		{
			myAdapter = (MyCustomAdapter)mExpandableList.getExpandableListAdapter();
			myParent = myAdapter.getParent(index);			
			
			if (myParent.isSelected())
			{
				noRoundSelected = false;
				myParent.getRound().setEnabled();
			}			
			else
			{
				myParent.getRound().setDisabled();
			}
		}		
		
		if (noRoundSelected && (activeBoulder != -1))
		{
			showNoRoundSelectedMessage();
		}
		else if (activeBoulder == 100)
		{
			showNoBoulderSelectedMessage();
		}
		else
		{
			if (activeBoulder == -1)
			{
				globalMatchData.setFlexMode();
				globalMatchData.fillScoreSlots(1);
				
		   		Intent intent = new Intent(this, EnterMatchData.class); 
				startActivity(intent);

			}
			else
			{
				globalMatchData.resetFlexMode();
				globalMatchData.fillScoreSlots(activeBoulder);
				
		   		Intent intent = new Intent(this, EnterMatchData.class); 
				startActivity(intent);
			}

		}
	}
	
	private void reloadButtonClicked()
	{
		new GetClimberInfo().execute();		
		globalMatchData = ((MatchData)getApplicationContext());		
		globalMatchData.clear();
		activeBoulder = 100;
		numberOfBouldersGroup.clearCheck();

	}	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_list);
		activeBoulder = 100;
		getClimberInfoFailed = false;

		
        startButton = (Button)findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		startButtonClicked();
        	}
        });       
 
        reloadButton = (Button)findViewById(R.id.reload_button);
        reloadButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		reloadButtonClicked();
        	}
        });        
        
		//firstNameTV = (TextView)findViewById(R.id.firstNameLabel);
		//lastNameTV = (TextView)findViewById(R.id.lastNameLabel);
	}

	class GetClimberInfo extends AsyncTask<String, String, String> 
	{

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(GetClimberInfoOnStartnumber.this);
			pDialog.setMessage("Retrieving climber info. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			URL url = null;
			HttpURLConnection urlConnection = null;
			getClimberInfoFailed = false;	
			try 
			{
				url = new URL(url_get_climberinfo);
			} 
			catch (MalformedURLException e1) 
			{
				getClimberInfoFailed = true;
				errorDescription = e1.getMessage();
				e1.printStackTrace();
			};
				
			try 
			{
				urlConnection = (HttpURLConnection) url.openConnection();
				readStream(urlConnection.getInputStream());
			} catch (IOException e1) 
			{
				getClimberInfoFailed = true;
				errorDescription = e1.getMessage();
				e1.printStackTrace();
			}
			catch (RuntimeException e)
			{
				getClimberInfoFailed = true;
				errorDescription = e.getMessage();				
			}
			
			return null;
		}


		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();

			if (getClimberInfoFailed) 
			{
		        startButton.setEnabled(false);

				showErrorMessage(errorDescription);
				return;
			}
	        startButton.setEnabled(true);

			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
			        ArrayList<Parent> arrayParents = new ArrayList<Parent>();
			        globalMatchData.reset();
			        			        
			        while (globalMatchData.hasNextRound())
			        {
			        	Round currentRound = globalMatchData.getNextRound();
			            currentRound.reset();

			            //for each "i" create a new Parent object to set the title and the children
			            Parent parent = new Parent();
			            
			            parent.setTitle(currentRound.getName());
			            parent.setRoundId(currentRound.getRoundId());
			            parent.setBoulderPrefix(currentRound.getBoulderPrefix());
			            parent.setNrOfBoulders(Integer.toString(currentRound.getNrOfBoulders()));
			            parent.setRound(currentRound);
			            			             
			            ArrayList<HashMap<String, String>> arrayChildren = new ArrayList<HashMap<String, String>>();
			            
			            while (currentRound.hasNextClimber())
			            {
			            	PolePositionedClimber currentClimber = currentRound.getNextClimber();
			            	
							HashMap<String, String> map = new HashMap<String, String>();
							map.put(TAG_STARTNUMBER, Integer.toString(currentClimber.getStartNumber()));
							map.put(TAG_FIRSTNAME, currentClimber.firstName());
							map.put(TAG_LASTNAME, currentClimber.lastName());
							arrayChildren.add(map);			                
			            }
			            parent.setArrayChildren(arrayChildren);
			 
			            //in this array we add the Parent object. We will use the arrayParents at the setAdapter
			            arrayParents.add(parent);
			        }
			 
			        //sets the adapter that provides data to the list.
			        mExpandableList.setAdapter(new MyCustomAdapter(GetClimberInfoOnStartnumber.this,arrayParents));													

				}
			});
//			phaseName.setText(activePhaseName);
//			eventName.setText(activeEventName);
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

		
		private void readStream(InputStream in)
		{
			String queryResult;

			BufferedReader reader = null;
			try 
			{
				StringBuilder sb = new StringBuilder();
				
			    reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
			    String line = "";
			    while ((line = reader.readLine()) != null) 
			    {
			    	sb.append(line + "\n");
			        System.out.println(line);
			    }
			    json = sb.toString();
			    
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
			try {
				globalMatchData.clear();
				jObj = new JSONObject(json);
				queryResult = jObj.getString(TAG_QUERY_RESULT);
				if (queryResult.equals("OK") == false)
				{
					throw new RuntimeException(queryResult);
				}
				lastName = ReplaceUnknownCharacters(jObj.getString(TAG_LASTNAME));		
				firstName = ReplaceUnknownCharacters(jObj.getString(TAG_FIRSTNAME));
			} catch (JSONException e) {
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}
		}	
		
	}
	
    
    private void showNoRoundSelectedMessage()
    {
        new AlertDialog.Builder(this)
        .setIcon(android.R.drawable.ic_dialog_info)
        .setTitle("Can not start entering data")
        .setMessage("Enroll at least one round")
        .setPositiveButton("Ok", new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }

    })
    .show();   	
    }
    
    private void showNoBoulderSelectedMessage()
    {
        new AlertDialog.Builder(this)
        .setIcon(android.R.drawable.ic_dialog_info)
        .setTitle("Can not start entering data")
        .setMessage("Select at least one boulder")
        .setPositiveButton("Ok", new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }

    })
    .show();   	
    }	
    
    private void showErrorMessage(String errorMessage)
    {
        new AlertDialog.Builder(this)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle("Can not retrieve data from server")
        .setMessage(errorMessage)
        .setPositiveButton("Ok", new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }

    })
    .show();   	
    }	    


    
}


