package com.pofsoft.t1b1client.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pofsoft.t1b1client.Climber;

public class ClimberTest {

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
    	Climber mClassToTest = new Climber(1,"Mark","Weber");
        assertEquals(1,mClassToTest.startNumber());
        assertEquals("Mark",mClassToTest.firstName());
        assertEquals("Weber",mClassToTest.lastName());
	}
	
	@Test
	public void testFirstNameSetterGetter()
	{
		Climber mClassToTest = new Climber(1,"Mark","Weber");
		
		mClassToTest.firstName("Piet");
		assertEquals("Piet", mClassToTest.firstName());
	}
	
	@Test
	public void testLastNameSetterGetter()
	{
		Climber mClassToTest = new Climber(1,"Mark","Weber");
		
		mClassToTest.lastName("Janssen");
		assertEquals("Janssen", mClassToTest.lastName());
	}
	
	@Test
	public void testStartNumberSetterGetter()
	{
		Climber mClassToTest = new Climber(1,"Mark","Weber");
		
		mClassToTest.startNumber(10);
		assertEquals(10, mClassToTest.startNumber());
	}   	
	
}
