package com.rtrsdk.arsentii.severalactivitiestask;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    private final static int SECONDS_BEFORE_ACTIVITY2 = 3;
    static final private String TIME_PASSED = "time_passed";

    //in seconds
    private int timePassed = 0;
    private CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (savedInstanceState != null) {
            timePassed = savedInstanceState.getInt(TIME_PASSED);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timePassed < SECONDS_BEFORE_ACTIVITY2) {
            timer = new CountDownTimer(1000 * (SECONDS_BEFORE_ACTIVITY2 - timePassed), 1000) {

                public void onTick(long millisUntilFinished) {
                    timePassed++;
                }

                public void onFinish() {
                    Intent intent = new Intent(SplashScreen.this, Activity2.class);
                    startActivity(intent);
                }
            }.start();
        } else {
            Intent intent = new Intent(SplashScreen.this, Activity2.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
        timer = null;
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(TIME_PASSED, timePassed);

        super.onSaveInstanceState(savedInstanceState);
    }
}
