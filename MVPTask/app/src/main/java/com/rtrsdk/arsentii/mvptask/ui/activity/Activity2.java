package com.rtrsdk.arsentii.mvptask.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.rtrsdk.arsentii.mvptask.Person;
import com.rtrsdk.arsentii.mvptask.R;
import com.rtrsdk.arsentii.mvptask.presentation.view.Activity2View;
import com.rtrsdk.arsentii.mvptask.presentation.presenter.Activity2Presenter;


import com.arellomobile.mvp.presenter.InjectPresenter;

public class Activity2 extends MvpAppCompatActivity implements Activity2View {

    public static final String TAG = "Activity2";

    @InjectPresenter
    Activity2Presenter mActivity2Presenter;


    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, Activity2.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        mActivity2Presenter.onReceiveNameAndPrintIt(getIntent().getParcelableExtra("Person"));

        Button requestBtn = (Button) findViewById(R.id.button_call_act3);
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity2Presenter.onButtonClick();
            }
        });
    }

    @Override
    public void printName(@NonNull final Person who) {
        TextView helloText = (TextView) findViewById(R.id.textViewHello);
        helloText.setText("Hello " + who.getFirstName() + " " + who.getLastName() + "!");
    }

    @Override
    public void startActivity3() {
        Intent intent =  Activity3.getIntent(Activity2.this);
        startActivity(intent);
        finish();
    }
}
