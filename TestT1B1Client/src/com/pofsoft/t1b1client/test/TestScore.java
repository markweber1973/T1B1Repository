package com.pofsoft.t1b1client.test;

import com.pofsoft.t1b1client.Score;

import junit.framework.TestCase;

public class TestScore extends TestCase {
    Score mClassToTest;
    int mArg1;
    int mArg2;
    protected void setUp() throws Exception {
            super.setUp();
    }

    protected void tearDown() throws Exception {
            super.tearDown();
    }
      
    public void testParamterlessConstructor()
    {
    	 	mClassToTest= new Score();	
            assertEquals(false,mClassToTest.getTopReached());
            assertEquals(false,mClassToTest.getBonusReached());
            assertEquals(0,mClassToTest.getAttemptsToTop());
            assertEquals(0,mClassToTest.getAttemptsToBonus());              
    }   
    
    public void testModifiers()
    {
    	 	mClassToTest= new Score();	
    	 	
            assertEquals(false,mClassToTest.getTopReached());
            assertEquals(false,mClassToTest.getBonusReached());
            assertEquals(0,mClassToTest.getAttemptsToTop());
            assertEquals(0,mClassToTest.getAttemptsToBonus());  
    }
      
    public void testTopReached()
    {
    	mClassToTest= new Score();
    	try
    	{
    		mClassToTest.setTopReached();
    	}
    	catch (RuntimeException e)
    	{
    		assertEquals(e.getMessage(), "Reaching top without any attempt impossible");
    	}
    	mClassToTest.setAttemptsToTop(1);
    	mClassToTest.setTopReached();
        assertEquals(true,mClassToTest.getTopReached());
        mClassToTest.resetTopReached();
        assertEquals(false,mClassToTest.getTopReached());
    }
    
    public void testBonusReached()
    {
    	mClassToTest= new Score();
    	try
    	{
    		mClassToTest.setBonusReached();
    	}
    	catch (RuntimeException e)
    	{
    		assertEquals(e.getMessage(), "Reaching bonus without any attempt impossible");
    	}
    	mClassToTest.setAttemptsToBonus(1);
    	mClassToTest.setBonusReached();
        assertEquals(true,mClassToTest.getBonusReached());
        mClassToTest.resetBonusReached();
        assertEquals(false,mClassToTest.getBonusReached());
    }    
       
    public void testSetAttemptsToBonus()
    {
    	mClassToTest= new Score();
    	try
    	{
    		mClassToTest.setAttemptsToBonus(-1);
    	}
    	catch (RuntimeException e)
    	{
    		assertEquals(e.getMessage(), "attempts shall be >= 0");
    	}   	
    	mClassToTest.setAttemptsToBonus(10);
    	assertEquals(10,mClassToTest.getAttemptsToBonus());
    	mClassToTest.setAttemptsToBonus(0);
    	assertEquals(0,mClassToTest.getAttemptsToBonus());	
    }
    
    public void testSetAttemptsToTop()
    {
    	mClassToTest= new Score();
    	try
    	{
    		mClassToTest.setAttemptsToTop(-1);
    	}
    	catch (RuntimeException e)
    	{
    		assertEquals(e.getMessage(), "attempts shall be >= 0");
    	}   	
    	mClassToTest.setAttemptsToTop(10);
    	assertEquals(10,mClassToTest.getAttemptsToTop());
    	mClassToTest.setAttemptsToTop(0);
    	assertEquals(0,mClassToTest.getAttemptsToTop());	
    }
}
