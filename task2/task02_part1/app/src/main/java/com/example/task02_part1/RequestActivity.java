package com.example.task02_part1;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Оленька on 27.02.2017.
 */

public class RequestActivity extends Activity {
    private static TextView mFirstName;
    private static TextView mLastName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        mFirstName = (TextView) findViewById(R.id.first_name_id);
        mLastName = (TextView) findViewById(R.id.last_name_id);

        Button mAnswerButton = (Button) findViewById(R.id.answer_id);

        mAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tmp_intent = new Intent();
                tmp_intent.putExtra("first name", String.valueOf(mFirstName.getText()));
                tmp_intent.putExtra("last name", String.valueOf(mLastName.getText()));
                setResult(RESULT_OK, tmp_intent);
                finish();
            }
        });
    }
}
