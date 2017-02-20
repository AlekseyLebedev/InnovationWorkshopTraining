package com.rtrsdk.arsentii.taskpain1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    static final private String COUNT = "count";

    private int count = 0;

    private TextView countTv;

    private IncrementTask myTask;

    private MainActivity thisMA = this;


    public TextView getCountTv() {
        return countTv;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(COUNT);
        }

        countTv = (TextView) findViewById(R.id.count_tv);
        Button countBtn = (Button) findViewById(R.id.count_btn);

        countTv.setText(Integer.toString(count));

        myTask = (IncrementTask) getLastNonConfigurationInstance();
        if (myTask != null) {
            myTask.link(this);
        }

        countBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myTask != null) {
                    myTask.cancel(true);
                    myTask.unLink();
                }

                myTask = new IncrementTask(thisMA);
                myTask.execute();
            }
        });
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        myTask.unLink();
        return myTask;
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(COUNT, count);

        super.onSaveInstanceState(savedInstanceState);
    }
}
