package ru.mipt.diht.maximdankovtsev.multiscreenapplication.splashscreen;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import android.os.AsyncTask;

import ru.mipt.diht.maximdankovtsev.multiscreenapplication.ui.activity.MainActivity;


@InjectViewState
public class SplashScreenPresenter extends MvpPresenter<SplashScreenView> {

    private final static int SPLASH_TIME_MS = 5000;

    SplashScreenPresenter() {
        AsyncTask<Integer, Void, Void> sleepTask = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(final Integer... params) {
                sleep(params[0]);
                return null;
            }

            @Override
            protected void onPostExecute(final Void aVoid) {
                // Завершение работы SplashScreen и переход на основную активность
                getViewState().substituteWithAnotherActivity(MainActivity.class);
            }

            private void sleep(int timeMs) {
                try {
                    Thread.sleep(timeMs);
                } catch (InterruptedException ignore) {
                }
            }
        };
        sleepTask.execute(SPLASH_TIME_MS);
    }
}
