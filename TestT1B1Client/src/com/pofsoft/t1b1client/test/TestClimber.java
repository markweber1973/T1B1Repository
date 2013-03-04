package com.pofsoft.t1b1client.test;

import com.pofsoft.t1b1client.Climber;

import junit.framework.TestCase;

public class TestClimber extends TestCase {
    Climber mClassToTest;
    int mArg1;
    int mArg2;
    protected void setUp() throws Exception {
            mClassToTest= new Climber(1, "Mark", "Weber");

            super.setUp();
    }

    protected void tearDown() throws Exception {
            super.tearDown();
    }
   
    public void testConstructor(){
            assertEquals(1,mClassToTest.startNumber());
            assertEquals("Mark",mClassToTest.firstName());
            assertEquals("Weber",mClassToTest.lastName());
    }
    
    public void testFirstNameSetterGetter()
    {
    	mClassToTest.firstName("Piet");
    	assertEquals("Piet", mClassToTest.firstName());
    }
    
    public void testLastNameSetterGetter()
    {
    	mClassToTest.lastName("Janssen");
    	assertEquals("Janssen", mClassToTest.lastName());
    }
    
    public void testStartNumberSetterGetter()
    {
    	mClassToTest.startNumber(10);
    	assertEquals(10, mClassToTest.startNumber());
    }   
}
