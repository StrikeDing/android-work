package com.example.ting.access_server_test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Account_Service extends Service {
    private Authenticator authenticator;
    public Account_Service() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        authenticator = new Authenticator(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
     return authenticator.getIBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }
}
