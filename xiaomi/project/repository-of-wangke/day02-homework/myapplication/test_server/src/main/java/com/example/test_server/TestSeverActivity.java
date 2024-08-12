package com.example.test_server;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class TestSeverActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_sever_activity);
        Intent intent =new Intent(this, TestServerService.class);
        startService(intent);
    }
}
