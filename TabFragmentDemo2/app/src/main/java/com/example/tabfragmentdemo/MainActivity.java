package com.example.tabfragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> mFragments;

    private LinearLayout mTabHome;
    private LinearLayout mTabVideo;
    private LinearLayout mTabQuan;
    private LinearLayout mTabMine;
    private LinearLayout mTabZhifou;

    private ImageButton mImgHome;
    private ImageButton mImgVideo;
    private ImageButton mImgQuan;
    private ImageButton mImgMine;
    private ImageButton mImgZhifou;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragments(); //初始化数据
        initViews(); //初始化控件
        initEvents(); //初始化事件
        initFirstRun(0);//第一次运行初始化界面，第一个碎片

    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        //将四个Fragment加入集合中
        mFragments.add(new HomeFragment());
        mFragments.add(new VideoFragment());
        mFragments.add(new QuanFragment());
        mFragments.add(new MineFragment());
    }

    //初始化控件
    private void initViews() {
        mTabHome = (LinearLayout) findViewById(R.id.id_tab_home);
        mTabVideo = (LinearLayout) findViewById(R.id.id_tab_video);
        mTabQuan = (LinearLayout) findViewById(R.id.id_tab_quan);
        mTabMine = (LinearLayout) findViewById(R.id.id_tab_mine);
        mTabZhifou = (LinearLayout) findViewById(R.id.id_tab_zhifou);

        mImgHome = (ImageButton) findViewById(R.id.id_tab_home_img);
        mImgVideo = (ImageButton) findViewById(R.id.id_tab_video_img);
        mImgQuan = (ImageButton) findViewById(R.id.id_tab_quan_img);
        mImgMine = (ImageButton) findViewById(R.id.id_tab_mine_img);
        mImgZhifou = (ImageButton) findViewById(R.id.id_tab_zhifou_img);
    }

    //初始化事件
    private void initEvents() {
        //设置四个Tab的点击事件

        mTabHome.setOnClickListener(onClickListener);
        mTabVideo.setOnClickListener(onClickListener);
        mTabQuan.setOnClickListener(onClickListener);
        mTabMine.setOnClickListener(onClickListener);
        mTabZhifou.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            //先将四个ImageButton置为灰色
            resetImgs();
            //根据点击的Tab切换不同的页面及设置对应的ImageButton为绿色
            switch (v.getId()) {
                case R.id.id_tab_home: selectTab(0); break;
                case R.id.id_tab_video: selectTab(1); break;
                case R.id.id_tab_quan: selectTab(2); break;
                case R.id.id_tab_mine: selectTab(3); break;
                case R.id.id_tab_zhifou: selectTab(4); break;
            }
        }};

    private void resetImgs() {
        mImgHome.setImageResource(R.mipmap.tab_home_normal);
        mImgVideo.setImageResource(R.mipmap.tab_video_normal);
        mImgQuan.setImageResource(R.mipmap.tab_quan_normal);
        mImgMine.setImageResource(R.mipmap. tab_mine_normal);
        mImgMine.setImageResource(R.mipmap. tab_zhifou_normal);
    }


    //实现selectTab():对选中的Tab着重显示，并调用方法设置当前应显示的碎片。
    private void selectTab(int i) {
        //根据点击的Tab设置对应的ImageButton为绿色
        switch (i) {
            case 0: mImgHome.setImageResource(R.mipmap.tab_home_pressed);break;
            case 1: mImgVideo.setImageResource(R.mipmap.tab_video_pressed);break;
            case 2: mImgQuan.setImageResource(R.mipmap.tab_quan_pressed);break;
            case 3:  mImgMine.setImageResource(R.mipmap.tab_mine_pressed);break;
            case 4:  mImgZhifou.setImageResource(R.mipmap.tab_zhifou_pressed);break;
        }
        //设置当前点击的Tab所对应的页面
        setCurrentFragment(i);
    }

    private void setCurrentFragment(int i)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();
        trans.replace(R.id.frag_layout, mFragments.get(i));
        trans.commit();
    }

    private void initFirstRun(int i)
    {
        resetImgs(); //重置所有Tab
        selectTab(i); //显示第i个碎片
    }


}