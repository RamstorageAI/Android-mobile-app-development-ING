package com.example.adapterdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class AutoCompleteTextViewArrayAdapter extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView=null;
    private String []array=new String[]{"湖南大学","湖南师范大学","湖南工商大学","湖南科技大学","湖南工学院"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text_view_array_adapter);

        autoCompleteTextView=findViewById(R.id.autotextview);  //取得组件
        ArrayAdapter<String> adapter=new ArrayAdapter<>(AutoCompleteTextViewArrayAdapter.this,
                android.R.layout.simple_dropdown_item_1line,array); //定义数据集
        autoCompleteTextView.setAdapter(adapter);   //设置数据集
        autoCompleteTextView.setThreshold(1);       //设置至少输入几个字符才会显示提示
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(AutoCompleteTextViewArrayAdapter.this,"你选择的是："+array[i],Toast.LENGTH_SHORT).show();
            }
        });
    }
}