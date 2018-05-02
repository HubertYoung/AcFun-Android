package com.kento.common.net.cookie;//package ddframework.gent.common.net.cookie;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.util.Log;
//
//import java.io.IOException;
//import java.util.List;
//
//import io.reactivex.Observable;
//import io.reactivex.annotations.NonNull;
//import io.reactivex.functions.Consumer;
//import io.reactivex.functions.Function;
//import okhttp3.Interceptor;
//import okhttp3.Response;
//
///**
// * @author:Yang
// * @date:29/07/17 17:08
// * @since:v1.0
// * @desc:ddframework.gent.common.net.cookie
// * @param: cookie 网络拦截器
// */
//public class ReceivedCookiesInterceptor implements Interceptor {
//    private Context context;
//    SharedPreferences sharedPreferences;
//
//    public ReceivedCookiesInterceptor(Context context) {
//        super();
//        this.context = context;
//        sharedPreferences = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
//    }
//
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        if (chain == null)
//        Log.d("http", "Receivedchain == null");
//        Response originalResponse = chain.proceed(chain.request());
//        Log.d("http", "originalResponse" + originalResponse.toString());
//        if (!originalResponse.headers("set-cookie").isEmpty()) {
//            final StringBuffer cookieBuffer = new StringBuffer();
//            Observable.just(originalResponse.headers("set-cookie"))
//                      .map( new Function< List<String>, List<String> >() {
//                          @Override
//                          public List<String> apply( @NonNull List< String > strings ) throws Exception {
////                              String[] cookieArray = strings.get( 0 ).split(";");
//                              return strings;
//                          }
//                      } )
//                      .subscribe( new Consumer< List<String> >() {
//                          @Override
//                          public void accept( @NonNull List<String> cookie ) throws Exception {
//                              cookieBuffer.append(cookie).append(";");
//                          }
//                      } );
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("cookie", cookieBuffer.toString());
//            Log.d("http", "ReceivedCookiesInterceptor" + cookieBuffer.toString());
//            editor.commit();
//        }
//
//        return originalResponse;
//    }
//}