package com.example.introduceme;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    private String TAG = "MainActivity";

    private EditText username,password;
    private Button login_bt,register_bt;
    private String input_username,input_pwd;
    private String userName = "Admin";
    private String pw_msg = "Hello Android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login_bt = (Button) findViewById(R.id.login_bt);
        register_bt = (Button) findViewById(R.id.register_bt);

        Log.d(TAG,"onCreate");

        login_bt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_username = username.getText().toString();
                input_pwd = password.getText().toString();

                if (input_username.length() == 0 || input_pwd.length() == 0 ||
                        !input_username.equals(userName) || !input_pwd.equals(pw_msg)) {
                    new AlertDialog.Builder(MainActivity3.this).setTitle("登录信息有误")
                            .setMessage("请输入正确的用户名和密码")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //finish();
                                }
                            }).show();
                    username.setText("");
                    password.setText("");
                    username.requestFocus();//获取焦点光标出现
                } else {
                    String msg = "欢迎进入DIY!! \n 您输入的用户名是:" + input_username + "\n 密码是:" + input_pwd;
                    Toast.makeText(MainActivity3.this, msg, Toast.LENGTH_LONG).show();
                }
            }
        });//整个这是是一条代码，监听器代码

        register_bt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        }
        @Override
        protected void onActivityResult (int requestCode, int resultCode,Intent data) {//从Intent中取出用户回传的用户名和密码
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 1) {
                ///在一个活动中有可能调用startActivityForResult()方法去启动很多不同的活动
                if (resultCode == RESULT_OK) {
                    userName = data.getStringExtra(" regName");
                    pw_msg = data.getStringExtra("regPwd");//reg匹配取值
                }
            }
        }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG,"onRestart");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

}






