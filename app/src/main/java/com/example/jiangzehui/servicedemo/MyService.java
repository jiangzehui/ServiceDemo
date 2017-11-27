package com.example.jiangzehui.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

/**
 * Created by jiangzehui on 17/11/25.
 */

public class MyService extends Service {
    private static String TAG = "MyService";
    private boolean flag = true;
     Random random = new Random();

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
    MyBindr myBindr = new MyBindr();
    class MyBindr extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    public int getRandomNum() {
        return random.nextInt(100);
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return myBindr;
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
