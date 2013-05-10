package com.pofsoft.t1b1client;

import android.os.Bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import android.view.View;
import android.view.Window;
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
	
	private ScoreSlot currentScoreSlot;
	private int currentScoreSlotIndex;
	private int nrOfScoreSlots;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		View v = findViewById(R.layout.activity_enter_match_data);
		//v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE); 
		currentScoreSlotIndex = 0;
		currentScoreSlot = globalMatchData.getScoreSlot(currentScoreSlotIndex);
		
		currentBoulderId = 0;
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
//		currentClimber = globalMatchData.getFirst();

		
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
					currentClimber.finished(currentBoulderId);
	        		updateUI();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		updateScoreDataOnServer();
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
		firstNameLastName.setText(currentClimber.firstName() + " " + currentClimber.lastName());
		startNumber.setText(String.valueOf(currentClimber.getStartNumber()));
		attemptsDone.setText(String.valueOf(currentClimber.attempts(currentBoulderId)));					
		firstNameLastName.setText(currentClimber.firstName() + " " + currentClimber.lastName());

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
		
		nextClimberButton.setEnabled(globalMatchData.hasNext());
		previousClimberButton.setEnabled(globalMatchData.hasPrevious());
		finishedButton.setEnabled(!currentClimber.isFinished(currentBoulderId));
		topButton.setEnabled(!currentClimber.topReached(currentBoulderId) && !currentClimber.isFinished(currentBoulderId));
		bonusButton.setEnabled(!currentClimber.bonusReached(currentBoulderId) && !currentClimber.isFinished(currentBoulderId));
		attemptButton.setEnabled(!currentClimber.isFinished(currentBoulderId) && !currentClimber.topReached(currentBoulderId));
//	    currentBoulder.setText(String.valueOf(globalMatchData.getBoulderId()));
	}

    private void updateScoreDataOnServer()
    {
//		 ScoreProducerQueueEntry producerQueueEntry = 
//	 			new ScoreProducerQueueEntry(globalMatchData.getBoulderId(), currentClimber.getStartNumber(), 
//	 					currentClimber.isFinished(), currentClimber.isStarted(),
//	 					currentClimber.topReached(), 
//	 					currentClimber.attemptsToTop(), currentClimber.bonusReached(), 
//	 					currentClimber.attemptsToBonus(), currentClimber.attempts(),
//	 					currentClimber.getEventId(), currentClimber.getPhaseId(), currentClimber.getRoundId());
//	     	producerQueue.add(producerQueueEntry);   	
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
 //       	currentClimber = globalMatchData.getNext();
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
        	currentScoreSlotIndex--;
        	currentScoreSlot = globalMatchData.getScoreSlot(currentScoreSlotIndex);
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

