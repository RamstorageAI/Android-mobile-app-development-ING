package com.example.projectnavigationdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager mViewPager;//
    private List<Fragment> mFragments;//放底部控件对应碎片的集合



    private LinearLayout mTabHome;//底部控件
    private LinearLayout mTabVideo;
    private LinearLayout mTabQuan;
    private LinearLayout mTabMine;


    private ImageButton mImgHome;//底部控件图片
    private ImageButton mImgVideo;
    private ImageButton mImgQuan;
    private ImageButton mImgMine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //先实现点击底部控件改变碎片

        //为 ViewPager 准备碎片
        initFragments();


        //获取底部导航组件及控件的引用
        initViews();
        //设置底部导航组件的点击事件监听器
        initEvents();
        //第一次运行初始化界面，显示第一个碎片
        initFirstRun(0);
    }


    /* 初始化碎片集，每个底部导航按钮对应一个碎片*///相对于准备数据
    private void initFragments() {
        mFragments = new ArrayList<>();
        //将四个Fragment加入集合中
        mFragments.add(new Home_Fragment());
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


    private void resetImgs() {//灰色
        mImgHome.setImageResource(R.drawable.tab_home_normal);
        mImgVideo.setImageResource(R.drawable.tab_video_normal);
        mImgQuan.setImageResource(R.drawable.tab_quan_normal);
        mImgMine.setImageResource(R.drawable. tab_mine_normal);
    }



    private void initEvents() {
        //设置四个Tab的点击事件
        mTabHome.setOnClickListener(onClickListener);
        mTabVideo.setOnClickListener(onClickListener);
        mTabQuan.setOnClickListener(onClickListener);
        mTabMine.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            //先将四个ImageButton置为灰色
            resetImgs();
            //根据点击的Tab切换不同的页面及设置对应的ImageButton为绿色
            if(v.getId()==R.id.id_tab_home)
                selectTab(0);
            if(v.getId()==R.id.id_tab_video)
                selectTab(1);
            if(v.getId()==R.id.id_tab_quan)
                selectTab(2);
            if(v.getId()== R.id.id_tab_mine)
                selectTab(3);

        }    };


    private void initFirstRun(int i)    {
        resetImgs(); //重置所有Tab
        selectTab(i); //显示第i个碎片             }
    }


    //实现selectTab():对选中的Tab着重显示，并调用方法设置当前应显示的碎片。
    /*显示第i个碎片*/
    private void selectTab(int i) {
        //设置ViewPager显示当前点击的导航组件所对应的碎片
        //设置当前点击的Tab所对应的碎片
        setCurrentFragment(i);
        //根据点击的Tab设置对应的ImageButton为pressed时的图片

        switch (i) {
            case 0://i是之前add的那个顺序
                mImgHome.setImageResource(R.drawable.tab_home_pressed);
                break;
            case 1:
                mImgVideo.setImageResource(R.drawable.tab_video_pressed);
                break;
            case 2:
                mImgQuan.setImageResource(R.drawable.tab_quan_pressed);
                break;
            case 3:
                mImgMine.setImageResource(R.drawable.tab_mine_pressed);
                break;    }
    }
    /*显示当前点击的Tab所对应的碎片*/
    private void setCurrentFragment(int i){//动态碎片
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();
        trans.replace(R.id.frag_layout,mFragments.get(i));
        trans.commit();
    }

}