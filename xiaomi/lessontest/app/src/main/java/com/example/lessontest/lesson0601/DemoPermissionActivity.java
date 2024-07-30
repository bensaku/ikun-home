package com.example.lessontest.lesson0601;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

/*
 * 权限申请步骤
 * 简单权限，直接在Androidmanifast中声明
 * 危险权限
 * a.在manifast中声明
 * b.检测是否有权限checkSelfPermission
 * c.没有权限就申请：requestPermission（权限数组，requestcode）
 * d.拿到权限结果，onRequestPermissionsResult
 * e.判断结果是否有对应权限
 * f.如果有，则执行操作
 */

public class DemoPermissionActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivCamera;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity_permission);
        initView();
    }

    private void initView() {
        TextView textView = findViewById(R.id.demo_camera);
        ivCamera = findViewById(R.id.demo_tv_camera);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.demo_camera:
                openCamera();
                break;
        }
    }

    private void openCamera() {
        if (checkPermission(Manifest.permission.CAMERA)) {
            //有权限
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 102);
        } else {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 101);
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
        if (requestCode == 102 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        }
        else {
//            Toast.makeText(this,"暂无权限",Toast.LENGTH_SHORT).show();
            jumpSetting();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102 && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Bitmap bitmap = (Bitmap) bundle.get("data");
                ivCamera.setImageBitmap(bitmap);
            }
        }
    }

    private void jumpSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

}
