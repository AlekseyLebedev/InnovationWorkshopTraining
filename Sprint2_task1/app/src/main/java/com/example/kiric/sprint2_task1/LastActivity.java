package com.example.kiric.sprint2_task1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by kiric on 26.02.2017.
 */

public class LastActivity extends Activity {
    TextView mFirstName, mLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        mFirstName = (TextView) findViewById(R.id.firstName);
        mLastName = (TextView) findViewById(R.id.lastName);
        Button mOkButton = (Button) findViewById(R.id.ok);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("first name", mFirstName.getText().toString());
                intent.putExtra("last name", mLastName.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}