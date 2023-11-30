package com.example.imagesproviderdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button btnSearch;
    private ListView imgList;
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> descs = new ArrayList<String>();
    ArrayList<String> fileNames = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSearch =(Button)findViewById(R.id.btnSearch);
        imgList = (ListView)findViewById(R.id.imgList);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names.clear();
                descs.clear();
                fileNames.clear();

                Log.e("MainActivity", MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
                Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
                String[] columns = cursor.getColumnNames();
                for (int i=0;i<columns.length;i++)
                    Log.e("MainActivity",columns[i]);

                while(cursor.moveToNext())
                {
                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                    String description = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DESCRIPTION));
                    //byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    String data = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    names.add(name);
                    descs.add(description);
                    fileNames.add(data);
                    Log.e("MainActivity",data);
                }
                List<Map<String,Object>> listItems = new ArrayList<>();
                for(int i=0;i<names.size();i++)
                {
                    HashMap<String,Object> item = new HashMap<String,Object>();
                    item.put("name",names.get(i));
                    item.put("desc",descs.get(i));
                    listItems.add(item);
                }
                SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,
                        listItems,R.layout.items,new String[]{"name","desc"},new int[]{R.id.txt1,R.id.txt2});
                imgList.setAdapter(adapter);
            }
        });
        imgList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View dialog_view = getLayoutInflater().inflate(R.layout.dialog_view,null);
                ImageView image1 = (ImageView)dialog_view.findViewById(R.id.image1);
                image1.setImageBitmap(BitmapFactory.decodeFile(fileNames.get(position)));
                new AlertDialog.Builder(MainActivity.this).setView(dialog_view).setPositiveButton("确定",null).show();
            }
        });
    }
}
