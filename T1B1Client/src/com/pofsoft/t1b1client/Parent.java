package com.pofsoft.t1b1client;

import java.util.ArrayList;
import java.util.HashMap;

import android.widget.CheckBox;

public class Parent {
    private String mTitle;
    private String nrofboulders;
    private String boulderprefix;
    int roundId;
    boolean enabled;
    Round round;
    
    private ArrayList<HashMap<String, String>> mArrayChildren;   
    CheckBox mCheckBox;
    
    public void setCheckBox(CheckBox checkBox)
    {
    	mCheckBox = checkBox;
    }
    
    public void setRound(Round round)
    {
    	this.round = round;
    }
    
    public boolean isSelected()
    {
    	return mCheckBox.isChecked();
    }
    
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
	
	public boolean getEnabled()
	{
		return enabled;
	}    
    
    public int getRoundId()
    {
    	return roundId;
    }
    
    public void setRoundId(int roundId)
    {
    	this.roundId = roundId;
    }
    
    public String getTitle() {
        return mTitle;
    }
 
    public String getNrOfBoulders() {
        return nrofboulders;
    }    
 
    public String getBoulderPrefix() {
        return boulderprefix;
    }       
    
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
 
    public void setNrOfBoulders(String nrofboulders) {
        this.nrofboulders = nrofboulders;
    }
    
    public void setBoulderPrefix(String boulderprefix) {
        this.boulderprefix = boulderprefix;
    }    
        
    public ArrayList<HashMap<String, String>> getArrayChildren() {
        return mArrayChildren;
    }
 
    public void setArrayChildren(ArrayList<HashMap<String, String>> mArrayChildren) {
        this.mArrayChildren = mArrayChildren;
    }
}