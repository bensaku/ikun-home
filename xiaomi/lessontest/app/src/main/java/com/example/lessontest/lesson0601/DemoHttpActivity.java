//package com.example.lessontest.lesson0601;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.myapplication.R;
//import com.example.myapplication.bean.BaseBean;
//import com.example.myapplication.bean.GameBean;
//import com.google.gson.Gson;
//
//import java.io.IOException;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.Response;
//
//public class DemoHttpActivity extends AppCompatActivity implements View.OnClickListener, Callback {
//
//    private String url_A = "https://hotfix-service-prod.g.mi.com/quick-game/game/110";
//
//    private final String URL_POST_FORM = "https://hotfix-service-prod.g.mi.com/quick-game/api/auth/sendCodeByFormData";
//
//
//    private Gson gson = new Gson();
//
//    private final String TAG = "DemoHttpActivity";
//    private TextView contentText;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.demo_activity_http);
//        initView();
//    }
//
//    private void initView() {
//        TextView tvOkhttpSync = findViewById(R.id.demo_tv_okhttp_sync);
//        tvOkhttpSync.setOnClickListener(this);
//        TextView tvOkhttpAsync = findViewById(R.id.demo_tv_okhttp_async);
//        tvOkhttpAsync.setOnClickListener(this);
//        TextView tvAsync2 = findViewById(R.id.demo_tv_okhttp_async_post);
//        tvAsync2.setOnClickListener(this);
//        TextView retrofitView = findViewById(R.id.demo_retrofit_get);
//        retrofitView.setOnClickListener(this);
//        contentText = findViewById(R.id.demo_tv_content);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.demo_tv_okhttp_sync:
//                getSync();
//                break;
//            case R.id.demo_tv_okhttp_async:
//                getAsync();
//                break;
//            case R.id.demo_tv_okhttp_async_post:
//                PostAsync();
//                break;
//            case R.id.demo_retrofit_get:
////                sendMsg("18118450507");
//                getGameDetail();
//                break;
//
//        }
//    }
//
//    private void getSync() {
//        OkhttpInstance.getInstance().getSync(url_A);
//    }
//
//    private void getAsync() {
//        OkhttpInstance.getInstance().getAsync(url_A, this);
//    }
//
//    private void PostAsync() {
//        OkhttpInstance.getInstance().postAsync(URL_POST_FORM, this);
//    }
//
//    private void getGameDetail() {
//        RetrofitApi api = RetrofitManager.getInstance().createApi();
//        retrofit2.Call<BaseBean<GameBean>> call = api.garGameDetail("120");
//        call.enqueue(new retrofit2.Callback<BaseBean<GameBean>>() {
//            @Override
//            public void onResponse(retrofit2.Call<BaseBean<GameBean>> call, retrofit2.Response<BaseBean<GameBean>> response) {
//                BaseBean<GameBean> bean = response.body();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        contentText.setText(bean.data.getGameName());
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<BaseBean<GameBean>> call, Throwable t) {
//
//            }
//        });
//    }
//
//
//    private void sendMsg(String phoneNumber){
//        RetrofitApi api = RetrofitManager.getInstance().createApi();
//        api.sendMsg(phoneNumber).enqueue(new retrofit2.Callback<BaseBean<Object>>() {
//            @Override
//            public void onResponse(retrofit2.Call<BaseBean<Object>> call, retrofit2.Response<BaseBean<Object>> response) {
//                Toast.makeText(DemoHttpActivity.this,"succeed",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<BaseBean<Object>> call, Throwable t) {
//
//            }
//        });
//    }
//
//    @Override
//    public void onFailure(@NonNull Call call, @NonNull IOException e) {
//
//    }
//
//    @Override
//    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//        String string = response.body().string();
////        GameInfoBean gameInfoBean = gson.fromJson(string, GameInfoBean.class);
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                contentText.setText(string);
//            }
//        });
//        Log.i(TAG, string);
//    }
//
//}
