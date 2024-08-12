package com.example.myapplication.work0530;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.R;

import java.util.ArrayList;

public class MainPageFragment extends Fragment {
    public MainPageFragment() {
        super(R.layout.work0530_mainpage_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.work0530_mainpage_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.work0530_recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        ArrayList<Item> itemList = new ArrayList<>();
        // 添加一些示例数据
        itemList.add(new Item(Item.TYPE_TEXT, "This is a text item"));
        itemList.add(new Item(Item.TYPE_IMAGE, String.valueOf(R.drawable.icon1)));
        itemList.add(new Item(Item.TYPE_TEXT, "This is a text item"));
        itemList.add(new Item(Item.TYPE_IMAGE, String.valueOf(R.drawable.icon2)));
        itemList.add(new Item(Item.TYPE_TEXT, "This is a text item"));
        itemList.add(new Item(Item.TYPE_IMAGE, String.valueOf(R.drawable.kirby)));
        itemList.add(new Item(Item.TYPE_IMAGE, String.valueOf(R.drawable.icon1)));
        itemList.add(new Item(Item.TYPE_TEXT, "This is a text item"));
        itemList.add(new Item(Item.TYPE_IMAGE, String.valueOf(R.drawable.icon1)));
        itemList.add(new Item(Item.TYPE_IMAGE, String.valueOf(R.drawable.kirby)));
        itemList.add(new Item(Item.TYPE_IMAGE, String.valueOf(R.drawable.icon1)));
        itemList.add(new Item(Item.TYPE_TEXT, "测试文本"));
        itemList.add(new Item(Item.TYPE_TEXT, "This is a text item"));
        itemList.add(new Item(Item.TYPE_TEXT, "测试文本"));
        itemList.add(new Item(Item.TYPE_IMAGE, String.valueOf(R.drawable.icon1)));
        MyBaseAdapter adapter = new MyBaseAdapter(itemList);
        recyclerView.setAdapter(adapter);

        // 下拉刷新
        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ArrayList<Item> newData = new ArrayList<>();
                newData.add(new Item(Item.TYPE_IMAGE, String.valueOf(R.drawable.kirby)));
                newData.add(new Item(Item.TYPE_TEXT, "刷新内容"));
                newData.add(new Item(Item.TYPE_TEXT, "刷新内容"));
                newData.add(new Item(Item.TYPE_TEXT, "刷新内容"));
                newData.add(new Item(Item.TYPE_IMAGE, String.valueOf(R.drawable.kirby)));
                newData.add(new Item(Item.TYPE_TEXT, "刷新内容"));
                newData.add(new Item(Item.TYPE_IMAGE, String.valueOf(R.drawable.kirby)));
                adapter.updateData(newData);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }



}
