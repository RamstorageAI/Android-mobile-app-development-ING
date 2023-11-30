package com.example.adapterdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private final Context context;//运行上下文
    private List<HashMap<String, Object>> listItems;//好友信息集合
    private LayoutInflater mInflater;//视图容器
    //用于存储CheckBox选中状态
//    public Map<Integer, Boolean> cbxFlag = null;


    public final class ViewHolder {
        public ImageView img;
        public TextView title;
        public TextView info;
        public View wholesView;
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

//    public boolean hasChecked(int position) {
//        return cbxFlag.get(position);
//    }

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
        holder.img.setBackgroundResource((Integer) data.get("news_thumb"));
        holder.title.setText(data.get("news_title").toString());
        holder.info.setText(data.get("news_info").toString());
//        holder.wholesView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                HashMap<String, Object> data = listItems.get(position);
//                new AlertDialog.Builder(context)
//                        .setIcon(Integer.parseInt(data.get("news_img").toString()))
//                        .setTitle(data.get("news_title").toString())
//                        .setMessage(data.get("news_info").toString())
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                            }
//                        }).show();
//            }
//        });

        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> data = listItems.get(position);
                new AlertDialog.Builder(context)
                        .setIcon(Integer.parseInt(data.get("news_img").toString()))
                        .setTitle(data.get("news_title").toString())
                        .setMessage(data.get("news_info").toString())
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
            }
        });

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> data = listItems.get(position);
                Toast.makeText(context,data.get("news_info").toString(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> data = listItems.get(position);
                Toast.makeText(context,data.get("news_info").toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;

    }
}