package com.example.uidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_TextView;
    private Button btn_EditText;
    private Button btn_Button;
    private Button btn_RadioButton;
    private Button btn_CheckBox;
    private Button btn_ToggleButton;
    private Button btn_ImageView;
    private Button btn_ProgressBar;
    private Button btn_Dialog;
    private Button btn_Spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_TextView = (Button)findViewById(R.id.btn_TextView) ;
        btn_EditText = (Button)findViewById(R.id.btn_EditText) ;
        btn_Button = (Button)findViewById(R.id.btn_Button) ;
        btn_RadioButton = (Button)findViewById(R.id.btn_RadioButton) ;
        btn_CheckBox = (Button)findViewById(R.id.btn_CheckBox) ;
        btn_ToggleButton = (Button)findViewById(R.id.btn_ToggleButton) ;
        btn_ImageView = (Button)findViewById(R.id.btn_ImageView) ;
        btn_ProgressBar = (Button)findViewById(R.id.btn_ProgressBar) ;
        btn_Dialog = (Button)findViewById(R.id.btn_Dialog) ;
        btn_Spinner = (Button)findViewById(R.id.btn_Spinner) ;

        btn_TextView.setOnClickListener(this);
        btn_EditText.setOnClickListener(this);
        btn_Button.setOnClickListener(this);
        btn_RadioButton.setOnClickListener(this);
        btn_CheckBox.setOnClickListener(this);
        btn_ToggleButton.setOnClickListener(this);
        btn_ImageView.setOnClickListener(this);
        btn_ProgressBar.setOnClickListener(this);
        btn_Dialog.setOnClickListener(this);
        btn_Spinner.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.btn_TextView:
                intent = new Intent(MainActivity.this,TextViewDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_EditText:
                intent = new Intent(MainActivity.this,EditTextDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_Button:
                intent = new Intent(MainActivity.this,ButtonDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_RadioButton:
                intent = new Intent(MainActivity.this,RadioButtonDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_CheckBox:
                intent = new Intent(MainActivity.this,CheckBoxDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ToggleButton:
                intent = new Intent(MainActivity.this,ToggleButtonDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ImageView:
                intent = new Intent(MainActivity.this,ImageViewDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ProgressBar:
                intent = new Intent(MainActivity.this,ProgressDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_Dialog:
                intent = new Intent(MainActivity.this,DialogDemoActivityV2.class);
                startActivity(intent);
                break;
            case R.id.btn_Spinner:
                intent = new Intent(MainActivity.this,SpinnerDemoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
