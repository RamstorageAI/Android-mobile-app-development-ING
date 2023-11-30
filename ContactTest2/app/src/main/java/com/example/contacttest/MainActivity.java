package com.example.contacttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    List<String> contactsList = new ArrayList<>();
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = (Button)findViewById(R.id.button) ;
        ListView contactsView = (ListView) findViewById(R.id.contacts_view);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, contactsList);
        contactsView.setAdapter(adapter);
        bt.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                askContactsPermission();//调用方法，申请读权限
            }
        });

    }
    //动态权限申请方法
    private void askContactsPermission(){
        String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_CONTACTS };//权限列表
        //   动态申请权限
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_CONTACTS }, 1);
        }
        else{
            readContacts1();
        }
    }

    private void readContacts1() {
        Cursor cursor = null;
        try {
            //得到ContentResolver对象
            ContentResolver cr = getContentResolver();
            //取得电话本中开始一项的光标
            cursor = cr.query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null,null);
            Log.e("Column",ContactsContract.PhoneLookup.DISPLAY_NAME);
            for(int i=0;i<cursor.getColumnCount();i++)
                Log.e("Column",cursor.getColumnName(i));
            if (cursor != null) {
                while(cursor.moveToNext()) {
                    //取得联系人名字
                    int nameFieldColumnIndex = cursor.getColumnIndex(
                            ContactsContract.PhoneLookup.DISPLAY_NAME);

                    String contact = cursor.getString(nameFieldColumnIndex);

                    //取得联系人ID，通过ID查询联系人的电话号码
                    String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    //通过联系人ID获取查询联系电话URI，获取联系人的电话号码
                    Cursor phone =  cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId,
                            null, null);
                    //                Log.e("Column","------------");
                    //                for(int i=0;i<phone.getColumnCount();i++)
                    //                    Log.e("Column",phone.getColumnName(i));
                    //                Log.e("Column",ContactsContract.CommonDataKinds.Phone.CONTENT_URI.toString());
                    String phoneNumbers = "";
                    while(phone.moveToNext())  {//一个联系人可能有多个号码
                        String PhoneNumber= phone.getString(phone.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        phoneNumbers += PhoneNumber+";";
                    }
                    contactsList.add (contact + ": " + phoneNumbers + "\n");
                }
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    private void readContacts2() {
        Cursor cursor = null;
        try {
            // 查询联系人数据
            cursor = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    // 获取联系人姓名
                    String displayName = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    // 获取联系人手机号
                    String number = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsList.add(displayName + "\n" + number);
                }
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private void readContacts3() {
        Cursor cur = null;
        try {
            // 获取用来操作数据的类的对象，对联系人的基本操作都是使用这个对象
            ContentResolver cr = getContentResolver();
            // 查询contacts表的所有记录
            cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            // 如果记录不为空
            if (cur.getCount() > 0) {
                String str = "";
                // 游标初始指向查询结果的第一条记录的上方，执行moveToNext函数会判断
                // 下一条记录是否存在，如果存在，指向下一条记录。否则，返回false。
                while (cur.moveToNext()) {
                    str = "";
                    String rawContactsId = "";
                    String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    Log.e("READCONTACTS", name);
                    str += "Name:" + name + "\n";
                    // 读取rawContactsId
                    Cursor rawContactsIdCur = cr.query(ContactsContract.RawContacts.CONTENT_URI,
                            null, ContactsContract.RawContacts.CONTACT_ID + " = ?", new String[]{id}, null);
                    // 该查询结果一般只返回一条记录，所以我们直接让游标指向第一条记录
                    if (rawContactsIdCur.moveToFirst()) {
                        // 读取第一条记录的RawContacts._ID列的值
                        rawContactsId = rawContactsIdCur.getString(rawContactsIdCur.getColumnIndex(
                                ContactsContract.RawContacts._ID));
                    }
                    rawContactsIdCur.close();
                    // 读取号码
                    if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                        // 根据查询RAW_CONTACT_ID查询该联系人的号码
                        Cursor PhoneCur =
                                cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                        null,
                                        ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID + " = ?",
                                        new String[]{rawContactsId}, null);
                        // 上面的ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                        // 可以用下面的phoneUri代替
                        // Uri phoneUri=Uri.parse("content://com.android.contacts/data/phones");
                        // 一个联系人可能有多个号码，需要遍历
                        while (PhoneCur.moveToNext()) {
                            // 号获取码
                            String number = PhoneCur.getString(PhoneCur.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER));
                            str += number + ";";
                            Log.e("READCONTACTS", number);
                            // 获取号码类型
                            String numberType = PhoneCur.getString(PhoneCur.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.TYPE));
                        }
                        PhoneCur.close();
                    }
                    contactsList.add(str);
                }
                adapter.notifyDataSetChanged();
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cur != null) {
                cur.close();
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //readContacts1();
                    //readContacts2();
                    readContacts3();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}
