package com.example.weibo_wangke;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startEvents();
    }

    private void startEvents() {
        SharedPreferences sharedPreferences = getSharedPreferences("LocalData", Context.MODE_PRIVATE);
        boolean savedAgree = sharedPreferences.getBoolean("isAgree", false);
        // 打印数据
        Log.i(TAG, "isAgree" + savedAgree);
        if (savedAgree) {
            Log.i(TAG, "in isAgree");
            startHomePage(1500L);
        } else {
            Log.i(TAG, "in notAgree");
            declareEvent();
        }

    }

    private void declareEvent() {
        View backgroundView = findViewById(R.id.gray_background);
        backgroundView.setVisibility(View.VISIBLE);
        View declareView = findViewById(R.id.decelerate);
        declareView.setVisibility(View.VISIBLE);
        //设置监听
        TextView declareText = declareView.findViewById(R.id.tv_declaration);
        TextView disagree = declareView.findViewById(R.id.tv_disagree);
        TextView agree = declareView.findViewById(R.id.tv_agree);
        disagree.setOnClickListener(this);
        agree.setOnClickListener(this);


        //设置富文本
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append("欢迎使用 iH微博 ，我们将严格遵守相关法律和隐私政策保护您的个人隐私，请您阅读并同意 ");

        String userAgreement = "《用户协议》";
        SpannableString userAgreementSpannable = new SpannableString(userAgreement);
        userAgreementSpannable.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(MainActivity.this, "点击了用户协议", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(ContextCompat.getColor(MainActivity.this, R.color.blue)); // 设置文本颜色
                ds.setUnderlineText(false); // 去掉下划线
            }
        }, 0, userAgreement.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(userAgreementSpannable);

        builder.append("与");

        String privacyPolicy = "《隐私政策》";
        SpannableString privacyPolicySpannable = new SpannableString(privacyPolicy);
        privacyPolicySpannable.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(MainActivity.this, "点击了隐私政策", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(ContextCompat.getColor(MainActivity.this, R.color.blue)); // 设置文本颜色
                ds.setUnderlineText(false); // 去掉下划线
            }
        }, 0, privacyPolicy.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(privacyPolicySpannable);

        declareText.setText(builder);
        declareText.setMovementMethod(LinkMovementMethod.getInstance());

    }


    private void startHomePage(Long delayTime) {
        // 使用 Handler 在指定时间后启动 HomePageActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, delayTime);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_disagree:
                this.finish();
                System.exit(0);
                break;
            case R.id.tv_agree:
                Log.i(TAG, "agree is clicked");
                SharedPreferences sharedPreferences = getSharedPreferences("LocalData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isAgree", true);
                editor.apply();
                startHomePage(50L);
                break;
        }
    }
}