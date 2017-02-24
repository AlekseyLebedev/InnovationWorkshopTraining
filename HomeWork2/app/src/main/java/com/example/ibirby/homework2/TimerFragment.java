package com.example.ibirby.homework2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class TimerFragment extends Fragment {
    static private final String LOG_TAG= "myLogs";

    private Timer mTimer;
    private MyTask mTask;

    public TimerFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "timer onCreate");
        setRetainInstance(true);
        mTask = new MyTask();
        mTimer = new Timer();
        mTimer.schedule(mTask, 10000);
    }

    private class MyTask extends TimerTask {

        @Override
        public void run() {
            Log.i(LOG_TAG, "timer run");
            ((SplashActivity) getActivity()).nextAct();
        }
    }
}
