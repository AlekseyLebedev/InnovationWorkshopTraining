package com.example.ibirby.homework2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.Timer;
import java.util.TimerTask;

public class TimerFragment extends Fragment {
    private Timer mTimer;
    private MyTask mTask;

    public TimerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mTask = new MyTask();
        mTimer = new Timer();
        mTimer.schedule(mTask, 10000);
    }

    private class MyTask extends TimerTask {

        @Override
        public void run() {
            ((SplashActivity) getActivity()).nextAct();
        }
    }
}
