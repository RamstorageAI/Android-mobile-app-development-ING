package com.example.projectnavigationdemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener{

    private static final String ARG_CHANNEL_LIST = "channel_list";
    //以下声明 fragment_home.xml 对应的控件实例
    private View view = null;
    private RadioGroup rgChannel = null;
    private ViewPager viewPager;
    private HorizontalScrollView hvChannel = null;
    private String[] channelList = {"关注", "头条", "视频", "长沙", "要闻", "新时代", "娱乐"};
    //栏目列表
    private NewsPageFragmentAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
            viewPager = (ViewPager) view.findViewById(R.id.vpNewsList);
            initViewPager();

            rgChannel = (RadioGroup) view.findViewById(R.id.rgChannel);
            hvChannel = (HorizontalScrollView) view.findViewById(R.id.hvChannel);
            initTab(inflater); //初始化内导航标签
            rgChannel.setOnCheckedChangeListener( //单选按钮的监听事件响应
                    new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            viewPager.setCurrentItem(checkedId);
                        }
                    });

        }
        return view;
    }

    //初始化内导航标签
    private void initTab(LayoutInflater inflater){
        for(int i=0;i<channelList.length;i++){//以下添加单选按钮的实例到内导航
            RadioButton rb=(RadioButton)inflater.inflate(R.layout.tab_rb, null);
            rb.setId(i);
            rb.setText(channelList[i]);
            RadioGroup.LayoutParams params=new
                    RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT);
            rgChannel.addView(rb,params);
        }
        rgChannel.check(0); //第一个选项
    }


    private void initViewPager(){
        FragmentManager fragmentManager = super.getActivity().getSupportFragmentManager();
        adapter=new NewsPageFragmentAdapter(fragmentManager, channelList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        //viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener((ViewPager.OnPageChangeListener) this);
    }

    //设置选定某个碎片时水平滚动视图的显示 HorizontalScrollView
    private void setTab(int idx) {
        RadioButton rb = (RadioButton) rgChannel.getChildAt(idx);
        rb.setChecked(true);
        int left = rb.getLeft();
        int width = rb.getMeasuredWidth();
        DisplayMetrics metrics = new DisplayMetrics();
        super.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int len = left + width / 2 - screenWidth / 2;
        hvChannel.smoothScrollTo(len, 0);
    }

    }

