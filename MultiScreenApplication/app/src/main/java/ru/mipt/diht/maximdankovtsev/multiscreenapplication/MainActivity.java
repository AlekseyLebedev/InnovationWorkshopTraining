package ru.mipt.diht.maximdankovtsev.multiscreenapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    public static final String STATE_FIRST_NAME = "first_name";
    public static final String STATE_SECOND_NAME = "second_name";
    private static final int REQUEST_NAME_CODE = 1;
    private TextView helloLabel;
    private String firstName;
    private String secondName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button requestNameButton = (Button) findViewById(R.id.button_request_name);
        requestNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent requestNameIntent = new Intent(MainActivity.this, EnterNameActivity.class);
                startActivityForResult(requestNameIntent, REQUEST_NAME_CODE);
            }
        });
        helloLabel = (TextView) findViewById(R.id.hello_label);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_FIRST_NAME, firstName);
        outState.putString(STATE_SECOND_NAME, secondName);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        firstName = savedInstanceState.getString(STATE_FIRST_NAME);
        secondName = savedInstanceState.getString(STATE_SECOND_NAME);
        helloLabel.setText(constructGreeting());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_NAME_CODE && resultCode == RESULT_OK) {
            firstName = data.getStringExtra(STATE_FIRST_NAME);
            secondName = data.getStringExtra(STATE_SECOND_NAME);
            helloLabel.setText(constructGreeting());
        }
    }

    private String constructGreeting() {
        // Если пользователь ничего не ввел в поля имя, фамилия, то игнорируем(подставим пустые строки)
        if (firstName == null) {
            firstName = "";
        }
        if (secondName == null) {
            secondName = "";
        }
        if (firstName.isEmpty() && secondName.isEmpty()) {
            return getResources().getString(R.string.hello);
        } else {
            return getResources().getString(R.string.hello) + ", " + firstName + " " + secondName + "!";
        }
    }
}
