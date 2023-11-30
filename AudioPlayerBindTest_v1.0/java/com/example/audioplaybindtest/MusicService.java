package com.example.audioplaybindtest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicService extends Service {

    private MyBinder mBinder;

    private static final String TAG = "MusicService";
    //待播放的音频列表
    private List<Map<String,Object>> musicList = new ArrayList<Map<String,Object>>();
    //记录音频列表数量
    private int songsCount = 0;
    // 记录当前正在播放的音乐
    private int current = -1;
    //MediaPlayer播放组件
    private MediaPlayer mPlayer;
    //当前的状态,0x11 代表没有播放 ；0x12代表 正在播放；0x13代表暂停
    final private int NOPLAY = 0x11;
    final private int PLAYING = 0x12;
    final private int PAUSE = 0x13;
    //记录当前播放状态
    private int status = NOPLAY;

    public class MyBinder extends Binder {
        MusicService getService(){
            return MusicService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"创建音乐播放服务");
        // 创建MediaPlayer
        mPlayer = new MediaPlayer();
        // 初始化音频数据列表、数量、当前播放的音频序号
        initSongsData();

        // 为MediaPlayer播放完成事件绑定监听器
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer mp){
                //设置下一首，current
                setNextMusic();
                Map<String,Object> map = musicList.get(current);
                // 准备、并播放音乐
                prepareAndPlay((String)map.get("songs_path"));
                status = PLAYING;
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("MusicService","onBind");
        // 完成onBind方法，返回一个Binder的实例
        。。。。。。
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"onStartCommand");
        Log.e(TAG,status+"");
        Log.e(TAG,"第"+current+"首音乐");
        return super.onStartCommand(intent, flags, startId);
    }
    //设置下一首
    private  void setNextMusic()
    {
        current++;
        if (current >= songsCount){
            current = 0;
        }
    }
    /**设置上一首*/
    private  void setPrieviousMusic()
    {
        current--;
        if (current <0){
            current = songsCount-1;
        }
    }
    /**加载并播放指定的音频文件*/
    private void prepareAndPlay(String songsPath){
        try{

            mPlayer.reset();
            //使用MediaPlayer加载指定的声音文件。
            mPlayer.setDataSource(songsPath);
            Log.e(TAG,songsPath);
            mPlayer.prepare();// 准备声音
            mPlayer.start();// 播放
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /*播放音乐,播放键按下被调用的方法*/
    public int  playMusic() throws IOException{
        Map<String,Object> map = musicList.get(current);
        // 原来处于没有播放状态
        if (status == NOPLAY){
            // 准备、并播放音乐
            prepareAndPlay((String)map.get("songs_path"));
            status = PLAYING;
        }
        // 原来处于播放状态
        else if (status == PLAYING){
            mPlayer.pause();// 暂停
            status = PAUSE;// 改变为暂停状态
        }
        // 原来处于暂停状态
        else if (status == PAUSE){
            mPlayer.start();// 播放
            status = PLAYING;// 改变状态
        }
        return status;
    }
    /*播放下一首*/
    public void playNextMusic() {
        setNextMusic();
        status = PLAYING;
        Map<String,Object> map = musicList.get(current);
        prepareAndPlay((String) map.get("songs_path"));
    }

    /*播放上一首*/
    public void playPreviousMusic() {
        setPrieviousMusic();
        status = PLAYING;
        Map<String,Object> map = musicList.get(current);
        prepareAndPlay((String) map.get("songs_path"));
    }

    /*重新初始化音频播放器*/
    public void resetMediaplayer(){
        // 如果原来正在播放或暂停
        if (status == PLAYING || status == PAUSE){
            current = 0;
            mPlayer.reset();
            status = NOPLAY;
        }
    }

    /*获取正在播放的音频长度*/
    public int getDuration() {
        return mPlayer.getDuration();
    }

    /*获取正在播放的音频的播放位置*/
    public int getPlayPosition() {

        return mPlayer.getCurrentPosition();
    }
    /*获取正在播放的音频名称*/
    public String getSongsTitle() {
        Map<String,Object> map = musicList.get(current);
        return (String)map.get("songs_title");
    }
    /*获取正在播放的音频的演奏者*/
    public String getSongsArtist() {
        Map<String,Object> map = musicList.get(current);
        return (String)map.get("songs_artist");
    }
    /*跳转到指定位置播放*/
    public void seekToPositon(int msec) {
        mPlayer.seekTo(msec);
    }
    /*获取播放状态*/
    public int getMediaPlayerStatus() {
        return status;
    }

    /*初始化播放列表、长度和current*/
    private void initSongsData() {
        try {
            //歌曲路径
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("songs_title","Mallow Flower(锦葵花)");
            map.put("songs_artist","Otokaze");
            map.put("songs_path", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC) + "/Otokaze - Mallow Flower (锦葵花).mp3");
            musicList.add(map);

            map = new HashMap<String, Object>();
            map.put("songs_title","华尔兹圆舞曲");
            map.put("songs_artist","Various Artists");
            map.put("songs_path", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC) + "/Various Artists - 华尔兹圆舞曲.mp3");
            musicList.add(map);

            map = new HashMap<String, Object>();
            map.put("songs_title","Almost A Whisper");
            map.put("songs_artist","Yanni");
            map.put("songs_path", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC) + "/Yanni - Almost A Whisper.mp3");
            musicList.add(map);

            map = new HashMap<String, Object>();
            map.put("songs_title","Refrain");
            map.put("songs_artist","阿南亮子 (Anan Ryoko)");
            map.put("songs_path", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC) + "/阿南亮子 - Refrain.mp3");
            musicList.add(map);
            songsCount = musicList.size();

            map = new HashMap<String, Object>();
            map.put("songs_title","华夏传说");
            map.put("songs_artist","凤凰传奇");
            map.put("songs_path", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC) + "/凤凰传奇 - 华夏传说.mp3");

            musicList.add(map);

            //初始化待播放的音频数量
            songsCount = musicList.size();
            //初始化正在播放的音频标记
            if(songsCount>0) {
                current = 0;
            }
            else {
                Toast.makeText(getApplicationContext(), "音频列表准备出错", Toast.LENGTH_SHORT).show();
                throw new Exception();
            }

            Log.e(TAG,songsCount+"首音乐");
        } catch (Exception e) {
            Log.d(TAG, "设置资源，准备阶段出错");
            e.printStackTrace();
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG,"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"onDestroy");
        super.onDestroy();
        // 释放媒体播放器
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}
