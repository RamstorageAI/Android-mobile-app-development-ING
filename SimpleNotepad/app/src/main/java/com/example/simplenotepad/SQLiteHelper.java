package com.example.simplenotepad;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Notepad";//数据库名
    public static final int DATABASE_VERION = 1; //数据库版本
    public static final String DATABASE_NOTEPADTB = "Note"; //记事本数据表名
    public static final String NOTEPAD_ID = "id"; //id 字段名
    public static final String NOTEPAD_CONTENT = "content"; //内容字段名
    public static final String NOTEPAD_NOTETIME = "notetime"; //时间字段名
    //建表语句
    public static final String CREATE_NOTE = "create table Note ("
            + "id integer primary key autoincrement, "
            + "content text, "
            + "notetime text)";
    //或以下建表语句
// public static final String CREATE_NOTE = "create table Note ("
// + NOTEPAD_ID+" integer primary key autoincrement, "
// + NOTEPAD_CONTENT+" text, "
// + NOTEPAD_NOTETIME+" text)";
    public SQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTE);
        String sql = "insert into Note values(null,'1 今天是忙碌的一天','2020 年 11 月 02 日 10:00:01')";
        db.execSQL(sql);
        sql = "insert into Note values(null,'2 今天天气很好','2020 年 11 月 01 日 12:00:01')";
        db.execSQL(sql);
        sql = "insert into Note values(null,'3 今天要带小乌龟去学校','2022 年 11 月 01 日 12:00:01')";
        db.execSQL(sql);
        Log.e("SQLiteHelper","onCreate func");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String CREATE_USER = "create table User("+
                "id integer primary key autoincrement,"+
                "username text,"+
                "password text)";
        db.execSQL(CREATE_USER);
        Log.e("SQLiteHelper","onUpgrade func");
    }



}
