package lesson2;

import android.app.Service;
import androidx.annotation.Nullable;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class Lesson2Service1 extends Service {
    private  final CustomBinder customBinder= new CustomBinder();
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Lseeon2Service1--->onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("Lseeon2Service1--->onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("Lseeon2Service1--->onBind");
        return customBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("Lseeon2Service1--->onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Lseeon2Service1--->onDestroy");
    }

    public class CustomBinder extends Binder{
        public Lesson2Service1 getService(){return Lesson2Service1.this;}
    }
}
