package com.example.ibirby.moxysample.ui.activity.blank;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.ibirby.moxysample.R;
import com.example.ibirby.moxysample.presentation.presenter.blank.HelloPresenter;
import com.example.ibirby.moxysample.presentation.view.blank.HelloView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HelloActivity extends MvpAppCompatActivity implements HelloView {

    public static final String TAG = "HelloActivity";

    @InjectPresenter
    HelloPresenter mHelloPresenter;

    TextView mTextView;

    @Override
    public void setNameText(final Integer s, final String firstName, final String lastName) {
        mTextView.setText(getString(s, firstName, lastName));
    }

    @Override
    public void showNextActivity(final Class activity) {
        Intent intent = new Intent(this, activity);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        mTextView = (TextView) findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mHelloPresenter.click();
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode,
            final Intent data) {
        if (resultCode == RESULT_OK) {
            mHelloPresenter.updateName(data.getStringExtra("f"), data.getStringExtra("l"));
        }
    }
}
