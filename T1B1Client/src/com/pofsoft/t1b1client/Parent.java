package com.pofsoft.t1b1client;

import java.util.ArrayList;
import java.util.HashMap;

public class Parent {
    private String mTitle;
    private ArrayList<HashMap<String, String>> mArrayChildren;
    //private ArrayList<HashMap<String, String>> mArrayChildren;
    
    public String getTitle() {
        return mTitle;
    }
 
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
 
    public ArrayList<HashMap<String, String>> getArrayChildren() {
        return mArrayChildren;
    }
 
    public void setArrayChildren(ArrayList<HashMap<String, String>> mArrayChildren) {
        this.mArrayChildren = mArrayChildren;
    }
}