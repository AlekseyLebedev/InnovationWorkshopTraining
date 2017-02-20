package com.rtrsdk.arsentii.taskpain1;


import android.os.AsyncTask;

import java.util.concurrent.TimeUnit;

public class IncrementTask extends AsyncTask<Void, Integer, Void> {
    private MainActivity mainActivity;

    public IncrementTask(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void link(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    void unLink() {
        mainActivity = null;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            for (int i = 0; i <= 1000; i++) {
                TimeUnit.SECONDS.sleep(1);
                publishProgress(i);
                if (isCancelled())
                    return null;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (mainActivity == null) {
            return;
        }
        mainActivity.getCountTv().setText("i = " + values[0]);
    }
}
