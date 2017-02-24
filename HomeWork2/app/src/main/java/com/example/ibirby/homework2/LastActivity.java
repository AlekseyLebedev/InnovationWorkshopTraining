package com.example.ibirby.homework2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LastActivity extends AppCompatActivity {
    private final static String LOG_TAG = "myLogs";

    TextView mFirstName, mLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last_layout);
        mFirstName = (TextView) findViewById(R.id.firstName);
        mLastName = (TextView) findViewById(R.id.lastName);
        Button mOkButton = (Button) findViewById(R.id.okButton);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(Person.class.getCanonicalName(),
                        new Person(mFirstName.getText().toString(), mLastName.getText().toString()));
                Log.d(LOG_TAG, "put name = " +
                        mFirstName.getText().toString() + mLastName.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
