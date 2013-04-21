package com.pofsoft.t1b1client;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;


public class MainActivity extends Activity {
	private Button selectBoulderButton;
	private Button getStartlistButton;	
	private Button enterMatchDataButton;
	private Button exitAppButton;
	private MatchData globalMatchData;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          
    	globalMatchData = ((MatchData)getApplicationContext());
    	      
        selectBoulderButton = (Button) findViewById(R.id.set_boulder_number_button);       
        selectBoulderButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		selectBoulder();
        	}
        });
        
        getStartlistButton = (Button) findViewById(R.id.get_startlist_button);       
        getStartlistButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		getStartList();
        	}
        });
    
        enterMatchDataButton = (Button) findViewById(R.id.enter_match_data_button);       
        enterMatchDataButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		enterMatchData();
        	}
        });
        
        exitAppButton = (Button) findViewById(R.id.exit_app_button);       
        exitAppButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		exitApp();
        	}
        });        
        
//       globalMatchData.setBoulderId(1);
        
    }
	
	private void exitApp()
	{
		finish();
	}

    private void selectBoulder()
    {
       	Intent intent = new Intent(this, SelectBoulder.class); 
    	startActivity(intent);
    }   
 
    private void getStartList()
    {
    	
       	Intent intent = new Intent(this, GetStartList.class); 
    	//Intent intent = new Intent(this, MyActivity.class); 
    	startActivity(intent);
    }
       
    private void enterMatchData()
    {
   // 	if (!globalMatchData.isEmpty())
    	{
    		Intent intent = new Intent(this, EnterMatchData.class); 
    		startActivity(intent);
    	}
    }
   
}
