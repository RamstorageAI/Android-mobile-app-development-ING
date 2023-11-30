package com.example.recyclerbase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {
    private final Context context;//运行上下文
    private List<HashMap<String, Object>> listItems;//好友信息集合
    private LayoutInflater mInflater;//视图容器
    //用于存储CheckBox选中状态
//    public Map<Integer, Boolean> cbxFlag = null;

    private String title;
    private String info;
    private int imageId;


    public final class ViewHolder {
        public ImageView img;
        public TextView title;
        public TextView info;
        public View wholesView;
        public ImageButton button;
    }


    public MyAdapter(Context context, List<HashMap<String, Object>> listItems) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.listItems = listItems;
//        cbxFlag = new HashMap<Integer, Boolean>();
//        init();
    }

//    public void init() {
//        for (int i = 0; i < listItems.size(); i++) {
//            cbxFlag.put(i, false);
//        }
//    }

    public void AlbumInfo(String title, String info, int imageId) {
        this.title = title;
        this.info = info;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public int getImageId() {
        return imageId;
    }

    @Override
    public int getCount() {// TODO Auto-generated method stub
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int selectId = position;
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listview_item2, null);
            holder = new ViewHolder();
            //获取控件对象
            holder.img = (ImageView) convertView.findViewById(R.id.news_thumb);
            holder.title = (TextView) convertView.findViewById(R.id.news_title);
            holder.info = (TextView) convertView.findViewById(R.id.news_info);
            holder.wholesView = convertView;
            //设置控件集到convertView
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HashMap<String, Object> data = listItems.get(position);
        if (position%2 ==0) {
            convertView.setBackgroundColor(Color.parseColor("#CAFFFF"));
        } else {
            convertView.setBackgroundColor(Color.parseColor("#B3FAFAFA"));
        }

        holder.img.setBackgroundResource((Integer) data.get("news_thumb"));
        holder.title.setText(data.get("news_title").toString());
        holder.info.setText(data.get("news_info").toString());

//        holder.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new AlertDialog.Builder(context)
//                        .setIcon((Integer) listItems.get(selectId).get("news_img"))
//                        .setTitle((String) listItems.get(selectId).get("news_title"))
//                        .setMessage((String) listItems.get(selectId).get("news_info"))
//                        .setIcon((Integer) listItems.get(selectId).get("button"))
//                        .setPositiveButton("确定", null)
//                        .create()
//                        .show();
//            }
//        });


        return convertView;

    }
}