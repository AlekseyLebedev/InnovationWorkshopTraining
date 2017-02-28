package com.example.mikhailivanov.increment;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private IncThread incThread;

    private TextView numberView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberView = (TextView) findViewById(R.id.number);
        Button increment = (Button) findViewById(R.id.increment);
        increment.setOnClickListener(onClickListener);

        incThread = (IncThread) getLastCustomNonConfigurationInstance();
        if (incThread == null) {
            incThread = new IncThread();
            incThread.execute();
        }
        // передаем в MyTask ссылку на текущее MainActivity
        incThread.link(this);

    }

    public Object onRetainCustomNonConfigurationInstance() {
        incThread.unLink();
        return incThread;
    }


    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            incThread = new IncThread();
            incThread.execute();
        }
    };

    class IncThread extends AsyncTask<Void, Integer, Void> {

        private MainActivity activity;

        public void link(MainActivity act) {
            activity = act;
        }

        public void unLink() {
            activity = null;
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                int counter = 0;

                for (int i = 0; i < 15; i++) {
                    doingHardWork();
                    publishProgress(++counter);
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            activity.numberView.setText(Integer.toString(values[0]));
        }

        private void doingHardWork() throws InterruptedException {
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
