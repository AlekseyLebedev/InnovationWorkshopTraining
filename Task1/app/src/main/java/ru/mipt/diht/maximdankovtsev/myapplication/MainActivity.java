package ru.mipt.diht.maximdankovtsev.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    // Bundle keys
    private static final String STATE_VALUE = "value";
    private final int DEFAULT_TIME_DELTA = 1000;
    private TextView textViewNum;
    private int value = 0;
    private AutoIncrementer autoIncrementer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonInc = (Button) findViewById(R.id.button_inc);
        textViewNum = (TextView) findViewById(R.id.text_num);
        final MainActivity thisActivity = this;

        buttonInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoIncrementer == null || autoIncrementer.isCancelled()) {
                    autoIncrementer = new AutoIncrementer(value, DEFAULT_TIME_DELTA);
                    autoIncrementer.link(thisActivity);
                    autoIncrementer.execute();
                } else {
                    autoIncrementer.cancel(false);
                    autoIncrementer = null;
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_VALUE, value);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        value = savedInstanceState.getInt(STATE_VALUE);
        textViewNum.setText(String.valueOf(value));
        // Restore auto increment if it was turned on.
        autoIncrementer = (AutoIncrementer) getLastNonConfigurationInstance();
        if (autoIncrementer != null && !autoIncrementer.isCancelled()) {
            autoIncrementer.link(this);
            value = autoIncrementer.getLocalValue();
        }
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        if (autoIncrementer != null && !autoIncrementer.isCancelled()) {
            autoIncrementer.unlink();
            return autoIncrementer;
        } else {
            return null;
        }
    }

    static private class AutoIncrementer extends AsyncTask<Void, Integer, Void> {
        private int localValue;
        private int timeDelta = 1000;
        private MainActivity mainActivity;

        public AutoIncrementer(int value, int timeDelta) {
            super();
            this.localValue = value;
            this.timeDelta = timeDelta;
        }

        public int getLocalValue() {
            return localValue;
        }

        public void link(MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        public void unlink() {
            this.mainActivity = null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mainActivity.textViewNum.setText(String.valueOf(values[0]));
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                while(!isCancelled()) {
                    Thread.sleep(timeDelta);
                    // While swapping links due to change of configuration,
                    // we have to save progress in local variable.
                    if (!isCancelled()) {
                        localValue += 1;
                        if (mainActivity != null) {
                            mainActivity.value += 1;
                            publishProgress(mainActivity.value);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    };
}
