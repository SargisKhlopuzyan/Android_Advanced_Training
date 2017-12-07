package com.example.started_bound_service_interactions;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private IBinder binder = new MyBinder();

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("LOG_TAG", "in onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("LOG_TAG", "in onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v("LOG_TAG", "in onBind");
        return binder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.v("LOG_TAG", "in onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v("LOG_TAG", "in onUnbind");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("LOG_TAG", "in onDestroy");
    }


    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }
}
