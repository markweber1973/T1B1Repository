package com.pofsoft.t1b1client.test;

import com.pofsoft.t1b1client.Climber;
import com.pofsoft.t1b1client.PolePositionedClimber;

import junit.framework.TestCase;

public class TestPolePositionedClimber extends TestCase {
    PolePositionedClimber mClassToTest;

    protected void setUp() throws Exception {
         
            super.setUp();
    }

    protected void tearDown() throws Exception {
            super.tearDown();
    }
   
    public void testConstructor(){
    	Climber climber = new Climber(1, "Mark", "Weber");
    	PolePositionedClimber polePosClimber = new PolePositionedClimber(climber, 1);
    	
        assertEquals(true,(polePosClimber != null));
    }
    
    public void testComparators()
    {
    	Climber smallClimber = new Climber(11, "Mark", "Weber");
    	PolePositionedClimber smallPolePosClimber = new PolePositionedClimber(smallClimber, 1);   	
    	
    	Climber bigClimber = new Climber(2, "Hanneke", "van den Boogerd");
    	PolePositionedClimber bigPolePosClimber = new PolePositionedClimber(bigClimber, 2);  
    	
    	Climber bigEqualClimber = new Climber(2, "Hanneke", "Tanneke");
    	PolePositionedClimber bigEqualPolePosClimber = new PolePositionedClimber(bigEqualClimber, 2);
    	
    	assertEquals(true, (smallPolePosClimber.compareTo(bigPolePosClimber) == -1));
    	assertEquals(true, (bigPolePosClimber.compareTo(smallPolePosClimber) == 1));
    	assertEquals(true, (bigPolePosClimber.compareTo(bigEqualPolePosClimber) == 0));

    }
    


}
