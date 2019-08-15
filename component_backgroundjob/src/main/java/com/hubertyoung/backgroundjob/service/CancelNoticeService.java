package com.hubertyoung.backgroundjob.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;


/**
 *
 */

public class CancelNoticeService extends Service {
    @Nullable
    @Override
    public IBinder onBind( Intent intent ) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand( Intent intent, int flags, int startId ) {

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 ) {
            Notification.Builder builder = new Notification.Builder( this );
//            builder.setSmallIcon( R.mipmap.ic_launcher );
            startForeground( DaemonService.NOTI_ID, builder.build() );
            //开启线程，移除DaemonService弹出的通知
            new Thread( new Runnable() {
                @Override
                public void run() {
                    //延迟1秒
                    SystemClock.sleep( 1000 );
                    //取消CancelNoticeService的前台
                    stopForeground( true );
                    //移移除DaemonService弹出的通知
                    NotificationManager notificationManager = ( NotificationManager ) getSystemService( NOTIFICATION_SERVICE );
                    notificationManager.cancel(DaemonService.CHANNEL_ID_STRING, DaemonService.NOTI_ID );
                    //任务完成结束自己
                    stopSelf();
                }
            } ).start();
        }
        return super.onStartCommand( intent, flags, startId );
    }
}
