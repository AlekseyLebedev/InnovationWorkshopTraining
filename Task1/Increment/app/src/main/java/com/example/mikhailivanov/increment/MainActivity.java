package com.example.mikhailivanov.increment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String LAST_NUM = "lastNumber";

    private TextView numberView;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num = 0;

        numberView = (TextView) findViewById(R.id.number);
        Button increment = (Button) findViewById(R.id.increment);

        increment.setOnClickListener(onClickListener);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(LAST_NUM, num);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        num = savedInstanceState.getInt(LAST_NUM);
        numberView.setText(Integer.toString(num));
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ++num;
            numberView.setText(Integer.toString(num));
        }
    };
}
