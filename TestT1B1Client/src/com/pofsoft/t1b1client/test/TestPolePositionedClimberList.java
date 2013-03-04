package com.pofsoft.t1b1client.test;

import java.util.Vector;

import com.pofsoft.t1b1client.Climber;
import com.pofsoft.t1b1client.PolePositionedClimber;
import com.pofsoft.t1b1client.PolePositionedClimberList;

import junit.framework.TestCase;

public class TestPolePositionedClimberList extends TestCase {
    PolePositionedClimberList mClassToTest;

    protected void setUp() throws Exception {
         
            super.setUp();
    }

    protected void tearDown() throws Exception {
            super.tearDown();
    }
        
    public void testConstructor(){
    	PolePositionedClimberList mClassToTest = new PolePositionedClimberList();

        assertEquals(true,(mClassToTest != null));
    }    
    
    public void testAddToList(){
    	mClassToTest = new PolePositionedClimberList();

    	Climber smallClimber = new Climber(11, "Mark", "Weber");
    	PolePositionedClimber onePolePosClimber = new PolePositionedClimber(smallClimber, 1);   	
    	
    	Climber bigClimber = new Climber(2, "Hanneke", "van den Boogerd");
    	PolePositionedClimber twoPolePosClimber = new PolePositionedClimber(bigClimber, 2);  
    	
    	Climber bigEqualClimber = new Climber(2, "Hanneke", "Tanneke");
    	PolePositionedClimber threeEqualPolePosClimber = new PolePositionedClimber(bigEqualClimber, 3);
    	
    	mClassToTest.add(twoPolePosClimber);
    	mClassToTest.add(threeEqualPolePosClimber);
    	mClassToTest.add(onePolePosClimber);

        assertEquals(true,(mClassToTest != null));
    }
    
    public void testSortedList()
    {
    	mClassToTest = new PolePositionedClimberList();
    	
    	Climber smallClimber = new Climber(11, "Mark", "Weber");
    	PolePositionedClimber onePolePosClimber = new PolePositionedClimber(smallClimber, 1);   	
    	
    	Climber bigClimber = new Climber(2, "Hanneke", "van den Boogerd");
    	PolePositionedClimber twoPolePosClimber = new PolePositionedClimber(bigClimber, 2);  
    	
    	Climber bigEqualClimber = new Climber(2, "Hanneke", "Tanneke");
    	PolePositionedClimber threePolePosClimber = new PolePositionedClimber(bigEqualClimber, 3);
    	
    	mClassToTest.add(twoPolePosClimber);
    	mClassToTest.add(threePolePosClimber);
    	mClassToTest.add(onePolePosClimber);

    	Vector<PolePositionedClimber> list = mClassToTest.getList();
    	
    	assertEquals(onePolePosClimber,list.firstElement());
    	assertEquals(threePolePosClimber,list.lastElement());
    	assertEquals(twoPolePosClimber,list.get(1));
    }
    
    public void testIterateThroughList()
    {
    	mClassToTest = new PolePositionedClimberList();
    	
    	Climber smallClimber = new Climber(11, "Mark", "Weber");
    	PolePositionedClimber onePolePosClimber = new PolePositionedClimber(smallClimber, 1);   	
    	
    	Climber bigClimber = new Climber(2, "Hanneke", "van den Boogerd");
    	PolePositionedClimber twoPolePosClimber = new PolePositionedClimber(bigClimber, 2);  
    	
    	Climber bigEqualClimber = new Climber(2, "Hanneke", "Tanneke");
    	PolePositionedClimber threePolePosClimber = new PolePositionedClimber(bigEqualClimber, 3);
    	
    	mClassToTest.add(twoPolePosClimber);
    	mClassToTest.add(onePolePosClimber);
    	mClassToTest.add(threePolePosClimber);

    	
    	assertEquals(onePolePosClimber, mClassToTest.getCurrent());
    	assertEquals(true, mClassToTest.hasNext());

    	assertEquals(twoPolePosClimber, mClassToTest.getNext());
    	assertEquals(twoPolePosClimber, mClassToTest.getCurrent());
    	assertEquals(threePolePosClimber, mClassToTest.getNext());
    	assertEquals(threePolePosClimber, mClassToTest.getCurrent());
    	assertEquals(false, mClassToTest.hasNext());

    	assertEquals(null, mClassToTest.getNext());
    	assertEquals(threePolePosClimber, mClassToTest.getCurrent());

    	assertEquals(twoPolePosClimber, mClassToTest.getPrevious());
    	assertEquals(twoPolePosClimber, mClassToTest.getCurrent());
    	assertEquals(true, mClassToTest.hasPrevious());

    	assertEquals(onePolePosClimber, mClassToTest.getPrevious());
    	assertEquals(onePolePosClimber, mClassToTest.getCurrent());
    	
    	assertEquals(null, mClassToTest.getPrevious());
    	assertEquals(false, mClassToTest.hasPrevious());
    	assertEquals(onePolePosClimber, mClassToTest.getCurrent());    	
    }
    
    public void testNoAttempt()
    {
    	PolePositionedClimber climber;
    	
    	testAddToList();
    	climber = mClassToTest.getCurrent();
    	assertEquals(false, climber.bonusReached());
    	assertEquals(false, climber.topReached());
    	assertEquals(0, climber.attemptsToBonus());
    	assertEquals(0, climber.attemptsToTop());    	
    	assertEquals(0, climber.attempts());    

    	climber.undo();
    	assertEquals(false, climber.bonusReached());
    	assertEquals(false, climber.topReached());
    	assertEquals(0, climber.attemptsToBonus());
    	assertEquals(0, climber.attemptsToTop());   	
    	assertEquals(0, climber.attempts());    

    }
    
