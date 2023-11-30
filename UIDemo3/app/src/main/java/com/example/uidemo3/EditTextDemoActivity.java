package com.example.uidemo3;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.uidemo3.R;

import androidx.appcompat.app.AppCompatActivity;

public class EditTextDemoActivity extends AppCompatActivity {

	private static String[] strArr = new String[]{"中国","中国香港","中国台湾","湖南",
					"湖南长沙","湖南师范大学","湖南大学","湖南工业大学"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edittext_demo);

		AutoCompleteTextView textView =(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView) ;
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,strArr);
		textView.setAdapter(adapter);
	}
	
}
