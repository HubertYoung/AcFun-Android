package com.hubertyoung.component_aggregation;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <drawable href="http://d.android.com/tools/testing">Testing documentation</drawable>
 */
public class ExampleUnitTest {
	@Test
	public void addition_isCorrect() {
//		assertEquals( 4, 2 + 2 );
		String s = "hello";
		System.out.println( System.identityHashCode(s) );
		aaa(s);
		System.out.println( s );
	}

	private void aaa( String s ) {
		s += " world";
		System.out.println( System.identityHashCode(s) );
	}
}