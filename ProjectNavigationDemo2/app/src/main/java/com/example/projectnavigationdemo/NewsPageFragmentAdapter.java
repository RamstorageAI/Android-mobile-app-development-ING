package com.example.projectnavigationdemo;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.Objects;

public class NewsPageFragmentAdapter extends FragmentStatePagerAdapter {
    private String[] channelList;
    private FragmentManager fm;
    public NewsPageFragmentAdapter(FragmentManager fm, String[] channelList) {
        super(fm);
        this.channelList = channelList;
        this.fm=fm;    }

    @Override
    public Fragment getItem(int idx) {
        Log.e("NewsPageFragmentAdapter","getItem"+idx);
        String newsCategoryTitle = channelList[idx];
        if(!Objects.equals(newsCategoryTitle, "关注")) {
            return NewsChannelFragment.newInstance(newsCategoryTitle);
        }

        else return GuanzhuFragment.newInstance("",newsCategoryTitle);
        //传递内导航标题栏，另一个无所谓，因为没有用到
        //else  return NewsChannelFragment.newInstance(newsCategoryTitle);
    }
    @Override
    public int getCount() {        return channelList.length;    }
}

