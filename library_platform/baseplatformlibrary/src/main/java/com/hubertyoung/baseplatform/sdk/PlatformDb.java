package com.hubertyoung.baseplatform.sdk;//package ddframework.gent.common.framwork.sdk;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//import com.gent.ddpsdk.tools.Hashon;
//import com.gent.ddpsdk.utils.DDPSDKLog;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//import ddframework.gent.common.commonutils.PayLogUtil;
//
///**
// * @author:Yang
// * Time::2017/9/5 14:02
// * @since:v1.0
// * Pkg:com.gent.ddpsdk.framwork.sdk
// * @param:
// */
//public class PlatformDb {
//	private static final String DB_NAME = "cn_ddpsdk_info_db";
//	private SharedPreferences db;
//	private String platformNname;
//	private int platformVersion;
//	private static final String TOKEN = "token";
//	private static final String SECRET = "secret";
//	private static final String EXPIRESIN = "expiresIn";
//	private String EXPIRESTIME = "expiresTime";
//	private String USERID = "userID";
//	private String NICKNAME = "nickname";
//	private String ICON = "icon";
//
//	public PlatformDb( Context context, String name, int version ) {
//		String spName = DB_NAME + "_" + name + "_" + version;
//		this.db = context.getSharedPreferences( spName, 0 );
//		this.platformNname = name;
//		this.platformVersion = version;
//	}
//
//	public void put( String key, String value ) {
//		SharedPreferences.Editor edit = this.db.edit();
//		edit.putString( key, value );
//		edit.commit();
//	}
//
//	public String get( String key ) {
//		return db.getString( key, "" );
//	}
//
//	public String getToken() {
//		return db.getString( TOKEN, "" );
//	}
//
//	public void putToken( String token ) {
//		SharedPreferences.Editor edit = db.edit();
//		edit.putString( TOKEN, token );
//		edit.commit();
//	}
//
//	public String getTokenSecret() {
//		return this.db.getString( SECRET, "" );
//	}
//
//	public void putTokenSecret( String secret ) {
//		SharedPreferences.Editor var2 = db.edit();
//		var2.putString( SECRET, secret );
//		var2.commit();
//	}
//
//	public long getExpiresIn() {
//		long i = 0L;
//
//		try {
//			i = db.getLong( EXPIRESIN, 0L );
//		} catch ( Throwable var6 ) {
//			try {
//				i = ( long ) db.getInt( EXPIRESIN, 0 );
//			} catch ( Throwable var5 ) {
//				i = 0L;
//			}
//		}
//
//		return i;
//	}
//
//	public void putExpiresIn( long expires ) {
//		SharedPreferences.Editor edit = this.db.edit();
//		edit.putLong( EXPIRESIN, expires );
//		edit.putLong( EXPIRESTIME, System.currentTimeMillis() );
//		edit.commit();
//	}
//
//	public long getExpiresTime() {
//		long var1 = this.db.getLong( EXPIRESTIME, 0L );
//		long var3 = this.getExpiresIn();
//		return var1 + var3 * 1000L;
//	}
//
//	public int getPlatformVersion() {
//		return this.platformVersion;
//	}
//
//	public String getPlatformNname() {
//		return this.platformNname;
//	}
//
//	public void putUserId( String platformId ) {
//		SharedPreferences.Editor edit = this.db.edit();
//		edit.putString( USERID, platformId );
//		edit.commit();
//	}
//
//	public String getUserId() {
//		return this.db.getString( USERID, "" );
//	}
//
//	public String getUserName() {
//		return this.db.getString( NICKNAME, "" );
//	}
//
//	public String getUserIcon() {
//		return this.db.getString( ICON, "" );
//	}
//
//	public void removeAccount() {
//		ArrayList list = new ArrayList();
//		Iterator iterator = this.db.getAll()
//								   .entrySet()
//								   .iterator();
//
//		while ( iterator.hasNext() ) {
//			Map.Entry entry = ( Map.Entry ) iterator.next();
//			list.add( entry.getKey() );
//		}
//
//		SharedPreferences.Editor edit = this.db.edit();
//		Iterator listIterator = list.iterator();
//
//		while ( listIterator.hasNext() ) {
//			String s = ( String ) listIterator.next();
//			edit.remove( s );
//		}
//
//		edit.commit();
//	}
//
//	public String exportData() {
//		try {
//			HashMap var1 = new HashMap();
//			var1.putAll( this.db.getAll() );
//			return ( new Hashon() ).fromHashMap( var1 );
//		} catch ( Throwable var2 ) {
//			PayLogUtil.logw( "importData", var2 );
//			return null;
//		}
//	}
//
//	public void importData( String json ) {
//		try {
//			HashMap var2 = ( new Hashon() ).fromJson( json );
//			if ( var2 != null ) {
//				SharedPreferences.Editor var3 = this.db.edit();
//				Iterator var4 = var2.entrySet()
//									.iterator();
//
//				while ( var4.hasNext() ) {
//					Map.Entry var5 = ( Map.Entry ) var4.next();
//					Object var6 = var5.getValue();
//					if ( var6 instanceof Boolean ) {
//						var3.putBoolean( ( String ) var5.getKey(), ( ( Boolean ) var6 ).booleanValue() );
//					} else if ( var6 instanceof Float ) {
//						var3.putFloat( ( String ) var5.getKey(), ( ( Float ) var6 ).floatValue() );
//					} else if ( var6 instanceof Integer ) {
//						var3.putInt( ( String ) var5.getKey(), ( ( Integer ) var6 ).intValue() );
//					} else if ( var6 instanceof Long ) {
//						var3.putLong( ( String ) var5.getKey(), ( ( Long ) var6 ).longValue() );
//					} else {
//						var3.putString( ( String ) var5.getKey(), String.valueOf( var6 ) );
//					}
//				}
//
//				var3.commit();
//			}
//		} catch ( Throwable var7 ) {
//			DDPSDKLog.w( "importData", var7 );
//		}
//
//	}
//
//	public boolean isValid() {
//		String var1 = this.getToken();
//		return var1 != null && var1.length() > 0 ? ( this.getExpiresIn() == 0L ? true : this.getExpiresTime() > System.currentTimeMillis() ) : false;
//	}
//
//	public String getUserGender() {
//		String var1 = this.db.getString( "gender", "2" );
//		return "0".equals( var1 ) ? "m" : ( "1".equals( var1 ) ? "f" : null );
//	}
//}
