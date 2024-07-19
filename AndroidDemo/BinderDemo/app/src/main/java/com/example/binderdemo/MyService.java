package com.example.binderdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyService extends Service {
    public static final int ADD_CODE = 0;

    IBinder binder = new Stub();


    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i("Main","service start");
        return binder;
    }

    public class Stub extends Binder implements IPlus {
        private static final String DESCRIPTOR = IPlus.DESCRIPTOR;
        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            Log.i("Main","onTransact");
            if (code == ADD_CODE) {
                data.enforceInterface(DESCRIPTOR);
                int arg0 = data.readInt();
                int arg1 = data.readInt();
                Log.i("Main","arg0:"+arg0);

                int result = add(arg0, arg1);
                Log.i("Main", "result:" + result);
                if (reply != null) {
                    reply.writeNoException();
                    reply.writeInt(result);
                }
                return true;
            }
            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public int add(int a, int b) {
            return a + b;
        }

        @Override
        public IBinder asBinder() {
            return this;
        }
    }

}