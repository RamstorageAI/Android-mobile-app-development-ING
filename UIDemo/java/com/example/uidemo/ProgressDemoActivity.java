package com.example.uidemo;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ProgressDemoActivity extends AppCompatActivity {
	// 进度条对话框
	Button progressBarBtn,seekBarBtn,ratingBarBtn;
	//存储进度条当前值，初始为 0
	int count = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_demo);


		progressBarBtn = (Button) findViewById(R.id.progressBarBtn);
		progressBarBtn.setOnClickListener(onClickListener);

		seekBarBtn = (Button) findViewById(R.id.seekBarBtn);
		seekBarBtn.setOnClickListener(onClickListener);

		ratingBarBtn = (Button) findViewById(R.id.ratingBarBtn);
		ratingBarBtn.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
				case R.id.progressBarBtn: {
					// 进度条
                    Intent intent = new Intent(ProgressDemoActivity.this,NormalProgressBarActivity.class);
                    startActivity(intent);
					break;
				}
				case R.id.seekBarBtn: {
					// 进度条
					Intent intent = new Intent(ProgressDemoActivity.this,SeekBarActivity.class);
					startActivity(intent);
					break;
				}
				case R.id.ratingBarBtn: {
					// 进度条
					Intent intent = new Intent(ProgressDemoActivity.this,RatingBarActivity.class);
					startActivity(intent);
					break;
				}
				default:
					break;
			}

		}
	};

}

