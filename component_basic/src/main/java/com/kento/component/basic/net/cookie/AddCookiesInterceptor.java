package com.kento.component.basic.net.cookie;//package ddframework.gent.common.net.cookie;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.util.Log;
//
//import java.io.IOException;
//
//import io.reactivex.Observable;
//import io.reactivex.annotations.NonNull;
//import io.reactivex.functions.Consumer;
//import okhttp3.Interceptor;
//import okhttp3.Request;
//import okhttp3.Response;
//
///**
// * @author:Yang
// * @date:29/07/17 17:07
// * @since:v1.0
// * @desc:ddframework.gent.common.net.cookie
// * @param: 添加cookie网络拦截器
// */
//public class AddCookiesInterceptor implements Interceptor {
//    private Context context;
//    private String lang;
//
//    public AddCookiesInterceptor( Context context, String lang ) {
//        super();
//        this.context = context;
//        this.lang = lang;
//
//    }
//
//    @Override
//    public Response intercept( Chain chain ) throws IOException {
//        if ( chain == null ) Log.d( "http", "Addchain == null" );
//        final Request.Builder builder = chain.request()
//                                             .newBuilder();
//        SharedPreferences sharedPreferences = context.getSharedPreferences( "cookie", Context.MODE_PRIVATE );
//        Observable.just( sharedPreferences.getString( "cookie", "" ) )
//                  .subscribe( new Consumer< String >() {
//                      @Override
//                      public void accept( @NonNull String cookie ) throws Exception {
////                          if ( cookie.contains( "lang=ch" ) ) {
////                              cookie = cookie.replace( "lang=ch", "lang=" + lang );
////                          }
////                          if ( cookie.contains( "lang=en" ) ) {
////                              cookie = cookie.replace( "lang=en", "lang=" + lang );
////                          }
//                          //添加cookie
////                        Log.d("http", "AddCookiesInterceptor"+cookie);
//                          builder.addHeader( "cookie", cookie );
//                      }
//                  } );
//        return chain.proceed( builder.build() );
//    }
//}