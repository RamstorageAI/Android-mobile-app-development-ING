package com.example.simplenotepad;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotepadDAO {
    private static final String DATABASE_NOTEPADTB = "Note";
    public static final String NOTEPAD_ID = "id"; //id 字段名
    public static final String NOTEPAD_CONTENT = "content"; //内容字段名
    public static final String NOTEPAD_NOTETIME = "notetime"; //时间字段名
    private SQLiteHelper dbOpenHelper;
    public NotepadDAO(Context context){
        //创建 DBSQLiteHelper 对象
        dbOpenHelper = new SQLiteHelper(context);
    }
    //添加数据
    public boolean insertData(String userContent,String userTime){
        //获取数据库对象
        SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOTEPAD_CONTENT,userContent);
        contentValues.put(NOTEPAD_NOTETIME,userTime);
        return sqLiteDatabase.insert(DATABASE_NOTEPADTB,null,contentValues)>0;
    }
    //删除数据
    public boolean deleteData(String id){
        SQLiteDatabase sqLiteDatabase=dbOpenHelper.getReadableDatabase();
        String whereClause = NOTEPAD_ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        return sqLiteDatabase.delete(DATABASE_NOTEPADTB,whereClause,whereArgs)>0;
    }
    //修改数据
    public boolean updateData(String id,String content,String time){
        SQLiteDatabase sqLiteDatabase=dbOpenHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOTEPAD_CONTENT,content);
        contentValues.put(NOTEPAD_NOTETIME,time);
        String whereClause = NOTEPAD_ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        return sqLiteDatabase.update(DATABASE_NOTEPADTB,contentValues,whereClause,whereArgs)>0;
    }
    //查询数据
    public List<NotepadBean> query(){
        SQLiteDatabase sqLiteDatabase=dbOpenHelper.getReadableDatabase();
        List<NotepadBean> list = new ArrayList<NotepadBean>();
        Cursor cursor = sqLiteDatabase.query(DATABASE_NOTEPADTB,null,null,null,
                null,null,NOTEPAD_ID+" desc");//按 id 降序排序
        if (cursor.moveToFirst()){
            do{
                NotepadBean noteInfo=new NotepadBean();
                String id = String.valueOf(cursor.getInt(cursor.getColumnIndex(NOTEPAD_ID)));
                String content = cursor.getString(cursor.getColumnIndex(NOTEPAD_CONTENT));
                String time = cursor.getString(cursor.getColumnIndex(NOTEPAD_NOTETIME));
                noteInfo.setId(id);
                noteInfo.setNotepadContent(content);
                noteInfo.setNotepadTime(time);
                list.add(noteInfo);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return list;
    }
    //获取当前日期，格式化为 yyyy 年 MM 月 dd 日 HH:mm:ss 形式的字符串
    public static final String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy 年 MM 月 dd 日 HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    /**
     * 查询，并将结果存入list中。
     * @param list
     * @param strSQL
     */
    public void execQuery(List<Map<String,Object>> list,final String strSQL) {
        SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery(strSQL, null);
            cursor.moveToFirst();
            list.clear();
            while (!cursor.isAfterLast()) {
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("id", cursor.getInt(0));
                map.put("content", cursor.getString(1));
                map.put("time", cursor.getString(2));
                list.add(map);
                cursor.moveToNext();
            }
        }catch (RuntimeException e) {
            e.printStackTrace();
        }
    }


    /**
     * 执行sql语句，
     * @param strSQL
     * @return
     */
    public boolean execSQL(final String strSQL) {
        SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();
        sqLiteDatabase.beginTransaction();  //
        try {
            sqLiteDatabase.execSQL(strSQL);
            sqLiteDatabase.setTransactionSuccessful();  //
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }finally {
            sqLiteDatabase.endTransaction();    //
        }

    }

}
