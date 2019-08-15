package com.hubertyoung.backgroundjob.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.component_backgroundjob.R;


/**
 * * 利用双service进行notification绑定，进而将Service的OOM_ADJ提高到1
 * * 同时利用LocationHelperService充当守护进程，在NotiService被关闭后，重启他。（如果LocationHelperService被停止，NotiService不负责唤醒)
 */

public class DaemonService extends Service {
    /**
     * i
     * startForeground的 noti_id
     */
    public static int NOTI_ID = 123323;

    @Nullable
    @Override
    public IBinder onBind( Intent intent ) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if ( Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2 ) {
            startForeground(NOTI_ID, buildNotification(getBaseContext()));
            // 如果觉得常驻通知栏体验不好
            // 可以通过启动CancelNoticeService，将通知移除，oom_adj值不变
//            Intent intent = new Intent( this, CancelNoticeService.class );
//            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startService( intent );
        } else {
            startForeground( NOTI_ID, new Notification() );
        }
    }

    public static final String CHANNEL_ID_STRING = "GuardLocation";

    public static Notification buildNotification( Context context ) {
        //适配8.0service
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder( context );
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID_STRING, "定位通知", NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
            builder = new Notification.Builder(context, CHANNEL_ID_STRING);
        }
        Notification notification = builder
//				.setTicker("Nature")
                .setSmallIcon( R.mipmap.ic_launcher )
                .setContentTitle( "定位服务正在运行" )
//				.setContentIntent(pendingIntent)
                .setContentText( "正在获取定位..." )
                .build();
        notification.flags |= Notification.FLAG_NO_CLEAR;
        return notification;
    }
    @Override
    public int onStartCommand( Intent intent, int flags, int startId ) {
        //如果Service被终止，当资源允许情况下，重启Service
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //如果service被杀死，干掉通知
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 ) {
            NotificationManager notificationManager = ( NotificationManager ) getSystemService( NOTIFICATION_SERVICE );
            notificationManager.cancel( NOTI_ID );
        }

        //重启自己
        Intent intent = new Intent( CommonApplication.getAppContext(), DaemonService.class );
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity( intent );
    }
}
