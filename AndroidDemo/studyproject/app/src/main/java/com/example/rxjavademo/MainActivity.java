package com.example.rxjavademo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String tag = "Main";

    MainViewModel viewModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(view -> {
            getUserWithRxJava();
        });

        viewModel = new ViewModelProvider(this,new MainViewModelFactory(6)).get(MainViewModel.class);

        TextView textView = findViewById(R.id.text1);
        textView.setText("count"+viewModel.getCount());
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(view -> {
            viewModel.incrementCount();
        });
        viewModel.count.observe(this, new androidx.lifecycle.Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText("count"+viewModel.getCount());
            }
        });

        getLifecycle().addObserver(new MyObserver());
    }

    private void RxJavaTest() {

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(tag, "onSubscribe");

            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(tag, s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        Observable<String> observable = Observable.create(emitter -> {
            emitter.onNext("Hello");
            emitter.onNext("Hi");
            emitter.onNext("Aloha");
            emitter.onComplete();
        });

        observable.subscribe(observer);

    }

    private void getUserWithRxJava() {
        RetrofitClient.getInstance().getApiService().getAppData()
                .subscribeOn(Schedulers.io()) // 指定在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理响应
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(tag, "onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull List<User> users) {
                        for (int i = 0; i < users.size(); i++) {
                            Log.i(tag, users.get(i).id + " " + users.get(i).name);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(tag, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(tag, "onComplete");
                    }
                });
    }


    private void getUser() {
//        RetrofitClient.getInstance().getApiService().getAppData().enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    // 处理成功的响应
//                    List<User> userList = response.body();
//                    // 更新UI或其他操作
//                    Log.i(tag,userList.get(0).name);
//                } else {
//                    // 处理失败的响应
//                    Log.i(tag,"Response failed");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//
//            }
//        });
    }


}