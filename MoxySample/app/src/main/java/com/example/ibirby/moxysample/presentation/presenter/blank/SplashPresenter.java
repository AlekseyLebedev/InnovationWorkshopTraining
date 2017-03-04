package com.example.ibirby.moxysample.presentation.presenter.blank;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ibirby.moxysample.presentation.view.blank.SplashView;
import com.example.ibirby.moxysample.ui.activity.blank.HelloActivity;

import android.os.AsyncTask;
import android.os.SystemClock;

@InjectViewState
public class SplashPresenter extends MvpPresenter<SplashView> {

    public SplashPresenter() {
        MyTask myTask = new MyTask();
        myTask.execute(5);
    }

    private class MyTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(final Integer... params) {
            SystemClock.sleep(params[0] * 1000);
            return null;
        }

        @Override
        protected void onPostExecute(final Void aVoid) {
            SplashPresenter.this.getViewState().showNextActivity(HelloActivity.class);
        }
    }
}
