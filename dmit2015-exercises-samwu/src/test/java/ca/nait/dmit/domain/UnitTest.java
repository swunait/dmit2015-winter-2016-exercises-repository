package ca.nait.dmit.domain;

import static org.junit.Assert.*;
import org.junit.Test;

public class UnitTest {

	@Test
	public void testPowerOperator() {
		assertEquals( 8 , Math.pow(2, 3), 0 );
	}
	
	@Test
	public void testDivision() {
		assertEquals( 0.8, 4.0 / 5, 0 );
	}
	
	@Test
	public void testStringCompare() {
//		assertEquals("DMIT2015","dmit2015");
		assertTrue( "DMIT2015" == "DMIT2015");
	}
	
	@Test(expected=java.lang.ArithmeticException.class)
	public void testForException() {
		assertEquals( 0, 3 / 0 );
	}
	
//	@Test(timeout=1000)
//	public void testInfiniteLoop() {
//		while( true );
//	}
}
