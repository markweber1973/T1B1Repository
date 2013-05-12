package com.pofsoft.t1b1client.test;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pofsoft.t1b1client.Climber;
import com.pofsoft.t1b1client.LiveData;
import com.pofsoft.t1b1client.PolePositionedClimber;
import com.pofsoft.t1b1client.Round;
import com.pofsoft.t1b1client.ScoreSlot;

public class LiveDataTest {

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
		LiveData b1 = new LiveData(1, 2);

    	Climber climbera = new Climber(1, "Truus", "Mier");
    	PolePositionedClimber ppclimbera = new PolePositionedClimber(climbera, 1, 3);	
    	Climber climberb = new Climber(2, "Hanneke", "van den Boogerd");
    	PolePositionedClimber ppclimberb = new PolePositionedClimber(climberb, 2, 3);	
    	Climber climberc = new Climber(3, "Lotje", "lust een ijsje");
    	PolePositionedClimber ppclimberc = new PolePositionedClimber(climberc, 3, 3);	
    	
		Round dames = new Round(1, "Dames", 10, 3, "W");

    	dames.addPolePositionedClimber(ppclimberb);
    	dames.addPolePositionedClimber(ppclimbera);
    	dames.addPolePositionedClimber(ppclimberc);
    	
    	b1.addRound(dames);
    	
    	Climber climberd = new Climber(1, "Billy", "Turf");
    	PolePositionedClimber ppclimberd = new PolePositionedClimber(climberd, 3, 3);	
    	Climber climbere = new Climber(2, "Jerommeke", "uit sis en wis");
    	PolePositionedClimber ppclimbere = new PolePositionedClimber(climbere, 1, 3);	
    	Climber climberf = new Climber(3, "Popeye", "de Zeeman");
    	PolePositionedClimber ppclimberf = new PolePositionedClimber(climberf, 2, 3);	
    	
		Round heren = new Round(2, "Heren", 5, 3, "M");

		heren.addPolePositionedClimber(ppclimberd);
		heren.addPolePositionedClimber(ppclimbere);
		heren.addPolePositionedClimber(ppclimberf);

    	b1.addRound(heren);
    	
    	b1.fillScoreSlots();
    	
    	while (b1.hasNextScoreSlot())
    	{
    		ScoreSlot localSlot = b1.getNextScoreSlot();
    	}

	}	
	
}
