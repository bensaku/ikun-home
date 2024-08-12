package com.example.weibo_wangke.bigimage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class ImageViewPagerAdapter extends FragmentStateAdapter {
    private final List<String> imageUrls;

    public ImageViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<String> imageUrls) {
        super(fragmentActivity);
        this.imageUrls = imageUrls;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ImageFragment.newInstance(imageUrls.get(position));
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }
}
