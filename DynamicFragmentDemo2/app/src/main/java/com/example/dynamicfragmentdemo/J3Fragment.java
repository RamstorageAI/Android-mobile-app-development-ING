package com.example.dynamicfragmentdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class J3Fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_j3, container, false);
        ImageView j3IV = (ImageView)view.findViewById(R.id.j3IV);
        j3IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"热烈祝贺孙一文获得东京奥运会女子女子重剑冠军！",Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }
}