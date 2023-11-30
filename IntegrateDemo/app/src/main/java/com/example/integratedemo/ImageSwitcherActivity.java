package com.example.integratedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ImageSwitcherActivity extends AppCompatActivity {

    ImageView imageBrowser;
    LinearLayout container;
    int[] resId={
            R.drawable.ag600,R.drawable.c919,R.drawable.jl,
            R.drawable.kjz, R.drawable.ty, R.drawable.zrh
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_switcher);

        imageBrowser = (ImageView)findViewById(R.id.imageBrowser);
        container = (LinearLayout)findViewById(R.id.imageList);

        for(int i=0;i<resId.length;i++){
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(new ViewGroup.LayoutParams(300, ViewGroup.LayoutParams.MATCH_PARENT));
            iv.setImageResource(resId[i]);
            int temp = i;
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageBrowser.setImageResource(resId[temp]);
                }
            });
            container.addView(iv);
        }
    }
}