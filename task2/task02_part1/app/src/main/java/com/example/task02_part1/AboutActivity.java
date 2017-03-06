package com.example.task02_part1;

/**
 * Created by Оленька on 27.02.2017.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity  {
    private static String mFirstName = "";
    private static String mLastName = "";
    private static TextView mRequest;
    public static final int REQUEST_CODE = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button mButton = (Button) findViewById(R.id.button_request_id);

        mRequest = (TextView) findViewById(R.id.hello_id);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tmp_intent = new Intent(AboutActivity.this, RequestActivity.class);
                startActivityForResult(tmp_intent, REQUEST_CODE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure the request was successful
        if (requestCode == REQUEST_CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                mFirstName = data.getStringExtra("first name");
                mLastName = data.getStringExtra("last name");
            }
        }
        mRequest.setText("Hello, "+ mFirstName + " " + mLastName + ".");
    }

}