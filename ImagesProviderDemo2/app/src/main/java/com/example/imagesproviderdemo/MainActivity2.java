package com.example.imagesproviderdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity2  extends AppCompatActivity {

    private Button btnSearch;
    private GridView imgList;
    private List<Map<String,Object>> listItems = new ArrayList<>();
    private MyAdapter myAdapter;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSearch =(Button)findViewById(R.id.btnSearch);
        imgList = (GridView)findViewById(R.id.imgList);
        myAdapter = new MyAdapter();
        imgList.setAdapter(myAdapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askExtStoragelPermission();
            }
        });

        imgList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View dialog_view = getLayoutInflater().inflate(R.layout.dialog_view,null);
                ImageView image1 = (ImageView)dialog_view.findViewById(R.id.image1);
                HashMap<String,Object> item = (HashMap<String, Object>) listItems.get(position);
                image1.setImageBitmap(BitmapFactory.decodeFile((String)item.get("fileName")));
                new AlertDialog.Builder(MainActivity2.this).setView(dialog_view).setPositiveButton("确定",null).show();
            }
        });
    }

    //动态权限申请方法
    private void askExtStoragelPermission(){
        //   动态申请权限
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE }, 1);
        }
        else{
            readImages();
        }
    }

    private void readImages() {
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,null,null,null);
        String[] columns = cursor.getColumnNames();
        for (int i=0;i<columns.length;i++)
            Log.e("MainActivity2",columns[i]);

        listItems.clear();
        while(cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
            int date = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.DATE_MODIFIED));
            //byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            String data = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            HashMap<String,Object> item = new HashMap<String,Object>();
            item.put("name",name);
            item.put("date",simpleDateFormat.format(date));
            item.put("fileName",data);
            listItems.add(item);
            Log.e("MainActivity2",data);
        }
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readImages();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    private class MyAdapter extends BaseAdapter{

        public MyAdapter() {
            super();
        }

        @Override
        public int getCount() {
            return listItems.size();
        }

        @Override
        public Object getItem(int position) {
            return listItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            if(convertView == null){
                convertView = LayoutInflater.from(MainActivity2.this).inflate(R.layout.itemview,null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }
            else{
                holder =  (ViewHolder)convertView.getTag();
            }
            HashMap<String,Object> item = (HashMap<String, Object>) listItems.get(position);
            holder.img.setImageBitmap(BitmapFactory.decodeFile((String)item.get("fileName")));
            return convertView;
        }
    }
    private class ViewHolder{
        private ImageView img;

        public ViewHolder(View view) {
            this.img = (ImageView)view.findViewById(R.id.imageView2);
        }
    }
}
