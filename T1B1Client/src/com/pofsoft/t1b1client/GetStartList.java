package com.pofsoft.t1b1client;

import android.os.Bundle;
import android.app.ListActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class GetStartList extends ListActivity
{

	// Progress Dialog
	private ProgressDialog pDialog;
	ArrayList<HashMap<String, String>> startList;
	private Button okButton;
	private Button reloadButton;
	static String json = "";
	static JSONObject jObj = null;
	boolean sex = false;

	private static final String url_get_startlist = "http://BoulderServer:8888/get_all_climbers_in_active_round_active_event.php";
	
	// JSON Node names
	private static final String TAG_STARTNUMBER = "startnumber";
	private static final String TAG_POLEPOSITION = "poleposition";
	private static final String TAG_FIRSTNAME = "firstName";
	private static final String TAG_LASTNAME = "lastName";	
	private static final String TAG_ROUNDDEFINITION = "roundDefinition";	
//	private static final String TAG_SUCCESS = "success";
	private MatchData globalMatchData;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_list);
			
		okButton = (Button) findViewById(R.id.ok_button);       
		okButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		finish();
        	}
        });
		
		reloadButton = (Button) findViewById(R.id.reload_button);       
		reloadButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		startList = new ArrayList<HashMap<String, String>>();	
        		
        		new LoadStartList().execute();               		
        	}
        });	
			
		startList = new ArrayList<HashMap<String, String>>();	
		new LoadStartList().execute();		
		globalMatchData = ((MatchData)getApplicationContext());			
	}

	class LoadStartList extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(GetStartList.this);
			pDialog.setMessage("Loading startlist. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			URL url = null;
			HttpURLConnection urlConnection = null;
			
			try 
			{
				url = new URL(url_get_startlist);
			} 
			catch (MalformedURLException e1) 
			{
				e1.printStackTrace();
			};
				
			try 
			{
				urlConnection = (HttpURLConnection) url.openConnection();
				readStream(urlConnection.getInputStream());
			} catch (IOException e1) 
			{
				e1.printStackTrace();
			}
			
			return null;
		}


		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
					ListAdapter adapter = new SimpleAdapter(
							GetStartList.this, startList,
							R.layout.list_item, new String[] { TAG_STARTNUMBER,	TAG_POLEPOSITION, TAG_FIRSTNAME, TAG_LASTNAME},
							new int[] { R.id.startnumber, R.id.poleposition, R.id.firstName, R.id.lastName });
					// updating listview
					setListAdapter(adapter);
				}
			});

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
				jObj = new JSONObject(json);
				JSONArray startlist = jObj.getJSONArray(TAG_ROUNDDEFINITION);
				int iterator = 0;
								
				globalMatchData.clear();
				for (iterator = 0; iterator < startlist.length(); iterator++)
				{
					JSONObject test =  startlist.getJSONObject(iterator);
					String startnumber = (String) test.get(TAG_STARTNUMBER);
					String lastName = (String) test.get(TAG_LASTNAME);
					String firstName = (String) test.get(TAG_FIRSTNAME);
					String poleposition = (String) test.get(TAG_POLEPOSITION);

					globalMatchData.addPolePositionedClimber(Integer.valueOf(startnumber), Integer.valueOf(poleposition), 
							firstName, lastName);
				}
				if (!globalMatchData.isEmpty())
				{
					globalMatchData.sort();					
					PolePositionedClimber currentClimber;					
					currentClimber = globalMatchData.getFirst();
					HashMap<String, String> map = new HashMap<String, String>();
					map.put(TAG_STARTNUMBER, Integer.toString(currentClimber.getStartNumber()));
					map.put(TAG_FIRSTNAME, currentClimber.firstName());
					map.put(TAG_LASTNAME, currentClimber.lastName());
					map.put(TAG_POLEPOSITION, Integer.toString(currentClimber.getPolePosition()));
					startList.add(map);
					
					while (globalMatchData.hasNext())
					{											
						currentClimber = globalMatchData.getNext();
						map = new HashMap<String, String>();
						map.put(TAG_STARTNUMBER, Integer.toString(currentClimber.getStartNumber()));
						map.put(TAG_FIRSTNAME, currentClimber.firstName());
						map.put(TAG_LASTNAME, currentClimber.lastName());
						map.put(TAG_POLEPOSITION, Integer.toString(currentClimber.getPolePosition()));
						startList.add(map);
					}	
					globalMatchData.reset();
				}
				

								
	//			int success = jObj.getInt(TAG_SUCCESS);
	//			int x = success;
				
				
				
				
			} catch (JSONException e) {
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}
		}					
		
	}

}

