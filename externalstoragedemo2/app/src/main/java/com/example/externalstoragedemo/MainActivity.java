package com.example.externalstoragedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button1,button2;
    TextView currentPath,dataArea;
    final String SUB_DIR = "jcut";  //子目录
    final String FILE_NAME = "data1.dat"; //文件名

//    private static final int REQUEST_EXTERNAL_STORAGE = 1;
//    private static String[] PERMISSIONS_STORAGE = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//    };

    //用于写入数据的按钮1的监听器
    View.OnClickListener button1Listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 低版本系统的外存空间，可以使用如下代码
            // File root=getExternalStorageDirecotry();
            File root=getExternalFilesDir("");
            if(root.exists()&& root.canWrite()){
                try {
                    File subDir = new File(root, "jcut");
                    if(!subDir.exists()) {
                        subDir.mkdir();
                    }
                    File file = new File(subDir, "/"+FILE_NAME);
                    file.createNewFile();
                    OutputStream os = new FileOutputStream(file);
                    DataOutputStream dos = new DataOutputStream(os);
                    Random random = new Random();
                    for (int i = 0; i < 100; i++) {
                        dos.writeInt(random.nextInt(1000));
                    }
                    currentPath.setText("创建文件并下入数据成功，当前路径:\n" + file.getAbsolutePath());
                    dos.flush();
                    dos.close();
                    dataArea.setText("");
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"创建并写入失败，原因如下："+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    //用于读入数据的按钮2的监听器
    View.OnClickListener button2Listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            File root=getExternalFilesDir("");
            if(root.exists() && root.canWrite()){
                try {
                    File file = new File(root, SUB_DIR+"/"+FILE_NAME);
                    InputStream is = new FileInputStream(file);
                    DataInputStream dis = new DataInputStream(is);
                    boolean flag = true;
                    while (flag) {
                        try {
                            int a = dis.readInt();
                            dataArea.setText(dataArea.getText().toString() + a + "    ");
                        } catch (EOFException e) {
                            flag = false;
                        }
                    }
                    currentPath.setText("数据读取成功，当前路径:\n" + file.getAbsolutePath());
                    dis.close();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"数据读取失败，原因如下："+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        currentPath = (TextView)findViewById(R.id.currentPath);
        dataArea = (TextView)findViewById(R.id.dataArea);

//        动态申请sd读写权限
//        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            // We don't have permission so prompt the user
//            ActivityCompat.requestPermissions(
//                    this,
//                    PERMISSIONS_STORAGE,
//                    REQUEST_EXTERNAL_STORAGE);
//        }
        button1.setOnClickListener(button1Listener);
        button2.setOnClickListener(button2Listener);

    }



}