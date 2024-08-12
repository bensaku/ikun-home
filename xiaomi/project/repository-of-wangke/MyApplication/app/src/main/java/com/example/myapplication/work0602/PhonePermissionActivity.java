package com.example.myapplication.work0602;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

/*动态申请权限流程
a.在manifest中声明
b.检测是否有权限checkSelfPermission
c.没有权限就申请：requestPermission（权限数组，requestCode）
d.拿到权限结果，onRequestPermissionsResult
e.判断结果是否有对应权限
f.如果有，则执行操作*/
public class PhonePermissionActivity extends AppCompatActivity implements View.OnClickListener {

    private String phoneNumber ="18156614670";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work0602_activity_permission);
        initView();
    }

    private void initView() {
        TextView textView = findViewById(R.id.work0602_tv_phone);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.work0602_tv_phone:
                openPhone();
                break;
        }
    }

    private void openPhone() {
        if (checkPermission(Manifest.permission.CALL_PHONE)) {
            //有权限
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+phoneNumber));
            startActivity(intent);
        } else {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 101);
        }
    }

    //检查是否有权限
    private boolean checkPermission(String permission) {
        int hasPermission = checkSelfPermission(permission);
        return hasPermission == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openPhone();
        }
        else {
            if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                // 第一次拒绝
                showPermissionExplanation();
            } else {
                // 不再询问或者多次拒绝
                Toast.makeText(this, "暂无权限", Toast.LENGTH_SHORT).show();
                jumpSetting();
            }
        }
    }


    private void jumpSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    private void showPermissionExplanation() {
        new AlertDialog.Builder(this)
                .setMessage("App需要电话权限来拨打电话，请授予电话权限。")
                .setPositiveButton("授予权限", (dialog, which) -> {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 101);
                })
                .setNegativeButton("取消", null)
                .show();
    }

}
