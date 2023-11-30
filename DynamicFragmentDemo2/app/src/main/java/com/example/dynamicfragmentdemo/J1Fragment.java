package com.example.dynamicfragmentdemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class J1Fragment extends Fragment {


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_j1, container, false);
        ImageView j1IV = (ImageView)view.findViewById(R.id.j1IV);
        j1IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"热烈祝贺小将杨倩获得东京奥运会女子10米气步枪冠军！",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}