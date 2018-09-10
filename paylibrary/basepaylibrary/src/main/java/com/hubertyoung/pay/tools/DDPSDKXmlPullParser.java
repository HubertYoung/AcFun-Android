package com.hubertyoung.pay.tools;

import android.app.Application;
import android.text.TextUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.HashMap;

/**
 * 作者：JIUU on 2017/5/31 17:30
 * QQ号：1344393464
 * 作用：xml解析
 */
public class DDPSDKXmlPullParser {
	private static String TAG = "DDPSDKXmlPullParser";

	public HashMap< String, HashMap< String, String > > map = new HashMap<>();
	private Application application;

	private HashMap< String, HashMap< String, String > > getMap() {
		if ( map == null ) {
			xmlParser( application );
		}
		return map;
	}

	private static volatile DDPSDKXmlPullParser xmlPullParserLoginPay;

	public static DDPSDKXmlPullParser getInstance() {
		if ( xmlPullParserLoginPay == null ) {
			synchronized ( DDPSDKXmlPullParser.class ) {
				if ( xmlPullParserLoginPay == null ) {
					xmlPullParserLoginPay = new DDPSDKXmlPullParser();
				}
			}
		}
		return xmlPullParserLoginPay;
	}

	public void xmlParser( Application application ) {
		this.application = application;
		try {
			XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
			xmlPullParserFactory.setNamespaceAware( true );
			XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
			InputStream inputStream = null;

			try {
				inputStream = application.getAssets()
									  .open( "DDPSDKConfig.xml" );
			} catch ( Throwable throwable ) {
				//                e.b(var11);
				PayLogUtil.loge( TAG, throwable.toString() );
				//                inputStream = context.getAssets().open("ShareSDK.conf");
			}

			xmlPullParser.setInput( inputStream, "utf-8" );

			for (int i = xmlPullParser.getEventType(); i != 1; i = xmlPullParser.next()) {
				if ( i == 2 ) {
					String name = xmlPullParser.getName();
					HashMap hashMap = new HashMap();
					int attributeCount = xmlPullParser.getAttributeCount();

					for (int j = 0; j < attributeCount; ++j) {
						String attributeName = xmlPullParser.getAttributeName( j );
						String attributeValue = xmlPullParser.getAttributeValue( j );
						hashMap.put( attributeName, attributeValue );
					}

					map.put( name, hashMap );
				}
			}

			inputStream.close();
		} catch ( Throwable var12 ) {
			// TODO: 2017/9/5 崩溃日志上传
		}

		if ( TextUtils.equals( "2", DDPSDKXmlPullParser.getInstance()
													   .othersType() ) || TextUtils.equals( "3", DDPSDKXmlPullParser.getInstance()
																													.othersType() ) ) {
//			if ( DDPSDKXmlPullParser.getInstance()
//									.isEnableFacebook() ) {
//				FacebookSdk.sdkInitialize( activity );
//			}
			if ( DDPSDKXmlPullParser.getInstance()
									.isEnableTwitter() ) {


			}
		}

	}

	public String getLanguage() {
		return getMap().get( "Language" )
					   .get( "Name" );
	}

	public String getAppKey() {
		return getMap().get( "DDPSDKAppKey" )
					   .get( "AppKey" );
	}

	/**
	 * @return 0 所有 1 国内 2 国外
	 */
	public String othersType() {
		try {
			return getMap().get( "OthersType" )
						   .get( "Type" );
		} catch ( Exception e ) {
			return "";
		}
	}

	public boolean isEnablePhone() {
		try {
			return TextUtils.equals( "true", getMap().get( "Phone" )
													 .get( "Enable" ) );
		} catch ( Exception e ) {
			return false;
		}
	}

	public boolean isEnableEmail() {
		try {
			return TextUtils.equals( "true", getMap().get( "Email" )
													 .get( "Enable" ) );
		} catch ( Exception e ) {
			return false;
		}
	}

	public boolean isEnableQQ() {
		try {
			return TextUtils.equals( "true", getMap().get( "QQ" )
													 .get( "Enable" ) );
		} catch ( Exception e ) {
			return false;
		}
	}

