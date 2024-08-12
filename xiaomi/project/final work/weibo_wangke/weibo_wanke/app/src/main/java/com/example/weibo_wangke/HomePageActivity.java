package com.example.weibo_wangke;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mineIcon;
    private TextView mineText;
    private ImageView recommendIcon;
    private TextView recommendText;
    private ViewPager2 viewPager2;
    private TextView tvCenter;
    private TextView tvRight;

    private Boolean loginFlag = false;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        View topLine = findViewById(R.id.top_line);
        tvCenter = topLine.findViewById(R.id.tv_center);
        tvRight = topLine.findViewById(R.id.tv_right);

        View mine = findViewById(R.id.mine);
        View recommend = findViewById(R.id.recommend);
        mineIcon = mine.findViewById(R.id.icon);
        mineIcon.setImageResource(R.drawable.mine_icon_selector);
        mineText = mine.findViewById(R.id.icon_text);
        mineText.setText("我的");

        recommendIcon = recommend.findViewById(R.id.icon);
        recommendText = recommend.findViewById(R.id.icon_text);

        viewPager2 = findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                updateTabSelection(position);
            }
        });

        mine.setOnClickListener(this);
        recommend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine:
                viewPager2.setCurrentItem(1);
                break;
            case R.id.recommend:
                viewPager2.setCurrentItem(0);
                break;
            case R.id.tv_right:
                SharedPreferences sharedPreferences = getSharedPreferences("LocalData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("token", null);
                editor.commit();
                loginFlag = false;
                MineFragment registeredFragment = (MineFragment) viewPagerAdapter.getRegisteredFragment(1);
                registeredFragment.logout();
                tvRight.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @SuppressLint("SetTextI18n")
    private void updateTabSelection(int position) {
        if (position == 0) {
            recommendIcon.setSelected(true);
            recommendText.setSelected(true);
            mineIcon.setSelected(false);
            mineText.setSelected(false);
            tvCenter.setText("iH推荐");
            tvRight.setClickable(false);
            tvRight.setVisibility(View.INVISIBLE);
        } else {
            mineIcon.setSelected(true);
            mineText.setSelected(true);
            recommendIcon.setSelected(false);
            recommendText.setSelected(false);
            tvCenter.setText("我的");
            if (loginFlag) {
                Log.i("Homepage", "获得用户信息");
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText("退出登录");
                tvRight.setClickable(true);
                tvRight.setOnClickListener(this);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setLogout(String s) {
        if (s.equals("userInfo")) {
            loginFlag = true;
        } else if (s.equals("login") && viewPager2.getCurrentItem() == 1) {
            Log.i("Homepage", "登录成功");
            Log.i("Homepage", "获得用户信息");
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText("退出登录");
            tvRight.setClickable(true);
            tvRight.setOnClickListener(this);
            viewPagerAdapter.notifyItemChanged(0);
        } else {
            loginFlag = true;
            viewPagerAdapter.notifyItemChanged(0);
        }
    }

}
