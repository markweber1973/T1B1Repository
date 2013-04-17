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
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//View v = findViewById(R.layout.activity_enter_match_data);
		//v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE); 
		setContentView(R.layout.activity_enter_match_data);	
		getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		producerQueue = new ArrayBlockingQueue<ScoreProducerQueueEntry>(1000);
		scoreConsumer = new ScoreConsumer(producerQueue);
		new Thread(scoreConsumer).start();
		
		//new SaveProductDetails().execute();
		
		firstNameLastName = (TextView)findViewById(R.id.firstNameLastNameTextView);
		startNumber = (TextView)findViewById(R.id.startNumberTextView);
		currentScore = (TextView)findViewById(R.id.currentScoreTextView);
		currentBoulder = (TextView)findViewById(R.id.currentBoulderTextView);
		attemptsDone = (TextView)findViewById(R.id.attemptsDoneTextView);
		
		globalMatchData = ((MatchData)getApplicationContext());
		currentClimber = globalMatchData.getFirst();
		
		nextClimberButton = (Button) findViewById(R.id.nextClimberButton); 
		nextClimberButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		confirmNextClimber();
        		//if (globalMatchData.hasNext())
        		//{
        		//	currentClimber = globalMatchData.getNext();
        		//}
        		//updateUI();
        	}
        });

		previousClimberButton = (Button) findViewById(R.id.previousClimberButton);       
		previousClimberButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		confirmPreviousClimber();
        		//if (globalMatchData.hasPrevious())
        		//{
        		//	currentClimber = globalMatchData.getPrevious();
        		//}
        		//updateUI();
        	}
        });
		
		attemptButton = (Button) findViewById(R.id.attemptButton); 
		attemptButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		currentClimber.startedAttempt();
        		updateUI();
        		updateScoreDataOnServer();
        	}
        });

		bonusButton = (Button) findViewById(R.id.bonusButton);       
		bonusButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 

        		currentClimber.reachedBonus();
        		updateUI();
        		updateScoreDataOnServer();
        	}
        });
		
		topButton = (Button) findViewById(R.id.topButton); 
		topButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 

        		currentClimber.reachedTop();
        		updateUI();
        		updateScoreDataOnServer();    	
        	}
        });

		undoButton = (Button) findViewById(R.id.undoButton);       
		undoButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 

        		currentClimber.undo();      		
        		updateUI();
        		updateScoreDataOnServer();
        	}
        });		
		
		finishedButton = (Button) findViewById(R.id.finishedButton);       
		finishedButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 

        		currentClimber.finished();
        		updateUI();
        		updateScoreDataOnServer();
        	}
        });	
		updateUI();
	}

	private void updateUI()
	{	
		firstNameLastName.setText(currentClimber.firstName() + " " + currentClimber.lastName());
		startNumber.setText(String.valueOf(currentClimber.getStartNumber()));
		attemptsDone.setText(String.valueOf(currentClimber.attempts()));					
		firstNameLastName.setText(currentClimber.firstName() + " " + currentClimber.lastName());

		String score = "T";
		if (currentClimber.topReached())
		{
			score+= currentClimber.attemptsToTop();
		}
		else
		{
			score+="-";
		}
		score+="B";
		if (currentClimber.bonusReached())
		{
			score+= currentClimber.attemptsToBonus();
		}
		else
		{
			score+="-";
		}		
		currentScore.setText(score);		
		
		nextClimberButton.setEnabled(globalMatchData.hasNext());
		previousClimberButton.setEnabled(globalMatchData.hasPrevious());
		finishedButton.setEnabled(!currentClimber.isFinished());
		topButton.setEnabled(!currentClimber.topReached() && !currentClimber.isFinished());
		bonusButton.setEnabled(!currentClimber.bonusReached() && !currentClimber.isFinished());
		attemptButton.setEnabled(!currentClimber.isFinished() && !currentClimber.topReached());
	    currentBoulder.setText(String.valueOf(globalMatchData.getBoulderId()));
	}

    private void updateScoreDataOnServer()
    {
		 ScoreProducerQueueEntry producerQueueEntry = 
	 			new ScoreProducerQueueEntry(globalMatchData.getBoulderId(), currentClimber.getStartNumber(), 
	 					currentClimber.isFinished(), currentClimber.isStarted(),
	 					currentClimber.topReached(), 
	 					currentClimber.attemptsToTop(), currentClimber.bonusReached(), 
	 					currentClimber.attemptsToBonus(), currentClimber.attempts(),
	 					currentClimber.getEventId(), currentClimber.getPhaseId(), currentClimber.getRoundId());
	     	producerQueue.add(producerQueueEntry);   	
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
    
    private void confirmNextClimber()
    {
        new AlertDialog.Builder(this)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle("Leaving current climber")
        .setMessage("Go to next climber ?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialog, int which) {
        	currentClimber = globalMatchData.getNext();
		    updateUI();  
        }

    })
    .setNegativeButton("No", null)
    .show();   	
    }

    private void confirmPreviousClimber()
    {
        new AlertDialog.Builder(this)       
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle("Leaving current climber")
        .setMessage("Go to previous climber ?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialog, int which) {
        	currentClimber = globalMatchData.getPrevious();
		    updateUI();  
        }

    })
    .setNegativeButton("No", null)
    .show();   	
    }    
}

