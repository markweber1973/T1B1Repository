package com.pofsoft.t1b1client.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pofsoft.t1b1client.Climber;
import com.pofsoft.t1b1client.PolePositionedClimber;
import com.pofsoft.t1b1client.Score;

public class PolePositionedClimberTest {

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
    	Climber climber = new Climber(1, "Mark", "Weber");
    	PolePositionedClimber polePosClimber = new PolePositionedClimber(climber, 1, 3);
    	
        assertEquals(true,(polePosClimber != null));
    }

	@Test
    public void testAddScore()
    {
    	Climber theClimber = new Climber(1, "Mark", "Weber");
    	PolePositionedClimber thePolePosClimber = new PolePositionedClimber(theClimber, 1, 3);   	   	
    }	
	
	@Test
    public void testComparators()
    {
    	Climber smallClimber = new Climber(11, "Mark", "Weber");
    	PolePositionedClimber smallPolePosClimber = new PolePositionedClimber(smallClimber, 1, 3);   	
    	
    	Climber bigClimber = new Climber(2, "Hanneke", "van den Boogerd");
    	PolePositionedClimber bigPolePosClimber = new PolePositionedClimber(bigClimber, 2, 3);  
    	
    	Climber bigEqualClimber = new Climber(2, "Hanneke", "Tanneke");
    	PolePositionedClimber bigEqualPolePosClimber = new PolePositionedClimber(bigEqualClimber, 2, 3);
    	
    	assertEquals(true, (smallPolePosClimber.compareTo(bigPolePosClimber) == -1));
    	assertEquals(true, (bigPolePosClimber.compareTo(smallPolePosClimber) == 1));
    	assertEquals(true, (bigPolePosClimber.compareTo(bigEqualPolePosClimber) == 0));

    }
	
	@Test
	public void testGetScoreFromVirginClimber() 
	{
		Score theScore = null;
    	Climber climber = new Climber(1, "Mark", "Weber");
    	PolePositionedClimber polePosClimber = new PolePositionedClimber(climber, 0, 3);	
 
		try {
			theScore = polePosClimber.getScore(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertNull("Should be null", theScore);
		}    	
    	
		try {
			theScore = polePosClimber.getScore(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertNull("Should be null", theScore);
		}    	
	}
	
	@Test
	public void testAddScoreToVirginClimber() 
	{		
    	Climber climber = new Climber(1, "Mark", "Weber");
    	PolePositionedClimber polePosClimber = new PolePositionedClimber(climber, 1, 5);	
    	int attempts = 0;
		try {
			polePosClimber.startedAttempt(1);
			attempts = polePosClimber.attempts(1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}  
        assertEquals(1, attempts);
	}
 
}
