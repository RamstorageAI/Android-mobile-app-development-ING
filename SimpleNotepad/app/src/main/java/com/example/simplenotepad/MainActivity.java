package com.example.simplenotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<NotepadBean> notelist;
    NotepadDAO notepadDAO;
    NotepadAdapter adapter;
    EditText txtContent,txtPublish;
    Button queryBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //用于显示便签的列表
        listView = (ListView) findViewById(R.id.listview);
        //设置便签列表，初始化显示，设置事件监听器
        notepadDAO = new NotepadDAO(this); //创建 NotepadDAO 对象
        if (notelist!=null){
            notelist.clear();
        }
        //从数据库中查询数据(保存的所有便签)
        notelist = notepadDAO.query();//第一次调用时创建数据库
        adapter = new NotepadAdapter(this, notelist);
        listView.setAdapter(adapter);

        // 添加一条备忘录（便签，note）
        ImageView add = (ImageView) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RecordActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        //单击：打开“修改便签”页面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id){
                NotepadBean notepadBean = notelist.get(position);
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                intent.putExtra("id", notepadBean.getId());
                intent.putExtra("time", notepadBean.getNotepadTime()); //便签的时间
                intent.putExtra("content", notepadBean.getNotepadContent()); //便签的内容
                MainActivity.this.startActivityForResult(intent, 1);//修改备忘录
            }
        });

        //长按：打开对话框删除记录
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int pi) {
                        NotepadBean notepadBean = notelist.get(position);
                        notepadDAO.deleteData(notepadBean.getId());
                        notelist.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this,"删除成功", Toast.LENGTH_SHORT).show();
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder( MainActivity.this);
                builder.setTitle("是否删除此备忘录？");
                builder.setPositiveButton("确定", listener);
                builder.setNegativeButton("取消", null);
                builder.show();
                return true;
            }
        });

//        queryBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                queryStudent();
//            }
//        });


    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) { //添加或者更改了备忘录，需要更新显示
            //showQueryData();
            if (notelist != null) {
                notelist.clear();
            }
            notelist = notepadDAO.query();
            adapter = new NotepadAdapter(this, notelist);
            listView.setAdapter(adapter);
        }
    }
//    //根据查询条件刷新列表数据
//    private void queryNote(){
//        String strSQL = "select * from note order by publish desc " ;
//        NotepadDAO.execQuery(notelist,strSQL);
//        adapter.notifyDataSetChanged();//只有当适配器数据容器中的值发生变化时，它才会触发uI的刷新；
//    }
//
//    private void queryStudent(){
//        String content = txtContent.getText().toString().trim();
//        String publish = txtPublish.getText().toString().trim();
//        String strSQL = "select * from note where 1=1 " ;
//        if(!"".equals(content)){
//            strSQL+=" and content like '%"+content+"%'";
//        }
//        if(!"".equals(publish)){
//            strSQL+=" and publish like '%"+publish+"%'";
//        }
//        strSQL+=" order by publish desc";
//        NotepadDAO.execQuery(notelist,strSQL);
//        adapter.notifyDataSetChanged();//只有当适配器数据容器中的值发生变化时，它才会触发uI的刷新；
//    }

}