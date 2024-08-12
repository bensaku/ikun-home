package com.example.weibo_wangke;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weibo_wangke.bean.BaseBean;
import com.example.weibo_wangke.bean.CodeRequestBody;
import com.example.weibo_wangke.bean.LoginRequestBody;
import com.example.weibo_wangke.retroapi.RetrofitApi;
import com.example.weibo_wangke.retroapi.RetrofitManager;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    int second = (int) msg.obj;
                    getCodeText.setText("获取验证码(" + second + "s)");

                    if (currentSecond > 0) {
                        getCodeText.setEnabled(false);
                        Message message = Message.obtain();
                        message.what = 100;
                        message.obj = --currentSecond;
                        handler.sendMessageDelayed(message, 1000);
                    } else {
                        getCodeText.setText("发送验证码");
                        getCodeText.setEnabled(true);
                        currentSecond = totalSecondCount;
                    }
                    break;
            }
        }
    };

    final int totalSecondCount = 60;

    int currentSecond = totalSecondCount;
    private final String TAG = "LoginActivity";
    private Call<BaseBean<Object>> getCode;
    private EditText phoneNumber;
    private TextView getCodeText;
    private EditText codeEdit;
    private Call<BaseBean<String>> loginCall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        View topLine = findViewById(R.id.top_line);
        TextView goBack = topLine.findViewById(R.id.tv_left);
        goBack.setText("返回");
        TextView centerText = topLine.findViewById(R.id.tv_center);
        centerText.setText("登录账号");
        getCodeText = findViewById(R.id.tv_code);
        TextView login = findViewById(R.id.login_button);
        phoneNumber = findViewById(R.id.et_phone_number);
        codeEdit = findViewById(R.id.ed_code);

        goBack.setOnClickListener(this);
        getCodeText.setOnClickListener(this);
        login.setOnClickListener(this);

        phoneNumber.addTextChangedListener(this);
        codeEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 6) {
                    login.setEnabled(false);
                } else {
                    login.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_left:
                this.finish();
                break;
            case R.id.tv_code:
                countDown();
                Log.i(TAG, "开始获取验证码" + phoneNumber.getText().toString());
                postPhoneNumber(phoneNumber.getText().toString());
                break;
            case R.id.login_button:
                Log.i(TAG, "click login");
                loginEvent(phoneNumber.getText().toString(), codeEdit.getText().toString());
                break;
        }
    }

    private void postPhoneNumber(String phone) {
        RetrofitManager.getInstance().setToken(null);
        RetrofitApi api = RetrofitManager.getInstance().createApi();
        CodeRequestBody codeRequestBody = new CodeRequestBody(phone);
        getCode = api.sendCode(codeRequestBody);
        getCode.enqueue(new Callback<BaseBean<Object>>() {
            @Override
            public void onResponse(Call<BaseBean<Object>> call, Response<BaseBean<Object>> response) {
                BaseBean<Object> baseBean = response.body();
                Log.i(TAG, baseBean.msg + baseBean.code + baseBean.data);
            }

            @Override
            public void onFailure(Call<BaseBean<Object>> call, Throwable t) {
                Log.i(TAG, "请求失败了");
            }
        });
    }

    private void loginEvent(String phone, String code) {
        RetrofitApi api = RetrofitManager.getInstance().createApi();
        LoginRequestBody loginRequestBody = new LoginRequestBody(phone, code);
        loginCall = api.postLogin(loginRequestBody);
        loginCall.enqueue(new Callback<BaseBean<String>>() {
            @Override
            public void onResponse(Call<BaseBean<String>> call, Response<BaseBean<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Gson gson = new Gson();
                    BaseBean<String> baseBean = response.body();
                    String jsonResponse = gson.toJson(response.body());
                    Log.d(TAG, "Response JSON: " + jsonResponse);
                    if(baseBean.code==200){
                        SharedPreferences sharedPreferences = getSharedPreferences("LocalData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token", baseBean.data);
                        editor.commit();
                        Log.i(TAG, "登录成功了");
                        String s = "login";
                        EventBus.getDefault().post(s);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "请输入正确的手机号和密码!", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "请求失败：" + response.code());
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "登录失败，请重新登录!", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "请求失败：" + response.code());
                }

            }

            @Override
            public void onFailure(Call<BaseBean<String>> call, Throwable t) {
                Log.i(TAG, "登录失败了");
            }
        });
    }


    private void countDown() {
        getCodeText.setEnabled(false);
        Message message = Message.obtain();
        message.what = 100;
        message.obj = currentSecond;
        handler.sendMessageDelayed(message, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getCode != null && !getCode.isCanceled()) {
            getCode.cancel();
        }
        if (loginCall != null && !loginCall.isCanceled()) {
            loginCall.cancel();
        }
    }


    //根据输入框判断后续内容是否可以输入、点击
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() < 11) {
            //文本长度小于最大长度时，禁用点击事件
            getCodeText.setEnabled(false);
            codeEdit.setEnabled(false);

        } else {
            //文本长度达到最大长度时，启用点击事件
            getCodeText.setEnabled(true);
            codeEdit.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
