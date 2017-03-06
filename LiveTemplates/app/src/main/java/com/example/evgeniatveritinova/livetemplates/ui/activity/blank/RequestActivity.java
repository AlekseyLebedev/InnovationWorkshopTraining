package com.example.evgeniatveritinova.livetemplates.ui.activity.blank;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.evgeniatveritinova.livetemplates.R;
import com.example.evgeniatveritinova.livetemplates.presentation.presenter.blank.RequestPresenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RequestActivity extends MvpAppCompatActivity implements MvpView {

    public static final String TAG = "RequestActivity";

    @InjectPresenter
    RequestPresenter mRequestPresenter;

    private EditText firstName;
    private EditText secondName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        firstName = (EditText) findViewById(R.id.edit_text1);
        secondName = (EditText) findViewById(R.id.edit_text2);

        Button okButton = (Button) findViewById(R.id.button_ok);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent confirm = new Intent();

                confirm.putExtra(MainActivity.FIRST_NAME, firstName.getText().toString());
                confirm.putExtra(MainActivity.SECOND_NAME, secondName.getText().toString());

                RequestActivity.this.setResult(RESULT_OK, confirm);
                RequestActivity.this.finish();
            }
        });
    }
}
