package com.example.binderdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private IBinder mBinder;

    private IReporter mReporterAidl;

    private class AidlConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mReporterAidl = IReporter.Stub.asInterface(iBinder);
            try {
                int hello = mReporterAidl.report("hello", 1);
                Log.d("MainActivity", "hello: " + hello);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }

    private class BindConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBinder = iBinder;
            int a = 1;
            int b = 2;
            Parcel data = Parcel.obtain();
            data.writeInterfaceToken(IPlus.DESCRIPTOR);
            data.writeInt(a);
            data.writeInt(b);
            Parcel reply = Parcel.obtain();
            try {
                mBinder.transact(MyService.ADD_CODE, data, reply,0);
                reply.readException();
                int result = reply.readInt();
                Log.i("Main", "Result: " + result);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }

            data.recycle();
            reply.recycle();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBinder = null;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = new Intent(this, MyService.class);
        bindService(intent, new BindConnection(), BIND_AUTO_CREATE);

        Intent intent2 = new Intent(this, AidlService.class);
        bindService(intent2, new AidlConnection(), BIND_AUTO_CREATE);


    }
}