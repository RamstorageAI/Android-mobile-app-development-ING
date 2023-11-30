package com.example.audioplaybindtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener{
    //当前的状态,0x11 代表没有播放 ；0x12代表 正在播放；0x13代表暂停
    final private int NOPLAY = 0x11;
    final private int PLAYING = 0x12;
    final private int PAUSE = 0x13;
    //获取界面中显示歌曲标题、作者文本框
    TextView title, author;
    // 播放/暂停、停止按钮
    ImageButton play, stop, next, previous;
    //显示音乐播放时长的标签
    private TextView tvDuration;

    //将毫秒化为m:ss格式
    private SimpleDateFormat time = new SimpleDateFormat("mm:ss");

    //“启动”服务的intent
    private Intent MusicServiceIntent;
    private ServiceConnection mServiceConnection;
    private MusicService musicService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // 获取程序界面中的4个按钮以及两个文本显示框
        play = (ImageButton) this.findViewById(R.id.play);
        stop = (ImageButton) this.findViewById(R.id.stop);
        next = (ImageButton) this.findViewById(R.id.next);
        previous = (ImageButton) this.findViewById(R.id.previous);
        title = (TextView) findViewById(R.id.title);
        author = (TextView) findViewById(R.id.author);
        tvDuration = findViewById(R.id.tv_duration);
        // 为两个按钮的单击事件添加监听器
        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        next.setOnClickListener(this);
        previous.setOnClickListener(this);
        //ServiceConnection实例，绑定服务需要
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder musicBinder) {
                Log.e("MainActivity", "Service与Activity已连接");
                //连接成功时，获得Service返回的Binder对象,保存对Service实例引用
                musicService = ______________________;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        //判断权限够不够，不够就申请
        if (ContextCompat.checkSelfPermission(PlayerActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 补充代码，申请权限
            。。。。。。
        } else {
            Log.e("MainActivity","bindService");
            //补充代码，绑定服务，准备播放
            。。。。。。
        }
    }

    //播放键的事件
    public void onClick(View source) {
        switch (source.getId()) {
            // 按下播放/暂停按钮
            case R.id.play:
                try {
                    int status = musicService.playMusic();
                    if(status == PLAYING) //播放状态，按钮变为暂停
                    {
                        play.setImageResource(R.drawable.pause);
                        title.setText(musicService.getSongsTitle());
                        author.setText(musicService.getSongsArtist());

                        tvDuration.setText(time.format(musicService.getDuration()) + "s");//总时长
                    }
                    else if(status == PAUSE)//暂停状态，按钮变为播放
                    {
                        play.setImageResource(R.drawable.play);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                break;
            // 按下停止按钮
            case R.id.stop:
                musicService.resetMediaplayer();
                play.setImageResource(R.drawable.play);//没有播放，设置为播放状态
                break;
            //下一首
            case R.id.next:
                musicService.playNextMusic();
                play.setImageResource(R.drawable.pause);//正在播放，设置为暂停状态
                title.setText(musicService.getSongsTitle());
                author.setText(musicService.getSongsArtist());
                tvDuration.setText(time.format(musicService.getDuration()) + "s");//总时长
                break;
            //上一首
            case R.id.previous:
                musicService.playPreviousMusic();
                play.setImageResource(R.drawable.pause);//正在播放，设置为暂停状态
                title.setText(musicService.getSongsTitle());
                author.setText(musicService.getSongsArtist());
                tvDuration.setText(time.format(musicService.getDuration()) + "s");//总时长
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("MainActivity","onDestroy");
        //补充代码，解除绑定，否则报 has leaked ServiceConnection 错误
        。。。。。。 
    }

    //获取到权限回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //补充代码，绑定服务，准备播放
                    。。。。。。
                } else {
                    Toast.makeText(this, "权限不够获取不到音乐，程序将退出", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }
//    //返回键的点击事件，活动不会被销毁
//    public boolean onKeyDown(int keyCode, KeyEvent event)
//    {
//        if(keyCode == KeyEvent.KEYCODE_BACK)
//        {
//            moveTaskToBack(false);
//            return true;
//        }
//        return super.onKeyDown(keyCode,event);
//    }

}
