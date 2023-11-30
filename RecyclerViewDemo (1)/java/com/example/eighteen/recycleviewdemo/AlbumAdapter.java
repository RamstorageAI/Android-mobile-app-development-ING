package com.example.eighteen.recycleviewdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private List<AlbumInfo> albumInfoList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView album_thumb;
        TextView album_title;
        TextView album_info;
        ImageButton album_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.album_thumb = (ImageView)itemView.findViewById(R.id.album_thumb);
            this.album_title = (TextView)itemView.findViewById(R.id.album_title);
            this.album_info =  (TextView)itemView.findViewById(R.id.album_info);
            this.album_btn =  (ImageButton) itemView.findViewById(R.id.album_btn);;
        }
    }

    public AlbumAdapter(List<AlbumInfo> albumInfoList) {
        this.albumInfoList = albumInfoList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlbumInfo album = albumInfoList.get(position);
        holder.album_thumb.setImageResource(album.getImageId());
        holder.album_title.setText(album.getTitle());
        holder.album_info.setText(album.getInfo());
        Log.e("AlbumAdapter","onBindViewHolder"+position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_bt,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        Log.e("AlbumAdapter","onCreateViewHolder");
        holder.album_thumb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int position = holder.getAdapterPosition();
                AlbumInfo data = albumInfoList.get(position);
                Toast.makeText(v.getContext(),data.getTitle()+"image",Toast.LENGTH_SHORT).show();
            }
        });
        holder.album_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int position = holder.getAdapterPosition();
                showInfo(position,v.getContext());
            }
        });
        return holder;
    }
    private void showInfo(int position, Context context)
    {
        AlbumInfo data = albumInfoList.get(position);
        new AlertDialog.Builder(context)
                .setTitle(data.getTitle())
                .setMessage(data.getInfo())
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    @Override
    public int getItemCount() {
        return albumInfoList.size();
    }
}
