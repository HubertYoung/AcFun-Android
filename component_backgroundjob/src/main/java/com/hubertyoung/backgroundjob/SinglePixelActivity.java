package com.hubertyoung.backgroundjob;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.hubertyoung.backgroundjob.receiver.KeepAliveReceiver;
import com.hubertyoung.backgroundjob.receiver.ScreenManager;
import com.hubertyoung.common.CommonApplication;

import backgroundjob.MainActivity;


/**
 * 1像素Activity
 */

public class SinglePixelActivity extends AppCompatActivity {
    private static final String TAG = SinglePixelActivity.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window mWindow = getWindow();
        mWindow.setGravity( Gravity.LEFT| Gravity.TOP);
        WindowManager.LayoutParams layoutParams = mWindow.getAttributes();
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.height = 300;
        layoutParams.width = 300;
        mWindow.setAttributes(layoutParams);
        //绑定1像素Activity到ScreeanManagerUtil
        ScreenManager.getScreenManagerInstance(this).setSingleActivity(this);
    }

    @Override
    protected void onDestroy() {

        if(!KeepAliveReceiver.isApplive(this, CommonApplication.getAppContext().getPackageName() )){
            Intent intentAlive = new Intent(this, MainActivity.class);
            intentAlive.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentAlive);
            //Log.i(TAG,"SinglePixelActivity--->App被干掉，我要重启它");
        }
        super.onDestroy();
    }
}
