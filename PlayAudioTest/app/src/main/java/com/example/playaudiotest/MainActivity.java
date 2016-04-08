package com.example.playaudiotest;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class MainActivity extends Activity implements View.OnClickListener {
    MediaPlayer mediaPlayer = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button play = (Button)findViewById(R.id.play);
        play.setOnClickListener(this);
        Button pause = (Button)findViewById(R.id.pause);
        pause.setOnClickListener(this);
        Button stop = (Button)findViewById(R.id.stop);
        stop.setOnClickListener(this);
        initAudioPlayer();
    }

    private void initAudioPlayer() {
        try {
            File audioFile = new File(Environment.getExternalStorageDirectory(),"music.mp3");
            mediaPlayer.setDataSource(audioFile.getPath());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:{
                if (!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
            }
                break;
            case R.id.pause:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
                break;
            case R.id.stop:{
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.reset();
                    initAudioPlayer();
                }
            }
                break;
            default:
                break;
            }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
