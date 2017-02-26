package com.example.kiric.sprint2_task1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by kiric on 26.02.2017.
 */

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Timer timer = new Timer();
        MyTimerTask myTimerTask = new MyTimerTask();
        timer.schedule(myTimerTask,3000);

    }

    class MyTimerTask extends TimerTask{
        @Override
        public void run(){
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
