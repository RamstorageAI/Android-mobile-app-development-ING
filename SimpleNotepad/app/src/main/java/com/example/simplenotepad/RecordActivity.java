package com.example.simplenotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecordActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView note_back; //返回首页
    TextView noteName; //根据情况显示“添加便签”or“修改便签”
    TextView note_time; //便签修改时间
    EditText content; //便签正文
    ImageView delete; //清除
    ImageView note_save; // 保存
    SQLiteHelper mSQLiteHelper;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        setContentView(R.layout.activity_record);

        note_back = (ImageView) findViewById(R.id.note_back);
        note_time = (TextView)findViewById(R.id.tv_time);
        content = (EditText) findViewById(R.id.note_content);
        delete = (ImageView) findViewById(R.id.delete);
        note_save = (ImageView) findViewById(R.id.note_save);
        noteName = (TextView) findViewById(R.id.note_name);
        initData();//根据来源决定顶部显示"添加便签"还是"修改便签"

        note_back.setOnClickListener(this); //返回首页
        delete.setOnClickListener(this); //清除便签
        note_save.setOnClickListener(this); //保存
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.note_back: //返回键功能，关闭当前页面
                finish();
                break;
            case R.id.delete: //“清除按钮”功能：清除编辑框显示
                content.setText("");
                break;
            case R.id.note_save:
                String noteContent=content.getText().toString().trim();
                if (id != null){//修改操作
                    if (noteContent.length()>0){
                        NotepadDAO notepadDAO = new NotepadDAO(this);
                        if (notepadDAO.updateData(id, noteContent, notepadDAO.getTime())){
                            showToast("修改成功");
                            setResult(1);
                            finish();
                        }else {
                            showToast("修改失败");
                        }
                    }else {
                        showToast("修改内容不能为空!");
                    }
                }else {
                    //向数据库中添加数据
                    if (noteContent.length()>0){
                        NotepadDAO notepadDAO = new NotepadDAO(this);
                        if (notepadDAO.insertData(noteContent, notepadDAO.getTime())){
                            showToast("保存成功");
                            setResult(1);
                            finish();
                        }else {
                            showToast("保存失败");
                        }
                    }else {
                        showToast("内容不能为空!");
                    }
                }
                break;
        }
    }
    public void showToast(String message){
        Toast.makeText(RecordActivity.this,message,Toast.LENGTH_SHORT).show();
    }


    //根据来源决定顶部显示"添加便签"还是"修改便签"
    //如果是长按打开显示"修改便签"，否则显示"添加便签"
    protected void initData() {
        mSQLiteHelper = new SQLiteHelper(this); //这一句很关键
        noteName.setText("添加便签");
        Intent intent = getIntent();
        if(intent!= null){
            id = intent.getStringExtra("id");
            if (id != null){ //"修改便签",显示原便签内容
                noteName.setText("修改便签");
                content.setText(intent.getStringExtra("content"));
                note_time.setText(intent.getStringExtra("time"));
                note_time.setVisibility(View.VISIBLE);
            }
        }
    }

}