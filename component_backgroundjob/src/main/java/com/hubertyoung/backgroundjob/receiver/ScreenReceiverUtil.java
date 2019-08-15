package com.hubertyoung.backgroundjob.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * 静态监听锁屏、解锁、开屏广播
 * 用户锁屏时，将Activity置于前台，开启1像素悬浮窗
 * 用户解锁，关闭1像素包活
 */

public class ScreenReceiverUtil {
    private Context mContext;

    //锁屏广播接收器
    private ScreenBroadcastReceiver mScreenReceiver;
    //屏幕状态改变回调接口
    private ScreenStateListener mScreenStateListener;

    public ScreenReceiverUtil( Context mContext){
        this.mContext = mContext;
    }

    public void setScreenReceiverListener(ScreenStateListener mScreenStateListener){
        this.mScreenStateListener = mScreenStateListener;

        //动态启动广播接收器
        this.mScreenReceiver = new ScreenBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction( Intent.ACTION_SCREEN_ON);
        filter.addAction( Intent.ACTION_SCREEN_OFF);
        filter.addAction( Intent.ACTION_USER_PRESENT);
        mContext.registerReceiver(mScreenReceiver,filter);
    }

    public void stopScreenReceiverListener(){
        mContext.unregisterReceiver(mScreenReceiver);
    }

    public class ScreenBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive( Context context, Intent intent) {
            String action = intent.getAction();
            //Log.i("收钱啦APP","ScreenLockBroadReceiver--->监听系统锁屏状态广播"+action);
            if(mScreenStateListener == null){
                return;
            }
            if( Intent.ACTION_SCREEN_ON.equals(action)){     //开屏
                mScreenStateListener.onScreenOn();
            }else if( Intent.ACTION_SCREEN_OFF.equals(action)){   //锁屏
                mScreenStateListener.onScreenOff();
            }else if( Intent.ACTION_USER_PRESENT.equals(action)){  //解锁
                mScreenStateListener.onUserPresent();
            }
        }
    }

    public interface ScreenStateListener{
        void onScreenOn();
        void onScreenOff();
        void onUserPresent();
    }
}
