package com.pofsoft.t1b1client.test;

import com.pofsoft.t1b1client.ActiveScore;

import junit.framework.TestCase;

public class TestActiveScore extends TestCase {
	ActiveScore mClassToTest;

    protected void setUp() throws Exception {
            mClassToTest= new ActiveScore();

            super.setUp();
    }

    protected void tearDown() throws Exception {
            super.tearDown();
    }
   
    public void testTopWithoutAttempt(){

    	mClassToTest.setTopReached();
    	assertEquals(true, mClassToTest.getTopReached());
    	assertEquals(true, mClassToTest.getBonusReached());
    	assertEquals(1, mClassToTest.getAttempts());
    	assertEquals(1, mClassToTest.getAttemptsToTop());
    	assertEquals(1, mClassToTest.getAttemptsToBonus());
    	assertEquals(true, mClassToTest.getFinished());
    }
    
    public void testTopWithOneAttempt(){
  
    	mClassToTest.addAttempt();
    	mClassToTest.setTopReached();
    	assertEquals(true, mClassToTest.getTopReached());
    	assertEquals(true, mClassToTest.getBonusReached());
    	assertEquals(1, mClassToTest.getAttempts());
    	assertEquals(1, mClassToTest.getAttemptsToTop());
    	assertEquals(1, mClassToTest.getAttemptsToBonus());
    }    
    
    public void testTopWithMoreAttempt(){
    	mClassToTest.addAttempt();
    	mClassToTest.addAttempt();
    	mClassToTest.addAttempt();

    	mClassToTest.setTopReached();
    	assertEquals(true, mClassToTest.getTopReached());
    	assertEquals(true, mClassToTest.getBonusReached());
    	assertEquals(3, mClassToTest.getAttempts());
    	assertEquals(3, mClassToTest.getAttemptsToTop());
    	assertEquals(3, mClassToTest.getAttemptsToBonus());
    }     
    
    public void testBonusWithoutAttempt(){
    	mClassToTest.setBonusReached();
    	assertEquals(false, mClassToTest.getTopReached());
    	assertEquals(true, mClassToTest.getBonusReached());
    	assertEquals(1, mClassToTest.getAttempts());
    	assertEquals(0, mClassToTest.getAttemptsToTop());
    	assertEquals(1, mClassToTest.getAttemptsToBonus());
    }
    
    public void testBonusWithOneAttempt(){
    	mClassToTest.addAttempt();
    	mClassToTest.setBonusReached();
    	assertEquals(false, mClassToTest.getTopReached());
    	assertEquals(true, mClassToTest.getBonusReached());
    	assertEquals(1, mClassToTest.getAttempts());
    	assertEquals(0, mClassToTest.getAttemptsToTop());
    	assertEquals(1, mClassToTest.getAttemptsToBonus());
    }
    
    public void testBonusWithMoreAttempt(){
    	mClassToTest.addAttempt();
    	mClassToTest.addAttempt();

    	mClassToTest.setBonusReached();
    	assertEquals(false, mClassToTest.getTopReached());
    	assertEquals(true, mClassToTest.getBonusReached());
    	assertEquals(2, mClassToTest.getAttempts());
    	assertEquals(0, mClassToTest.getAttemptsToTop());
    	assertEquals(2, mClassToTest.getAttemptsToBonus());
    }
    
    public void testTopAndBonusWithoutAttempt()
    {
    	this.testBonusWithoutAttempt();
    	this.testTopWithoutAttempt();
    	assertEquals(true, mClassToTest.getTopReached());
    	assertEquals(true, mClassToTest.getBonusReached());
    	assertEquals(1, mClassToTest.getAttempts());
    	assertEquals(1, mClassToTest.getAttemptsToTop());
    	assertEquals(1, mClassToTest.getAttemptsToBonus());
    }
    
    public void testTopAfterBonusWithoutAttempts()
    {
    	this.testBonusWithoutAttempt();
    	mClassToTest.addAttempt();
    	mClassToTest.setTopReached();
    	assertEquals(true, mClassToTest.getTopReached());
    	assertEquals(true, mClassToTest.getBonusReached());
    	assertEquals(2, mClassToTest.getAttempts());
    	assertEquals(2, mClassToTest.getAttemptsToTop());
    	assertEquals(1, mClassToTest.getAttemptsToBonus());   	
    }
    
    public void testTopWithAttAndBonusWithAtt()
    {
    	testBonusWithOneAttempt();
    	mClassToTest.addAttempt();
    	mClassToTest.setTopReached();
    	assertEquals(true, mClassToTest.getTopReached());
    	assertEquals(true, mClassToTest.getBonusReached());
    	assertEquals(2, mClassToTest.getAttempts());
    	assertEquals(2, mClassToTest.getAttemptsToTop());
    	assertEquals(1, mClassToTest.getAttemptsToBonus());  
    }
    
    public void testUndoTopWithoutAttempt()
    {
    	testTopWithoutAttempt();
    	mClassToTest.undo();
    	assertEquals(true, mClassToTest.getTopReached());
    	assertEquals(true, mClassToTest.getBonusReached());
    	assertEquals(1, mClassToTest.getAttempts());
    	assertEquals(1, mClassToTest.getAttemptsToTop());
    	assertEquals(1, mClassToTest.getAttemptsToBonus());   	
    }
    
    public void testUndoBonusWithoutAttempt()
    {
    	testBonusWithoutAttempt();
    	mClassToTest.undo();
    	assertEquals(false, mClassToTest.getTopReached());
    	assertEquals(false, mClassToTest.getBonusReached());
    	assertEquals(1, mClassToTest.getAttempts());
    	assertEquals(0, mClassToTest.getAttemptsToTop());
    	assertEquals(0, mClassToTest.getAttemptsToBonus());   	
    }
    
    public void testUndoTopWithAttempt()
    {
    	testTopWithOneAttempt();
    	mClassToTest.undo();
    	assertEquals(true, mClassToTest.getTopReached());
    	assertEquals(true, mClassToTest.getBonusReached());
    	assertEquals(1, mClassToTest.getAttempts());
    	assertEquals(1, mClassToTest.getAttemptsToTop());
    	assertEquals(1, mClassToTest.getAttemptsToBonus());   	
    }
    
    public void testUndoBonusWithAttempt()
    {
    	testBonusWithOneAttempt();
    	mClassToTest.undo();
    	assertEquals(false, mClassToTest.getTopReached());
    	assertEquals(false, mClassToTest.getBonusReached());
    	assertEquals(1, mClassToTest.getAttempts());
    	assertEquals(0, mClassToTest.getAttemptsToTop());
    	assertEquals(0, mClassToTest.getAttemptsToBonus());   	
    }
    
}
