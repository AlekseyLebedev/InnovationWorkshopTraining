package com.rtrsdk.arsentii.mvptask.presentation.presenter;


import com.rtrsdk.arsentii.mvptask.presentation.view.SplashScreenView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.rtrsdk.arsentii.mvptask.ui.activity.Activity2;

import android.content.Intent;
import android.os.CountDownTimer;

@InjectViewState
public class SplashScreenPresenter extends MvpPresenter<SplashScreenView> {

    private static final int DURATION = 3;//in seconds

    private int timePassed = 0;
    private CountDownTimer timer;

    public SplashScreenPresenter() {
        getViewState().showSplashScreen();
    }

    public void onResumeTimer() {
        if (timePassed < DURATION) {
            timer = new CountDownTimer(1000 * (DURATION - timePassed), 1000) {
                public void onTick(long millisUntilFinished) {
                    timePassed++;
                }

                public void onFinish() {
                    getViewState().startActivity2();
                }
            }.start();
        } else {
            getViewState().startActivity2();
        }
    }

    public void onPauseTimer() {
        timer.cancel();
        timer = null;
    }
}
