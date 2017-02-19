package com.example.kiric.sprint1_task1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView countTv;
    private int count;
    static final String countKey = "countKey";
    static final String textKey = "textKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countTv = (TextView) findViewById(R.id.count_tv);
        Button countBtn = (Button) findViewById(R.id.count_btn);

        countBtn.setOnClickListener(onClickListener);

        count = 0;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(countKey, count);
        savedInstanceState.putCharSequence(textKey,countTv.getText());
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt(countKey);
        countTv.setText(savedInstanceState.getCharSequence(textKey));
    }


    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            count++;
            countTv.setText(count + "");
        }
    };
}