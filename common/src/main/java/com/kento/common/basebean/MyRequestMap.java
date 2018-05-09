package com.kento.common.basebean;

import com.kento.common.constant.AppConfig;
import com.kento.common.utils.AppUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 作者：JIUU on 2017/6/25 22:55
 * QQ号：1344393464
 * 作用：自定义map 添加基类请求参数
 */
public class MyRequestMap {

	public HashMap< String, String > map;

	public MyRequestMap() {
		map = new HashMap<>();
		map.put( AppConfig.DID, AppUtils.getDeviceID() );
		map.put( AppConfig.DT, "2" );//1=iphone；2=android；3=pc, 4=wap
//		CPUInfo.getMobileInfo()
//		map.put( AppConfig.DI, "" );
//		map.put( AppConfig.LAT, SPUtils.getSharedStringData( "Latitude" ) );
//		map.put( AppConfig.LON, SPUtils.getSharedStringData( "Longitude" ) );
//		map.put( AppConfig.MT, AppConfig.CHANNELID );
//		 TODO: 30/07/17 这里手机号暂时
//		map.put( AppConfig.MOB, "15241259983" );
		map.put( AppConfig.OST, "android" );
		map.put( AppConfig.OSV, android.os.Build.VERSION.SDK_INT + "" );
		map.put( AppConfig.VER, AppUtils.getAppVersionCode() + "" );
//		map.put( AppConfig.AREAID, SPUtils.getSharedStringData( "SelectedCity" ) );
//		map.put( AppConfig.LANG, SPUtils.getSharedStringData( AppConfig.LANG ) );
	}

	public MyRequestMap put( String key, String value ) {
		map.put( key, value );
		return this;
	}

	public MyRequestMap putAll( Map< String, String > map ) {
		this.map.putAll( map );
		return this;
	}
}
