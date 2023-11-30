package com.example.uidemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Switch;

public class SwitchDemoActivity extends AppCompatActivity {
	//定义变量
	Switch switcher;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.switch_demo);
		//初始化组件
		switcher = (Switch) findViewById(R.id.switcher);
		final LinearLayout test = (LinearLayout) findViewById(R.id.test);
		//为switch组件添加事件监听器
		OnCheckedChangeListener listener = new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton button,
										 boolean isChecked) {
				if (isChecked) {
					// 设置LinearLayout垂直布局
					test.setOrientation(LinearLayout.VERTICAL);
					switcher.setChecked(true);
				} else {
					// 设置LinearLayout水平布局
					test.setOrientation(LinearLayout.HORIZONTAL);
					switcher.setChecked(false);
				}
			}
		};
		switcher.setOnCheckedChangeListener(listener);
	}
}
