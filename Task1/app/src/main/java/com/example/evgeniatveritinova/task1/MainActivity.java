package com.example.evgeniatveritinova.task1;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    private int number;
    private TextView text;
    private CountTask countTask;

    static final String STATE_NUMBER = "number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        final MainActivity thisActivity = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.button:
                        countTask = new CountTask();
                        countTask.link(thisActivity);
                        countTask.execute();
                        break;
                }
            }
        });

        text = (TextView) findViewById(R.id.text);

        if (savedInstanceState != null) {
            number = savedInstanceState.getInt(STATE_NUMBER);
        } else {
            number = 0;
        }

        text.setText(String.valueOf(number));

        countTask = (CountTask) getLastNonConfigurationInstance();
        if (countTask != null) {
            countTask.link(this);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(STATE_NUMBER, number);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        countTask.unLink();
        return countTask;
    }

    static class CountTask extends AsyncTask<Void, Integer, Void> {

        MainActivity activity;

        void link(MainActivity act) {
            activity = act;
        }

        void unLink() {
            activity = null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            activity.text.setText(String.valueOf(activity.number));
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {

                while(true) {
                    TimeUnit.SECONDS.sleep(1);
                    ++activity.number;
                    publishProgress(activity.number);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            activity.text.setText(String.valueOf(values[0]));
        }
    }
}
