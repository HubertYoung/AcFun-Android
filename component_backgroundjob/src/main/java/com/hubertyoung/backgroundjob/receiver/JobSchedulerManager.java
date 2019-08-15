package com.hubertyoung.backgroundjob.receiver;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

import com.hubertyoung.backgroundjob.service.AliveJobService;


/**
 * JobScheduler管理类，单例模式
 * 执行系统任务
 * Created by ly on 2017/11/7.
 */
@TargetApi( Build.VERSION_CODES.LOLLIPOP )
public class JobSchedulerManager {
    private static final int JOB_ID = 1;
    private static JobSchedulerManager mJobSchedulerManager;
    private JobScheduler mJobSchedule;
    private static Context mContext;

    private JobSchedulerManager( Context mContext){
        JobSchedulerManager.mContext = mContext;
        mJobSchedule = ( JobScheduler ) mContext.getSystemService( Context.JOB_SCHEDULER_SERVICE);
    }
    public static final JobSchedulerManager getJobSchedulerManager( Context mContext){
        if(mJobSchedulerManager == null){
            mJobSchedulerManager = new JobSchedulerManager(mContext);
        }
        return mJobSchedulerManager;
    }

    @TargetApi(21)
    public void startJobScheduler(){
        //如果JobService已经启动或者API<21,
        if( AliveJobService.isJobServiceAlive()|| isBlowLOLLIPOP()){
            return;
        }
        //构建JobInfo对象，传递给JobSchedulerService
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID,new ComponentName(mContext,AliveJobService.class));
        //设置每隔3秒执行
        builder.setPeriodic(3000);
        //设置设备重启的时候，执行该任务
        builder.setPersisted(true);
        //设置设备插入充电器的时候，执行该任务
        builder.setRequiresCharging(true);
        JobInfo info = builder.build();
        //开始定时执行该系统任务
        mJobSchedule.schedule(info);
    }

    @TargetApi(21)
    public void stopJobScheduler(){
        if(isBlowLOLLIPOP()){
            return;
        }
        mJobSchedule.cancelAll();
    }
    private boolean isBlowLOLLIPOP(){
        //API<21
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP;
    }

}
