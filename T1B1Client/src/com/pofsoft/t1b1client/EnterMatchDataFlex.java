package com.pofsoft.t1b1client;

import android.os.AsyncTask;
import android.os.Bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
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

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class EnterMatchDataFlex extends Activity {

	private enum KeypadFocus {StartNumber, BoulderId}
	private enum FormState {EnteringStartnumber, ClimberToBeConfirmed, EnteringBoulderNumber, ClimberFound, BoulderEntered, AllDataToBeConfirmed}
	private FormState formState;

	private KeypadFocus keyPadFocus;

	private MatchData globalMatchData;
		
	private TextView feedbackLabel;

	private TextView boulderNumberLabel;
	private TextView startNumberLabel;
	private TextView startNumberTextView;
	private TextView nameTextView;
	private TextView boulderNumberTextView;
	private TextView currentScoreLabelTextView;
	private TextView currentScoreTextView;
	private TextView attemptsDoneLabelTextView;
	private TextView attemptsDoneTextView;
	
	private String errorDescription;
	private String lastName;
	private String firstName;
	ScoreSlot currentScoreSlot;

	private Button oneButton;
	private Button twoButton;
	private Button threeButton;
	private Button fourButton;
	private Button fiveButton;
	private Button sixButton;
	private Button sevenButton;
	private Button eightButton;
	private Button nineButton;
	private Button zeroButton;
	private Button undoButton;
	private Button enterButton;
	
	private int eventId;
	private int roundId;
	private int phaseId;
	
	private boolean getClimberInfoFailed;
	private boolean getScoreInfoFailed;
	String startNumberString;
	int startNumberInt;
    int startNumberDigit;
  
    int keypadValue;
    boolean keypadDirty;
    int keypadDigit;
  
    int boulderNumber;
    int startNumber;

    boolean boulderNumberFilledIn;
    boolean startNumberFilledIn;
	PolePositionedClimber currentClimber = null;
	private ProgressDialog pDialog;
	private static final String url_get_climberinfo = "http://BoulderServer:8888/get_climber_info_on_startnumber.php";
	private static final String url_get_score = "http://BoulderServer:8888/get_score_from_startnumber_in_current_phase.php";

	private static final String TAG_FIRSTNAME = "firstname";
	private static final String TAG_LASTNAME = "lastname";	
	private static final String TAG_TOPPED = "topped";	
	private static final String TAG_EVENT_ID = "eventId";
	private static final String TAG_ROUND_ID = "roundId";	
	private static final String TAG_PHASE_ID = "phaseId";	
	private static final String TAG_BONUSSED = "bonussed";
	private static final String TAG_TOPATTEMPTS = "topAttempts";	
	private static final String TAG_BONUSATTEMPTS = "bonusAttempts";	
	private static final String TAG_ATTEMPTS = "attempts";	
	
	private Score currentScore;
	private int attempts;
	static String json = "";
	static JSONObject jObj = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
				
		setContentView(R.layout.activity_flex_data_entry);	
		getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		currentScore = new Score();
		attempts = 0;
		keyPadFocus = KeypadFocus.StartNumber;   
		
		formState = FormState.EnteringStartnumber;

		globalMatchData = ((MatchData)getApplicationContext());	
		globalMatchData.clear();

		feedbackLabel = (TextView)findViewById(R.id.feedbackLabel);	
		boulderNumberLabel = (TextView)findViewById(R.id.boulderNumberLabel);	

		nameTextView = (TextView)findViewById(R.id.firstname_lastname);	
		startNumberLabel = (TextView)findViewById(R.id.startNumberLabel);

		startNumberTextView = (TextView)findViewById(R.id.startNumberTextView);
		startNumberTextView.setCursorVisible(true);
		startNumberTextView.setBackgroundColor(100);
		boulderNumberTextView = (TextView)findViewById(R.id.boulderNumberTextView);
		currentScoreLabelTextView = (TextView)findViewById(R.id.currentScoreLabelTextView);
		currentScoreTextView = (TextView)findViewById(R.id.currentScoreTextView);
		attemptsDoneLabelTextView = (TextView)findViewById(R.id.attemptsDoneLabelTextView);
		attemptsDoneTextView = (TextView)findViewById(R.id.attemptsDoneTextView);		
		globalMatchData = ((MatchData)getApplicationContext());

		oneButton = (Button) findViewById(R.id.oneButton); 
		oneButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		numericButtonPressed(1);
        	}
        });
		
		twoButton = (Button) findViewById(R.id.twoButton); 
		twoButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		numericButtonPressed(2);
        	}
        });
		
		threeButton = (Button) findViewById(R.id.threeButton); 
		threeButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		numericButtonPressed(3);
        	}
        });

		fourButton = (Button) findViewById(R.id.fourButton); 
		fourButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		numericButtonPressed(4);
        	}
        });		

		fiveButton = (Button) findViewById(R.id.fiveButton); 
		fiveButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		numericButtonPressed(5);
        	}
        });	
	
		sixButton = (Button) findViewById(R.id.sixButton); 
		sixButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		numericButtonPressed(6);
        	}
        });
		
		sevenButton = (Button) findViewById(R.id.sevenButton); 
		sevenButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		numericButtonPressed(7);
        	}
        });
		
		eightButton = (Button) findViewById(R.id.eightButton); 
		eightButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		numericButtonPressed(8);
        	}
        });

		nineButton = (Button) findViewById(R.id.nineButton); 
		nineButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		numericButtonPressed(9);
        	}
        });		

		zeroButton = (Button) findViewById(R.id.zeroButton); 
		zeroButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		numericButtonPressed(0);
        	}
        });	
		
		undoButton = (Button) findViewById(R.id.undoButton); 
		undoButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		undoButtonPressed();
        	}
        });
	
		enterButton = (Button) findViewById(R.id.enterButton); 
		enterButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		enterButtonPressed();
        	}
        });		
		
		try {
			updateUI();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    protected void onActivityResult(int requestCode, int resultCode, Intent data) 
    {
		currentScore = new Score();
		keyPadFocus = KeypadFocus.StartNumber;   		
		formState = FormState.EnteringStartnumber;
		globalMatchData = ((MatchData)getApplicationContext());	
		globalMatchData.clear();    	
        updateUI();
    }
	
	class GetClimberInfo extends AsyncTask<String, String, String> 
	{

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(EnterMatchDataFlex.this);
			pDialog.setMessage("Retrieving climber info. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		
		private boolean postToServer()
		{
			try 
			{
				getClimberInfoFailed = true;
				HttpClient httpclient = new DefaultHttpClient(); 
				HttpPost httppost = new HttpPost(url_get_climberinfo); 
	
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();							
				nameValuePairs.add(new BasicNameValuePair("startNumber",  String.valueOf(keypadValue)));			
		    								
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs)); 

		        HttpResponse response = httpclient.execute(httppost); 	        	        
		        HttpEntity entity = response.getEntity();
		        String responseString = EntityUtils.toString(entity);
		       
		        if (responseString.contains("404 Not Found"))
		        {
		        	return false;
		        }
		        
		    	String TAG_QUERY_SUCCESS       = "success";
	    		    				
		    	JSONObject jObj = null;
				try {
					jObj = new JSONObject(responseString);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				try 
				{
					if (jObj.getInt(TAG_QUERY_SUCCESS)==1)
					{
						firstName = ReplaceUnknownCharacters(jObj.getString(TAG_FIRSTNAME));	
						lastName = ReplaceUnknownCharacters(jObj.getString(TAG_LASTNAME));
						getClimberInfoFailed=false;
						return true;
					}
					else
					{
						firstName = "Not found";
						lastName = "Not found";
						getClimberInfoFailed=true;
						return false;
					}
					//querySuccess   = 		
				} catch (JSONException e) {
					e.printStackTrace();
				}	    						
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
				return false;
			}	
			catch (IllegalArgumentException e1)
			{
				e1.printStackTrace();
				return false;			
			}
			return true;
		}
						
		protected String doInBackground(String... args) {			
			postToServer();						
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
				showErrorMessage(errorDescription);
				return;
			}
			else
			{
				formState = FormState.ClimberToBeConfirmed;
				updateUI();
			}
		}	
	}
	
	void DisableAllNumericButtons()
	{
		oneButton.setEnabled(false);
		twoButton.setEnabled(false);
		threeButton.setEnabled(false);
		fourButton.setEnabled(false);
		fiveButton.setEnabled(false);
		sixButton.setEnabled(false);
		sevenButton.setEnabled(false);
		eightButton.setEnabled(false);
		nineButton.setEnabled(false);
		zeroButton.setEnabled(false);		
	}
	
	void EnableAllNumericButtons()
	{
		oneButton.setEnabled(true);
		twoButton.setEnabled(true);
		threeButton.setEnabled(true);
		fourButton.setEnabled(true);
		fiveButton.setEnabled(true);
		sixButton.setEnabled(true);
		sevenButton.setEnabled(true);
		eightButton.setEnabled(true);
		nineButton.setEnabled(true);
		zeroButton.setEnabled(true);		
	}	
	
	private void updateUI()
	{	
		if (FormState.EnteringStartnumber == formState)
		{
    		currentScoreTextView.setVisibility(View.INVISIBLE);
    		currentScoreLabelTextView.setVisibility(View.INVISIBLE);
    		attemptsDoneTextView.setVisibility(View.INVISIBLE);
    		attemptsDoneLabelTextView.setVisibility(View.INVISIBLE);
    		boulderNumberTextView.setVisibility(View.INVISIBLE);
    		boulderNumberLabel.setVisibility(View.INVISIBLE);
    		nameTextView.setVisibility(View.INVISIBLE);
    		startNumberLabel.setVisibility(View.INVISIBLE);

			if (startNumber == 0)
			{
				undoButton.setEnabled(false);
				enterButton.setEnabled(false);
			}
			else
			{
				undoButton.setEnabled(true);
				enterButton.setEnabled(true);				
			}
			EnableAllNumericButtons();
			feedbackLabel.setVisibility(View.VISIBLE);
	    	startNumberTextView.setVisibility(View.VISIBLE);
	    	feedbackLabel.setText("Enter startnumber");
	    	feedbackLabel.setTextColor(getResources().getColor(R.color.whitelight));
	    	keyPadFocus = KeypadFocus.StartNumber;
		}
		else if (FormState.ClimberToBeConfirmed == formState)
		{
			feedbackLabel.setText("Confirm climber");
	    	startNumberTextView.setVisibility(View.VISIBLE);
    		startNumberLabel.setVisibility(View.INVISIBLE);
    		nameTextView.setVisibility(View.VISIBLE);			
            nameTextView.setText(lastName);			
			DisableAllNumericButtons();
		}		
		else if (FormState.EnteringBoulderNumber == formState)
		{
			feedbackLabel.setText("Enter boulder number");
	    	startNumberTextView.setVisibility(View.VISIBLE);
    		startNumberLabel.setVisibility(View.VISIBLE);

			EnableAllNumericButtons();
            nameTextView.setText(lastName);
    		nameTextView.setVisibility(View.VISIBLE);
    		boulderNumberTextView.setVisibility(View.VISIBLE);
    		boulderNumberLabel.setVisibility(View.VISIBLE);
    		boulderNumberLabel.setText("Boulder:");
	    	keyPadFocus = KeypadFocus.BoulderId;
    		currentScoreTextView.setVisibility(View.INVISIBLE);
    		currentScoreLabelTextView.setVisibility(View.INVISIBLE);
    		attemptsDoneTextView.setVisibility(View.INVISIBLE);
    		attemptsDoneLabelTextView.setVisibility(View.INVISIBLE);    		
		}
		else if (FormState.AllDataToBeConfirmed == formState)
		{
			feedbackLabel.setText("Confirm all data");
	    	startNumberTextView.setVisibility(View.VISIBLE);
    		startNumberLabel.setVisibility(View.VISIBLE);

    		currentScoreTextView.setVisibility(View.VISIBLE);
    		currentScoreLabelTextView.setVisibility(View.VISIBLE);
    		attemptsDoneTextView.setVisibility(View.VISIBLE);
    		attemptsDoneLabelTextView.setVisibility(View.VISIBLE);
    		currentScoreTextView.setText(currentScore.toString());
    		attemptsDoneTextView.setText(Integer.toString(attempts));
			DisableAllNumericButtons();
		}     	       	   

		if (startNumber == 0)
		{
	    	startNumberTextView.setText("");    		    		
		}
		else
		{
			startNumberTextView.setText(Integer.toString(startNumber)); 
		}
		if (boulderNumber == 0)
		{
			boulderNumberTextView.setText("");   			
		}
		else
		{
			boulderNumberTextView.setText(Integer.toString(boulderNumber));   
		}
	}

	public void resetKeypad()
	{
    	keypadDirty = false;
    	keypadValue = 0;
    	keypadDigit = 0;		
	}
    public void numericButtonPressed(int number)
    {
    	keypadDirty = true;
    	keypadValue = (keypadValue * 10) + number;
    	keypadDigit++;
    	
        if (keyPadFocus == KeypadFocus.BoulderId)
    	{
    		boulderNumberFilledIn = true;
    		boulderNumber = keypadValue; 		
    	}    
    	else if (keyPadFocus == KeypadFocus.StartNumber)
    	{
    		startNumberFilledIn = true;
    		startNumber = keypadValue; 		
    	}
    	else
    	{
    		// do nothing; 	   		   		
    	}    	
    	updateUI();
    }    
        
    public void enterButtonPressed()
    {
        if (FormState.EnteringStartnumber == formState)
    	{
    	    new GetClimberInfo().execute();	
    	}
        else if (FormState.EnteringBoulderNumber == formState)
        {
        	new GetScore().execute();	
        }
        else if (FormState.ClimberToBeConfirmed == formState)
        {
        	formState = FormState.EnteringBoulderNumber;
        	resetKeypad();
        	updateUI();
        }
        else  if (FormState.AllDataToBeConfirmed == formState)
        {    	
        	ScoreSlot localSlot = FillScoreSlot();
        	globalMatchData.clear();
        	globalMatchData.addScoreSlot(localSlot);
        	formState = FormState.EnteringStartnumber;
        	startNumberFilledIn = false;
        	boulderNumberFilledIn = false;
    		keyPadFocus = KeypadFocus.StartNumber;
    		boulderNumber = 0;
    		startNumber = 0;
    		resetKeypad();
   		    Intent intent = new Intent(this, EnterMatchData.class); 
		    startActivityForResult(intent, 0);
		    //updateUI();
        }
    	
    }    

    
    public void undoButtonPressed()
    {
   	    	
    	if (formState == FormState.EnteringStartnumber)
    	{
    		startNumber = 0;
		    resetKeypad(); 		
    	}
    	else if (FormState.ClimberToBeConfirmed == formState)  
		{
    		keyPadFocus = KeypadFocus.StartNumber;
		    formState = FormState.EnteringStartnumber;	
    		startNumberFilledIn = false;
		    resetKeypad();
		} 
    	else if (FormState.EnteringBoulderNumber == formState)
		{
    		if (boulderNumber == 0)
    		{
    			formState = FormState.ClimberToBeConfirmed;
    		}
    		else
    		{
    			resetKeypad(); 		
    			boulderNumber = 0;
    		}
		} 
    	else if (FormState.AllDataToBeConfirmed == formState)
		{
        	keypadValue = boulderNumber; 
    		boulderNumberFilledIn = false;
    		formState = FormState.EnteringBoulderNumber;
    		keyPadFocus = KeypadFocus.BoulderId;
		    resetKeypad(); 		
		}
    	updateUI();
    }     
    
    public void ResetAll()
    {
    	currentScoreSlot = null;

        boulderNumberFilledIn=false;
        startNumberFilledIn = false;
        
	    boulderNumber = 0;
	    startNumber = 0;
		startNumberInt = 0;
		startNumberDigit = 1;
		
	    keypadValue = 0;
	    keypadDirty = false;
	    keypadDigit = 1;
		
		keyPadFocus = KeypadFocus.StartNumber;   
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
    
    
	class GetScore extends AsyncTask<String, String, String> 
	{

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(EnterMatchDataFlex.this);
			pDialog.setMessage("Retrieving score. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		
		private boolean postToServer()
		{
			try 
			{
				getScoreInfoFailed = true;
				HttpClient httpclient = new DefaultHttpClient(); 
				HttpPost httppost = new HttpPost(url_get_score); 
	
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();							
				nameValuePairs.add(new BasicNameValuePair("startNumber",  String.valueOf(startNumber)));			
				nameValuePairs.add(new BasicNameValuePair("boulderNumber",  String.valueOf(boulderNumber)));			

		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs)); 

		        HttpResponse response = httpclient.execute(httppost); 	        	        
		        HttpEntity entity = response.getEntity();
		        String responseString = EntityUtils.toString(entity);
		       
		        if (responseString.contains("404 Not Found"))
		        {
		        	return false;
		        }
		        
		    	String TAG_QUERY_SUCCESS       = "success";
	    		    				
		    	JSONObject jObj = null;
				try {
					jObj = new JSONObject(responseString);
				} catch (JSONException e) {
					e.printStackTrace();
				}
		
				try 
				{
					if (jObj.getInt(TAG_QUERY_SUCCESS)==1)
					{
						if (1 == jObj.getInt(TAG_TOPPED))
						{
							currentScore.setAttemptsToTop(jObj.getInt(TAG_TOPATTEMPTS));
							currentScore.setTopReached();
						}
						else
						{
							currentScore.resetTopReached();
						}
						if (1 == jObj.getInt(TAG_BONUSSED))
						{
							currentScore.setAttemptsToBonus(jObj.getInt(TAG_BONUSATTEMPTS));
							currentScore.setBonusReached();
						}
						else
						{
							currentScore.resetTopReached();
						}
						eventId = (jObj.getInt(TAG_EVENT_ID));	
						phaseId = (jObj.getInt(TAG_PHASE_ID));	
						roundId = (jObj.getInt(TAG_ROUND_ID));	

						attempts = (jObj.getInt(TAG_ATTEMPTS));	
						getScoreInfoFailed=false;
						return true;
					}
					else
					{
						attempts = 0;
						getScoreInfoFailed=true;
						return false;
					} 
					//querySuccess   = 		
				} catch (JSONException e) {
					e.printStackTrace();
				}	    						
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
				return false;
			}	
			catch (IllegalArgumentException e1)
			{
				e1.printStackTrace();
				return false;			
			}
			return true;
		}
						
		protected String doInBackground(String... args) {			
			postToServer();						
			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();

			if (getScoreInfoFailed) 
			{
				showErrorMessage(errorDescription);
				return;
			}
			else
			{
				formState = FormState.AllDataToBeConfirmed;
				updateUI();				
			}
		}		
	}
	//currentScore
	// attempts
	ScoreSlot FillScoreSlot()
	{
		Climber localClimber = new Climber(startNumber, firstName, lastName);
		PolePositionedClimber localPolePosClimber = new PolePositionedClimber(localClimber,1,20);
		
	    try
	    {
	    	if (!currentScore.getBonusReached() && !currentScore.getTopReached())
	    	{
		    	for (int x=0; x< attempts ; x++)
		    	{	    		
		    		localPolePosClimber.startedAttempt(boulderNumber);
		    	}		    		
	    	}
	    	else if (currentScore.getBonusReached() && !currentScore.getTopReached())
		    {
		    	for (int x=0; x< currentScore.getAttemptsToBonus(); x++)
		    	{	    		
		    		localPolePosClimber.startedAttempt(boulderNumber);
		    	}
		    	localPolePosClimber.reachedBonus(boulderNumber);
		    	int additionalAttempts = attempts - currentScore.getAttemptsToBonus();
		    	if (additionalAttempts > 0)
		    	{
			    	for (int x=0; x< additionalAttempts; x++)
			    	{	    		
			    		localPolePosClimber.startedAttempt(boulderNumber);
			    	}		    		
		    	}
		    } 
		    else if (currentScore.getTopReached())
		    {
		    	if (currentScore.getAttemptsToBonus() == currentScore.getAttemptsToTop())
		    	{
			    	for (int x=0; x< currentScore.getAttemptsToBonus(); x++)
			    	{	    		
			    		localPolePosClimber.startedAttempt(boulderNumber);
			    	}	
			    	localPolePosClimber.reachedBonus(boulderNumber);
			    	localPolePosClimber.reachedTop(boulderNumber);
		    	}
		    	else
		    	{
			    	for (int x=0; x< currentScore.getAttemptsToBonus(); x++)
			    	{	    		
			    		localPolePosClimber.startedAttempt(boulderNumber);
			    	}		
			    	localPolePosClimber.reachedBonus(boulderNumber);
			    	
			    	for (int x=0; x< (currentScore.getAttemptsToTop() - currentScore.getAttemptsToBonus()); x++)
			    	{	    		
			    		localPolePosClimber.startedAttempt(boulderNumber);
			    	}		
			    	localPolePosClimber.reachedTop(boulderNumber);
		    	}
		    }		

	    } catch (Exception e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    }
	    ScoreSlot localSlot = new ScoreSlot(localPolePosClimber, boulderNumber, eventId, phaseId, roundId, "");

	    return localSlot;
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
   
    
}
