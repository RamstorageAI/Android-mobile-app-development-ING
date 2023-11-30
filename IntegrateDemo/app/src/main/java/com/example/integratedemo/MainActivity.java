package com.example.integratedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_InfoManage;
    private Button btn_ImageBrowser;
    private Button btn_RelativeLayout;
    private Button btn_MultiLayout;
    private Button btn_ConstraintLayout;
    private Button btn_ImageSwitcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_InfoManage = (Button) findViewById(R.id.btn_InfoManage);
        btn_ImageBrowser = (Button) findViewById(R.id.btn_ImageBrowser);
        btn_RelativeLayout = (Button) findViewById(R.id.btn_RelativeLayout);
        btn_MultiLayout = (Button) findViewById(R.id.btn_MultiLayout);
        btn_ConstraintLayout = (Button) findViewById(R.id.btn_ConstraintLayout);
        btn_ImageSwitcher = (Button) findViewById(R.id.btn_ImageSwitcher);

        btn_InfoManage.setOnClickListener(this);
        btn_ImageBrowser.setOnClickListener(this);
        btn_RelativeLayout.setOnClickListener(this);
        btn_MultiLayout.setOnClickListener(this);
        btn_ConstraintLayout.setOnClickListener(this);
        btn_ImageSwitcher.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_InfoManage:
                intent = new Intent(MainActivity.this, InfoManageActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ImageBrowser:
                intent = new Intent(MainActivity.this, ImageBrowserActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_RelativeLayout:
                intent = new Intent(MainActivity.this, RelativeLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_MultiLayout:
                intent = new Intent(MainActivity.this, MultiLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ConstraintLayout:
                intent = new Intent(MainActivity.this, ConstraintLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ImageSwitcher:
                intent = new Intent(MainActivity.this, ImageSwitcherActivity.class);
                startActivity(intent);
                break;
        }
    }
}