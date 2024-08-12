package com.example.weibo_wangke.bigimage;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.weibo_wangke.R;
import com.example.weibo_wangke.bean.WeiboInfoBean;
import com.example.weibo_wangke.retroapi.RetrofitApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BigImageActivity extends AppCompatActivity implements View.OnClickListener {

    private WeiboInfoBean weiboInfoBean;
    private TextView count;
    private List<String> imageUrls;

    private int currentPosition;
    private File imageFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);


        Intent intent = getIntent();
        weiboInfoBean = (WeiboInfoBean) intent.getSerializableExtra("weibo_info");
        //设置图片初始位置
        int initialPosition = intent.getIntExtra("imgPosition", 0);
        currentPosition = initialPosition;
        ViewPager2 viewPager = findViewById(R.id.image_viewpager);

        imageUrls = weiboInfoBean.images;
        ImageViewPagerAdapter imageViewPagerAdapter = new ImageViewPagerAdapter(this, imageUrls);
        viewPager.setAdapter(imageViewPagerAdapter);
        viewPager.setCurrentItem(initialPosition, false);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                updateCountText(position, imageUrls.size());
            }
        });
        initView();
    }

    // 更新显示的文本
    private void updateCountText(int position, int total) {
        currentPosition = position;
        String text = (position + 1) + "/" + total;
        count.setText(text);
    }

    private void initView() {
        ImageView avatar = findViewById(R.id.avatar_image);
        TextView username = findViewById(R.id.user_name);
        TextView download = findViewById(R.id.download);
        count = findViewById(R.id.count);
        download.setOnClickListener(this);
        Glide.with(this).load(weiboInfoBean.avatar).apply(RequestOptions.circleCropTransform()).into(avatar);
        username.setText(weiboInfoBean.username);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.download:
                //如果有权限开始下载
//                if (checkPermission()) {
//                    downloadImage(imageUrls.get(currentPosition));
//                    Toast.makeText(this, "开始下载", Toast.LENGTH_SHORT).show();
//                } else {
//                    //否则开始申请权限
//                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
//                }
                downloadAndSaveImage(imageUrls.get(currentPosition));
                break;
        }
    }

    private void downloadAndSaveImage(String imageUrl) {
        // 使用Glide下载图片
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        saveImageToGallery(resource);
                    }
                });
    }

    private void saveImageToGallery(Bitmap bitmap) {
        // 使用MediaStore API将Bitmap保存到系统相册
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, "image.jpg");
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

        ContentResolver contentResolver = getContentResolver();
        Uri imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        try {
            OutputStream outputStream = contentResolver.openOutputStream(Objects.requireNonNull(imageUri));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            Objects.requireNonNull(outputStream).close();
            Toast.makeText(this, "图片保存成功", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "图片保存失败", Toast.LENGTH_SHORT).show();
        }
    }


    //检查是否有权限
    private boolean checkPermission() {
        int hasPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return hasPermission == PackageManager.PERMISSION_GRANTED;
    }

    private void downloadImage(String fileUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://your-base-url.com/") // 实际上这个URL不会被用到，因为我们使用@Url注解传递完整的URL
                .build();

        RetrofitApi apiService = retrofit.create(RetrofitApi.class);
        Call<ResponseBody> call = apiService.downloadImage(fileUrl);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    boolean writtenToDisk = writeResponseBodyToDisk(response.body());
                    if (writtenToDisk) {
                        // 保存图片成功后，添加到系统相册
                        addImageToGallery(imageFile);
                        Toast.makeText(BigImageActivity.this, "Image downloaded", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BigImageActivity.this, "Failed to save the image", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BigImageActivity.this, "Failed to download the image", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(BigImageActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyApp");
            if (!storageDir.exists()) {
                storageDir.mkdirs();
            }

            imageFile = new File(storageDir, "downloadedImage.jpg");
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];
                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(imageFile);

                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                }

                outputStream.flush();
                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    private void addImageToGallery(File imageFile) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, "downloadedImage.jpg");
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        contentValues.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
        contentValues.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        contentValues.put(MediaStore.Images.Media.DATA, imageFile.getAbsolutePath());

        ContentResolver contentResolver = getContentResolver();
        contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            downloadImage(imageUrls.get(currentPosition));
        } else {
            if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
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
                .setMessage("App需要权限来下载图片，请授予权限。")
                .setPositiveButton("授予权限", (dialog, which) -> {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
                })
                .setNegativeButton("取消", null)
                .show();
    }

}
