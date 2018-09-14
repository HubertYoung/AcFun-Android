package com.hubertyoung.baseplatform.tools;

import android.app.Application;
import android.text.TextUtils;

import com.hubertyoung.baseplatform.PlatformSDKConfig;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.HashMap;

/**
 * 作者：JIUU on 2017/5/31 17:30
 * QQ号：1344393464
 * 作用：xml解析
 */
public class PlatformXmlPullParser {
	private static String TAG = "PlatformSDKConfig";

	public HashMap< String, HashMap< String, String > > map = new HashMap<>();
	private Application application;

	private HashMap< String, HashMap< String, String > > getMap() {
		if ( map == null ) {
			xmlParser( application );
		}
		return map;
	}

	private static volatile PlatformXmlPullParser xmlPullParserLoginPay;

	public static PlatformXmlPullParser getInstance() {
		if ( xmlPullParserLoginPay == null ) {
			synchronized ( PlatformXmlPullParser.class ) {
				if ( xmlPullParserLoginPay == null ) {
					xmlPullParserLoginPay = new PlatformXmlPullParser();
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
				inputStream = application.getAssets().open( PlatformSDKConfig.FILENAME );
			} catch ( Throwable throwable ) {
				//                e.b(var11);
				PlatformLogUtil.loge( TAG, throwable.toString() );
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

		if ( TextUtils.equals( "2", PlatformXmlPullParser.getInstance().othersType() ) || TextUtils.equals( "3", PlatformXmlPullParser.getInstance().othersType() ) ) {
//			if ( DDPSDKXmlPullParser.getInstance()
//									.isEnableFacebook() ) {
//				FacebookSdk.sdkInitialize( activity );
//			}
			if ( PlatformXmlPullParser.getInstance().isEnableTwitter() ) {


			}
		}

	}

	public String getLanguage() {
		return getMap().get( PlatformSDKConfig.LANGUAGE ).get( PlatformSDKConfig.NAME );
	}

	public String getAppKey() {
		return getMap().get( PlatformSDKConfig.SDKAPPKEY ).get( PlatformSDKConfig.APPKEY );
	}

	/**
	 * @return 0 所有 1 国内 2 国外
	 */
	public String othersType() {
		try {
			return getMap().get( PlatformSDKConfig.OTHERSTYPE ).get( PlatformSDKConfig.TYPE );
		} catch ( Exception e ) {
			return "";
		}
	}

	public boolean isEnablePhone() {
		try {
			return TextUtils.equals( "true", getMap().get( PlatformSDKConfig.PHONE ).get( PlatformSDKConfig.ENABLE ) );
		} catch ( Exception e ) {
			return false;
		}
	}

	public boolean isEnableEmail() {
		try {
			return TextUtils.equals( "true", getMap().get( PlatformSDKConfig.EMAIL ).get( PlatformSDKConfig.ENABLE ) );
		} catch ( Exception e ) {
			return false;
		}
	}

	public boolean isEnableQQ() {
		try {
			return TextUtils.equals( "true", getMap().get( PlatformSDKConfig.QQ ).get( PlatformSDKConfig.ENABLE ) );
		} catch ( Exception e ) {
			return false;
		}
	}

	public String getQQID() {
		try {
			return getMap().get( PlatformSDKConfig.QQ ).get( PlatformSDKConfig.APPID);
		} catch ( Exception e ) {
			PlatformLogUtil.loge( "getQQID", "QQ AppId is null" );
			return "";
		}
	}

	public boolean isEnableWechat() {
		try {
			return TextUtils.equals( "true", getMap().get( PlatformSDKConfig.WECHAT).get(PlatformSDKConfig.ENABLE ) );
		} catch ( Exception e ) {
			return false;
		}
	}

	public String getWechatID() {
		try {
			return getMap().get( PlatformSDKConfig.WECHAT).get( PlatformSDKConfig.APPID );
		} catch ( Exception e ) {
			PlatformLogUtil.loge( "getWechatID", "Wechat AppId is null" );
			return "";
		}
	}

	public String getWechatSecret() {
		try {
			return getMap().get( PlatformSDKConfig.WECHAT ).get( PlatformSDKConfig.APPSECRET);
		} catch ( Exception e ) {
			PlatformLogUtil.loge( "getWechatSecret", "Wechat AppSecret is null" );
			return "";
		}
	}

	public boolean isEnableSinaWeibo() {
		try {
			return TextUtils.equals( "true", getMap().get( PlatformSDKConfig.SINAWEIBO).get( PlatformSDKConfig.ENABLE ) );
		} catch ( Exception e ) {
			return false;
		}
	}

	public String getSinaWeiboKey() {
		try {
			return getMap().get( PlatformSDKConfig.SINAWEIBO).get( PlatformSDKConfig.APPKEY);
		} catch ( Exception e ) {
			PlatformLogUtil.loge( "getSinaWeiboKey", "SinaWeibo AppId is null" );
			return "";
		}
	}

	public String getSinaWeiboRedirectUrl() {
		try {
			return getMap().get( PlatformSDKConfig.SINAWEIBO).get( PlatformSDKConfig.REDIRECTURL);
		} catch ( Exception e ) {
			PlatformLogUtil.loge( "getSinaWeiboKey", "RedirectUrl AppId is null" );
			return "";
		}
	}

	public boolean isEnableFacebook() {
		try {
			return TextUtils.equals( "true", getMap().get( PlatformSDKConfig.FACEBOOK).get( PlatformSDKConfig.ENABLE) );
		} catch ( Exception e ) {
			return false;
		}
	}

	public String getFacebookKey() {
		try {
			return getMap().get( PlatformSDKConfig.FACEBOOK ).get( PlatformSDKConfig.CONSUMERKEY);
		} catch ( Exception e ) {
			PlatformLogUtil.loge( "getFacebookKey", "Facebook ConsumerKey is null" );
			return "";
		}
	}

	public String getFacebookSecret() {
		try {
			return getMap().get( PlatformSDKConfig.FACEBOOK).get( PlatformSDKConfig.CONSUMERSECRET);
		} catch ( Exception e ) {
			PlatformLogUtil.loge( "getFacebookSecret", "Facebook ConsumerSecret is null" );
			return "";
		}
	}

	public boolean isEnableTwitter() {
		try {
			return TextUtils.equals( "true", getMap().get( PlatformSDKConfig.TWITTER).get( PlatformSDKConfig.ENABLE) );
		} catch ( Exception e ) {
			return false;
		}
	}

	public String getTwitterKey() {
		try {
			return getMap().get( PlatformSDKConfig.TWITTER ).get( PlatformSDKConfig.CONSUMERKEY);
		} catch ( Exception e ) {
			PlatformLogUtil.loge( "getTwitterKey", "Twitter ConsumerKey is null" );
			return "";
		}
	}

	public String getTwitterSecret() {
		try {
			return getMap().get( PlatformSDKConfig.TWITTER ).get( PlatformSDKConfig.CONSUMERSECRET);
		} catch ( Exception e ) {
			PlatformLogUtil.loge( "getTwitterSecret", "Twitter ConsumerSecret is null" );
			return "";
		}
	}

	public boolean isEnableGoogle() {
		try {
			return TextUtils.equals( "true", getMap().get( PlatformSDKConfig.GOOGLEPLUS).get( PlatformSDKConfig.ENABLE) );
		} catch ( Exception e ) {
			return false;
		}
	}

	public String getGooglePlusClientID() {
		try {
			return getMap().get( PlatformSDKConfig.GOOGLEPLUS ).get( PlatformSDKConfig.CLIENTID);
		} catch ( Exception e ) {
			PlatformLogUtil.loge( "getGooglePlusClientID", "GooglePlus ClientID is null" );
			return "";
		}
	}

	public String getGooglePlusPublicKey() {
		try {
			return getMap().get( PlatformSDKConfig.GOOGLEPLUS).get( PlatformSDKConfig.BASE64ENCODEDPUBLICKEY);
		} catch ( Exception e ) {
			PlatformLogUtil.loge( "getGooglePlusPublicKey", "GooglePlus base64EncodedPublicKey is null" );
			return "";
		}
	}


}
