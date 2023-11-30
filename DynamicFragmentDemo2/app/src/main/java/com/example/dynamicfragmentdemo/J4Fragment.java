package com.example.dynamicfragmentdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class J4Fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_j4, container, false);
        ImageView j4IV = (ImageView)view.findViewById(R.id.j4IV);
        j4IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"热烈祝贺王涵/施廷懋获得东京奥运会女子双人3米板跳水金牌",Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }
}