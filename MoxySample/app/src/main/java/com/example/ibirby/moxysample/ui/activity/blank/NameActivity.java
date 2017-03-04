package com.example.ibirby.moxysample.ui.activity.blank;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.ibirby.moxysample.R;
import com.example.ibirby.moxysample.presentation.presenter.blank.NamePresenter;
import com.example.ibirby.moxysample.presentation.view.blank.NameView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NameActivity extends MvpAppCompatActivity implements NameView {

    public static final String TAG = "NameActivity";
    @InjectPresenter
    NamePresenter mNamePresenter;
    EditText mFirstName, mLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        mFirstName = (EditText) findViewById(R.id.firstName);
        mLastName = (EditText) findViewById(R.id.lastName);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent();
                intent.putExtra("f", mFirstName.getText().toString());
                intent.putExtra("l", mLastName.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
