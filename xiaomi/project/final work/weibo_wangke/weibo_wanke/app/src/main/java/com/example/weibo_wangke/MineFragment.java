package com.example.weibo_wangke;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.weibo_wangke.bean.BaseBean;
import com.example.weibo_wangke.bean.UserInfoBean;
import com.example.weibo_wangke.retroapi.RetrofitApi;
import com.example.weibo_wangke.retroapi.RetrofitManager;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MineFragment extends Fragment implements View.OnClickListener {
    private final String TAG = "MineFragment";
    private ImageView avatarImage;
    private TextView userName;
    private TextView fenCount;
    private TextView infoText;


    private Call<BaseBean<UserInfoBean>> getCall;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        avatarImage = view.findViewById(R.id.avatar_image);
        avatarImage.setOnClickListener(this);
        userName = view.findViewById(R.id.user_name);
        fenCount = view.findViewById(R.id.fenCount);
        infoText = view.findViewById(R.id.tv_info);
        isLogin();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isLogin();
            }
        }, 1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getCall != null && !getCall.isCanceled()) {
            getCall.cancel();
        }
    }

    public void logout() {
        avatarImage.setImageResource(R.drawable.empty_avatar);
        avatarImage.setClickable(true);
        userName.setText("请先登录");
        fenCount.setText("点击头像去登陆");
        infoText.setText("登录后查看");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.avatar_image:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    //判断是否登录
    private void isLogin() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LocalData", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        if (token != null && !token.isEmpty()) {
            if (token.length() > 30) {
                Log.i(TAG, token);
                getUserInfo(token);
            }
        } else {
            logout();
        }
    }

    private void getUserInfo(String token) {
        RetrofitManager.getInstance().setToken(token);
        RetrofitApi api = RetrofitManager.getInstance().createApi();
        getCall = api.getUserInfo();
        getCall.enqueue(new Callback<BaseBean<UserInfoBean>>() {
            @Override
            public void onResponse(Call<BaseBean<UserInfoBean>> call, Response<BaseBean<UserInfoBean>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Gson gson = new Gson();
                    BaseBean<UserInfoBean> baseBean = response.body();
                    String jsonResponse = gson.toJson(baseBean);
                    Log.d(TAG, "Response JSON: " + jsonResponse);
                    UserInfoBean userInfoBean = baseBean.data;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(getActivity())
                                    .load(userInfoBean.avatar)
                                    .apply(RequestOptions.circleCropTransform())
                                    .into(avatarImage);
                            userName.setText(userInfoBean.username);
                            fenCount.setText(userInfoBean.phone);
                            infoText.setText("现在还没有动态");
                            String s = "userInfo";
                            EventBus.getDefault().post(s);
                            avatarImage.setClickable(false);
                        }
                    });
                } else if (response.code() == 403) {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LocalData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", null);
                    editor.apply();
                    isLogin();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    Toast.makeText(getActivity(), "登录超时，请重新登录", Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                } else {
                    Log.e(TAG, "请求失败：" + response.code());
                }

            }

            @Override
            public void onFailure(Call<BaseBean<UserInfoBean>> call, Throwable t) {

            }
        });
    }
}
