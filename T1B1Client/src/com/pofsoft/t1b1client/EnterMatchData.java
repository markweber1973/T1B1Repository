package com.pofsoft.t1b1client;

import android.os.Bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EnterMatchData extends Activity {

	EditText txtName;
	EditText txtPrice;
	EditText txtDesc;
	EditText txtCreatedAt;
	private MatchData globalMatchData;
	
	private int currentBoulderId;
	private int currentEventId;
	private int currentPhaseId;
	private int currentRoundId;
	private String boulderPrefix;

	
	private TextView firstNameLastName;
	private TextView startNumber;
	private TextView currentScore;
	private TextView currentBoulder;
	private TextView attemptsDone;

	private Button nextClimberButton;
	private Button previousClimberButton;
	private Button attemptButton;
	private Button bonusButton;
	private Button topButton;
	private Button undoButton;
	private Button finishedButton;
	
	PolePositionedClimber currentClimber = null;
	private BlockingQueue<ScoreProducerQueueEntry> producerQueue = null;
	private ScoreConsumer scoreConsumer;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		View v = findViewById(R.layout.activity_enter_match_data);
		//v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE); 
		currentBoulderId = 1;
		currentPhaseId = 0;
		currentRoundId = 0;
		currentEventId = 0;

		setContentView(R.layout.activity_enter_match_data);	
		getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		producerQueue = new ArrayBlockingQueue<ScoreProducerQueueEntry>(1000);
		scoreConsumer = new ScoreConsumer(producerQueue);
		new Thread(scoreConsumer).start();		
		
		firstNameLastName = (TextView)findViewById(R.id.firstNameLastNameTextView);
		startNumber = (TextView)findViewById(R.id.startNumberTextView);
		currentScore = (TextView)findViewById(R.id.currentScoreTextView);
		currentBoulder = (TextView)findViewById(R.id.currentBoulderTextView);
		attemptsDone = (TextView)findViewById(R.id.attemptsDoneTextView);
		
		globalMatchData = ((MatchData)getApplicationContext());
		globalMatchData.reset();

		fillLocalIdData(globalMatchData.getNextScoreSlot());
							
		nextClimberButton = (Button) findViewById(R.id.nextClimberButton); 
		nextClimberButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		confirmNextClimber();
        	}
        });

		previousClimberButton = (Button) findViewById(R.id.previousClimberButton);       
		previousClimberButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		confirmPreviousClimber();
        	}
        });
		
		attemptButton = (Button) findViewById(R.id.attemptButton); 
		attemptButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		try {
					currentClimber.startedAttempt(currentBoulderId);
					updateUI();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        		updateScoreDataOnServer();
        	}
        });

		bonusButton = (Button) findViewById(R.id.bonusButton);       
		bonusButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 

        		try {
            		currentClimber.reachedBonus(currentBoulderId);
					updateUI();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		updateScoreDataOnServer();
        	}
        });
		
		topButton = (Button) findViewById(R.id.topButton); 
		topButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 

        		try {
					currentClimber.reachedTop(currentBoulderId);
	        		updateUI();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		updateScoreDataOnServer();    	
        	}
        });

		undoButton = (Button) findViewById(R.id.undoButton);       
		undoButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 

        		try {
					currentClimber.undo(currentBoulderId);
	        		updateUI();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}      		
        		updateScoreDataOnServer();
        	}
        });		
		
		finishedButton = (Button) findViewById(R.id.finishedButton);       
		finishedButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 

        		try {
        			if (globalMatchData.getFlexMode())
        			{
        				confirmClimberFinished();
        			}
        			else
        			{
				   	    currentClimber.finished(currentBoulderId);
		        		updateScoreDataOnServer();
        			}
	        		updateUI();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });	
		try {
			updateUI();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateUI() throws Exception
	{	
		startNumber.setText(String.valueOf(currentClimber.getStartNumber()));
		firstNameLastName.setText(currentClimber.lastName() + ", " + currentClimber.firstName());
		attemptsDone.setText(String.valueOf(currentClimber.attempts(currentBoulderId)));					

		String score = "T";
		if (currentClimber.topReached(currentBoulderId))
		{
			score+= currentClimber.attemptsToTop(currentBoulderId);
		}
		else
		{
			score+="-";
		}
		score+="B";
		if (currentClimber.bonusReached(currentBoulderId))
		{
			score+= currentClimber.attemptsToBonus(currentBoulderId);
		}
		else
		{
			score+="-";
		}		
		currentScore.setText(score);		
		if (globalMatchData.getFlexMode())
		{
			nextClimberButton.setEnabled(false);
			nextClimberButton.setVisibility(View.INVISIBLE);
			previousClimberButton.setEnabled(false);
			previousClimberButton.setVisibility(View.INVISIBLE);
		}
		else
		{
		    nextClimberButton.setEnabled(globalMatchData.hasNextScoreSlot());
		    previousClimberButton.setEnabled(globalMatchData.hasPreviousScoreSlot());
		}
		finishedButton.setEnabled(!currentClimber.isFinished(currentBoulderId) || globalMatchData.getFlexMode());
		topButton.setEnabled(!currentClimber.topReached(currentBoulderId) && !currentClimber.isFinished(currentBoulderId));
		bonusButton.setEnabled(!currentClimber.bonusReached(currentBoulderId) && !currentClimber.isFinished(currentBoulderId));
		attemptButton.setEnabled(!currentClimber.isFinished(currentBoulderId) && !currentClimber.topReached(currentBoulderId));
	    currentBoulder.setText(boulderPrefix + String.valueOf(currentBoulderId));
	}

    private void updateScoreDataOnServer()
    {
		 ScoreProducerQueueEntry producerQueueEntry;
		try {
			producerQueueEntry = new ScoreProducerQueueEntry(currentBoulderId, currentClimber.getStartNumber(), 
					currentClimber.isFinished(currentBoulderId), currentClimber.isStarted(currentBoulderId),
					currentClimber.topReached(currentBoulderId), 
					currentClimber.attemptsToTop(currentBoulderId), currentClimber.bonusReached(currentBoulderId), 
					currentClimber.attemptsToBonus(currentBoulderId), currentClimber.attempts(currentBoulderId),
					currentEventId, currentPhaseId, currentRoundId);
	     	producerQueue.add(producerQueueEntry);   	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Stop entering data")
            .setMessage("Are you sure you want to stop?")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            	getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                finish();    
            }

        })
        .setNegativeButton("No", null)
        .show();
    }

    private void confirmClimberFinished()
    {
        new AlertDialog.Builder(this)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle("Leaving current climber")
        .setMessage("Is current climber finished ?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) 
            {
            	getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            	try {
					currentClimber.finished(currentBoulderId);
            		updateScoreDataOnServer();
            		finish();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }	    
        })
	    .setNegativeButton("No", null)
	    .show();   	   	
    }
    
    private void confirmNextClimber()
    {
    	if (globalMatchData.getFlexMode())
    	{
        	if (globalMatchData.hasNextScoreSlot())
        	{
        		fillLocalIdData(globalMatchData.getNextScoreSlot());
        	}
       	
		    try {
				updateUI();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    		
    	}
    	else
    	{
	        new AlertDialog.Builder(this)
	        .setIcon(android.R.drawable.ic_dialog_alert)
	        .setTitle("Leaving current step")
	        .setMessage("Go to next step ?")
	        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
	        {
	
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	        	
	        	if (globalMatchData.hasNextScoreSlot())
	        	{
	        		fillLocalIdData(globalMatchData.getNextScoreSlot());
	        	}
	       	
			    try {
					updateUI();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
	        }
	
		    })
		    .setNegativeButton("No", null)
		    .show();   	
    	}
    }

    private void confirmPreviousClimber()
    {
    	if (globalMatchData.getFlexMode())
    	{
        	if (globalMatchData.hasPreviousScoreSlot())
        	{
        		fillLocalIdData(globalMatchData.getPreviousScoreSlot());
        	}
       	
		    try {
				updateUI();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    		
    	}
    	else
    	{
	        new AlertDialog.Builder(this)       
	        .setIcon(android.R.drawable.ic_dialog_alert)
	        .setTitle("Leaving current step")
	        .setMessage("Go to previous step ?")
	        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
	    {
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	       	
	        	if (globalMatchData.hasPreviousScoreSlot())
	        	{
	        		fillLocalIdData(globalMatchData.getPreviousScoreSlot());
	        	}
	       	
			    try {
					updateUI();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
	        }
	
	    })
	    .setNegativeButton("No", null)
	    .show();   	
    	}
    }    
    
    private void fillLocalIdData(ScoreSlot scoreSlot)
    {    	
    	currentClimber = scoreSlot.getClimber();
    	currentBoulderId = scoreSlot.getBoulderId();

    	currentEventId = scoreSlot.getEventId();
    	currentRoundId = scoreSlot.getRoundId();
    	currentPhaseId = scoreSlot.getPhaseId();   	
    	boulderPrefix = scoreSlot.getBoulderPrefix();
		if (globalMatchData.getFlexMode())
		{
			
		}
    }
}

