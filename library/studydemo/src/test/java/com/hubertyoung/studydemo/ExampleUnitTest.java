package com.hubertyoung.studydemo;

import com.hubertyoung.studydemo.sort.BubbleSort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
	@Test
	public void addition_isCorrect() {
		assertEquals( 4, 2 + 2 );
	}
	@Test
	public void bubbleSort() {
		BubbleSort sort = new BubbleSort();
		int[] sourceArray = new int[]{3,4,7,89,1,3,42};
		int[] sort1 = sort.sort( sourceArray );
	}

}