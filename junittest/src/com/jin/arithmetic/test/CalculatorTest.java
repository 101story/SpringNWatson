package com.jin.arithmetic.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.jin.arithmetic.Calculator;

public class CalculatorTest {
	Calculator c ;
	
	@Before
	public void testStart() {
		c = new Calculator();
	}

	@After
	public void testEnd() {
		c.clear();
		c = null;
	}
	
	@Test
	public void testAdd() {
		c.add(10);
		assertEquals(10, c.getResult());
	}

	@Test
	public void testSub() {
		c.sub(10);
		assertEquals(-10, c.getResult());
	}

	@Test
	public void testMul() {
		c.mul(10);
		assertEquals(0, c.getResult());
	}

	@Test
	public void testDiv() {
		c.div(10);
		assertEquals(0, c.getResult());
	}

	@Ignore
	@Test
	public void testGetResult() {
		fail("Not yet implemented");
	}

}
