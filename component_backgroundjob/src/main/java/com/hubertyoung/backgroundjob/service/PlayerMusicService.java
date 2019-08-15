package com.hubertyoung.backgroundjob.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.component_backgroundjob.R;


/**
 * 循环播放一段无声音频，以便提升进程优先级
 */

public class PlayerMusicService extends Service {
    private static final String TAG = PlayerMusicService.class.getSimpleName();
    private MediaPlayer mMediaPlayer;
    @Nullable
    @Override
    public IBinder onBind( Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mMediaPlayer = MediaPlayer.create( CommonApplication.getAppContext(), R.raw.silent);
        mMediaPlayer.setLooping(true);
    }

    @Override
    public int onStartCommand( Intent intent, int flags, int startId) {
        new Thread( new Runnable() {
            @Override
            public void run() {
                startPlayMusic();
            }
        }).start();
        return START_STICKY;
    }

    private void startPlayMusic() {
        if(mMediaPlayer != null){

            mMediaPlayer.start();
        }
    }
    private void stopPlayMusic(){
        if(mMediaPlayer != null){

            mMediaPlayer.stop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopPlayMusic();

        //重启自己
        Intent intent = new Intent(CommonApplication.getAppContext(),PlayerMusicService.class);
        startActivity(intent);
    }
}
