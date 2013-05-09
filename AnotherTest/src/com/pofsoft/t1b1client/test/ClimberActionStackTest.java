package com.pofsoft.t1b1client.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pofsoft.t1b1client.ClimberActionStack;

public class ClimberActionStackTest {

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
	public final void testAddAttempt() {
		ClimberActionStack stack = new ClimberActionStack();
		stack.addAttempt();
		assertEquals(ClimberActionStack.AttemptStarted, stack.undo());
		assertEquals(ClimberActionStack.NothingToUndo, stack.undo());
		assertEquals(ClimberActionStack.NothingToUndo, stack.undo());
	}

	@Test
	public final void testAddBonus() {
		ClimberActionStack stack = new ClimberActionStack();
		stack.addBonus();
		assertEquals(ClimberActionStack.BonusReached, stack.undo());
		assertEquals(ClimberActionStack.NothingToUndo, stack.undo());
		assertEquals(ClimberActionStack.NothingToUndo, stack.undo());
	}

	@Test
	public final void testAddTop() {
		ClimberActionStack stack = new ClimberActionStack();
		stack.addTop();
		assertEquals(ClimberActionStack.TopReached, stack.undo());
		assertEquals(ClimberActionStack.NothingToUndo, stack.undo());
		assertEquals(ClimberActionStack.NothingToUndo, stack.undo());
	}

	@Test
	public final void testAddFinished() {
		ClimberActionStack stack = new ClimberActionStack();
		stack.addFinished();
		assertEquals(ClimberActionStack.Finished, stack.undo());
		assertEquals(ClimberActionStack.NothingToUndo, stack.undo());
		assertEquals(ClimberActionStack.NothingToUndo, stack.undo());
	}

	@Test
	public final void testClear() {
		ClimberActionStack stack = new ClimberActionStack();
		stack.clear();
		assertEquals(ClimberActionStack.NothingToUndo, stack.undo());
		testAddFinished();
		testAddTop();
		testAddBonus();
		stack.clear();
		assertEquals(ClimberActionStack.NothingToUndo, stack.undo());	
		stack.addBonus();
		assertEquals(ClimberActionStack.BonusReached, stack.undo());
	}

	@Test
	public final void testUndo() {
		ClimberActionStack stack = new ClimberActionStack();
		stack.addFinished();
		stack.addTop();
		stack.addBonus();
		assertEquals(ClimberActionStack.BonusReached, stack.undo());
		assertEquals(ClimberActionStack.TopReached, stack.undo());
		assertEquals(ClimberActionStack.Finished, stack.undo());		
		assertEquals(ClimberActionStack.NothingToUndo, stack.undo());		
	}	
	
}
