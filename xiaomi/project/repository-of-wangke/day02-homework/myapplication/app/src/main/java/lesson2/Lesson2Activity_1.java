package lesson2;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.test_server.ITestInterface;
import com.example.myapplication.R;

public class Lesson2Activity_1 extends Activity{

    private Button mBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson2_activity_1);
        System.out.println("Lessson2Activity_1---->onCreate");
        mBtn = findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //四种启动模式测试
                //Singletop模式
//                Intent intent = new Intent(Lesson2Activity_1.this, Lesson2Activity_1.class);
//                startActivity(intent);

                //Singletask模式和Singleinstance模式
//                Intent intent = new Intent(Lesson2Activity_1.this, Lesson2Activity_2.class);
//                startActivity(intent);

                //startService生命周期
//                Intent serviceIntent = new Intent(Lesson2Activity_1.this,Lesson2Service1.class);
//                startService(serviceIntent);
//                stopService(serviceIntent);

                //bindService生命周期
//                Intent serviceIntent =new Intent(Lesson2Activity_1.this,Lesson2Service1.class);
//                bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
//                startService(serviceIntent);


                //aidl跨进程通信
                Intent severIntent =new Intent();
                ComponentName componentName = new ComponentName("com.example.test_server","com.example.test_server.TestServerService");
                severIntent.setComponent(componentName);
                bindService(severIntent,serverConnection, Context.BIND_AUTO_CREATE);


//                Intent intent = new Intent();
//                intent.setAction("activity_2_action");
//                Uri uri = Uri.parse("test_schem://www.baidu.com?a=1&b=2");
//                intent.setDataAndType(uri,"test/type");
//                intent.setType("test/type");
//                intent.putExtra("test_key_1","value_1");
//                startActivity(intent);



            }
        });
        //动态注册广播
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("test_action");
        registerReceiver(customReceiver,intentFilter);
    }

    private CustomReceiver customReceiver =new CustomReceiver();


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("onServiceConnected--->"+service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("onServiceDisconnected--->");
        }
    };

    private ServiceConnection serverConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("onServiceConnected--->"+service);
            ITestInterface iTest = ITestInterface.Stub.asInterface(service);
            try {
                iTest.add(3,5);
                System.out.println("onServiceConnected--->"+service);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("Lessson2Activity_1---->onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Lessson2Activity_1---->onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("Lessson2Activity_1---->onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Lessson2Activity_1---->onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("Lessson2Activity_1---->onDestroy");
        unbindService(serviceConnection);
        unbindService(serverConnection);
        unregisterReceiver(customReceiver);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("Lessson2Activity_1---->onRestart");
    }
}

