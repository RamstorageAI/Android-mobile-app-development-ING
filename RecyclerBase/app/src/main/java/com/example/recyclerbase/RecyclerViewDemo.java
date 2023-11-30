package com.example.recyclerbase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDemo extends AppCompatActivity {

    private List<AlbumInfo> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);

        getData();
        //Log.e("MainActivity","getData()");
        RecyclerView recycleView = (RecyclerView)findViewById(R.id.babyAlbumlist);
        // Log.e("MainActivity","get RecycleView");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);
        //Log.e("MainActivity","layoutManager");
        AlbumAdapter adapter = new AlbumAdapter(mData);
        // Log.e("MainActivity","adapter");
        recycleView.setAdapter(adapter);

        // Log.e("MainActivity","setAdapter");

    }

    private void getData()
    {
        AlbumInfo albumInfo = new AlbumInfo("杨倩绝杀女子10米气步枪","2023年游泳世界杯布达佩斯站：覃海洋、张雨霏冲击年度总冠军。",R.drawable.yq);
        mData.add(albumInfo);
        AlbumInfo albumInfo2 = new AlbumInfo("巴黎2024新项目：马拉松竞走混合接力","国际奥委会孟买全会通过洛杉矶2028奥组委增设5个项目的提议。",R.drawable.j2);
        mData.add(albumInfo2);
        AlbumInfo albumInfo3 = new AlbumInfo("花滑奥运冠军韩聪宣布退出米兰科尔蒂纳冬奥周期所有赛事","从西蒙·拜尔斯到诺阿·莱尔斯，奥运明星们谈论关注心理健康的重要性。",R.drawable.j3);
        mData.add(albumInfo3);
        AlbumInfo albumInfo4 = new AlbumInfo("孙一文女子重剑","杭州亚运会竞技体操单项决赛：邹敬园完美表现摘得男子双杠金牌，张博恒、林超攀包揽男子单杠金银。",R.drawable.j4);
        mData.add(albumInfo4);
        AlbumInfo albumInfo5 = new AlbumInfo("郑钦文斩获郑州网球公开赛女单冠军","杭州亚运会体操单项决赛：兰星宇吊环摘金，张博恒男子自由操夺银。",R.drawable.j5);
        mData.add(albumInfo5);
        AlbumInfo albumInfo6 = new AlbumInfo("单板滑雪奥运冠军苏翊鸣：羽生结弦配得上“传奇”二字","解放思想：NBA选手艾派·尤度与丹麦运动心理学家克里斯托夫·亨里克森解决心理健康问题。",R.drawable.j6);
        mData.add(albumInfo6);
        AlbumInfo albumInfo7 = new AlbumInfo("2023年花样滑冰世锦赛：宇野昌磨卫冕男子单人滑世界冠军","米查·汉考克：排球名将谈美国体育的发展和巴黎奥运会冲击第二金。",R.drawable.j7);
        mData.add(albumInfo7);
        AlbumInfo albumInfo8 = new AlbumInfo("棒球-垒球：需要了解的重要信息","肯尼亚拳击传奇伊丽莎白·安迪耶戈：和死亡擦肩后继续争取参加个人第二届奥运会。",R.drawable.j8);
        mData.add(albumInfo8);
        AlbumInfo albumInfo9 = new AlbumInfo("杨紫琼独家专访：体育是传递爱、尊重和尊严的语言","五届自由式小轮车世界冠军汉娜·罗伯茨：大满贯的最后一块拼图——奥运金牌。",R.drawable.j9);
        mData.add(albumInfo9);
        AlbumInfo albumInfo10 = new AlbumInfo("郑钦文斩获郑州网球公开赛女单冠军","2023年游泳世界杯雅典站：收官日，张雨霏斩获女子100米蝶泳冠军并刷新赛会纪录，覃海洋再度包揽男子蛙泳三金2023年游泳世界杯雅典站：收官日，张雨霏斩获女子100米蝶泳冠军并刷新赛会纪录，覃海洋再度包揽男子蛙泳三金。",R.drawable.j10);
        mData.add(albumInfo10);
        AlbumInfo albumInfo11 = new AlbumInfo("杨倩绝杀女子10米气步枪","2023年游泳世界杯布达佩斯站：覃海洋、张雨霏冲击年度总冠军。",R.drawable.yq);
        mData.add(albumInfo11);
        AlbumInfo albumInfo12 = new AlbumInfo("巴黎2024新项目：马拉松竞走混合接力","国际奥委会孟买全会通过洛杉矶2028奥组委增设5个项目的提议。",R.drawable.j2);
        mData.add(albumInfo12);
        AlbumInfo albumInfo13 = new AlbumInfo("花滑奥运冠军韩聪宣布退出米兰科尔蒂纳冬奥周期所有赛事","从西蒙·拜尔斯到诺阿·莱尔斯，奥运明星们谈论关注心理健康的重要性。",R.drawable.j3);
        mData.add(albumInfo13);
        AlbumInfo albumInfo14 = new AlbumInfo("孙一文女子重剑","杭州亚运会竞技体操单项决赛：邹敬园完美表现摘得男子双杠金牌，张博恒、林超攀包揽男子单杠金银。",R.drawable.j4);
        mData.add(albumInfo14);
        AlbumInfo albumInfo15 = new AlbumInfo("郑钦文斩获郑州网球公开赛女单冠军","杭州亚运会体操单项决赛：兰星宇吊环摘金，张博恒男子自由操夺银。",R.drawable.j5);
        mData.add(albumInfo15);
        AlbumInfo albumInfo16 = new AlbumInfo("单板滑雪奥运冠军苏翊鸣：羽生结弦配得上“传奇”二字","解放思想：NBA选手艾派·尤度与丹麦运动心理学家克里斯托夫·亨里克森解决心理健康问题。",R.drawable.j6);
        mData.add(albumInfo16);
        AlbumInfo albumInfo17 = new AlbumInfo("2023年花样滑冰世锦赛：宇野昌磨卫冕男子单人滑世界冠军","米查·汉考克：排球名将谈美国体育的发展和巴黎奥运会冲击第二金。",R.drawable.j7);
        mData.add(albumInfo17);
        AlbumInfo albumInfo18 = new AlbumInfo("棒球-垒球：需要了解的重要信息","肯尼亚拳击传奇伊丽莎白·安迪耶戈：和死亡擦肩后继续争取参加个人第二届奥运会。",R.drawable.j8);
        mData.add(albumInfo18);
    }
}