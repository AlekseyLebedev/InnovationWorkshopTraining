package com.example.evgeniatveritinova.livetemplates.presentation.presenter.blank;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.evgeniatveritinova.livetemplates.presentation.view.blank.SplashScreenView;
import com.example.evgeniatveritinova.livetemplates.ui.activity.blank.MainActivity;

import android.os.AsyncTask;

@InjectViewState
public class SplashScreenPresenter extends MvpPresenter<SplashScreenView> {

    public SplashScreenPresenter() {
        AsyncTask<Integer, Void, Void> sleepTask = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(final Integer... params) {
                try {
                    Thread.sleep(params[0]);
                } catch (InterruptedException ignore) {
                }

                return null;
            }

            @Override
            protected void onPostExecute(final Void aVoid) {
                getViewState().changeActivity(MainActivity.class);
            }
        };
        sleepTask.execute(3000);
    }
}
