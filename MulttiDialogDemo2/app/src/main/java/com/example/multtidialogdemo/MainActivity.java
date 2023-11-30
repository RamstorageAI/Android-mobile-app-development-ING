package com.example.multtidialogdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText bookNameET,publisDateET,typeET,subjectET;
    ImageView pdIV,tpIV,sjIV;
    Button button3;
    //定义所属学科点击监听器
    View.OnClickListener subjectClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            createMultiChoiceDialog().show();
        }
    };
    //定义图书所属类别点击监听器
    View.OnClickListener typeClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            createOneChoiceDialog().show();
        }
    };

    //定义出版日期组件点击监听器
    View.OnClickListener publishClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DatePickerDialog dpd = new DatePickerDialog(MainActivity.this,
                    MainActivity.this,  2021, 8,  8);  //实例化日期选择器，参数二为监听器
            dpd.show();
        }
    };
    //注册按钮的点击监听器
    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String bookName = bookNameET.getText().toString();
            String publishDate = publisDateET.getText().toString();
            String subject = subjectET.getText().toString();
            String type =typeET.getText().toString();
            if(bookName.equals("") || publishDate.equals("") || subject.equals("") || type.equals("")){
                createAlertDialog("请填写所有的数据项！").show();
                return;
            }else{
                createProgressDialog().show();
            }
        }
    };

    private void getComponents(){
        bookNameET = (EditText)findViewById(R.id.bookNameET);
        publisDateET = (EditText)findViewById(R.id.publishDateET);
        typeET =(EditText)findViewById(R.id.typeET);
        subjectET = (EditText)findViewById(R.id.subjectET);
        pdIV = (ImageView)findViewById(R.id.pdIV);
        tpIV = (ImageView)findViewById(R.id.tpIV);
        sjIV = (ImageView)findViewById(R.id.sjIV);
        button3 = (Button)findViewById(R.id.button3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getComponents();
        publisDateET.setKeyListener(null);
        typeET.setKeyListener(null);  // android:editable已经过时，通过setKeyListener（）
        subjectET.setKeyListener(null);
        publisDateET.setOnClickListener(publishClickListener);
        pdIV.setOnClickListener(publishClickListener);
        subjectET.setOnClickListener(subjectClickListener);
        sjIV.setOnClickListener(subjectClickListener);
        typeET.setOnClickListener(typeClickListener);
        tpIV.setOnClickListener(typeClickListener);
        button3.setOnClickListener(buttonClickListener);


    }

    /** 生成信息提醒对话框！*/
    private AlertDialog createAlertDialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.info);  //设置标题的图片
        builder.setTitle("温馨提示");   //设置对话框的标题
        builder.setMessage(msg);   //设置对话框的内容
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return builder.create();
    }

    /** 生成单选列表对话框！ */
    private AlertDialog createOneChoiceDialog(){
        final String items[] = {"儿童读物", "专业书","工具书", "剧本","手册"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.xunwen);//设置标题的图片
        builder.setTitle("请选择图书的类别");//设置对话框的标题
        builder.setSingleChoiceItems(items,-1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which>=0)
                    typeET.setText(items[which]);
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return builder.create();
    }

    /** 生成多选列表对话框！ */
    private AlertDialog createMultiChoiceDialog(){
        final String items[] = {"信息科学", "民用化工", "人工智能", "大数据","经济管理","人文社会","家庭伦理"};
        final List<String> chooses = new ArrayList<String>();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.xunwen);//设置标题的图片
        builder.setTitle("请选择所属学科：");//设置对话框的标题
        builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                if(b)
                    chooses.add(items[i]);
                else
                    chooses.remove(items[i]);
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuffer sb = new StringBuffer("");
                for(int i=0;i<chooses.size();i++)
                    sb.append(chooses.get(i)).append("  ");
                subjectET.setText(sb.toString());
                dialog.dismiss();
            }
        });
        return builder.create();
    }

    /** 生成保存进度条对话框！    */
    private ProgressDialog createProgressDialog(){
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("正在保存中...");
        dialog.setMax(100);
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int progress = 0;
            @Override
            public void run() {
                dialog.setProgress(progress += 10);
                if (progress == 100) {
                    timer.cancel();
                    dialog.dismiss();
                }
            }
        }, 0, 500);
        return dialog;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        StringBuffer sb = new StringBuffer("");
        sb.append(year).append("年");
        sb.append(month+1).append("月");
        sb.append(day).append("日");
        publisDateET.setText(sb.toString());
    }
}