    public void testOneAttempt()
    {
    	PolePositionedClimber climber;
    	
    	testAddToList();
    	climber = mClassToTest.getCurrent();
    	climber.startedAttempt();
    	assertEquals(false, climber.bonusReached());
    	assertEquals(false, climber.topReached());
    	assertEquals(0, climber.attemptsToBonus());
    	assertEquals(0, climber.attemptsToTop());    
    	assertEquals(1, climber.attempts());    

    	climber.undo();
    	assertEquals(false, climber.bonusReached());
    	assertEquals(false, climber.topReached());
    	assertEquals(0, climber.attemptsToBonus());
    	assertEquals(0, climber.attemptsToTop());  
    	assertEquals(0, climber.attempts());    

    }
   
    public void testOneAttemptBonus()
    {
    	PolePositionedClimber climber;
    	
    	testAddToList();
    	climber = mClassToTest.getCurrent();
    	climber.startedAttempt();
    	climber.reachedBonus();
    	assertEquals(true, climber.bonusReached());
    	assertEquals(false, climber.topReached());
    	assertEquals(1, climber.attemptsToBonus());
    	assertEquals(0, climber.attemptsToTop());   
    	assertEquals(1, climber.attempts());    

    	climber.undo();
    	assertEquals(false, climber.bonusReached());
    	assertEquals(false, climber.topReached());
    	assertEquals(0, climber.attemptsToBonus());
    	assertEquals(0, climber.attemptsToTop());    	
    	assertEquals(1, climber.attempts());    

    }    
    
    public void testOneAttemptTop()
    {
    	PolePositionedClimber climber;
    	
    	testAddToList();
    	climber = mClassToTest.getCurrent();
    	climber.startedAttempt();
    	climber.reachedTop();
    	assertEquals(true, climber.bonusReached());
    	assertEquals(true, climber.topReached());
    	assertEquals(1, climber.attemptsToBonus());
    	assertEquals(1, climber.attemptsToTop());    
    	assertEquals(1, climber.attempts());    

    	climber.undo();

    	assertEquals(true, climber.bonusReached());
    	assertEquals(true, climber.topReached());
    	assertEquals(1, climber.attemptsToBonus());
    	assertEquals(1, climber.attemptsToTop()); 
    	assertEquals(1, climber.attempts());    

    	
    	climber.undo();

    	assertEquals(true, climber.bonusReached());
    	assertEquals(false, climber.topReached());
    	assertEquals(1, climber.attemptsToBonus());
    	assertEquals(0, climber.attemptsToTop());
    	assertEquals(1, climber.attempts());    

    	climber.undo();

    	assertEquals(false, climber.bonusReached());
    	assertEquals(false, climber.topReached());
    	assertEquals(0, climber.attemptsToBonus());
    	assertEquals(0, climber.attemptsToTop());
    	assertEquals(1, climber.attempts());   
    } 
    
    public void testOneAttemptBonusTop()
    {
    	PolePositionedClimber climber;
    	
    	testAddToList();
    	climber = mClassToTest.getCurrent();
    	climber.startedAttempt();
    	climber.reachedBonus();
    	climber.reachedTop();
    	assertEquals(true, climber.bonusReached());
    	assertEquals(true, climber.topReached());
    	assertEquals(1, climber.attemptsToBonus());
    	assertEquals(1, climber.attemptsToTop());    
    	
    	climber.undo();

    	assertEquals(true, climber.bonusReached());
    	assertEquals(true, climber.topReached());
    	assertEquals(1, climber.attemptsToBonus());
    	assertEquals(1, climber.attemptsToTop()); 
    	
    	climber.undo();

    	assertEquals(true, climber.bonusReached());
    	assertEquals(false, climber.topReached());
    	assertEquals(1, climber.attemptsToBonus());
    	assertEquals(0, climber.attemptsToTop());    	
    } 
    
    public void testOneAttemptBonusAnotherForTop()
    {
    	PolePositionedClimber climber;
    	
    	testAddToList();
    	climber = mClassToTest.getCurrent();
    	climber.startedAttempt();
    	climber.reachedBonus();
    	climber.startedAttempt();
    	climber.reachedTop();
    	assertEquals(true, climber.bonusReached());
    	assertEquals(true, climber.topReached());
    	assertEquals(1, climber.attemptsToBonus());
    	assertEquals(2, climber.attemptsToTop());    
    	assertEquals(2, climber.attempts());    

    	climber.undo();

    	assertEquals(true, climber.bonusReached());
    	assertEquals(true, climber.topReached());
    	assertEquals(1, climber.attemptsToBonus());
    	assertEquals(2, climber.attemptsToTop()); 
    	assertEquals(2, climber.attempts());    

    	climber.undo();

    	assertEquals(true, climber.bonusReached());
    	assertEquals(false, climber.topReached());
    	assertEquals(1, climber.attemptsToBonus());
    	assertEquals(0, climber.attemptsToTop());   
    	assertEquals(2, climber.attempts());    

    	climber.undo();

    	assertEquals(true, climber.bonusReached());
    	assertEquals(false, climber.topReached());
    	assertEquals(1, climber.attemptsToBonus());
    	assertEquals(0, climber.attemptsToTop()); 
    	assertEquals(1, climber.attempts());    
    	
    	climber.undo();

    	assertEquals(false, climber.bonusReached());
    	assertEquals(false, climber.topReached());
    	assertEquals(0, climber.attemptsToBonus());
    	assertEquals(0, climber.attemptsToTop()); 
    	assertEquals(1, climber.attempts());  

    }
}
