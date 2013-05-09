package com.pofsoft.t1b1client.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pofsoft.t1b1client.ActiveScore;

public class ActiveScoreTest {

	ActiveScore mClassToTest;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
        mClassToTest= new ActiveScore();
	}

	@After
	public void tearDown() throws Exception {
		mClassToTest = null;
	}

	@Test
    public void testTopWithoutAttempt(){

    	mClassToTest.setTopReached();
    	assertEquals(true, mClassToTest.getTopReached());
    	assertEquals(true, mClassToTest.getBonusReached());
    	assertEquals(1, mClassToTest.getAttempts());
    	assertEquals(1, mClassToTest.getAttemptsToTop());
    	assertEquals(1, mClassToTest.getAttemptsToBonus());
    	assertEquals(true, mClassToTest.getFinished());
    }
    
	@Test
    public void testTopWithOneAttempt(){
  
    	mClassToTest.addAttempt();
    	mClassToTest.setTopReached();
    	assertEquals(true, mClassToTest.getTopReached());
    	assertEquals(true, mClassToTest.getBonusReached());
    	assertEquals(1, mClassToTest.getAttempts());
    	assertEquals(1, mClassToTest.getAttemptsToTop());
    	assertEquals(1, mClassToTest.getAttemptsToBonus());
    }    
   
	@Test
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
  
	@Test
    public void testBonusWithoutAttempt(){
    	mClassToTest.setBonusReached();
    	assertEquals(false, mClassToTest.getTopReached());
    	assertEquals(true, mClassToTest.getBonusReached());
    	assertEquals(1, mClassToTest.getAttempts());
    	assertEquals(0, mClassToTest.getAttemptsToTop());
    	assertEquals(1, mClassToTest.getAttemptsToBonus());
    }
   
	@Test
    public void testBonusWithOneAttempt(){
    	mClassToTest.addAttempt();
    	mClassToTest.setBonusReached();
    	assertEquals(false, mClassToTest.getTopReached());
    	assertEquals(true, mClassToTest.getBonusReached());
    	assertEquals(1, mClassToTest.getAttempts());
    	assertEquals(0, mClassToTest.getAttemptsToTop());
    	assertEquals(1, mClassToTest.getAttemptsToBonus());
    }
 
	@Test
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
  
	@Test
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
    
	@Test
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
    
	@Test
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
    
	@Test
	public void testUndoTopWithoutAttempt()
    {
    	testTopWithoutAttempt();
    	mClassToTest.undo();
    	assertEquals(false, mClassToTest.getFinished());
    	assertEquals(true, mClassToTest.getTopReached());
    	assertEquals(true, mClassToTest.getBonusReached());
    	assertEquals(1, mClassToTest.getAttempts());
    	assertEquals(1, mClassToTest.getAttemptsToTop());
    	assertEquals(1, mClassToTest.getAttemptsToBonus());   	
    }
    
	@Test
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
   
	@Test
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
	
	@Test
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
	
	@Test
	public void testBoulderId()
	{
		mClassToTest.setBoulderId(1);
		assertEquals(1, mClassToTest.getBoulderId());   	
	}
	
}
