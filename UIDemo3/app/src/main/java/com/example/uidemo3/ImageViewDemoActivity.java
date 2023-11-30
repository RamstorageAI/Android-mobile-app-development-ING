package com.example.uidemo3;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uidemo3.R;

public class ImageViewDemoActivity extends AppCompatActivity {
	//定义变量
	//国旗对应的ImageView
	ImageView flagImageView;
	TextView flagTxt;
	//上一页
	ImageButton backImageBtn;
	//下一页
	ImageButton forwardImageBtn;
	//国旗数组 中国 德国 英国
	int[] flag = {R.drawable.china, R.drawable.germany, R.drawable.britain};
	String[] flagNames = {"中国","德国","英国"};
	//当前页 默认第一页
	int currentPage = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageview_demo);
		//初始化组件
		flagImageView = (ImageView) findViewById(R.id.flagImageView);
		//国旗名称
		flagTxt = (TextView)findViewById(R.id.flagTxt);
		//上一页、下一页
		backImageBtn = (ImageButton)findViewById(R.id.backImageBtn);
		forwardImageBtn = (ImageButton)findViewById(R.id.forwardImageBtn);
		//注册监听器
		backImageBtn.setOnClickListener(onClickListener);
		forwardImageBtn.setOnClickListener(onClickListener);
	}
	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.backImageBtn:
					if(currentPage == 0){
						Toast.makeText(ImageViewDemoActivity.this, "第一页，前面没有了", Toast.LENGTH_SHORT).show();
						return;
					}
					//上翻一页
					currentPage-- ;
					//设置国旗图片
					flagImageView.setImageResource(flag[currentPage]);
					//设置国旗名字
					flagTxt.setText(flagNames[currentPage]);
					break;
				case R.id.forwardImageBtn:
					if(currentPage == (flag.length-1)){
						Toast.makeText(ImageViewDemoActivity.this, "最后一页，后面没有了", Toast.LENGTH_SHORT).show();
						return;
					}
					//下翻一页
					currentPage++;
					//设置国旗图片
					flagImageView.setImageResource(flag[currentPage]);
					//设置国旗名字
					flagTxt.setText(flagNames[currentPage]);
					break;
				default:
					break;
			}

		}
	};
}
