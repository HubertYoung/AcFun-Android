package com.hubertyoung.component_backgroundjob;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Looper;
import android.support.test.runner.AndroidJUnit4;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.utils.os.ClipboardUtils;

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
	@Test
	public void testClipboardUtils(){
		Looper.prepare();
		ClipboardUtils.copyText( "hhhh" );
		ClipboardManager clipboard = (ClipboardManager) CommonApplication.getAppContext().getSystemService( Context.CLIPBOARD_SERVICE);
		clipboard.setPrimaryClip( ClipData.newPlainText("hhhh", "22222"));
		ClipboardUtils.getText();
	}
}
