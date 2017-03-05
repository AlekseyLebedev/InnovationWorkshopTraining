package com.example.kiric.sprint3_moxy.presentation.presenter;


import com.example.kiric.sprint3_moxy.presentation.view.SplashScreenView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import android.os.AsyncTask;

import java.util.concurrent.TimeUnit;

@InjectViewState
public class SplashScreenPresenter extends MvpPresenter<SplashScreenView> {
    public SplashScreenPresenter() {
        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                sleepSecond();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                getViewState().skip();
            }

            private void sleepSecond() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException ignore) {
                }
            }
        };

        asyncTask.execute();

    }
}
