package com.hubertyoung.component_host;


import android.os.Environment;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

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
		String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"Phantom";
		System.out.println(absolutePath);
		try {
//			DeviceUtils.cpuType();
		} catch ( Throwable throwable ) {
			throwable.printStackTrace();
		}
	}
}
