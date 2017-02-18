package com.example.ibirby.counter;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.os.SystemClock;


public class MainActivity extends AppCompatActivity {


    private static final String COUNT_TAG = "count";
    private static final String WORKING_TAG = "working";
    private TextView tv;
    private Button btn;
    private int count = 0;
    private int working = -1;
    private Counter counter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("debug", "find UI");
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);
        btn = (Button) findViewById(R.id.button);

        if (savedInstanceState != null) {
            Log.d("debug", "get state");
            working = savedInstanceState.getInt(WORKING_TAG);
            Log.d("debug", "working = " + working);
            count = savedInstanceState.getInt(COUNT_TAG);
            Log.d("debug", "count = " + count);
            tv.setText(Integer.toString(count));
        }

        counter = (Counter) getLastCustomNonConfigurationInstance();
        if (counter != null) {
            Log.d("debug", "link");
            counter.link(this);
        }
        Log.d("debug", "set click listener");
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (working > 0) {
                    Log.d("debug", "Start cancelling task");
                    counter.cancel(false);
                    counter = null;
                } else {
                    Log.d("debug", "New task");
                    counter = new Counter();
                    Log.d("debug", "link");
                    counter.link(MainActivity.this);
                    Log.d("debug", "execute");
                    counter.execute(count);
                }
                working *= -1;
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt(COUNT_TAG, count);
        savedInstanceState.putInt(WORKING_TAG, working);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance () {
        if (working > 0) {
            Log.d("debug", "unlink");
            counter.link(null);
        }
        return counter;
    }

    static class Counter extends AsyncTask<Integer, Integer, Void> {
        private MainActivity myActivity = null;

        public void link(MainActivity act){
            myActivity = act;
        }

        @Override
        protected Void doInBackground(Integer... params) {
            Log.d("debug", "start with " + params[0]);
            for (int i = params[0];; ++i) {
                if (isCancelled()) {
                    Log.d("debug", "FULL STOP");
                    break;
                }
                Log.d("debug", "new i = " + i);
                publishProgress(i);
                SystemClock.sleep(1000);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d("debug", "try to upd with " + values[0]);
            if (myActivity != null){
                Log.d("debug", "upd with " + values[0]);
                myActivity.tv.setText(Integer.toString(values[0]));
                myActivity.count = values[0];
            }
        }

        @Override
        protected void onCancelled() {
            myActivity = null;
            super.onCancelled();
        }
    }

}
