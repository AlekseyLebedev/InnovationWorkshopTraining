package com.example.kiric.sprint3_moxy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.kiric.sprint3_moxy.R;
import com.example.kiric.sprint3_moxy.presentation.view.SplashScreenView;
import com.example.kiric.sprint3_moxy.presentation.presenter.SplashScreenPresenter;

import com.arellomobile.mvp.MvpActivity;


import com.arellomobile.mvp.presenter.InjectPresenter;

public class SplashScreenActivity extends MvpAppCompatActivity implements SplashScreenView {

    public static final String TAG = "SplashScreenActivity";

    @InjectPresenter
    SplashScreenPresenter mSplashScreenPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    @Override
    public void skip() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
