package com.example.evgeniatveritinova.task2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    public static final String FIRST_NAME = "first_name";
    public static final String SECOND_NAME = "second_name";
    public static final int REQUEST_CODE = 0;

    private String firstName;
    private String secondName;
    private TextView helloTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button requestButton = (Button) findViewById(R.id.button_request);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent requestIntent = new Intent(MainActivity.this, RequestActivity.class);
                startActivityForResult(requestIntent, REQUEST_CODE);
            }
        });

        helloTextView = (TextView) findViewById(R.id.string_hello);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                firstName = data.getStringExtra(FIRST_NAME);
                secondName = data.getStringExtra(SECOND_NAME);
                helloTextView.setText(generateString(firstName, secondName));
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(FIRST_NAME, firstName);
        savedInstanceState.putString(SECOND_NAME, secondName);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        firstName = savedInstanceState.getString(FIRST_NAME);
        secondName = savedInstanceState.getString(SECOND_NAME);

        helloTextView.setText(generateString(firstName, secondName));

        super.onRestoreInstanceState(savedInstanceState);
    }

    private String generateString(String firstName, String secondName) {
        return "Привет, " + firstName + " " + secondName + "!";
    }
}
