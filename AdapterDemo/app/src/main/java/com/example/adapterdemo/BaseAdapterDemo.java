package com.example.adapterdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BaseAdapterDemo extends AppCompatActivity {

    private String[] names = new String[]{"2023年游泳世界杯布达佩斯站：覃海洋、张雨霏冲击年度总冠军",
            "国际奥委会孟买全会通过洛杉矶2028奥组委增设5个项目的提议",
            "从西蒙·拜尔斯到诺阿·莱尔斯，奥运明星们谈论关注心理健康的重要性",
            "杭州亚运会竞技体操单项决赛：邹敬园完美表现摘得男子双杠金牌，张博恒、林超攀包揽男子单杠金银",
            "杭州亚运会体操单项决赛：兰星宇吊环摘金，张博恒男子自由操夺银",
            "解放思想：NBA选手艾派·尤度与丹麦运动心理学家克里斯托夫·亨里克森解决心理健康问题",
            "米查·汉考克：排球名将谈美国体育的发展和巴黎奥运会冲击第二金",
            "肯尼亚拳击传奇伊丽莎白·安迪耶戈：和死亡擦肩后继续争取参加个人第二届奥运会",
            "五届自由式小轮车世界冠军汉娜·罗伯茨：大满贯的最后一块拼图——奥运金牌",
            "2023年游泳世界杯雅典站：收官日，张雨霏斩获女子100米蝶泳冠军并刷新赛会纪录，覃海洋再度包揽男子蛙泳三金2023年游泳世界杯雅典站：收官日，张雨霏斩获女子100米蝶泳冠军并刷新赛会纪录，覃海洋再度包揽男子蛙泳三金"
    };

    private List<HashMap<String,Object>> getData()
    {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String, Object>>();
        HashMap<String,Object> map = new HashMap<String, Object>();
        map = new HashMap<String, Object>();
        map.put("news_title","杨倩绝杀女子10米气步枪");
        map.put("news_info","2023年游泳世界杯布达佩斯站：覃海洋、张雨霏冲击年度总冠军");
        map.put("news_thumb",R.drawable.yq);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("news_title","巴黎2024新项目：马拉松竞走混合接力");
        map.put("news_info","国际奥委会孟买全会通过洛杉矶2028奥组委增设5个项目的提议");
        map.put("news_thumb",R.drawable.j2);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("news_title","花滑奥运冠军韩聪宣布退出米兰科尔蒂纳冬奥周期所有赛事");
        map.put("news_info","从西蒙·拜尔斯到诺阿·莱尔斯，奥运明星们谈论关注心理健康的重要性");
        map.put("news_thumb",R.drawable.j3);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("news_title","孙一文女子重剑");
        map.put("news_info","杭州亚运会竞技体操单项决赛：邹敬园完美表现摘得男子双杠金牌，张博恒、林超攀包揽男子单杠金银");
        map.put("news_thumb",R.drawable.j4);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("news_title","郑钦文斩获郑州网球公开赛女单冠军");
        map.put("news_info","杭州亚运会体操单项决赛：兰星宇吊环摘金，张博恒男子自由操夺银");
        map.put("news_thumb",R.drawable.j5);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("news_title","单板滑雪奥运冠军苏翊鸣：羽生结弦配得上“传奇”二字");
        map.put("news_info","解放思想：NBA选手艾派·尤度与丹麦运动心理学家克里斯托夫·亨里克森解决心理健康问题");
        map.put("news_thumb",R.drawable.j6);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("news_title","2023年花样滑冰世锦赛：宇野昌磨卫冕男子单人滑世界冠军");
        map.put("news_info","米查·汉考克：排球名将谈美国体育的发展和巴黎奥运会冲击第二金");
        map.put("news_thumb",R.drawable.j7);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("news_title","棒球-垒球：需要了解的重要信息");
        map.put("news_info","肯尼亚拳击传奇伊丽莎白·安迪耶戈：和死亡擦肩后继续争取参加个人第二届奥运会");
        map.put("news_thumb",R.drawable.j8);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("news_title","杨紫琼独家专访：体育是传递爱、尊重和尊严的语言");
        map.put("news_info","五届自由式小轮车世界冠军汉娜·罗伯茨：大满贯的最后一块拼图——奥运金牌");
        map.put("news_thumb",R.drawable.j9);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("news_title","郑钦文斩获郑州网球公开赛女单冠军");
        map.put("news_info","2023年游泳世界杯雅典站：收官日，张雨霏斩获女子100米蝶泳冠军并刷新赛会纪录，覃海洋再度包揽男子蛙泳三金2023年游泳世界杯雅典站：收官日，张雨霏斩获女子100米蝶泳冠军并刷新赛会纪录，覃海洋再度包揽男子蛙泳三金");
        map.put("news_thumb",R.drawable.j10);
        list.add(map);


        //……
        return list;
    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter_demo);

        ListView listview = (ListView)findViewById(R.id.babyAlbumListView);
        MyAdapter adapter = new MyAdapter(this, getData());
        //视图
        //准备Adapter
        //桥接
        listview.setAdapter(adapter);
    }
}


