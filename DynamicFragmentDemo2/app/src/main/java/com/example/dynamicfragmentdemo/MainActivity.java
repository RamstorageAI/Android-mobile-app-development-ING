package com.example.dynamicfragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3, b4, b5;


    private void getComponents(){
        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        b4 = (Button)findViewById(R.id.b4);
        b5 = (Button)findViewById(R.id.b5);

    }

    /**
     * 定义通用的碎片切换方法，将参数碎片对象替换到fContainer容器中。
     * @param fragment
     */
    private void switchFragement(Fragment fragment){
        FragmentManager manager = this.getSupportFragmentManager();
        FragmentTransaction fTransaction = manager.beginTransaction();
        fTransaction.replace(R.id.fContainer,fragment);
        fTransaction.commit();
    }

    /**
     * 定义方法，将所有按钮置位浅紫色
     * @param b
     */
    private void resetBtnColor(Button b){
        b1.setTextColor(Color.WHITE);
        b2.setTextColor(Color.WHITE);
        b3.setTextColor(Color.WHITE);
        b4.setTextColor(Color.WHITE);
        b5.setTextColor(Color.WHITE);
        b.setTextColor(Color.YELLOW);
    }

    private void setListener(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetBtnColor(b1);
                switchFragement(new J1Fragment());
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetBtnColor(b2);
                switchFragement(new J2Fragment());
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetBtnColor(b3);
                switchFragement(new J3Fragment());
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetBtnColor(b4);
                switchFragement(new J4Fragment());
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetBtnColor(b5);
                switchFragement(new J5Fragment());
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getComponents();
        resetBtnColor(b1);   //默认第一个按钮被点击
        switchFragement(new J1Fragment());  //第一个fragment被显示
        setListener(); //为所有按钮注册监听器
    }
}