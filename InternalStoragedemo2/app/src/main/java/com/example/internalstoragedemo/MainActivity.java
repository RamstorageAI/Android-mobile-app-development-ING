package com.example.internalstoragedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    EditText title, content;
    Button button1,button2,button3;
    final String fileName = "data.bat";

    private void initComponents(){
        title = (EditText) findViewById(R.id.title);
        content = (EditText)findViewById(R.id.content);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
    }

    View.OnClickListener resetListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            title.setText("");
            content.setText("");
        }
    };

    View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String s = title.getText().toString();
            String contents = content.getText().toString();
            if(s.equals("")||contents.equals("")){
                Toast.makeText(MainActivity.this,"请填写完整数据",Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                OutputStream os = MainActivity.this.openFileOutput(fileName, Context.MODE_PRIVATE);
                os.write(s.getBytes());  //写入标题行，
                os.write("\n".getBytes());  //写入换行
                os.write(contents.getBytes()); //写入浏览内容
                os.close();
                Toast.makeText(MainActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Toast.makeText(MainActivity.this,"数据写入异常",Toast.LENGTH_SHORT).show();
            }
        }
    };

    View.OnClickListener readListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                InputStream is = openFileInput(fileName);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String firstLine = br.readLine();
                String secondLine = br.readLine();
                title.setText(firstLine);
                content.setText(secondLine);
                br.close();
                Toast.makeText(MainActivity.this,"加载完成",Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Toast.makeText(MainActivity.this,"数据读取异常",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        button1.setOnClickListener(saveListener);
        button2.setOnClickListener(resetListener);
        button3.setOnClickListener(readListener);
    }
}