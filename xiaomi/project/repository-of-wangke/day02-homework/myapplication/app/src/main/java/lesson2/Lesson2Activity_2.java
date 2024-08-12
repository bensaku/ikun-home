package lesson2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class Lesson2Activity_2 extends Activity{
    private Button mBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson2_activity_2);
        System.out.println("Lessson2Activity_2---->onCreate");
        Intent intent = getIntent();
        mBtn = findViewById(R.id.btn2);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动模式测试
                Intent intent = new Intent(Lesson2Activity_2.this, Lesson2Activity_1.class);
                startActivity(intent);}
        });

//        String scheme = intent.getScheme();
//        String data = intent.getDataString();
//        Uri uri = intent.getData();
//        String host = uri.getHost();
//
//        String a =uri.getQueryParameter("a");
//        String b =uri.getQueryParameter("b");
//
//        System.out.println("Lesson2Activity_2--->onCreate--->scheme--->"+scheme);
//        System.out.println("Lesson2Activity_2--->onCreate--->data--->"+data);
//        System.out.println("Lesson2Activity_2--->onCreate--->host--->"+host);
//        System.out.println("Lesson2Activity_2--->onCreate--->a--->"+a);
//        System.out.println("Lesson2Activity_2--->onCreate--->b--->"+b);
//        String type= intent.getType();
//        String value= intent.getStringExtra("test_key_1");
//        System.out.println("Lesson2Activity_2--->onCreate--->type--->"+type);
//        System.out.println("Lesson2Activity_2--->onCreate--->value--->"+value);

    }
    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("Lessson2Activity_2---->onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Lessson2Activity_2---->onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("Lessson2Activity_2---->onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Lessson2Activity_2---->onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("Lessson2Activity_2---->onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("Lessson2Activity_2---->onRestart");
    }
    }