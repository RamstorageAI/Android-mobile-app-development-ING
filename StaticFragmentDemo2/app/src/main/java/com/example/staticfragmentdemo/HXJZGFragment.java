package com.example.staticfragmentdemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class HXJZGFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_hxjzg, container, false);
        //获取view对象，并注册单击事件
        ImageView iv = (ImageView)view.findViewById(R.id.hxjzgIV);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"社会主义核心价值观总览！",Toast.LENGTH_LONG).show();;

            }
        });

        return view;
    }
}