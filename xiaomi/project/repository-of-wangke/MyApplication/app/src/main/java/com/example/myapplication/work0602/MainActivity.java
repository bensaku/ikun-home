package com.example.myapplication.work0602;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.work0603.SearchActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work0602_activity_main);
        TextView gotoPhone = findViewById(R.id.work0602_goto_phone);
        gotoPhone.setOnClickListener(this);
        TextView gotoSearch = findViewById(R.id.work0602_goto_search);
        gotoSearch.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.work0602_goto_phone:
                intent = new Intent(MainActivity.this, PhonePermissionActivity.class);
                startActivity(intent);
                break;
            case R.id.work0602_goto_search:
                intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}