	public String getQQID() {
		try {
			return getMap().get( "QQ" )
						   .get( "AppId" );
		} catch ( Exception e ) {
			PayLogUtil.loge( "getQQID", "QQ AppId is null" );
			return "";
		}
	}

	public boolean isEnableWechat() {
		try {
			return TextUtils.equals( "true", getMap().get( "Wechat" )
													 .get( "Enable" ) );
		} catch ( Exception e ) {
			return false;
		}
	}

	public String getWechatID() {
		try {
			return getMap().get( "Wechat" )
						   .get( "AppId" );
		} catch ( Exception e ) {
			PayLogUtil.loge( "getWechatID", "Wechat AppId is null" );
			return "";
		}
	}

	public String getWechatSecret() {
		try {
			return getMap().get( "Wechat" )
						   .get( "AppSecret" );
		} catch ( Exception e ) {
			PayLogUtil.loge( "getWechatSecret", "Wechat AppSecret is null" );
			return "";
		}
	}

	public boolean isEnableSinaWeibo() {
		try {
			return TextUtils.equals( "true", getMap().get( "SinaWeibo" )
													 .get( "Enable" ) );
		} catch ( Exception e ) {
			return false;
		}
	}

	public String getSinaWeiboKey() {
		try {
			return getMap().get( "SinaWeibo" )
						   .get( "AppKey" );
		} catch ( Exception e ) {
			PayLogUtil.loge( "getSinaWeiboKey", "SinaWeibo AppId is null" );
			return "";
		}
	}

	public String getSinaWeiboRedirectUrl() {
		try {
			return getMap().get( "SinaWeibo" )
						   .get( "RedirectUrl" );
		} catch ( Exception e ) {
			PayLogUtil.loge( "getSinaWeiboKey", "RedirectUrl AppId is null" );
			return "";
		}
	}

	public boolean isEnableFacebook() {
		try {
			return TextUtils.equals( "true", getMap().get( "Facebook" )
													 .get( "Enable" ) );
		} catch ( Exception e ) {
			return false;
		}
	}

	public String getFacebookKey() {
		try {
			return getMap().get( "Facebook" )
						   .get( "ConsumerKey" );
		} catch ( Exception e ) {
			PayLogUtil.loge( "getFacebookKey", "Facebook ConsumerKey is null" );
			return "";
		}
	}

	public String getFacebookSecret() {
		try {
			return getMap().get( "Facebook" )
						   .get( "ConsumerSecret" );
		} catch ( Exception e ) {
			PayLogUtil.loge( "getFacebookSecret", "Facebook ConsumerSecret is null" );
			return "";
		}
	}

	public boolean isEnableTwitter() {
		try {
			return TextUtils.equals( "true", getMap().get( "Twitter" )
													 .get( "Enable" ) );
		} catch ( Exception e ) {
			return false;
		}
	}

	public String getTwitterKey() {
		try {
			return getMap().get( "Twitter" )
						   .get( "ConsumerKey" );
		} catch ( Exception e ) {
			PayLogUtil.loge( "getTwitterKey", "Twitter ConsumerKey is null" );
			return "";
		}
	}

	public String getTwitterSecret() {
		try {
			return getMap().get( "Twitter" )
						   .get( "ConsumerSecret" );
		} catch ( Exception e ) {
			PayLogUtil.loge( "getTwitterSecret", "Twitter ConsumerSecret is null" );
			return "";
		}
	}

	public boolean isEnableGoogle() {
		try {
			return TextUtils.equals( "true", getMap().get( "GooglePlus" )
													 .get( "Enable" ) );
		} catch ( Exception e ) {
			return false;
		}
	}

	public String getGooglePlusClientID() {
		try {
			return getMap().get( "GooglePlus" )
						   .get( "ClientID" );
		} catch ( Exception e ) {
			PayLogUtil.loge( "getGooglePlusClientID", "GooglePlus ClientID is null" );
			return "";
		}
	}

	public String getGooglePlusPublicKey() {
		try {
			return getMap().get( "GooglePlus" )
						   .get( "base64EncodedPublicKey" );
		} catch ( Exception e ) {
			PayLogUtil.loge( "getGooglePlusPublicKey", "GooglePlus base64EncodedPublicKey is null" );
			return "";
		}
	}


}
