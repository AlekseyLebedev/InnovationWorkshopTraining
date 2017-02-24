package com.example.ibirby.homework2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    static private final String LOG_TAG= "myLogs";

    private TimerFragment mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        mTimer = new TimerFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(mTimer, getString(R.string.timer));

        transaction.commit();
    }

    void nextAct() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
