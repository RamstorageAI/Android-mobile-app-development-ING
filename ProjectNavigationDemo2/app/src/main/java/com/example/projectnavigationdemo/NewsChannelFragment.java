package com.example.projectnavigationdemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsChannelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsChannelFragment extends Fragment {
    private static final String ARG_CATEGORY_TITLE = "category_title";
    private String newsCategoryTitle = "Default";
    private TextView mTitleField;

    public static NewsChannelFragment newInstance(String newsCategoryTitle) {//newsCategoryTitle
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY_TITLE, newsCategoryTitle);
        NewsChannelFragment fragment = new NewsChannelFragment();
        fragment.setArguments(args);//setArguments:向碎片里传递参数
        return fragment;//
    }

    public NewsChannelFragment() {
    }

    // Required empty public constructor    }
//
//
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            newsCategoryTitle = (String) getArguments().getString(ARG_CATEGORY_TITLE);
        } catch (java.lang.NullPointerException e) {
            System.out.println("TestFragment getArg error!");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_channel, container, false);
        mTitleField = (TextView) view.findViewById(R.id.newsCategoryTitle);
        mTitleField.setText(newsCategoryTitle);
        Log.e("NewsChannelFragment", "createView" + newsCategoryTitle);
        return view;
    }

}
