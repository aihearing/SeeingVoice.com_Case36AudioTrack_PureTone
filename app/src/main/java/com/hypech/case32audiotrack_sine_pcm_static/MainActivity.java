package com.hypech.case32audiotrack_sine_pcm_static;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    private PlayThread mPlayThread;
    private PlayThreadPCM mPlayThreadPCM;

    Button btnPlay, btnLeft, btnRight, btnStop,btnPCM;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (Button) findViewById(R.id.btn_play);
        btnLeft = (Button) findViewById(R.id.btn_left);
        btnRight = (Button) findViewById(R.id.btn_right);
        btnStop = (Button) findViewById(R.id.btn_stop);
        btnPCM = (Button) findViewById(R.id.btnPCM);

        btnPlay.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnPCM.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            if (null != mPlayThread) {
                mPlayThread.stopPlay();
                mPlayThread = null;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        int vid= view.getId();
        if (vid == R.id.btn_play) {
            playSound(true, true);
        }else if (vid == R.id.btnPCM){
            playPCM(true,true);
        }else if (vid == R.id.btn_left){
                playSound(true,false);
        }else if (vid == R.id.btn_right){
                playSound(false,true);
        }else if (vid == R.id.btn_stop){
                if (null != mPlayThread) {
                    mPlayThread.stopPlay();
                    mPlayThread = null;
                }
        }
    }
    private void playPCM(boolean left, boolean right) {
        if (null != mPlayThreadPCM) {
            mPlayThreadPCM.stopPlay();
            mPlayThreadPCM = null;
        }
        mPlayThreadPCM = new PlayThreadPCM(this,"ding.wav", R.raw.tts1);
        mPlayThreadPCM.setChannel(left,right);
        mPlayThreadPCM.start();
    }

    private void playSound(boolean left, boolean right) {
        if (null != mPlayThread) {
            mPlayThread.stopPlay();
            mPlayThread = null;
        }
        mPlayThread = new PlayThread(400);
        mPlayThread.setChannel(left,right);
        mPlayThread.start();
    }
}