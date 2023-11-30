package com.example.viewpagerdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.PagerTabStrip;

import java.util.ArrayList;
import java.util.List;

public class PhotoPagerAdaper extends PagerAdapter {

    private List<ImageView> list;
    private String[] names;

    public PhotoPagerAdaper(Context context, int[] resIds, String[] names){
        this.names = names;
        list = new ArrayList<ImageView>();
        for(int i=0;i<resIds.length;i++){
            ImageView iv = new ImageView(context);
            iv.setBackgroundResource(resIds[i]);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
            iv.setLayoutParams(params);
            list.add(iv);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject( View view,  Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(list.get(position));
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return names[position];
    }
}
