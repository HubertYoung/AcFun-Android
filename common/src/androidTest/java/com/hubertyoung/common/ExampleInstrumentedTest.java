package com.hubertyoung.common;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.hubertyoung.common.utils.IPInfomation;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

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
		IPInfomation ipInfomation = new IPInfomation( InstrumentationRegistry.getContext() );
		String macAddress = ipInfomation.getMacAddress();
		String wifiLocalIpAdress = ipInfomation.getWIFILocalIpAdress();
		System.out.println( macAddress );
		System.out.println( wifiLocalIpAdress );
	}

}
