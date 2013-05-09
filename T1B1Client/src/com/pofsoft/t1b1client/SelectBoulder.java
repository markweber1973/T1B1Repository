package com.pofsoft.t1b1client;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class SelectBoulder extends Activity 
{
	SharedPreferences sharedPreferences;
	private RadioGroup numberOfBouldersGroup;
	private MatchData globalMatchData;
	private Button okButton;
	private Button cancelButton;
	int activeBoulder;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_boulder);
        sharedPreferences = getPreferences (MODE_PRIVATE);
            
        numberOfBouldersGroup = (RadioGroup) findViewById(R.id.bouldersRadioGroup);
        globalMatchData = ((MatchData)getApplicationContext());

        activeBoulder = globalMatchData.getBoulderId();
        numberOfBouldersGroup.check(activeBoulder);
        
        okButton = (Button) findViewById(R.id.okButton);       
        okButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		if (activeBoulder == 0)
        		{
        			globalMatchData.setBoulderInfoDefinedOnServer();       			
        		}
        		else
        		{
        			globalMatchData.resetBoulderInfoDefinedOnServer();
        		}
        		globalMatchData.setBoulderId(activeBoulder);
        		finish();
        	}
        });
        
        cancelButton = (Button) findViewById(R.id.cancelButton);       
        cancelButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v) 
        	{ 
        		finish();
        	}
        });
                
        switch(getBoulderId())
        {
        	case 1:
        		numberOfBouldersGroup.check(R.id.oneBoulder);
        		break;
        	case 2:
        		numberOfBouldersGroup.check(R.id.twoBoulders);
        		break;    
        	case 3:
        		numberOfBouldersGroup.check(R.id.threeBoulders);
        		break;
        	case 4:
        		numberOfBouldersGroup.check(R.id.fourBoulders);
        		break;  
        	case 0:
        		numberOfBouldersGroup.check(R.id.allBoulders);
        		break;		
       		
        }
   
        numberOfBouldersGroup.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {

	       	public void onCheckedChanged(RadioGroup arg0, int arg1) 
	       	{
				switch(arg1)
				{
					case R.id.oneBoulder:
						activeBoulder = 1;
						break;
					case R.id.twoBoulders:
						activeBoulder = 2;
						break;
					case R.id.threeBoulders:
						activeBoulder = 3;
						break;
					case R.id.fourBoulders:
						activeBoulder = 4;
						break;	
					case R.id.allBoulders:
						activeBoulder = 0;
						break;							
					default:
						break;
				};
	       	};
        });
    };
    

    
    private int getBoulderId()
    {     	
       return (globalMatchData.getBoulderId());      
    }
};
