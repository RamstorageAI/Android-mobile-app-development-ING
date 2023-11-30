package com.example.projectnavigationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailAcitivity extends AppCompatActivity {

    private static TextView detail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acitivity);

        detail=(TextView) findViewById(R.id.DetailText);
        //首先获取传递过来详情信息
        String newsTitle = getIntent().getStringExtra("detail");
        detail.setText(newsTitle);//将传递过来的信息设置为内容，
    }
    public static void actionStart(Context context, String newsTitle) {
        //另一种跳转新活动的方法，在要跳转的活动的java文件中创建方法，该方法是启动该活动的入口
        //只要在别的活动、碎片中调用该方法就可以启动新的活动
        Intent intent = new Intent(context, DetailAcitivity.class);
        intent.putExtra("detail", newsTitle); //
        context.startActivity(intent);//点击按钮跳转到该活动

    }
}