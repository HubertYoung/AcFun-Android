package com.hubertyoung.common.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/8/14 17:18
 * @since:V$VERSION
 * @desc:com.hubertyoung.common.utils
 */
public class IPInfomation {
	private WifiManager mWifiManager;
	private WifiInfo mWifiInfo;

	public IPInfomation( Context mContext ) {

		//获取wifi服务
		mWifiManager = ( WifiManager ) mContext.getSystemService( Context.WIFI_SERVICE );
		//判断wifi是否开启
		if ( !mWifiManager.isWifiEnabled() ) {
			mWifiManager.setWifiEnabled( true );
		}
		mWifiInfo = mWifiManager.getConnectionInfo();
	}

	public String getWIFILocalIpAdress() {
		int ipAddress = mWifiInfo.getIpAddress();
		return formatIpAddress( ipAddress );
	}

	private static String formatIpAddress( int ipAdress ) {

		return ( ipAdress & 0xFF ) + "." + ( ( ipAdress >> 8 ) & 0xFF ) + "." + ( ( ipAdress >> 16 ) & 0xFF ) + "." + ( ipAdress >> 24 & 0xFF );
	}

	public String getMacAddress() {
		try {
			List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
			for (NetworkInterface nif : all) {
				if (!nif.getName().equalsIgnoreCase("wlan0")) continue;
				byte[] macBytes = nif.getHardwareAddress();
				if (macBytes == null) {
					return "";
				}
				StringBuilder res1 = new StringBuilder();
				for (byte b : macBytes) {
					res1.append(String.format("%02X:",b));
				}
				if (res1.length() > 0) {
					res1.deleteCharAt(res1.length() - 1);
				}
				return res1.toString();
			}
		} catch (Exception ex) {
			return "02:00:00:00:00:00";
		}
		return "02:00:00:00:00:00";
	}

}
