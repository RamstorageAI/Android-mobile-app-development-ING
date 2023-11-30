package com.example.uidemo3;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.example.uidemo3.R;
/**
 * 演示ToggleButton的用法
 *
 */
public class ToggleButtonDemoActivity extends AppCompatActivity {
	//声明xml中定义的组件
	private ToggleButton mToggleButton;
	private TextView tvSound;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.togglebutton_demo);
		initView();
	}
	// 初始化控件方法
	private void initView() {
		mToggleButton = (ToggleButton) findViewById(R.id.tglSound); // 获取到控件
		tvSound = (TextView) findViewById(R.id.tvSound);
		// 注册监听器，添加监听事件
		mToggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
										 boolean isChecked) {
				if (isChecked) {
					tvSound.setText("已开启");
				} else {
					tvSound.setText("已关闭");
				}

			}
		});

	}

}
