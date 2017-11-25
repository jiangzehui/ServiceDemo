package com.example.jiangzehui.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by jiangzehui on 17/11/25.
 */

public class MyService extends Service {
    private static String TAG = "MyService";
    private boolean flag = true;

    //启动服务都会回调
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }


    //startService方式启动回调此方法
    @Override
    public int onStartCommand(Intent intent, final int flags, final int startId) {
        Log.d(TAG, "onStartCommand");
        final String content = intent.getStringExtra("content");
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (flag) {
                    try {
                        Thread.sleep(5000);
                        Log.d(TAG, "flags=" + flags + ",startId=" + startId + ",content=" + content);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }.start();
        return super.onStartCommand(intent, flags, startId);

    }

    //BindService方式启动回调此方法
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        flag = false;
    }
}
