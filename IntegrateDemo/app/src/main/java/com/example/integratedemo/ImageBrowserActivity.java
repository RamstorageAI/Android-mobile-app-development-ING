package com.example.integratedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageBrowserActivity extends AppCompatActivity {

    ImageView imageView1, imageView2, imageView3, imageView4, imageView5,
            imageView6, imageView7, imageView8, imageView9;
    //获取所有的imageView组件，并将图片源存入View的tag属性中
    private void getComponents(){
        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView1.setTag(R.drawable.beijing);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView2.setTag(R.drawable.shanghai);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        imageView3.setTag(R.drawable.guangzhou);
        imageView4 = (ImageView)findViewById(R.id.imageView4);
        imageView4.setTag(R.drawable.tianjin);
        imageView5 = (ImageView)findViewById(R.id.imageView5);
        imageView5.setTag(R.drawable.chongqing);
        imageView6 = (ImageView)findViewById(R.id.imageView6);
        imageView6.setTag(R.drawable.wuhan);
        imageView7 = (ImageView)findViewById(R.id.imageView7);
        imageView7.setTag(R.drawable.guiyang);
        imageView8 = (ImageView)findViewById(R.id.imageView8);
        imageView8.setTag(R.drawable.nanjing);
        imageView9 = (ImageView)findViewById(R.id.imageView9);
        imageView9.setTag(R.drawable.xian);
    }

    //定义通用的ImageView点击监听器
    View.OnClickListener imageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ImageView iView = (ImageView)view;
            int resId = ((Integer)iView.getTag()).intValue();
            Intent intent= new Intent(ImageBrowserActivity.this, BigImageActivity.class);
            intent.putExtra("resId",resId);
            startActivity(intent);
        }
    };

    /**
     * 为所有的imageView注册通用的监听器
     */
    private void setListener(){
        imageView1.setOnClickListener(imageClickListener);
        imageView2.setOnClickListener(imageClickListener);
        imageView3.setOnClickListener(imageClickListener);
        imageView4.setOnClickListener(imageClickListener);
        imageView5.setOnClickListener(imageClickListener);
        imageView6.setOnClickListener(imageClickListener);
        imageView7.setOnClickListener(imageClickListener);
        imageView8.setOnClickListener(imageClickListener);
        imageView9.setOnClickListener(imageClickListener);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_browser);
        this.getComponents();
        this.setListener();
    }
}