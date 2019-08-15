package com.hubertyoung.backgroundjob.receiver;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.hubertyoung.common.utils.os.NetworkUtil;

import java.util.List;

/**
 * 监听系统广播，复活进程
 * 监听网络变化广播、屏幕解锁广播、应用安装卸载广播、开机广播
 */

public class KeepAliveReceiver extends BroadcastReceiver {

    private  static final String TAG = KeepAliveReceiver.class.getSimpleName();

    @Override
    public void onReceive( Context context, Intent intent) {

        if( NetworkUtil.isNetworkConnected(context)){

            //因为此，安装完APK后会出现MainActivity界面
//            Intent intentAlive = new Intent(context, MainActivity.class);
//            intentAlive.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intentAlive);
            Log.i("启动MainActivity!!!!!!","启动MainActivity!!!!!!");
            //Toast.makeText(context,"启动MainActivity!!!!!!",Toast.LENGTH_SHORT).show();
            //Log.i(TAG,"KeepAliveReceiver-->复活进程APP");
        }


    }

    /**
     * 注册监听网络状态的广播
     * @param context
     * @return
     */
//    public static RxBroadcastTool.BroadcastReceiverNetWork initRegisterReceiverNetWork( Context context) {
//        // 注册监听网络状态的服务
//        RxBroadcastTool.BroadcastReceiverNetWork mReceiverNetWork = new RxBroadcastTool.BroadcastReceiverNetWork();
//        IntentFilter mFilter = new IntentFilter();
//        mFilter.addAction( ConnectivityManager.CONNECTIVITY_ACTION);
//        context.registerReceiver(mReceiverNetWork, mFilter);
//        return mReceiverNetWork;
//    }

    /*
    * 判断本应用是否存活
    * 判断本应用在前台还是后台使用getRunningTask
    * */
    public static boolean isApplive( Context context, String packageName) {
        boolean isApplive = false;
        //获取activity管理对象
        ActivityManager activitymanager = ( ActivityManager )context.getSystemService( Context.ACTIVITY_SERVICE);
        //获取正在运行的APP
        List< ActivityManager.RunningAppProcessInfo> appProcessInfoList = activitymanager.getRunningAppProcesses();
        //遍历，进程名即为包名
        for( ActivityManager.RunningAppProcessInfo appInfo:appProcessInfoList){
            if(packageName.equals(appInfo.processName)){
                isApplive = true;
                break;
            }
        }
        return isApplive;
    }


    @SuppressWarnings("deprecation")
    private void getNetWorkBroadcast( Context mContext, Intent intent){
        String action = intent.getAction();
        //wifi状态改变
        if( WifiManager.WIFI_STATE_CHANGED_ACTION.equals(action)){
            int wifiState = intent.getIntExtra( WifiManager.EXTRA_WIFI_STATE,0);
            switch (wifiState){
                case WifiManager.WIFI_STATE_DISABLED:
                    Toast.makeText(mContext,"wifi关闭", Toast.LENGTH_SHORT).show();
                    //Log.i(TAG,"wifi关闭");
                    break;
                case WifiManager.WIFI_STATE_ENABLED:
                    Toast.makeText(mContext,"wifi开启", Toast.LENGTH_SHORT).show();
                    //Log.i(TAG,"wifi开启");
                    break;
                default:
                    break;
            }
        }
        //连接到一个有效的wifi路由器
        if( WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(action)){
            Parcelable parcelableExtra =intent.getParcelableExtra( WifiManager.EXTRA_NETWORK_INFO);
            if(null != parcelableExtra){
                NetworkInfo networkInfo = ( NetworkInfo )parcelableExtra;
                NetworkInfo.State state = networkInfo.getState();
                boolean isConnected = state == NetworkInfo.State.CONNECTED;
                if(isConnected){
                    Toast.makeText(mContext,"设备连接到一个有效的WIFI路由器", Toast.LENGTH_SHORT).show();
                    //Log.i(TAG,"设备连接到一个有效的WIFI路由器");
                }
            }
        }
        //监听网络连接状态，包括wifi和移动网路数据的打开和关闭
        if( ConnectivityManager.CONNECTIVITY_ACTION.equals(action)){
            ConnectivityManager manager = ( ConnectivityManager )mContext.getSystemService( Context.CONNECTIVITY_SERVICE);
            NetworkInfo GPRs = manager.getNetworkInfo( ConnectivityManager.TYPE_MOBILE);
            if(GPRs.isConnected()){
                Toast.makeText(mContext,"移动网络打开", Toast.LENGTH_SHORT).show();
                //Log.i(TAG,"移动网络打开");
            }else{
                Toast.makeText(mContext,"移动网络关闭", Toast.LENGTH_SHORT).show();
                //Log.i(TAG,"移动网络关闭");
            }
        }
    }
}
