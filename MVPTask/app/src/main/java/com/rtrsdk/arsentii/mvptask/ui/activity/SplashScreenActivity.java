package com.rtrsdk.arsentii.mvptask.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.PresenterType;
import com.rtrsdk.arsentii.mvptask.R;
import com.rtrsdk.arsentii.mvptask.presentation.view.SplashScreenView;
import com.rtrsdk.arsentii.mvptask.presentation.presenter.SplashScreenPresenter;


import com.arellomobile.mvp.presenter.InjectPresenter;

public class SplashScreenActivity extends MvpAppCompatActivity implements SplashScreenView {

    public static final String TAG = "SplashScreenActivity";

    @InjectPresenter(type = PresenterType.GLOBAL, tag = TAG)
    SplashScreenPresenter mSplashScreenPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, SplashScreenActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    @Override
    protected void onResume() {
        mSplashScreenPresenter.onResumeTimer();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mSplashScreenPresenter.onPauseTimer();
        super.onPause();
    }

    @Override
    public void showSplashScreen() {
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.splash_image);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.splash_screen_layout);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        rl.addView(iv, lp);
    }

    @Override
    public void startActivity2() {
        Intent intent = Activity2.getIntent(SplashScreenActivity.this);
        startActivity(intent);
        finish();
    }
}
