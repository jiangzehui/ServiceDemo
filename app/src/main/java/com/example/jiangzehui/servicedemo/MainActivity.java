package com.example.jiangzehui.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startService方式启动服务
                intent = new Intent(MainActivity.this, MyService.class).putExtra("content", "jzh");
                startService(intent);
            }
        });

        findViewById(R.id.btnStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startService方式停止服务
                stopService(intent);
            }
        });


        //Bind服务
        findViewById(R.id.btnStartBind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startService方式启动服务
                intent = new Intent(MainActivity.this, MyService.class).putExtra("content", "jzh");
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.btnStopBind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startService方式停止服务
                unbindService(connection);
            }
        });

    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBindr myBindr = (MyService.MyBindr) service;
            int r = myBindr.getService().getRandomNum();
            Log.d("onServiceConnected=", "绑定服务成功  " + r);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("onServiceDisconnected=", "服务已断开");
        }
    };
}
