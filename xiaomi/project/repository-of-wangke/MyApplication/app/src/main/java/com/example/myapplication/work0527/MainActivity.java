package com.example.myapplication.work0527;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work0527_activity_main);

        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.work0527_fragment_view, mainFragment)
                .commit();


        Page2Fragment page2Fragment = new Page2Fragment();
        Page3Fragment page3Fragment = new Page3Fragment();
        Page4Fragment page4Fragment = new Page4Fragment();
        findViewById(R.id.work0527_tv_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.work0527_fragment_view, mainFragment)
                        .commit();
            }
        });


        findViewById(R.id.work0527_tv_page2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.work0527_fragment_view, page2Fragment)
                        .commit();
            }
        });


        findViewById(R.id.work0527_tv_page3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.work0527_fragment_view, page3Fragment)
                        .commit();
            }
        });


        findViewById(R.id.work0527_tv_page4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.work0527_fragment_view, page4Fragment)
                        .commit();
            }
        });

    }


}
