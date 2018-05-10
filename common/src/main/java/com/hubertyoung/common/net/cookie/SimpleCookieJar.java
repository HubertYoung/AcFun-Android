package com.hubertyoung.common.net.cookie;//package com.kento.common.net.cookie;
//
//import android.content.Context;
//
//import java.util.List;
//
//import okhttp3.Cookie;
//import okhttp3.CookieJar;
//import okhttp3.HttpUrl;
//
//public class SimpleCookieJar implements CookieJar {
//
//    private static PersistentCookieStore cookieStore;
//    private Context mContext;
//
//    public SimpleCookieJar( Context context ) {
//        this.mContext = context;
//        if ( cookieStore == null ) {
//            cookieStore = new PersistentCookieStore( mContext );
//        }
//    }
//
//    @Override
//    public synchronized void saveFromResponse( HttpUrl url, List< Cookie > cookies ) {
//        if ( cookies != null && cookies.size() > 0 ) {
//            for ( Cookie item : cookies ) {
//                cookieStore.add( url, item );
//            }
//        }
//    }
//
//
//    @Override
//    public synchronized List< Cookie > loadForRequest( HttpUrl url ) {
//        List< Cookie > cookies = cookieStore.get( url );
//        return cookies;
//    }
//}
