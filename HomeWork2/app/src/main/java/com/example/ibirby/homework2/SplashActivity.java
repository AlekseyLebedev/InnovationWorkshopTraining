package com.example.ibirby.homework2;


import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private final static String LOG_TAG= "myLogs";

    private TimerFragment mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mTimer = (TimerFragment) fragmentManager.findFragmentByTag(getString(R.string.timer));

        if (mTimer == null) {
            mTimer = new TimerFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(mTimer, getString(R.string.timer));
            transaction.commit();
        }
    }

    void nextAct() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
