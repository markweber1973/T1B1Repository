package com.pofsoft.t1b1client.test;

import com.pofsoft.t1b1client.ClimberActionStack;

import junit.framework.TestCase;

public class TestClimberActionStack extends TestCase {
	ClimberActionStack mClassToTest;

    protected void setUp() throws Exception {
            mClassToTest= new ClimberActionStack();

            super.setUp();
    }

    protected void tearDown() throws Exception {
            super.tearDown();
    }
   
    public void testAttemptStarted(){
    	mClassToTest.addAttempt();
    	assertEquals(ClimberActionStack.AttemptStarted, mClassToTest.undo());
    	assertEquals(ClimberActionStack.NothingToUndo, mClassToTest.undo());
    	
    	for (int x = 0; x <= 300; x++)
    	{
    		mClassToTest.addAttempt();
    	}
    	
    	for (int x = 0; x <= 310; x++)
    	{
    		mClassToTest.undo();
    	}
    	
    	assertEquals(ClimberActionStack.NothingToUndo, mClassToTest.undo());
    }
    
    public void testBonusReached(){
    	mClassToTest.addBonus();
    	assertEquals(ClimberActionStack.BonusReached, mClassToTest.undo());
    	assertEquals(ClimberActionStack.NothingToUndo, mClassToTest.undo());
    }
    
    public void testTopReached(){
    	mClassToTest.addTop();
    	assertEquals(ClimberActionStack.TopReached, mClassToTest.undo());
    	assertEquals(ClimberActionStack.NothingToUndo, mClassToTest.undo());
    }
    
    public void testFinished(){
    	mClassToTest.addFinished();
    	assertEquals(ClimberActionStack.Finished, mClassToTest.undo());
    	assertEquals(ClimberActionStack.NothingToUndo, mClassToTest.undo());
    }
    
    public void testClear() {
    	mClassToTest.addFinished();
    	mClassToTest.addAttempt();
    	mClassToTest.addTop();
    	mClassToTest.clear();
    	assertEquals(ClimberActionStack.NothingToUndo, mClassToTest.undo());
    	
    }
}
