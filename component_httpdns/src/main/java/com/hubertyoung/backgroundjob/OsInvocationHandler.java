package com.hubertyoung.backgroundjob;
//       _       _    _     _
//__   _(_)_ __ | | _(_) __| |
//\ \ / / | '_ \| |/ / |/ _` |
// \ V /| | |_) |   <| | (_| |
//  \_/ |_| .__/|_|\_\_|\__,_|
//        |_|

/* guolei2@vipkid.com.cn
 *
 *2018/11/2
 */

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;

public final class OsInvocationHandler implements InvocationHandler {

    private Object mOrigin;
    private Field mAiFlagsField;

    OsInvocationHandler( Object os ) {
        mOrigin = os;
    }

    @Override
    public Object invoke( Object proxy, Method method, Object[] args ) throws Throwable {
        if ( method.getName().equals( "android_getaddrinfo" ) || method.getName().equals( "getaddrinfo" ) ) {
            try {
                if ( mAiFlagsField == null ) {
                    mAiFlagsField = ReflectHelper.getAiFlagsField();
                }
            } catch ( Exception e ) {
                e.printStackTrace();
            }

            int mAiFlagsValue = -1;
            try {
                mAiFlagsValue = ReflectHelper.getAINUMERICHOST();
            } catch ( Exception e ) {
                e.printStackTrace();
            }

            if ( args[ 0 ] instanceof String
                    && mAiFlagsField != null && mAiFlagsValue >= 0
                    && ( ( int ) mAiFlagsField.get( args[ 1 ] ) != mAiFlagsValue ) ) {
                String host = ( String ) args[ 0 ];
                boolean shouldUse = HttpDnsServiceProvider.getInstance().shouldUseHttpDns( host );
                String ip = HttpDnsServiceProvider.getInstance().getHttpDnsStrategy().getIpByHost( host );
                if ( shouldUse ) {
                    if ( HttpDnsServiceProvider.getInstance().getMonitor() != null ) {
                        HttpDnsServiceProvider.getInstance().getMonitor().onHttpDnsParseEnd( host, ip );
                    }
                    if ( !TextUtils.isEmpty( ip ) ) {
                        return InetAddress.getAllByName( ip );
                    }
                }
                try {
                    return method.invoke( mOrigin, args );
                } catch ( InvocationTargetException e ) {
                    throw e.getCause();
                }
            }
        }
        try {
            return method.invoke( mOrigin, args );
        } catch ( InvocationTargetException e ) {
            throw e.getCause();
        }
    }
}
