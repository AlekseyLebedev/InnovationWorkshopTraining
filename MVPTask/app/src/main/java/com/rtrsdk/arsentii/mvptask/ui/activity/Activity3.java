package com.rtrsdk.arsentii.mvptask.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.PresenterType;
import com.rtrsdk.arsentii.mvptask.Person;
import com.rtrsdk.arsentii.mvptask.R;
import com.rtrsdk.arsentii.mvptask.presentation.view.Activity3View;
import com.rtrsdk.arsentii.mvptask.presentation.presenter.Activity3Presenter;


import com.arellomobile.mvp.presenter.InjectPresenter;

public class Activity3 extends MvpAppCompatActivity implements Activity3View {

    public static final String TAG = "Activity3";

    @InjectPresenter
    Activity3Presenter mActivity3Presenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, Activity3.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3);

        Button okBtn = (Button) findViewById(R.id.button_ok);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstName = (EditText) findViewById(R.id.editTextFirstName);
                EditText lastName = (EditText) findViewById(R.id.editTextLastName);

                Person who = new Person(firstName.getText().toString(), lastName.getText().toString());

                mActivity3Presenter.onReceiveNameAndButtonClick(who);
            }
        });
    }


    @Override
    public void startActivity2(Person who) {
        Intent intent =  Activity2.getIntent(Activity3.this);
        intent.putExtra("Person", who);
        startActivity(intent);
        finish();
    }
}
