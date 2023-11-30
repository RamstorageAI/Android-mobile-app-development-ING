package com.example.myviewpagerapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import java.sql.Array;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home_Fragment extends Fragment  implements ViewPager.OnPageChangeListener {
    private static final String ARG_CHANNEL_LIST = "channel_list";
    //以下声明fragment_home.xml对应的控件实例
    private View view = null;
    private RadioGroup rgChannel = null;
    private ViewPager viewPager;
    private HorizontalScrollView hvChannel = null;
    //private String[] channelList = {"关注", "头条", "视频", "长沙", "要闻", "新时代", "娱乐"};  //栏目列
//
    //用string-array来存放导航栏的信息
    String[] channelList2 ;
// 获取Array数组

    private NewsPageFragmentAdapter adapter;

    int flag=0;

    public Home_Fragment() {
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view==null){
            view=inflater.inflate(R.layout.fragment_home_, container, false);
            viewPager=(ViewPager)view.findViewById(R.id.vpNewsList);
            initViewPager();
            rgChannel=(RadioGroup)view.findViewById(R.id.rgChannel);
            hvChannel=(HorizontalScrollView)view.findViewById(R.id.hvChannel);
            initTab(inflater); //初始化内导航标签
            rgChannel.setOnCheckedChangeListener( //单选按钮的监听事件响应
                    new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group,int checkedId) {
                            viewPager.setCurrentItem(checkedId);

                        }                });
        }

        return view;}
    //初始化内导航标签
    private void initTab(LayoutInflater inflater) {
        channelList2 = getResources().getStringArray(R.array.channelList2);
        for (int i = 0; i < channelList2.length; i++) {//以下添加单选按钮的实例到内导航
            RadioButton rb = (RadioButton) inflater.inflate(R.layout.tab_rb, null);
            rb.setId(i);
            rb.setText(channelList2[i]);
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            rgChannel.addView(rb, params);
        }
        rgChannel.check(0); //第一个选项}

    }

    private  void initViewPager(){
        FragmentManager fragmentManager = super.getActivity().getSupportFragmentManager();
        channelList2=getResources().getStringArray(R.array.channelList2);
        adapter=new NewsPageFragmentAdapter(fragmentManager, channelList2);

            viewPager.setAdapter(adapter);
            viewPager.setOffscreenPageLimit(2);
            viewPager.setCurrentItem(0);//ViewPage的自带的方法，
            viewPager.addOnPageChangeListener(this);

    }

    //设置选定某个碎片时水平滚动视图的显示HorizontalScrollView
    private void setTab(int idx){
        RadioButton rb=(RadioButton)rgChannel.getChildAt(idx);
        rb.setChecked(true);
        int left=rb.getLeft();
        int width=rb.getMeasuredWidth();
        DisplayMetrics metrics=new DisplayMetrics();
        super.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth=metrics.widthPixels;
        int len=left+width/2-screenWidth/2;
        hvChannel.smoothScrollTo(len, 0);}

    @Override
    public void onPageScrolled(int i, float v, int i1) {}
    @Override//某个碎片被选中时的动作，对应的内导航标签将要滑动到屏幕中央，并且有下划线显示
    public void onPageSelected(int i) {
        setTab(i); }//后面再实现，控制导航栏保持一致}
    @Override
    public void onPageScrollStateChanged(int i) {}





}
