package com.example.ibirby.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private final static String LOG_TAG = "myLogs";
    private final static int REQUEST_CODE = 42;

    private String mFirstName = "";
    private String mLastName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        Button mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, LastActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView mText = (TextView) findViewById(R.id.textView);
        Log.d("myLogs", "show welcome");
        mText.setText(getString(R.string.hello, mFirstName, mLastName));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("myLogs", "requestCode = " + requestCode + ", resultCode = " + resultCode);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            Person mPerson = data.getParcelableExtra(Person.class.getCanonicalName());
            mFirstName = mPerson.mFirstName;
            mLastName = mPerson.mLastName;

            Log.d("myLogs", "name = " + mFirstName + mLastName);
        }
    }
}
