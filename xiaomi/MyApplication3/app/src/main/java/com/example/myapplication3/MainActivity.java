package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements DemoFragment.TopPageActionListener {

    @Override
    public void onTopPageAction() {
        DemoFragment2 demoFragment2 = new DemoFragment2();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container_bottom, demoFragment2)
                .commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Bundle bundle =new Bundle();
//        bundle.putInt("some_int",0);
//
//        getSupportFragmentManager().beginTransaction()
//                .setReorderingAllowed(true)
//                .add(R.id.fragment_container_view,ExampleFragment.class,bundle)
//                .commit();

        DemoFragment demoFragment = new DemoFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container_view, demoFragment)
                .commit();

        DemoFragment2 demoFragment2 = new DemoFragment2();
        findViewById(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View v) {
                                                             getSupportFragmentManager()
                                                                     .beginTransaction()
                                                                     .add(R.id.fragment_container_view, demoFragment2)
                                                                     .commit();
                                                         }
                                                     }
        );

        findViewById(R.id.tv_hide).setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View v) {
                                                             getSupportFragmentManager()
                                                                     .beginTransaction()
                                                                     .hide(demoFragment)
                                                                     .commit();
                                                         }
                                                     }
        );

        findViewById(R.id.tv_replace).setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View v) {
                                                             getSupportFragmentManager()
                                                                     .beginTransaction()
                                                                     .replace(R.id.fragment_container_view, demoFragment2)
                                                                     .commit();
                                                         }
                                                     }
        );

        findViewById(R.id.add_to_fragment).setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View v) {
                                                             demoFragment.getChildFragmentManager()
                                                                     .beginTransaction()
                                                                     .add(R.id.fragment_container_view_demo, demoFragment2)
                                                                     .commit();
                                                         }
                                                     }
        );
    }
}