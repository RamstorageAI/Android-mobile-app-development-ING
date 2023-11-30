package com.example.projectnavigationdemo;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class NewsPageFragmentAdapter extends FragmentStatePagerAdapter {
    private String[] channelList;
    private FragmentManager fm;
    public NewsPageFragmentAdapter(FragmentManager fm, String[] channelList) {
        super(fm);
        this.channelList = channelList;
        this.fm=fm;
    }
    @Override
    public Fragment getItem(int idx) {
        Log.e("NewsPageFragmentAdapter","getItem"+idx);
        String newsCategoryTitle = channelList[idx];
        return NewsChannelFragment.newInstance(newsCategoryTitle);
    }
    @Override
    public int getCount() {
        return channelList.length;
    }
}
