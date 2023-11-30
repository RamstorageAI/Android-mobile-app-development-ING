package com.example.infomanagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userNameET, pwdET;
    CheckBox checkBox1,checkBox2,checkBox3,checkBox4;
    RadioGroup radioGroup1;
    EditText memoET;
    Button saveBtn, resetBtn;
    String hobby = "";// 存放选取的兴趣爱好！

    //定义复选框，选择变化监听器
    OnCheckedChangeListener ocListener=new OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton cb, boolean isChecked){
            //获取被单击的复选框的值
            String s = cb.getText().toString();
            if(isChecked)
                hobby+=s+"  "; //如果被选中，则hobby加上该选项
            else{   //如果点击取消，则从hobby中删除这一项
                String temp=s+"  ";
                hobby=hobby.replaceAll(temp, "");
            }
            Toast.makeText(MainActivity.this, hobby,
                    Toast.LENGTH_LONG).show();
        }
    };

    //定义重置按钮点击监听器，
    private View.OnClickListener resetListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            userNameET.setText("");
            pwdET.setText("");
            checkBox1.setChecked(false);
            checkBox2.setChecked(false);
            checkBox3.setChecked(false);
            checkBox4.setChecked(false);
            radioGroup1.check(R.id.radio1); //设置默认选第一个；
            memoET.setText("");
        }
    };

    //定义点击保存按钮的监听器
    View.OnClickListener saveListener = new View.OnClickListener(){
        @Override
        public void onClick(View arg0) {
            String userName= userNameET.getText().toString();
            String pwd=pwdET.getText().toString();
            int gid =radioGroup1.getCheckedRadioButtonId();
            RadioButton rb = (RadioButton)findViewById(gid);
            String edu= rb.getText().toString();
            String memo = memoET.getText().toString();
            if(userName.equals("")){
                Toast.makeText(MainActivity.this,"用户名不能空",Toast.LENGTH_SHORT).show();
                return ;
            }
            if(pwd.equals("")){
                Toast.makeText(MainActivity.this,"密码不能空",Toast.LENGTH_SHORT).show();
                return ;
            }
            StringBuffer sb = new StringBuffer();
            sb.append("您的个人信息如下：\n");
            sb.append("  用户名：").append(userName).append("\n");
            sb.append("  密码：").append(pwd).append("\n");
            sb.append("  学历：").append(edu).append("\n");
            sb.append("  兴趣爱好：").append(hobby).append("\n");
            sb.append("  个人简介：").append(memo);
            Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
        }
    };

    /**
     * 在onCreate回调方法中，获取页面组件，为相关组件注册监听器
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getComponents();
        checkBox1.setOnCheckedChangeListener(ocListener);
        checkBox2.setOnCheckedChangeListener(ocListener);
        checkBox3.setOnCheckedChangeListener(ocListener);
        checkBox4.setOnCheckedChangeListener(ocListener);
        saveBtn.setOnClickListener(saveListener);
        resetBtn.setOnClickListener(resetListener);
    }

    //定义单独方法，获取页面上所有的组件
    private void getComponents(){
        userNameET = (EditText)findViewById(R.id.userNameET);
        pwdET = (EditText)findViewById(R.id.pwdET);
        checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox)findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox)findViewById(R.id.checkBox4);
        radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
        memoET = (EditText)findViewById(R.id.memoET);
        saveBtn = (Button)findViewById(R.id.saveBtn);
        resetBtn = (Button)findViewById(R.id.resetBtn);
    }
}