package com.example.dynamicfragmentdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class J5Fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_j5, container, false);
        ImageView j5IV = (ImageView)view.findViewById(R.id.j5IV);
        j5IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"热烈祝贺李发彬获得东京奥运会男子举重61公斤级冠军！",Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }
}