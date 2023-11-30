package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.logindemo.R;

public class RegisterActivity extends AppCompatActivity {

    private Button regButton;
    private Spinner spinner;
    private EditText userName,pw_msg,rpw_msg;
    private RadioGroup rg_sex;
    private CheckBox hobby_swim,hobby_music,hobby_book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = (Spinner)findViewById(R.id.academic_msg);
        userName = (EditText)findViewById(R.id.username_msg);
        pw_msg = (EditText)findViewById(R.id.pwd_msg);
        rpw_msg = (EditText)findViewById(R.id.rpwd_msg);
        rg_sex = (RadioGroup)findViewById(R.id.rg_sex);
        hobby_swim = (CheckBox)findViewById(R.id.hobby_swim);
        hobby_music = (CheckBox)findViewById(R.id.hobby_music);
        hobby_book = (CheckBox)findViewById(R.id.hobby_book);
        regButton = (Button)findViewById(R.id.signover_button);

    }

    public void onRegClick(View v){
        String  name_str,pw_str,rpw_str,msg ="";
        name_str = userName.getText().toString();
        pw_str =  pw_msg.getText().toString();
        rpw_str = rpw_msg.getText().toString();
        if(name_str.length() == 0 || pw_str.length() == 0 || (pw_str.compareTo(rpw_str) != 0)) {
            Toast.makeText(this,"您输入的用户名或密码有误",Toast.LENGTH_SHORT).show();
            userName.setText("");
            userName.setFocusable(true);
        }
        else
        {
            msg = "用户名："+ name_str +"\n";
            msg = msg + "密码：" + pw_str + "\n";
        }

        if (rg_sex.getCheckedRadioButtonId() == R.id.sex_male)
            msg += "性别：男\n";
        else
            msg += "性别：女\n";

        msg += "学历："+ spinner.getSelectedItem().toString()+"\n";
        msg += "爱好：";
        if (hobby_swim.isChecked())
            msg +=  hobby_swim.getText().toString() + " ";
        if (hobby_music.isChecked())
            msg += hobby_music.getText().toString() + " ";
        if (hobby_book.isChecked())
            msg += hobby_book.getText().toString() ;
        msg += "\n";

        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        setSignUpMsg(name_str, pw_str);
    }

    // 可带注册信息返回
    private void setSignUpMsg(String name_str,String pw_str)
    {
//        Intent data = new Intent();
//        Bundle bundle = new Bundle();
//        bundle.putString("userName",name_str);
//        bundle.putString("pw_msg",pw_str);
//        data.putExtras(bundle);
//        setResult(RESULT_OK,data);
        //       finish();
    }
}
