package com.example.adapterdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;


public class GridViewSimpleAdapter extends AppCompatActivity {

    private GridView gview;
    private List<Map<String, Object>> data_list;
    private int[] icon = { R.drawable.yq, R.drawable.j2,
            R.drawable.j3, R.drawable.j4, R.drawable.j5,
            R.drawable.j6, R.drawable.j7, R.drawable.j8,
            R.drawable.j9, R.drawable.j10 };

    private String[] iconName = {"杨倩绝杀女子10米气步枪",
            "巴黎2024新项目：马拉松竞走混合接力",
            "花滑奥运冠军韩聪宣布退出米兰科尔蒂纳冬奥周期所有赛事",
            "孙一文女子重剑","郑钦文斩获郑州网球公开赛女单冠军",
            "单板滑雪奥运冠军苏翊鸣",
            "2023年花样滑冰世锦赛",
            "棒球-垒球：需要了解的重要信息",
            "杨紫琼独家专访",
            "郑钦文斩获郑州网球公开赛女单冠军",
            "五届自由式小轮车世界冠军汉娜·罗伯茨"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_simple_adapter);

        gview = (GridView) findViewById(R.id.gview);
        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        data_list = getData();
        //新建适配器
        String [] from ={"image","text"};
        int [] to = {R.id.image,R.id.text};
        SimpleAdapter sim_adapter = new SimpleAdapter(this,data_list,R.layout.gridview_item, from, to);
        gview.setAdapter(sim_adapter);
    }

    public List<Map<String, Object>> getData(){
        //在map中赋值
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            //将map添加至数据源
            data_list.add(map);
        }
        return data_list;
    }
}