package com.example.kiric.sprint1_task2;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


public class MainActivity extends Activity {

    TextView countTv;
    Button countBtn;
    boolean alreadyPushed;
    IncreaseEverySecond inc;
    private MainActivity tmp;
    static final String pushed = "pushed";
    static final String textKey = "textKey";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countTv = (TextView) findViewById(R.id.count_tv);
        countBtn = (Button) findViewById(R.id.count_btn);
        alreadyPushed = false;

        countBtn.setOnClickListener(onClickListener);
        inc = (IncreaseEverySecond) getLastNonConfigurationInstance();
        tmp = this;


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(pushed, alreadyPushed);
        savedInstanceState.putCharSequence(textKey,countTv.getText());
        super.onSaveInstanceState(savedInstanceState);
    }
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        countTv.setText(savedInstanceState.getCharSequence(textKey));
        alreadyPushed = savedInstanceState.getBoolean(pushed);
        if (alreadyPushed) {
            inc.link(this);
        }
    }
    public Object onRetainNonConfigurationInstance() {
        if (alreadyPushed) {
            inc.unLink();
            return inc;
        }
        return null;
    }



    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!alreadyPushed) {
                alreadyPushed = true;
                inc = new IncreaseEverySecond();
                //countBtn.setVisibility(View.INVISIBLE);
                inc.execute();
                inc.link(tmp);
            }
        }
    };



    static class IncreaseEverySecond extends AsyncTask<Void, Integer, Void> {

        MainActivity activity;
        int count = 0;

        // получаем ссылку на MainActivity
        void link(MainActivity act) {
            activity = act;
        }

        // обнуляем ссылку
        void unLink() {
            activity = null;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                while (true) {
                    TimeUnit.SECONDS.sleep(1);
                    count++;
                    publishProgress(count);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            activity.countTv.setText(values[0] + "");
        }
    }
}
