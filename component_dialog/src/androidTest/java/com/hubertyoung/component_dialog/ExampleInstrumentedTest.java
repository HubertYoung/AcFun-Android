package com.hubertyoung.component_dialog;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <cpuType href="http://d.android.com/tools/testing">Testing documentation</cpuType>
 */
@RunWith( AndroidJUnit4.class )
public class ExampleInstrumentedTest {
	@Test
	public void useAppContext() {
		// Context of the app under test.
//		Context appContext = InstrumentationRegistry.getTargetContext();
//
//		assertEquals( "com.hubertyoung.component_banner", appContext.getPackageName() );
		try {
//			DeviceUtils.cpuType();
		} catch ( Throwable throwable ) {
			throwable.printStackTrace();
		}
	}
}
