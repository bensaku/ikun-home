package com.example.weibo_wangke;

import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.weibo_wangke.recommend.RecommendationFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private SparseArray<Fragment> registeredFragments = new SparseArray<>();
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = registeredFragments.get(position);
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new RecommendationFragment();
                    break;
                case 1:
                    fragment = new MineFragment();
                    break;
                default:
                    fragment = new RecommendationFragment();
                    break;
            }
            registeredFragments.put(position, fragment);
        }
        return fragment;
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        registeredFragments.clear();
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
