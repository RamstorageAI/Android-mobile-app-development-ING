package com.example.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<AlbumInfo> mData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
        //Log.e("MainActivity","getData()");
        RecyclerView recycleView = (RecyclerView)findViewById(R.id.babyAlbumlist);
        // Log.e("MainActivity","get RecycleView");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);
        //Log.e("MainActivity","layoutManager");
        AlbumAdapter adapter = new AlbumAdapter(mData);
        // Log.e("MainActivity","adapter");
        recycleView.setAdapter(adapter);

        // Log.e("MainActivity","setAdapter");
    }
    private void getData()
    {
        AlbumInfo albumInfo = new AlbumInfo("毡帽系列1","此系列服装有点cute，像不像小车夫。",R.drawable.i1);
        mData.add(albumInfo);
        AlbumInfo albumInfo2 = new AlbumInfo("蜗牛系列2","宝宝变成了小蜗牛，爬啊爬啊爬啊。",R.drawable.i2);
        mData.add(albumInfo2);
        AlbumInfo albumInfo3 = new AlbumInfo("小蜜蜂系列3","小蜜蜂，嗡嗡嗡，飞到西，飞到东。",R.drawable.i3);
        mData.add(albumInfo3);
        AlbumInfo albumInfo4 = new AlbumInfo("毡帽系列4","此系列服装有点cute，像不像小车夫。",R.drawable.i4);
        mData.add(albumInfo4);
        AlbumInfo albumInfo5 = new AlbumInfo("蜗牛系列5","宝宝变成了小蜗牛，爬啊爬啊爬啊。",R.drawable.i5);
        mData.add(albumInfo5);
        AlbumInfo albumInfo6 = new AlbumInfo("小蜜蜂系列6","小蜜蜂，嗡嗡嗡，飞到西，飞到东。",R.drawable.i6);
        mData.add(albumInfo6);
        AlbumInfo albumInfo7 = new AlbumInfo("毡帽系列7","此系列服装有点cute，像不像小车夫。",R.drawable.i1);
        mData.add(albumInfo7);
        AlbumInfo albumInfo8 = new AlbumInfo("蜗牛系列8","宝宝变成了小蜗牛，爬啊爬啊爬啊。",R.drawable.i2);
        mData.add(albumInfo8);
        AlbumInfo albumInfo9 = new AlbumInfo("小蜜蜂系列9","小蜜蜂，嗡嗡嗡，飞到西，飞到东。",R.drawable.i3);
        mData.add(albumInfo9);
        AlbumInfo albumInfo10 = new AlbumInfo("毡帽系列10","此系列服装有点cute，像不像小车夫。",R.drawable.i4);
        mData.add(albumInfo10);
        AlbumInfo albumInfo11 = new AlbumInfo("蜗牛系列11","宝宝变成了小蜗牛，爬啊爬啊爬啊。",R.drawable.i5);
        mData.add(albumInfo11);
        AlbumInfo albumInfo12 = new AlbumInfo("小蜜蜂系列12","小蜜蜂，嗡嗡嗡，飞到西，飞到东。",R.drawable.i6);
        mData.add(albumInfo12);
        AlbumInfo albumInfo13 = new AlbumInfo("小蜜蜂系列13","小蜜蜂，嗡嗡嗡，飞到西，飞到东。",R.drawable.i6);
        mData.add(albumInfo13);
        AlbumInfo albumInfo14 = new AlbumInfo("小蜜蜂系列14","小蜜蜂，嗡嗡嗡，飞到西，飞到东。",R.drawable.i6);
        mData.add(albumInfo14);
        AlbumInfo albumInfo15 = new AlbumInfo("小蜜蜂系列15","小蜜蜂，嗡嗡嗡，飞到西，飞到东。",R.drawable.i6);
        mData.add(albumInfo15);
        AlbumInfo albumInfo16 = new AlbumInfo("小蜜蜂系列16","小蜜蜂，嗡嗡嗡，飞到西，飞到东。",R.drawable.i6);
        mData.add(albumInfo16);
        AlbumInfo albumInfo17 = new AlbumInfo("小蜜蜂系列17","小蜜蜂，嗡嗡嗡，飞到西，飞到东。",R.drawable.i6);
        mData.add(albumInfo17);
        AlbumInfo albumInfo18 = new AlbumInfo("小蜜蜂系列18","小蜜蜂，嗡嗡嗡，飞到西，飞到东。",R.drawable.i6);
        mData.add(albumInfo18);
    }
}
