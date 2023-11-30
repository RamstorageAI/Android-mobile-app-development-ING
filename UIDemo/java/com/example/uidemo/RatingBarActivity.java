package com.example.uidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

public class RatingBarActivity extends AppCompatActivity {
    private RatingBar ratingBar,ratingBar1;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);

        //初始化控件
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar1 = (RatingBar) findViewById(R.id.ratingBar1);
        textView = (TextView) findViewById(R.id.textView);
        //ratingbar的常用方法
        //设置是否为指示器模式，及不可交互
        ratingBar1.setIsIndicator(false);
        //设置评分条的最大范围
        ratingBar1.setMax(20);
        //设置星星数量
        ratingBar1.setNumStars(10);
        //设置当前等级
        ratingBar1.setRating(1);
        //设置步长
        ratingBar1.setStepSize(1);
        //设置监听器
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //获取星星数量
                int num = ratingBar.getNumStars();
                //获取步长
                float step = ratingBar.getStepSize();
                //获取当前评分,与参数rating一致
                // float currentRating = ratingBar.getRating();
                Log.d("debug","num="+num+",step="+step+",rating="+rating);
                textView.setText(""+rating);
            }
        });
    }
}
