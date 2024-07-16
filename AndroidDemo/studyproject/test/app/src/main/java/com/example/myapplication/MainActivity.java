package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        WebView webView = findViewById(R.id.webview);
//
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//
//        // 加载网页
//        webView.loadUrl("https://www.baidu.com");
//
//        // 防止跳转到默认浏览器
//        webView.setWebViewClient(new WebViewClient());

       RecyclerView recyclerView = findViewById(R.id.demo_recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 初始化数据
        ArrayList<Item>  itemList = new ArrayList<>();
        itemList.add(new Item("Title 1", "Description 1"));
        itemList.add(new Item("Title 2", "Description 2"));
        itemList.add(new Item("Title 3", "Description 3"));

        // 设置适配器
        recycleAdapter itemAdapter = new recycleAdapter(itemList);
        recyclerView.setAdapter(itemAdapter);
    }
}