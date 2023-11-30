package com.example.myviewpagerapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuanzhuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuanzhuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //
    View view;

    RecyclerView recyclerView;
    List<AlbumInfo1> nData = new ArrayList<>();



    public static class AlbumInfo1 {//创造类

        private final String title;
        private final String info;
        private final int imageId;

        public AlbumInfo1(String title, String info, int imageId) {
            this.title = title;
            this.info = info;
            this.imageId = imageId;
        }

        public String getTitle() {
            return title;
        }

        public String getInfo() {
            return info;
        }

        public int getImageId() {
            return imageId;
        }
    }

    private List<AlbumInfo1> getData() {
        AlbumInfo1 albumInfo1 = new AlbumInfo1("杨倩绝杀女子10米气步枪", "中国00后运动员杨倩最后一枪绝杀，为中国代表团射落东京奥运会的首枚金牌。", R.drawable.yq);
        nData.add(albumInfo1);

        AlbumInfo1 albumInfo2 = new AlbumInfo1("侯志慧女子举重49公斤级夺冠", "在2020年奥运会女子举重49公斤级比赛中，侯志慧以总成绩210kg获得冠军。", R.drawable.hzh);
        nData.add(albumInfo2);

        AlbumInfo1 albumInfo3 = new AlbumInfo1("孙一文女子重剑夺得中国第三金", "女子重剑个人赛，孙一文以11比10击败罗马选手，夺得冠军", R.drawable.syw);
        nData.add(albumInfo3);

        AlbumInfo1 albumInfo4 = new AlbumInfo1("跳水女子三米板施廷懋王涵夺冠", "中国跳水队施廷懋/王涵发挥稳定，她们一路领先以326.40分夺得冠军。", R.drawable.stm);
        nData.add(albumInfo4);

        AlbumInfo1 albumInfo5 = new AlbumInfo1("举重男子61公斤级李发彬夺冠", "。。。。。。。。。。。看不清", R.drawable.lfb);
        nData.add(albumInfo5);

        AlbumInfo1 albumInfo6 = new AlbumInfo1("男子举重67kg公斤级", "。。。。。。。。。。。看不清", R.drawable.j6);
        nData.add(albumInfo6);

        AlbumInfo1 albumInfo7 = new AlbumInfo1("十米气手枪混双", "。。。。。。。。。。。看不清", R.drawable.j7);
        nData.add(albumInfo7);

        return nData;
    }

    public GuanzhuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GuanzhuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance(String param1, String param2) {
        GuanzhuFragment fragment = new GuanzhuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_guanzhu, container, false);
        //将碎片视图化（实例化）才能用findViewById
        nData = getData();
        //获取数据
        AlbumAdapter adapter = new AlbumAdapter(view.getContext(), nData, mParam2);
        //根据需要来调整适配器
        recyclerView = view.findViewById(R.id.Ricyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        recyclerView.setAdapter(adapter);

        return view;
    }









        //自定义内部类
        class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
            private List<AlbumInfo1> albumInfoList;//数据，相对于List，包含许多子项

            //LayoutInflater是用来找layout下xml布局文件
            private LayoutInflater mInflater;
            private String Title;//用于记录传递过来的内导航标题，将他赋值给DetailActivity的内容


            public AlbumAdapter(Context context, List<AlbumInfo1> albumInfoList,String Title) {//构造函数，传递数据源
                this.albumInfoList = albumInfoList;
                this.mInflater = LayoutInflater.from(context);//如果没有传递context
                this.Title=Title;
            }


            //缓存子项布局中的子控件
            //内部类，保存子项item布局实例,子项第一次出现时调用
            class ViewHolder extends RecyclerView.ViewHolder {
                ImageView album_thumb;
                TextView album_title;
                TextView album_info;
                ImageButton album_btn;

                public ViewHolder(@NonNull View itemView) {//子类ViewHolder的构造函数
                    super(itemView);//调用父类AlbumAdapter的构造函数，当在子类对象中，子类想访问父类的东西，可以使用“super.”的方式访问。例如：方法覆盖后，子类内部虽然重写了父类的方法，但子类也想使用一下父类的被覆盖的方法，此时可以使用“super.”的方式。当子类中出现和父类一样的属性或者方法，此时，你要想去调用父类的那个属性或者方法，此时“super.”不能省略

                    this.album_thumb = (ImageView) itemView.findViewById(R.id.album_thumb);
                    this.album_title = (TextView) itemView.findViewById(R.id.album_title);
                    this.album_info = (TextView) itemView.findViewById(R.id.album_info);
                    this.album_btn = (ImageButton) itemView.findViewById(R.id.album_btn);

                }
            }


            //// 绑定数据到 ItemView 显示
            //对子项数据赋值，在每个子项滚动到屏幕内时执行，positon表明要显示哪个子项
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                AlbumInfo1 album = albumInfoList.get(position);//获得子项
                holder.album_thumb.setImageResource(album.getImageId());
                holder.album_title.setText(album.getTitle());
                holder.album_info.setText(album.getInfo());
                Log.e("AlbumAdapter", "onBindViewHolder" + position);
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//加载子项布局并实例化，然后用其创建一个ViewHolder的实例并返回该实例
                //View view = from(parent.getContext()).inflate(R.layout.cardview, parent, false);//没有传递context的情况
                View view = mInflater.inflate(R.layout.cardview, parent, false);//前面有定义private LayoutInflater mInflater;
                // 且构造函数中有传输数据给mInflater:this.mInflater=LayoutInflater.from(context);
                final ViewHolder holder = new ViewHolder(view);
                Log.e("AlbumAdapter", "onCreateViewHolder");//输出Logcat

                holder.album_thumb.setOnClickListener(new View.OnClickListener() {//点击图片Toats
                    @Override
                    public void onClick(View v) {

                        int position = holder.getAdapterPosition();//获得被点击子项的position
                        AlbumInfo1 data = albumInfoList.get(position);
                        Toast.makeText(v.getContext(), data.getTitle() + "image", Toast.LENGTH_SHORT).show();
                    }
                });

                holder.album_title.setOnClickListener(new View.OnClickListener() {//点击标题
                    @Override
                    public void onClick(View v) {

                        int position = holder.getAdapterPosition();
                        AlbumInfo1 data = albumInfoList.get(position);
                        Toast.makeText(v.getContext(), data.getTitle() + "title", Toast.LENGTH_SHORT).show();
                    }
                });

                holder.album_info.setOnClickListener(new View.OnClickListener() {//点击详情
                    @Override
                    public void onClick(View v) {

                        int position = holder.getAdapterPosition();
                        AlbumInfo1 data = albumInfoList.get(position);
                        Toast.makeText(v.getContext(), data.getTitle() + "info", Toast.LENGTH_SHORT).show();
                    }
                });

                holder.album_btn.setOnClickListener(new View.OnClickListener() {//点击按钮
                    @Override
                    public void onClick(View v) {
                        int position = holder.getAdapterPosition();
                        AlbumInfo1 data = albumInfoList.get(position);

                        DetailAcitivity.actionStart(getActivity(), data.getInfo());
                        //启动活动的另一种方法,在碎片中没有startActivity方法

                    }
                });
                return holder;
            }

            //重写函数
            @Override
            public int getItemCount() {
                return albumInfoList.size();
            }



        }
    }



