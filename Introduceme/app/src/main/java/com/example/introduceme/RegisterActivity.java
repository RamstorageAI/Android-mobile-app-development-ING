package com.example.introduceme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameET, pwdET ;
    private Button regBt ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameET = (EditText) findViewById(R.id.reg_name);
        pwdET = (EditText) findViewById(R.id.reg_pwd);

        regBt = (Button) findViewById(R.id.reg_bt);

        regBt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent3 = new Intent(); //该“意图”仅仅只传递数据
//
//                intent3.putExtra("regName", nameET.getText().toString());
//                intent3.putExtra("regPwd", pwdET.getText().toString());
//                setResult(RESULT_OK, intent3); //3. 设置返回结果intent3,并且其中包含传递的参数
//                String msg = "注册成功!! \n 您输入的用户名是:" + nameET.getText().toString() + "\n 密码是:" + pwdET.getText().toString();
//                Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_LONG).show();
//                finish();//关闭当前页面
                Intent intent3 = new Intent(RegisterActivity.this,MainActivity3.class);
                intent3.putExtra("regName",nameET.getText().toString());
                intent3.putExtra("regPwd",pwdET.getText().toString());
                startActivity(intent3);
            }
        });
    }
}