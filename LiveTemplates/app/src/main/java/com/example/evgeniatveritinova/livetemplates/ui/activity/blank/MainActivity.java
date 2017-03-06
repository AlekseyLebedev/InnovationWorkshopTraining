package com.example.evgeniatveritinova.livetemplates.ui.activity.blank;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.evgeniatveritinova.livetemplates.R;
import com.example.evgeniatveritinova.livetemplates.presentation.presenter.blank.MainPresenter;
import com.example.evgeniatveritinova.livetemplates.presentation.view.blank.MainView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    public static final String TAG = "MainActivity";

    @InjectPresenter
    MainPresenter mMainPresenter;

    public static final String FIRST_NAME = "first_name";
    public static final String SECOND_NAME = "second_name";
    public static final int REQUEST_CODE = 0;

    private String firstName;
    private String secondName;


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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                firstName = data.getStringExtra(FIRST_NAME);
                secondName = data.getStringExtra(SECOND_NAME);

                mMainPresenter.onNamesChanged(firstName, secondName);
            }
        }
    }

    @Override
    public void showHelloMessage(String firstName, String secondName) {
        TextView helloTextView = (TextView) findViewById(R.id.string_hello);
        helloTextView.setText(generateString(firstName, secondName));
    }

    private String generateString(String firstName, String secondName) {
        return "Привет, " + firstName + " " + secondName + "!";
    }
}
