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
import com.example.kiric.sprint3_moxy.presentation.view.MainView;
import com.example.kiric.sprint3_moxy.presentation.presenter.MainPresenter;

import com.arellomobile.mvp.MvpActivity;


import com.arellomobile.mvp.presenter.InjectPresenter;

import static android.R.attr.data;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    TextView mText;
    Button mButton;
    String name = "";
    private final static int REQUEST_CODE = 7;
    public static final String TAG = "MainActivity";

    @InjectPresenter
    MainPresenter mMainPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, MainActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.button);
        mText = (TextView) findViewById(R.id.textView);
        mMainPresenter.showMessege(mText);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RequestActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }


    /*public void onClick (){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RequestActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                Log.d("LOG_TAG", "Нажал на кнопку");
            }
        });
    }*/



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("LOG_TAG", "Получил ответ");
        String mFirstName = "";
        String mLastName = "";
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("LOG_TAG", "1 " + requestCode + " 2 " + resultCode);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Log.d("LOG_TAG", "2");
            mFirstName = data.getStringExtra("first name");
            Log.d("LOG_TAG", "mFirstName: " + mFirstName);
            mLastName = data.getStringExtra("last name");
        }
        mMainPresenter.saveResukt(mFirstName+ " " + mLastName);
        mMainPresenter.showMessege(mText);
        }


}
