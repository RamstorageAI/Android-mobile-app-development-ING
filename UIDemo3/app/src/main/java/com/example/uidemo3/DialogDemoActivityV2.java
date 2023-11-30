package com.example.uidemo3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

import com.example.uidemo3.R;


public class DialogDemoActivityV2 extends AppCompatActivity implements View.OnClickListener{

    private Button btn_dialog_one;
    private Button btn_dialog_two;
    private Button btn_dialog_three;
    private Button btn_dialog_four;

    private Button btn_dialog_5,btn_dialog_6,btn_dialog_7,btn_dialog_8,btn_dialog_9;

    private Context mContext;
    private boolean[] checkItems;

    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;

    int year, month,dayOfMonth,hourOfDay,minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_demo_v2);
        mContext = DialogDemoActivityV2.this;
        bindView();
    }
    private void bindView() {
        btn_dialog_one = (Button) findViewById(R.id.button1);
        btn_dialog_two = (Button) findViewById(R.id.button4);
        btn_dialog_three = (Button) findViewById(R.id.button2);
        btn_dialog_four = (Button) findViewById(R.id.button3);
        btn_dialog_one.setOnClickListener(this);
        btn_dialog_two.setOnClickListener(this);
        btn_dialog_three.setOnClickListener(this);
        btn_dialog_four.setOnClickListener(this);

        btn_dialog_5 = (Button) findViewById(R.id.button5);
        btn_dialog_6 = (Button) findViewById(R.id.button6);
        btn_dialog_7 = (Button) findViewById(R.id.button7);
        btn_dialog_8 = (Button) findViewById(R.id.button8);
        btn_dialog_9 = (Button) findViewById(R.id.button9);
        btn_dialog_5.setOnClickListener(this);
        btn_dialog_6.setOnClickListener(this);
        btn_dialog_7.setOnClickListener(this);
        btn_dialog_8.setOnClickListener(this);
        btn_dialog_9.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //普通对话框
            case R.id.button1:
                alert = null;
                builder = new AlertDialog.Builder(mContext);
                alert = builder.setTitle("系统提示：")
                        .setMessage("这是一个最普通的AlertDialog,\n带有三个按钮，分别是取消，中立和确定")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext, "你点击了取消按钮~",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext, "你点击了确定按钮~",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("中立", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext, "你点击了中立按钮~",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).create();             //创建AlertDialog对象
                alert.show();                    //显示对话框
                break;
            //普通列表对话框
            case R.id.button4:
                final String[] lesson = new String[]{"语文", "数学", "英语", "化学", "生物", "物理", "体育"};
                alert = null;
                builder = new AlertDialog.Builder(mContext);
                alert = builder.setTitle("列表对话框")
                        .setItems(lesson, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "你选择了" + lesson[which],
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                alert.show();
                break;
            //单选列表对话框
            case R.id.button2:
                final String[] fruits = new String[]{"男","女", "其他"};
                alert = null;
                builder = new AlertDialog.Builder(mContext);
                alert = builder.setTitle("单选对话框")
                        .setSingleChoiceItems(fruits, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "你选择了" + fruits[which], Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                alert.show();
                break;
            //多选列表对话框
            case R.id.button3:
                final String[] menu = new String[]{"Java Web", "智能终端应用","数据库","操作系统"};
                //定义一个用来记录个列表项状态的boolean数组
                checkItems = new boolean[]{false, false, false, false};
                alert = null;
                builder = new AlertDialog.Builder(mContext).setTitle("多选列表项对话框");
                alert = builder.setMultiChoiceItems(menu, checkItems,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                checkItems[which] = isChecked;
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String result = "";
                                for (int i = 0; i < checkItems.length; i++) {
                                    if (checkItems[i])
                                        result += menu[i] + " ";
                                }
                                Toast.makeText(getApplicationContext(), "你点了:" + result,
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alert.show();
                break;
            // 自定义视图对话框
            // 视图可自定义，setView()
            case R.id.button5:
                View seekView = getLayoutInflater().inflate(R.layout.seek_dialog, null);
                SeekBar sbar = (SeekBar) seekView.findViewById(R.id.seekBar1);
                sbar.setMax(100);
                final TextView tv_seekbar = (TextView) seekView.findViewById(R.id.tv_seekbar);
                tv_seekbar.setText("当前的进度为：" + sbar.getProgress());
                sbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar){

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar){

                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                        tv_seekbar.setText("当前的进度为：" + seekBar.getProgress());
                    }
                });
                new AlertDialog.Builder(mContext)
                        .setTitle("自定义视图对话框")
                        .setView(seekView)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create()
                        .show();
                break;
            // ProgresssDialog
            //进度条对户框需要模拟进度，因此需要开线程实现
            case R.id.button6:
                final ProgressDialog pDialog = new ProgressDialog(mContext);
                pDialog.setTitle("文件下载中");
                pDialog.setMax(100);
                pDialog.setMessage("文件已下载");
                //设置进度条风格 STYLE_SPINNER圆形 旋转 STYLE_HORIZONTAL长形进度条
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //是否可以按back键取消
                pDialog.setCancelable(true);
                pDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int progress = 0;
                        while (progress < 100) {
                            try {
                                Thread.sleep(100);
                                progress++;
                                pDialog.incrementProgressBy(5);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                break;

            //日期选择对话框
            case R.id.button7:
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                //获取年
                year = calendar.get(Calendar.YEAR);
                //获取月，从0开始
                month = calendar.get(Calendar.MONTH);
                //获取日
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                //监听日期设置
                DatePickerDialog.OnDateSetListener listener1 = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int m_year, int m_month, int m_dayOfMonth) {
                        year = m_year;
                        month = m_month + 1;
                        dayOfMonth = m_dayOfMonth;
                        Toast.makeText(mContext, "你设置的时间是：" + year + "年" + month + "月" + dayOfMonth + "日",
                                Toast.LENGTH_SHORT).show();
                    }
                };

                DatePickerDialog dpDialog = new DatePickerDialog(mContext,
                        listener1, year, month, dayOfMonth);
                dpDialog.setMessage("请选择日期");
                dpDialog.show();
                break;
            //时间选择对话框
            case R.id.button8:
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTimeInMillis(System.currentTimeMillis());
                //监听时间的设置
                TimePickerDialog.OnTimeSetListener listener2 = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int m_hourOfDay, int m_minute) {
                        hourOfDay = m_hourOfDay;
                        minute = m_minute;
                        Toast.makeText(mContext, "你设置的时间是： " + hourOfDay + " : " + minute, Toast.LENGTH_SHORT).show();
                    }
                };

                TimePickerDialog tpDialog = new TimePickerDialog(mContext, listener2,
                        calendar2.get(Calendar.HOUR_OF_DAY), calendar2.get(Calendar.MINUTE), true);
                tpDialog.setMessage("请设置时间");
                tpDialog.show();
                break;
            //自定义对话框
            case R.id.button9:
                //创建自定义的Dialog对象
                final com.example.uidemo3.MyDialog userDialog = new MyDialog(mContext);
                //设置对话框的图标
                userDialog.setIcon(R.mipmap.ic_launcher);
                //设置标题
                userDialog.setTitle("自定义对话框");
                //显示对话框
                userDialog.show();
                //通过MyDialog对象找到相关控件
                Button btn_ok = (Button) userDialog.findViewById(R.id.btn_ok_dialog);
                Button btn_cancel = (Button) userDialog.findViewById(R.id.btn_cancel_dialog);
                final EditText et_userName = (EditText) userDialog.findViewById(R.id.et_username_dialog);
                final EditText et_pwd = (EditText) userDialog.findViewById(R.id.et_pwd_dialog);
                //设置按钮的监听事件
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userName = et_userName.getText().toString();
                        String userPwd = et_pwd.getText().toString();
                        //弹出一个短消息
                        Toast.makeText(mContext, "用户名"+userName+"密码"+userPwd, Toast.LENGTH_SHORT).show();
                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //回收对话框
                        userDialog.dismiss();
                    }
                });
                break;
        }
    }
}
