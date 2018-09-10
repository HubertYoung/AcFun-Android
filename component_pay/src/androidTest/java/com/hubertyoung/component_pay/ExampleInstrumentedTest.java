package com.hubertyoung.component_pay;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

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

		assertEquals( "com.hubertyoung.component_banner", appContext.getPackageName() );
	}
}
