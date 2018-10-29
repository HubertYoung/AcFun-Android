package com.hubertyoung.baseplatform.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/29 18:17
 * @since:V$VERSION
 * @desc:com.hubertyoung.baseplatform.toos
 */
public class PlatformUtils {
	static final String PACKAGE_QQ = "com.tencent.mobileqq";
	static final String PACKAGE_WX = "com.tencent.mm";

	public static boolean isQQInstalled( Context context ) {
		return isApplicationInstalled( context, PACKAGE_QQ );
	}

	public static boolean isWxInstalled( Context context ) {
		return isApplicationInstalled( context, PACKAGE_WX );
	}

	static boolean  isApplicationInstalled( Context context, String packageName ) {
		PackageManager packageManager = context.getPackageManager();
		List< PackageInfo > list = packageManager.getInstalledPackages( 0 );
		for (int i = 0; i < list.size(); i++) {
			if ( list.get( i ).packageName.equalsIgnoreCase( packageName ) ) {
				return true;
			}
		}
		return false;
	}
}
