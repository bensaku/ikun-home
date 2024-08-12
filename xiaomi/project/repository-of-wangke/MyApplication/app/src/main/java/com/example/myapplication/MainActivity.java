package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.work0603.SearchActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView gotoPhone = findViewById(R.id.demo_goto_handler);
        gotoPhone.setOnClickListener(this);
        TextView gotoSearch = findViewById(R.id.demo_goto_0603);
        gotoSearch.setOnClickListener(this);
        TextView gotoLeak = findViewById(R.id.demo_goto_leak);
        gotoLeak.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
//            case R.id.demo_goto_handler:
//                intent = new Intent(MainActivity.this, HandlerActivity.class);
//                startActivity(intent);
//                break;
            case R.id.demo_goto_0603:
                intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
//            case R.id.demo_goto_leak:
//                intent = new Intent(MainActivity.this, LeakActivity.class);
//                startActivity(intent);
        }
    }
}