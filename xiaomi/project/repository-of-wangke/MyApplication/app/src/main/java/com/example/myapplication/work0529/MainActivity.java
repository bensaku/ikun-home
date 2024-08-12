package com.example.myapplication.work0529;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {
    private void setButtonProperties(int includeLayoutId, int iconResId, String text) {

        ConstraintLayout includedLayout = findViewById(includeLayoutId);
        // 找到包含布局中的ImageView和TextView
        ImageView buttonIcon = includedLayout.findViewById(R.id.button_icon);
        TextView buttonText = includedLayout.findViewById(R.id.button_text);

        // 动态设置图标和文字
        buttonIcon.setImageResource(iconResId);
        buttonText.setText(text);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work0529_activity_main);


        setButtonProperties(R.id.work0529_button2, R.drawable.icon_account, "账号申诉");
        setButtonProperties(R.id.work0529_button3, R.drawable.icon_freeze, "冻结账号");
        setButtonProperties(R.id.work0529_button4, R.drawable.icon_unfreeze, "解冻账号");
        setButtonProperties(R.id.work0529_button5, R.drawable.icon_unlock, "解封账号");
        setButtonProperties(R.id.work0529_button6, R.drawable.icon_deleteaccount, "注销账号");




        TextView problemButton = findViewById(R.id.work0529_problem);
        TextView loginButton = findViewById(R.id.work0529_login);
        TextView informationButton = findViewById(R.id.work0529_information);
        TextView accountButton = findViewById(R.id.work0529_account);

        replaceFragment(ProblemFragment.newInstance(" 如何找回账号?;忘记密码怎么办?;如何修改密码?;账户被盗怎么办?"));

        problemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(ProblemFragment.newInstance("如何找回账号?;忘记密码怎么办?;如何修改密码?;账户被盗怎么办?"));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(ProblemFragment.newInstance("登录时提示错误怎么办?;忘记登录邮箱怎么办?;无法接收验证码怎么办?;如何设置两步验证?"));
            }
        });

        informationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(ProblemFragment.newInstance("如何修改个人信息?;如何设置隐私选项?;如何绑定手机号码?;如何更改邮箱地址?"));
            }
        });

        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(ProblemFragment.newInstance("如何保证账户安全?;如何防止账户被盗?;如何举报盗号行为?;如何找回被盗账户?"));
            }
        });
    }

    private void replaceFragment(ProblemFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.work0529_fragment, fragment);
        fragmentTransaction.commit();
    }
}
