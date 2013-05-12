package com.pofsoft.t1b1client.test;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pofsoft.t1b1client.Climber;
import com.pofsoft.t1b1client.PolePositionedClimber;
import com.pofsoft.t1b1client.Round;

public class RoundTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
    public void testConstructor(){
		Round dames = new Round(1, "Dames", 10, 3, "W");

        assertEquals(3,dames.getNrOfBoulders());
        assertEquals(1,dames.getSequence());
        assertEquals("W",dames.getBoulderPrefix());
        assertEquals(10,dames.getRoundId());
        assertEquals("Dames",dames.getName());

	}
	
	@Test
    public void testAddClimbers(){
		Round dames = new Round(1, "Dames", 10, 3, "W");
				
    	Climber climbera = new Climber(1, "Mark", "Weber");
    	PolePositionedClimber ppclimbera = new PolePositionedClimber(climbera, 1, 3);	
    	Climber climberb = new Climber(2, "Hanneke", "van den Boogerd");
    	PolePositionedClimber ppclimberb = new PolePositionedClimber(climberb, 2, 3);	
    	Climber climberc = new Climber(3, "Lotje", "lust een ijsje");
    	PolePositionedClimber ppclimberc = new PolePositionedClimber(climberc, 3, 3);		
    	
    	dames.addPolePositionedClimber(ppclimberb);
    	dames.addPolePositionedClimber(ppclimbera);
    	dames.addPolePositionedClimber(ppclimberc);
     	
    	dames.sort();
    	assertEquals(3,dames.getSize());
    	
    	PolePositionedClimber someone;
    	

    	dames.reset();
    	
    	someone = dames.getNextClimber();
    	assertEquals(someone, ppclimbera); 
    	
    	someone = dames.getNextClimber();
    	assertEquals(someone, ppclimberb);  
 
    	someone = dames.getNextClimber();
    	assertEquals(someone, ppclimberc);      	
    	
    	try
    	{
    		someone = dames.getNextClimber();
    		fail("No exception generated");
    	}
    	catch (RuntimeException e)
    	{   		
        	assertEquals(someone, ppclimberc);  
    	}    	    	  	    	
	}
}
