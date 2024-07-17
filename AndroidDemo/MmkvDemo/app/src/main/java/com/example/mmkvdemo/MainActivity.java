package com.example.mmkvdemo;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tencent.mmkv.MMKV;

public class MainActivity extends AppCompatActivity {

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

        MMKV mmkv = MMKV.defaultMMKV();

        mmkv.encode("bool", true);
        boolean bool = mmkv.decodeBool("bool");

        sharePreferenceTest();
        getPreference();
    }

    private void sharePreferenceTest() {
        SharedPreferences sharedPreferences = getSharedPreferences("test",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("test","this is test");
        editor.apply();
    }

    private void getPreference() {
        SharedPreferences sharedPreferences= getSharedPreferences("test", MODE_PRIVATE);
        String test=sharedPreferences.getString("test","");
        Log.i("test","test"+test);
    }

}