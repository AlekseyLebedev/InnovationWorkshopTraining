package com.example.task02_part1;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;

import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer mTimer = new Timer();
        TmpTimerTask timerTask = new TmpTimerTask();
        mTimer.schedule(timerTask, 5000);
    }

    class TmpTimerTask extends TimerTask{
        @Override
        public void run(){
            Intent tmp_intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(tmp_intent);
            finish();
        }
    }

}
