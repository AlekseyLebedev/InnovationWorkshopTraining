package com.example.kiric.sprint3_moxy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.kiric.sprint3_moxy.R;
import com.example.kiric.sprint3_moxy.presentation.view.RequestView;
import com.example.kiric.sprint3_moxy.presentation.presenter.RequestPresenter;

import com.arellomobile.mvp.MvpActivity;


import com.arellomobile.mvp.presenter.InjectPresenter;

public class RequestActivity extends MvpAppCompatActivity implements RequestView {

    public static final String TAG = "RequestActivity";

    @InjectPresenter
    RequestPresenter mRequestPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, RequestActivity.class);

        return intent;
    }

    TextView mFirstName, mLastName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Log.d("LOG_TAG", "вошел в реквест");
        mFirstName = (TextView) findViewById(R.id.first);
        mLastName = (TextView) findViewById(R.id.second);
        Button mOkButton = (Button) findViewById(R.id.ok);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LOG_TAG", "4");
                Intent intent = new Intent();
                intent.putExtra("first name", mFirstName.getText().toString());
                Log.d("LOG_TAG", mFirstName.getText().toString());
                intent.putExtra("last name", mLastName.getText().toString());
                Log.d("LOG_TAG", "3");
                setResult(RESULT_OK, intent);
                finish();
                Log.d("LOG_TAG", "нажал на ок");
            }
        });
    }


}
