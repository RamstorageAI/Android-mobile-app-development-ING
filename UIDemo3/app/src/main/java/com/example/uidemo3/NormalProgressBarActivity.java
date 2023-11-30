package com.example.uidemo3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.uidemo3.R;

public class NormalProgressBarActivity extends AppCompatActivity {
    private TextView main_tv;
    private ProgressBar pb_horizontal;
    //存储进度条当前值，初始为 0
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_progress_bar);

        main_tv = (TextView) findViewById(R.id.main_tv);

        pb_horizontal = (ProgressBar)findViewById(R.id.pb_horizontal);
        // 设置进度条是否为不明确
        pb_horizontal.setIndeterminate(false);
        // 设置进度条的最大值
        pb_horizontal.setMax(15);
        // 设置当前默认进度为 0
        pb_horizontal.setProgress(0);

        count = 0;
        new Thread() {
            public void run() {
                while (count <= 15) {
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                            pb_horizontal.setProgress(count++);
                            main_tv.setText("进度:"+ String.format("%.2f",count/15.0*100)+"%");
                        }
                    });
                    try {
                        Thread.sleep(500);  //暂停 0.05秒
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {

                    }
                }
            }
        }.start();
    }
}
