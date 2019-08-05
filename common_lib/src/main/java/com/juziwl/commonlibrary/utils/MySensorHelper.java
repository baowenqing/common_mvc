package com.juziwl.commonlibrary.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.OrientationEventListener;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2016/12/3 0003.
 * 监听重力系统传感器的变化，为Vr视频播放器而定制
 */

public class MySensorHelper {
    private static final String TAG = MySensorHelper.class.getSimpleName();
    private OrientationEventListener mLandOrientationListener;
    private OrientationEventListener mPortOrientationListener;
    private WeakReference<Activity> mActivityWeakRef;
    private boolean isPortLock = false;
    private boolean isLandLock=false;

    public MySensorHelper(final Activity activity) {
        this.mActivityWeakRef = new WeakReference(activity);
        this.mLandOrientationListener = new OrientationEventListener(activity, 3) {
            @Override
            public void onOrientationChanged(int orientation) {
                Log.d(MySensorHelper.TAG, "mLandOrientationListener");
                if(orientation < 100 && orientation > 80 || orientation < 280 && orientation > 260) {
                    Log.e(MySensorHelper.TAG, "转到了横屏");
                    if(!MySensorHelper.this.isLandLock) {
                        Activity mActivity = (Activity)MySensorHelper.this.mActivityWeakRef.get();
                        if(mActivity != null) {
                            Log.e(MySensorHelper.TAG, "转到了横屏##################");
                            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                            isLandLock=true;
                            isPortLock=false;
                        }
                    }
                }

            }
        };
        this.mPortOrientationListener = new OrientationEventListener(activity, 3) {
            @Override
            public void onOrientationChanged(int orientation) {
                Log.w(MySensorHelper.TAG, "mPortOrientationListener");
                if(orientation < 10 || orientation > 350 || orientation < 190 && orientation > 170) {
//                    Log.e(MySensorHelper.TAG, "转到了竖屏");
                    if(!MySensorHelper.this.isPortLock) {
                        Activity mActivity = (Activity)MySensorHelper.this.mActivityWeakRef.get();
                        if(mActivity != null) {
//                            Log.e(MySensorHelper.TAG, "转到了竖屏!!!!!!!!!!!!!!!!!!!!!!");
                            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            isPortLock=true;
                            isLandLock=false;
                        }
                    }
                }

            }
        };
      //this.disable();
    }
    //禁用切换屏幕的开关
    public void disable() {
        Log.e(TAG, "disable");
        this.mPortOrientationListener.disable();
        this.mLandOrientationListener.disable();
    }
    //开启横竖屏切换的开关
    public void enable(){
        this.mPortOrientationListener.enable();
        this.mLandOrientationListener.enable();
    }
    //设置竖屏是否上锁，true锁定屏幕,fanle解锁
    public void setPortLock(boolean lockFlag) {
        this.isPortLock = lockFlag;
    }
    //设置横屏是否锁定，true锁定，false解锁
    public void setLandLock(boolean isLandLock){
        this.isLandLock=isLandLock;
    }
}