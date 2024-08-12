package com.example.myapplication.work0603;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.example.myapplication.R;
import com.example.myapplication.bean.BaseBean;
import com.example.myapplication.bean.DataBean;
import com.example.myapplication.bean.GameBean;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, OnLoadMoreListener {

    private String TAG = "SearchActivity";
    private RecyclerView recyclerView;
    private Call<BaseBean<DataBean>> call;
    private GameDetailRecycleAdapter adapter;

    private String currentSearch = "";

    private int currentPage = 1;//当前页

    private boolean isPageEnd = false;//是否已经加载所有的页
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work0602_activity_search);
        initView();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        TextView textView = findViewById(R.id.work0602_tv_search);
        textView.setOnClickListener(this);
        recyclerView = findViewById(R.id.work0602_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //初始化adapter
        adapter = new GameDetailRecycleAdapter(R.layout.work0528_game, null);
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener((adapter, view, position) -> {
            GameBean gameBean = (GameBean) adapter.getItem(position);
            if (gameBean != null) {
                //打开详情页面
                Intent intent =new Intent(SearchActivity.this, GameDetailActivity.class);
                intent.putExtra("extra_game",gameBean);
                startActivity(intent);
            }
        });

        //下拉刷新
        swipeRefreshLayout = findViewById(R.id.work0603_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1; // 重置页码和标志
                isPageEnd = false;
                getAsync(currentSearch, 1);
            }
        });


        //上拉加载更多,设置上滑的监听
        adapter.getLoadMoreModule().setOnLoadMoreListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.work0602_tv_search:
                currentPage = 1; // 重置页码和标志
                isPageEnd = false;
                EditText editText = findViewById(R.id.work0602_et_search);
                currentSearch = editText.getText().toString();
                getAsync(editText.getText().toString(), currentPage);
                break;
        }
    }

    private void getAsync(String key, int currentPage) {
        GameSearchApi api = GameRetrofitManager.getInstance().createApi();
        call = api.searchGames(key, currentPage);
        call.enqueue(new Callback<BaseBean<DataBean>>() {
            @Override
            public void onResponse(Call<BaseBean<DataBean>> call, Response<BaseBean<DataBean>> response) {
                BaseBean<DataBean> baseBean = response.body();
                if (baseBean != null) {
                    DataBean dataBean = baseBean.data;
                    Gson gson = new Gson();
                    String jsonResponse = gson.toJson(dataBean.getRecords());
                    Log.i(TAG, "Response JSON: " + jsonResponse);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (currentPage == 1) {
                                //如果是第一页，更新内容
                                adapter.updateData(dataBean.getRecords());
                                swipeRefreshLayout.setRefreshing(false);
                            } else {
                                //如果是上滑，判断内容，添加新内容或结束加载
                                if (dataBean.getRecords().isEmpty()) {
                                    isPageEnd = true;
                                    adapter.getLoadMoreModule().loadMoreEnd();
                                } else {
                                    adapter.addData(dataBean.getRecords());
                                    adapter.getLoadMoreModule().loadMoreComplete();
                                }
                            }
                        }
                    });
                }else isPageEnd = true;
            }

            @Override
            public void onFailure(Call<BaseBean<DataBean>> call, Throwable t) {
                Log.i(TAG, "请求出错了");
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call != null) {
            call.cancel();
        }
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getBus(String str){
        Log.i(TAG,str);
    }


    @Override
    public void onLoadMore() {
        if (!isPageEnd) {
            currentPage++;
            getAsync(currentSearch, currentPage);
        } else {
            adapter.getLoadMoreModule().loadMoreEnd();
        }

    }
}
