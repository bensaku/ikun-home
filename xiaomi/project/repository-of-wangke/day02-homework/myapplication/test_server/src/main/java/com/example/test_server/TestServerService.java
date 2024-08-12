package com.example.test_server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

public class TestServerService extends Service {
    private ITestInterface.Stub testStb = new ITestInterface.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            System.out.println("ITestInterface--->add---->"+a+","+b);
            return a+b;
        }
    };

    @Override
    public void onCreate() {
        System.out.println("testServerService--->onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("testServerService--->onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("testServerService--->onBind");
        return testStb;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("testServerService--->onDestory");
    }
}
