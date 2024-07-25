package com.example.binderdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AidlService extends Service {

    public static final class Reporter extends IReporter.Stub {

        @Override
        public int report(String values, int type) throws RemoteException {
            Log.d("AidlService", "report: values = " + values + ", type = " + type);
            return type;
        }
    }

    private Reporter reporter;

    public AidlService() {
        reporter = new Reporter();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return reporter;
    }
}