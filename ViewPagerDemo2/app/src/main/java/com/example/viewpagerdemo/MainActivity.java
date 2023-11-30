package com.example.viewpagerdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    ViewPager vp;
    private PagerTabStrip pts;
    String[] names={"黄旭华","袁隆平","屠呦呦","孙家栋","张富清"};
    int[]  resIds ={ R.drawable.hxh,
                     R.drawable.ylp,
                     R.drawable.tyy,
                     R.drawable.sjd,
                     R.drawable.zfq  };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp = (ViewPager)findViewById(R.id.viewPager);
        PhotoPagerAdaper ppa = new PhotoPagerAdaper(this, resIds, names);
        vp.setAdapter(ppa);
        vp.setCurrentItem(0);
        vp.addOnPageChangeListener(this);
        pts = (PagerTabStrip)findViewById(R.id.tabStrip);
        pts.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        String s = "国家勋章获得者："+names[position];
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}