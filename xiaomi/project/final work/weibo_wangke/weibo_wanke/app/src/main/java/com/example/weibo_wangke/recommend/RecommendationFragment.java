package com.example.weibo_wangke.recommend;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.example.weibo_wangke.HomePageActivity;
import com.example.weibo_wangke.LoginActivity;
import com.example.weibo_wangke.MainActivity;
import com.example.weibo_wangke.R;
import com.example.weibo_wangke.bean.BaseBean;
import com.example.weibo_wangke.bean.LikeRequestBody;
import com.example.weibo_wangke.bean.RecordsBean;
import com.example.weibo_wangke.bean.WeiboInfoBean;
import com.example.weibo_wangke.bigimage.BigImageActivity;
import com.example.weibo_wangke.retroapi.RetrofitApi;
import com.example.weibo_wangke.retroapi.RetrofitManager;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendationFragment extends Fragment implements OnLoadMoreListener, RecommendRecycleAdapter.OnItemClickListener, View.OnClickListener {

    private static final String TAG = "RecommendationFragment";
    private Call<BaseBean<RecordsBean>> homePageCall;

    private RecommendRecycleAdapter myRecycleAdapter;
    private RecyclerView recyclerView;

    private int currentPage = 1;//当前页

    private boolean isPageEnd = false;//是否已经加载所有的页
    private SwipeRefreshLayout swipeRefreshLayout;
    private ViewFlipper viewSwitcher;
    private List<WeiboInfoBean> weiboInfoBeans;
    private Call<BaseBean<Boolean>> likeCall;
    private Call<BaseBean<Boolean>> dislikeCall;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommedation, container, false);
        EventBus.getDefault().register(this);
        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycleAdapter = new RecommendRecycleAdapter(R.layout.recycle_items, null);
        myRecycleAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(myRecycleAdapter);

        viewSwitcher = view.findViewById(R.id.view_flipper);
        TextView retry = view.findViewById(R.id.retry_button);
        // 设置重试按钮点击事件
        retry.setOnClickListener(this);

        viewSwitcher.setDisplayedChild(0);
        getWeiboHomePage(currentPage);//获取推荐信息

        //下拉刷新
        swipeRefreshLayout = view.findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1; // 重置页码和标志
                isPageEnd = false;
                myRecycleAdapter.getLoadMoreModule().loadMoreComplete();
                getWeiboHomePage(currentPage);
            }
        });

        //上拉加载更多,设置上滑的监听
        myRecycleAdapter.getLoadMoreModule().setOnLoadMoreListener(this);


        //滚动监听，暂停目前不可见的视频
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                pauseInvisibleVideos();
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                pauseInvisibleVideos();
            }
        });

        return view;
    }

    private void pauseInvisibleVideos() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

        for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
            VideoViewHolder holder = (VideoViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
            if (holder != null && holder.isVideoPlaying) {
                int[] location = new int[2];
                holder.videoView.getLocationOnScreen(location);
                int videoTop = location[1];
                int videoBottom = videoTop + holder.videoView.getHeight();

                int screenTop = 0;
                int screenBottom = getResources().getDisplayMetrics().heightPixels;

                if (videoBottom <= screenTop || videoTop >= screenBottom) {
                    holder.videoView.pause();
                    holder.isVideoPlaying = false;
                }
            }
        }
    }


    private void getWeiboHomePage(int page) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LocalData", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        if (token != null && !token.isEmpty()) {
            if (token.length() > 30) {
                Log.i(TAG, token);
                getHomePage(token, page);
            }
        } else getHomePage(null, page);
    }


    private void getHomePage(String token, int page) {
        RetrofitManager.getInstance().setToken(token);
        RetrofitApi api = RetrofitManager.getInstance().createApi();
        homePageCall = api.getHomePage(page);
        homePageCall.enqueue(new Callback<BaseBean<RecordsBean>>() {
            @Override
            public void onResponse(Call<BaseBean<RecordsBean>> call, Response<BaseBean<RecordsBean>> response) {
                if (response.isSuccessful() && response.body() != null) {
//                    Gson gson = new Gson();
                    BaseBean<RecordsBean> baseBean = response.body();
                    if (baseBean.code != 403) {
                        RecordsBean recordsBean = baseBean.data;
//                        String jsonResponse = gson.toJson(weiboInfoBeans);
//                        Log.d(TAG, "Response JSON: " + jsonResponse);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (currentPage == 1) {
                                    if (weiboInfoBeans != null) {
                                        weiboInfoBeans.clear();
                                        weiboInfoBeans.addAll(recordsBean.records);
                                    } else {
                                        weiboInfoBeans = recordsBean.records;
                                    }
                                    Collections.shuffle(weiboInfoBeans);
                                    myRecycleAdapter.updateAdapter(weiboInfoBeans);
                                    swipeRefreshLayout.setRefreshing(false);
                                    viewSwitcher.setDisplayedChild(2);
                                    Log.i(TAG,"加载页："+currentPage);
                                } else {
                                    if (recordsBean.records.isEmpty()) {
                                        Log.i(TAG,"加载到空白页，结束加载更多"+currentPage);
                                        isPageEnd = true;
                                        myRecycleAdapter.getLoadMoreModule().loadMoreEnd();
                                    } else {
                                        Log.i(TAG,"加载页："+currentPage);
//                                        weiboInfoBeans.addAll(recordsBean.records);
                                        myRecycleAdapter.addData(recordsBean.records);
                                        myRecycleAdapter.getLoadMoreModule().loadMoreComplete();
                                    }
                                }

                            }
                        });
                    } else {
                        viewSwitcher.setDisplayedChild(2);
                        tokenOverTime();
                    }
                } else {
                    viewSwitcher.setDisplayedChild(1);
                }
            }

            @Override
            public void onFailure(Call<BaseBean<RecordsBean>> call, Throwable t) {
                Log.d(TAG, "请求失败了", t);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewSwitcher.setDisplayedChild(1); // 显示错误视图
                    }
                });
            }
        });

    }

    private void tokenOverTime() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LocalData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", null);
        editor.apply();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        Toast.makeText(getActivity(), "登录超时，请重新登录", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (homePageCall != null && !homePageCall.isCanceled()) {
            homePageCall.cancel();
        }
        if (likeCall != null && !likeCall.isCanceled()) {
            likeCall.cancel();
        }
        if (dislikeCall != null && !dislikeCall.isCanceled()) {
            dislikeCall.cancel();
        }
    }


    @Override
    public void onLoadMore() {
        Log.i(TAG,"加载更多状态"+isPageEnd);
        if (!isPageEnd) {
            currentPage++;
            getWeiboHomePage(currentPage);
        }else {
            myRecycleAdapter.getLoadMoreModule().loadMoreEnd();
        }
    }

    @Override
    public void onDeleteClick(int position) {
        weiboInfoBeans.remove(position);
//        weiboInfoBeans.clear();
        getActivity().runOnUiThread(() -> {
//            myRecycleAdapter.notifyDataSetChanged();
            if(weiboInfoBeans.isEmpty()){
                myRecycleAdapter.notifyDataSetChanged();
            }else {
                myRecycleAdapter.notifyItemRemoved(position);
                myRecycleAdapter.notifyItemRangeChanged(position, weiboInfoBeans.size() - position);
            }

        });
    }

    @Override
    public void onLikeClick(int position) {
        like(weiboInfoBeans.get(position).id, position);

    }

    @Override
    public void onDislikeClick(int position) {
        dislike(weiboInfoBeans.get(position).id, position);
    }

    @Override
    public void onOpenImage(int position,int imgPosition) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                WeiboInfoBean weiboInfoBean = weiboInfoBeans.get(position);
                Intent intent = new Intent(getActivity(), BigImageActivity.class);
                intent.putExtra("weibo_info", weiboInfoBean);
                intent.putExtra("imgPosition", imgPosition);
                startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, 300);
    }

    private void like(Long id, int position) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LocalData", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        if (token != null && !token.isEmpty()) {
            RetrofitManager.getInstance().setToken(token);
            RetrofitApi api = RetrofitManager.getInstance().createApi();
            LikeRequestBody likeRequestBody = new LikeRequestBody(id);
            likeCall = api.postLike(likeRequestBody);
            likeCall.enqueue(new Callback<BaseBean<Boolean>>() {
                @Override
                public void onResponse(Call<BaseBean<Boolean>> call, Response<BaseBean<Boolean>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        BaseBean<Boolean> baseBean = response.body();
                        if (baseBean.code == 200) {
                            Gson gson = new Gson();
                            String jsonResponse = gson.toJson(baseBean);
                            Log.d(TAG, "Like Response JSON: " + jsonResponse);
                            if (baseBean.data) {
//                                weiboInfoBeans.get(position).likeFlag = true;
                                // 通知 RecyclerView 更新 item，仅更新特定部分
                                myRecycleAdapter.notifyItemChanged(position, true);
//                                myRecycleAdapter.notifyItemChanged(position);
                            }
                        }
                    } else {
                        tokenOverTime();
                    }
                }

                @Override
                public void onFailure(Call<BaseBean<Boolean>> call, Throwable t) {
                    Log.i(TAG, "点赞请求失败");
                }
            });
        } else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            Toast.makeText(getActivity(), "点赞需要登录，请登录", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }

    private void dislike(Long id, int position) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LocalData", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        if (token != null && !token.isEmpty()) {
            RetrofitManager.getInstance().setToken(token);
            RetrofitApi api = RetrofitManager.getInstance().createApi();
            LikeRequestBody likeRequestBody = new LikeRequestBody(id);
            dislikeCall = api.postDislike(likeRequestBody);
            dislikeCall.enqueue(new Callback<BaseBean<Boolean>>() {
                @Override
                public void onResponse(Call<BaseBean<Boolean>> call, Response<BaseBean<Boolean>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        BaseBean<Boolean> baseBean = response.body();
                        if (baseBean.code == 200) {
                            Gson gson = new Gson();
                            String jsonResponse = gson.toJson(baseBean);
                            Log.d(TAG, "Like Response JSON: " + jsonResponse);
                            if (baseBean.data) {
                                myRecycleAdapter.notifyItemChanged(position, false);
                            }
                        }
                    } else {
                        tokenOverTime();
                    }
                }

                @Override
                public void onFailure(Call<BaseBean<Boolean>> call, Throwable t) {
                    Log.i(TAG, "点赞请求失败");
                }
            });
        } else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            Toast.makeText(getActivity(), "点赞需要登录，请登录", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retry_button:
                currentPage = 1; // 重置页码和标志
                isPageEnd = false;
                getWeiboHomePage(currentPage);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(String s) {
        if (s.equals("login")){
            currentPage = 1; // 重置页码和标志
            isPageEnd = false;
            myRecycleAdapter.getLoadMoreModule().loadMoreComplete();
            getWeiboHomePage(currentPage);
        }
    }
}
