package com.pofsoft.t1b1client.test;

import com.pofsoft.t1b1client.Climber;
import com.pofsoft.t1b1client.PolePositionedClimber;
import com.pofsoft.t1b1client.PolePositionedClimberComparator;

import junit.framework.TestCase;

public class TestPolePositionedClimberComparator extends TestCase {
	PolePositionedClimberComparator mClassToTest;

    protected void setUp() throws Exception {
         
            super.setUp();
    }

    protected void tearDown() throws Exception {
            super.tearDown();
    }
    
    public void testComparator()
    {
    	mClassToTest = new PolePositionedClimberComparator();
    	Climber smallClimber = new Climber(11, "Mark", "Weber");
    	    	
    	PolePositionedClimber smallPolePosClimber = new PolePositionedClimber(smallClimber, 1, 5);   	
    	
    	Climber bigClimber = new Climber(2, "Hanneke", "van den Boogerd");
    	PolePositionedClimber bigPolePosClimber = new PolePositionedClimber(bigClimber, 2, 5);  
    	
    	Climber bigEqualClimber = new Climber(2, "Hanneke", "Tanneke");
    	PolePositionedClimber bigEqualPolePosClimber = new PolePositionedClimber(bigEqualClimber, 2, 5);
    	
    	assertEquals(true, (mClassToTest.compare(smallPolePosClimber, bigPolePosClimber)) == -1);
    	assertEquals(true, (mClassToTest.compare(bigEqualPolePosClimber, bigPolePosClimber)) == 0);
    	assertEquals(true, (mClassToTest.compare(bigPolePosClimber, smallPolePosClimber)) == 1);
    }    
}
