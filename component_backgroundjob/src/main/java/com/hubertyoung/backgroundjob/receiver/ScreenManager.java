package com.hubertyoung.backgroundjob.receiver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.hubertyoung.backgroundjob.SinglePixelActivity;
import com.hubertyoung.common.utils.os.AppUtils;

import java.lang.ref.WeakReference;


/**
 * 1像素管理类
 */

public class ScreenManager {
    private static final String TAG = ScreenManager.class.getSimpleName();

    private Context mContext;
    private static ScreenManager mScreenManagerUtils;

    //使用弱引用，防止内存泄漏
    private WeakReference< Activity > mActivityRef;

    private ScreenManager( Context mContext){
        this.mContext = mContext;
    }
    //单例模式
    public static ScreenManager getScreenManagerInstance( Context mContext){
        if(mScreenManagerUtils == null){
            mScreenManagerUtils  = new ScreenManager(mContext);
        }
        return mScreenManagerUtils;
    }

    //获得SinglePixelActivity的引用
    public void setSingleActivity( Activity mActivity){
        mActivityRef = new WeakReference< Activity >(mActivity);
    }

    //启动SinglePixelActivity
    public void startActivity(){
        if( AppUtils.isDebuggable() ){
            Log.i(TAG,"准备启动SinglePixelActivity...");
        }
        Intent intent = new Intent(mContext, SinglePixelActivity.class);
        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    //结束SinglePixelActivity
    public void finishActivity(){
        if(AppUtils.isDebuggable()){
            Log.i(TAG,"准备结束SinglePixelActivity...");
        }
        if(mActivityRef != null){
            Activity mActivity = mActivityRef.get();
            if(mActivity != null ){
                mActivity.finish();
            }
        }
    }

}
