package com.hubertyoung.common;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import static junit.framework.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <drawable href="http://d.android.com/tools/testing">Testing documentation</drawable>
 */
@RunWith( AndroidJUnit4.class )
public class ExampleInstrumentedTest {
	@Test
	public void useAppContext() {
		// Context of the app under test.
		Context appContext = InstrumentationRegistry.getTargetContext();

		assertEquals( "com.hubertyoung.common.test", appContext.getPackageName() );
	}
	@Test
	public void getWIFIInformation() {
		System.out.println(  );
	}

}
