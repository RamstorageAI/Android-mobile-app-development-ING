package com.example.projectnavigationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //装载 Fragment 的集合
    private List<Fragment> mFragments;
    //四个 Tab 对应的布局
    private LinearLayout mTabHome;
    private LinearLayout mTabVideo;
    private LinearLayout mTabQuan;
    private LinearLayout mTabMine;
    //四个 Tab 对应的 ImageButton
    private ImageButton mImgHome;
    private ImageButton mImgVideo;
    private ImageButton mImgQuan;
    private ImageButton mImgMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragments(); //初始化数据
        initViews(); //初始化控件
        initEvents(); //初始化事件
        //第一次运行初始化界面，显示第一个碎片
        initFirstRun(0);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        //将四个 Fragment 加入集合中
        mFragments.add(new HomeFragment());
        mFragments.add(new VideoFragment());
        mFragments.add(new QuanFragment());
        mFragments.add(new MineFragment());
    }

    private void initViews() {
        FrameLayout frag_layout = (FrameLayout) findViewById(R.id.frag_layout);
        mTabHome = (LinearLayout) findViewById(R.id.id_tab_home);
        mTabVideo = (LinearLayout) findViewById(R.id.id_tab_video);
        mTabQuan = (LinearLayout) findViewById(R.id.id_tab_quan);
        mTabMine = (LinearLayout) findViewById(R.id.id_tab_mine);
        mImgHome = (ImageButton) findViewById(R.id.id_tab_home_img);
        mImgVideo = (ImageButton) findViewById(R.id.id_tab_video_img);
        mImgQuan = (ImageButton) findViewById(R.id.id_tab_quan_img);
        mImgMine = (ImageButton) findViewById(R.id.id_tab_mine_img);
    }

    /*设置四个底部导航键的的点击事件监听器，实现导航显示以及碎片切换*/
    private void initEvents() {
        mTabHome.setOnClickListener(onClickListener);
        mTabVideo.setOnClickListener(onClickListener);
        mTabQuan.setOnClickListener(onClickListener);
        mTabMine.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //先将四个 ImageButton 置为灰色
            resetImgs();
            //然后根据点击的 Tab 切换不同的碎片，并设置对应的 ImageButton 为 pressed 时的图片
            switch (v.getId()) {
                case R.id.id_tab_home:
                    selectTab(0);
                    break;
                case R.id.id_tab_video:
                    selectTab(1);
                    break;
                case R.id.id_tab_quan:
                    selectTab(2);
                    break;
                case R.id.id_tab_mine:
                    selectTab(3);
                    break;
            }
        }
    };

    /*将四个 ImageButton 设置为灰色*/
    private void resetImgs() {
        mImgHome.setImageResource(R.mipmap.tab_home_normal);
        mImgVideo.setImageResource(R.mipmap.tab_video_normal);
        mImgQuan.setImageResource(R.mipmap.tab_quan_normal);
        mImgMine.setImageResource(R.mipmap.tab_mine_normal);
    }

    /*选中第 i 个碎片*/
    private void selectTab(int i) {
        //设置当前点击的 Tab 所对应的碎片
        setCurrentFragment(i);
        //根据点击的 Tab 设置对应的 ImageButton 为 pressed 时的图片
        switch (i) {
            case 0:
                mImgHome.setImageResource(R.mipmap.tab_home_pressed);
                break;
            case 1:
                mImgVideo.setImageResource(R.mipmap.tab_video_pressed);
                break;
            case 2:
                mImgQuan.setImageResource(R.mipmap.tab_quan_pressed);
                break;
            case 3:
                mImgMine.setImageResource(R.mipmap.tab_mine_pressed);
                break;
        }
    }

    /*显示当前点击的 Tab 所对应的碎片*/
    private void setCurrentFragment(int i)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();
        trans.replace(R.id.frag_layout,mFragments.get(i));
        trans.commit();
    }

    /* 在 FrameLayout 中显示第 i 个碎片*/
    private void initFirstRun(int i)
    {
        resetImgs();
        selectTab(i);
    }







}