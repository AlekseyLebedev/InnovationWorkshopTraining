package com.example.ibirby.moxysample.ui.activity.blank;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.ibirby.moxysample.R;
import com.example.ibirby.moxysample.presentation.presenter.blank.SplashPresenter;
import com.example.ibirby.moxysample.presentation.view.blank.SplashView;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends MvpAppCompatActivity implements SplashView {

    public static final String TAG = "SplashActivity";
    @InjectPresenter
    SplashPresenter mSplashPresenter;

    @Override
    public void showNextActivity(final Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
}
